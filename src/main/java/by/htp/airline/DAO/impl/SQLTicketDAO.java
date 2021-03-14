package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.TicketDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.Ticket;

public class SQLTicketDAO implements TicketDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public Ticket viewTicketInfoByFlightId(int flightId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Ticket ticketInfo = new Ticket();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TICKET_INFO_BY_FLIGHT_ID);

			ps.setInt(1, flightId);
			rs = ps.executeQuery();

			if (rs.next()) {
				Date departingDate;
				Date arrivingDate;

				Calendar departingDateCal = Calendar.getInstance();
				Calendar departingTimeCal = Calendar.getInstance();
				Calendar flightTimeCal = Calendar.getInstance();

				departingDateCal.setTime(rs.getDate(2));
				departingTimeCal.setTime(rs.getTime(3));
				flightTimeCal.setTime(rs.getTime(7));

				departingDateCal.set(Calendar.HOUR_OF_DAY, departingTimeCal.get(Calendar.HOUR_OF_DAY));
				departingDateCal.set(Calendar.MINUTE, departingTimeCal.get(Calendar.MINUTE));
				departingDateCal.set(Calendar.SECOND, departingTimeCal.get(Calendar.SECOND));

				departingDate = departingDateCal.getTime();

				departingDateCal.add(Calendar.HOUR_OF_DAY, flightTimeCal.get(Calendar.HOUR_OF_DAY));
				departingDateCal.add(Calendar.MINUTE, flightTimeCal.get(Calendar.MINUTE));
				departingDateCal.add(Calendar.SECOND, flightTimeCal.get(Calendar.SECOND));

				arrivingDate = departingDateCal.getTime();

				ticketInfo.setFlightId(rs.getInt(1));
				ticketInfo.setDeparting(departingDate);
				ticketInfo.setArriving(arrivingDate);
				ticketInfo.setFlightNumber(rs.getString(4));
				ticketInfo.setFromPlace(rs.getString(5));
				ticketInfo.setToPlace(rs.getString(6));
				ticketInfo.setFlightTime(rs.getString(7));
				ticketInfo.setStartingPrice(rs.getDouble(8));
				ticketInfo.setPlaneType(rs.getString(9));

			} else {
				return null;
			}

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();

				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return ticketInfo;
	}

	@Override
	public Ticket viewTicketInfoById(int ticketId) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Ticket ticketInfo = new Ticket();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TICKET_INFO_BY_ID);

			ps.setInt(1, ticketId);
			rs = ps.executeQuery();

			if (rs.next()) {
				ticketInfo.setTicketId(rs.getInt(1));
				ticketInfo.setFinalPrice(rs.getDouble(2));
				ticketInfo.setFlightId(rs.getInt(3));
			} else {
				return null;
			}

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();

				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return ticketInfo;
	}

	@Override
	public void blockSelectedTicket(int flightId, int accountId, double newPrice, String[] selectedPlaceNumber)
			throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.BLOCK_SELECTED_TICKET);

			for (int i = 0; i < selectedPlaceNumber.length; i++) {

				ps.setInt(1, flightId);
				ps.setInt(2, accountId);
				ps.setDouble(3, newPrice);
				ps.setString(4, selectedPlaceNumber[i]);

				ps.executeUpdate();
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			System.err.println(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);
		}

	}

	@Override
	public List<Ticket> viewTicketsByFlightId(int flightId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Ticket> tickets = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TICKETS_BY_FLIGHT_ID);
			ps.setInt(1, flightId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Ticket info = new Ticket();

				info.setTicketId(rs.getInt(1));
				info.setPlaceNumber(rs.getString(2));

				tickets.add(info);
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return tickets;
	}

	@Override
	public List<Ticket> viewTicketsByFlightId(int flightId, int accountId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Ticket> tickets = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TICKETS_BY_FLIGHT_ID_AND_ACCOUNT_ID);
			ps.setInt(1, flightId);
			ps.setInt(2, accountId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Ticket info = new Ticket();

				info.setTicketId(rs.getInt(1));
				info.setPlaceNumber(rs.getString(2));

				tickets.add(info);
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return tickets;
	}

	@Override
	public List<Ticket> verificationTicket(int accountId, String[] priorityRegistration, String[] priorityBoarding,
			String[] luggage) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Ticket> tickets = new ArrayList<>();

		try {
			con = connectionPool.take();

			if (priorityRegistration != null) {
				ps = con.prepareStatement(FinalStringInsert.UPDATE_PRIORITY_REGISTRATION);

				for (int i = 0; i < priorityRegistration.length; i++) {
					ps.setBoolean(1, true);
					ps.setDouble(2, 15);
					ps.setInt(3, Integer.parseInt(priorityRegistration[i]));
					ps.executeUpdate();
				}
			}

			if (priorityBoarding != null) {
				ps = con.prepareStatement(FinalStringInsert.UPDATE_PRIORITY_BOARDING);

				for (int i = 0; i < priorityBoarding.length; i++) {
					ps.setBoolean(1, true);
					ps.setDouble(2, 10);
					ps.setInt(3, Integer.parseInt(priorityBoarding[i]));
					ps.executeUpdate();
				}
			}

			if (luggage != null) {
				ps = con.prepareStatement(FinalStringInsert.UPDATE_LUGGAGE);

				for (int i = 0; i < luggage.length; i++) {
					ps.setBoolean(1, true);
					ps.setDouble(2, 20);
					ps.setInt(3, Integer.parseInt(luggage[i]));
					ps.executeUpdate();
				}
			}

			ps = con.prepareStatement(FinalStringInsert.FIND_TICKETS_BY_ACCOUNT_ID);
			ps.setInt(1, accountId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Ticket info = new Ticket();
				Date departingDate;

				Calendar departingDateCal = Calendar.getInstance();
				Calendar departingTimeCal = Calendar.getInstance();

				departingDateCal.setTime(rs.getDate(7));
				departingTimeCal.setTime(rs.getTime(8));

				departingDateCal.set(Calendar.HOUR_OF_DAY, departingTimeCal.get(Calendar.HOUR_OF_DAY));
				departingDateCal.set(Calendar.MINUTE, departingTimeCal.get(Calendar.MINUTE));
				departingDateCal.set(Calendar.SECOND, departingTimeCal.get(Calendar.SECOND));

				departingDate = departingDateCal.getTime();

				info.setTicketId(rs.getInt(1));
				info.setPlaceNumber(rs.getString(2));
				info.setLuggage(rs.getBoolean(3));
				info.setPriorityBoarding(rs.getBoolean(4));
				info.setPriorityRegistration(rs.getBoolean(5));
				info.setFinalPrice(rs.getDouble(6));
				info.setDeparting(departingDate);
				info.setFlightNumber(rs.getString(9));
				info.setFromPlace(rs.getString(10));
				info.setToPlace(rs.getString(11));

				tickets.add(info);
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return tickets;
	}

	@Override
	public List<Ticket> confirmPayment(String[] ticketsId) throws DAOException {

		List<Ticket> tickets = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_TICKET_NUMBER);

			for (int i = 0; i < ticketsId.length; i++) {

				ps.setInt(1, rnd());
				ps.setInt(2, Integer.parseInt(ticketsId[i]));

				ps.executeUpdate();
			}

			for (int i = 0; i < ticketsId.length; i++) {
				ps = con.prepareStatement(FinalStringInsert.FIND_TICKETS_NUMBER_BY_TICKET_ID);
				ps.setInt(1, Integer.parseInt(ticketsId[i]));
				rs = ps.executeQuery();

				while (rs.next()) {
					Ticket info = new Ticket();

					info.setTicketNumber(rs.getInt(1));
					info.setFinalPrice(rs.getDouble(2));

					tickets.add(info);
				}
			}
		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			System.err.println(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);
		}

		return tickets;
	}

	private final int rnd() {

		int min = 1000000000;
		int max = 2147483647;

		max -= min;
		return (int) (Math.random() * ++max) + min;
	}

	@Override
	public List<Ticket> viewTicketsByAccountId(int accountId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Ticket> tickets = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_PURCHASED_TICKETS_BY_ACCOUNT_ID);
			ps.setInt(1, accountId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Ticket info = new Ticket();
				Date departingDate;

				Calendar departingDateCal = Calendar.getInstance();
				Calendar departingTimeCal = Calendar.getInstance();

				departingDateCal.setTime(rs.getDate(7));
				departingTimeCal.setTime(rs.getTime(8));

				departingDateCal.set(Calendar.HOUR_OF_DAY, departingTimeCal.get(Calendar.HOUR_OF_DAY));
				departingDateCal.set(Calendar.MINUTE, departingTimeCal.get(Calendar.MINUTE));
				departingDateCal.set(Calendar.SECOND, departingTimeCal.get(Calendar.SECOND));

				departingDate = departingDateCal.getTime();

				info.setTicketId(rs.getInt(1));
				info.setPlaceNumber(rs.getString(2));
				info.setLuggage(rs.getBoolean(3));
				info.setPriorityBoarding(rs.getBoolean(4));
				info.setPriorityRegistration(rs.getBoolean(5));
				info.setFinalPrice(rs.getDouble(6));
				info.setDeparting(departingDate);
				info.setFlightNumber(rs.getString(9));
				info.setFromPlace(rs.getString(10));
				info.setToPlace(rs.getString(11));
				info.setTicketNumber(rs.getInt(12));

				tickets.add(info);
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return tickets;
	}

	@Override
	public void unBlockTicket(int accountId) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UNBLOCK_SELECTED_TICKET);
			ps.setInt(1, accountId);
			
			ps.executeUpdate();

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			System.err.println(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);
		}

		
	}

	@Override
	public void delete(int ticketId) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_TICKET);

			ps.setInt(1, ticketId);
			
			ps.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			System.err.println(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);
		}
		
	}

}
