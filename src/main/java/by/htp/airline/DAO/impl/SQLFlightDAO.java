package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.FlightDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.Flight;

public class SQLFlightDAO implements FlightDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(Date date, int flightInfoId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Time sqlTime = new java.sql.Time(date.getTime());

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_FLIGHT);

			ps.setDate(1, sqlDate);
			ps.setTime(2, sqlTime);
			ps.setInt(3, 0);
			ps.setInt(4, flightInfoId);

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
	public List<Flight> viewAllFlight() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_FLIGHT_WITH_FLIGHT_INFO);

			while (rs.next()) {
				Flight flight = new Flight();
				Date date;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date = dateCal.getTime();

				newPrice = priceByDate(rs.getDouble(9), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightInfoId(rs.getInt(5));
				flight.setFlightNumber(rs.getString(6));
				flight.setFromPlace(rs.getString(7));
				flight.setToPlace(rs.getString(8));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(10));

				flights.add(flight);
			}

		} catch (InterruptedException e) {
			throw new DAOException(e);

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceToPlace(String fromPlace, String toPlace) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FROM_PLACE_TO_PLACE);
			ps.setString(1, fromPlace);
			ps.setString(2, toPlace);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlace(String fromPlace) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FROM_PLACE);
			ps.setString(1, fromPlace);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchToPlace(String toPlace) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TO_PLACE);
			ps.setString(1, toPlace);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchByDate(Date date) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_BY_DATE);
			ps.setDate(1, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date1;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date1 = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date1);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceByDate(String fromPlace, Date date) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FROM_PLACE_BY_DATE);
			ps.setString(1, fromPlace);
			ps.setDate(2, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date1;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date1 = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date1);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchToPlaceByDate(String toPlace, Date date) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_TO_PLACE_BY_DATE);
			ps.setString(1, toPlace);
			ps.setDate(2, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date1;
				Date nowDate = new Date();
				double newPrice;


				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date1 = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date1);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceToPlaceByDate(String fromPlace, String toPlace, Date date)
			throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		List<Flight> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FROM_PLACE_TO_PLACE_BY_DATE);
			ps.setString(1, fromPlace);
			ps.setString(2, toPlace);
			ps.setDate(3, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight();
				Date date1;
				Date nowDate = new Date();
				double newPrice;

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date1 = dateCal.getTime();
				
				newPrice = priceByDate(rs.getDouble(8), date);

				flight.setFlightId(rs.getInt(1));
				flight.setDate(date1);
				flight.setNowDate(nowDate);
				flight.setFilling(rs.getInt(4));
				flight.setFlightNumber(rs.getString(5));
				flight.setFromPlace(rs.getString(6));
				flight.setToPlace(rs.getString(7));
				flight.setStartingPrice(newPrice);
				flight.setNumberOfSeats(rs.getInt(9));

				flights.add(flight);
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
		return flights;
	}

	@Override
	public Flight viewFlightById(int flightId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Flight newFlight = new Flight();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FLIGHT_BY_ID);

			ps.setInt(1, flightId);
			rs = ps.executeQuery();

			if (rs.next()) {
				Date date;
				String strDate;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

				Calendar dateCal = Calendar.getInstance();
				Calendar timeCal = Calendar.getInstance();
				dateCal.setTime(rs.getDate(2));
				timeCal.setTime(rs.getTime(3));

				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

				date = dateCal.getTime();
				strDate = df.format(date);

				newFlight.setFlightId(rs.getInt(1));
				newFlight.setStrDate(strDate);
				newFlight.setFilling(rs.getInt(4));
				newFlight.setFlightInfoId(rs.getInt(5));
				newFlight.setFlightNumber(rs.getString(6));
				newFlight.setFromPlace(rs.getString(7));
				newFlight.setToPlace(rs.getString(8));
				newFlight.setStartingPrice(rs.getDouble(9));
				newFlight.setNumberOfSeats(rs.getInt(10));

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
		return newFlight;
	}

	@Override
	public void update(int flightId, Date date, int filling, int flightInfoId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Time sqlTime = new java.sql.Time(date.getTime());

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_FLIGHT);

			ps.setDate(1, sqlDate);
			ps.setTime(2, sqlTime);
			ps.setInt(3, filling);
			ps.setInt(4, flightInfoId);
			ps.setInt(5, flightId);

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
	public void delete(int flightId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_FLIGHT);

			ps.setInt(1, flightId);

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

	private double priceByDate(double startingPrice, Date date) {

		double newPrice;
		long result;
		Date nowDate = new Date();

		result = (date.getTime() - nowDate.getTime()) / 1000 / 60 / 60 / 24;

		if (result > 30) {
			newPrice = startingPrice;
		} else if (result <= 30 && result > 7) {
			newPrice = startingPrice * 1.1;
		} else if (result <= 7 && result >= 0){
			newPrice = startingPrice * 1.3;
		} else {
			newPrice = startingPrice;
		}
		
		newPrice= ((int)(100 * newPrice)) / 100.0;
		return newPrice;
	}

	@Override
	public void updateFilling(int flightId, int filling) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_FILLING);

			ps.setInt(1, filling);
			ps.setInt(2, flightId);

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
