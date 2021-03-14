package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.FlightHasEmployeeDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.FlightHasEmployee;

public class SQLFlightHasEmployeeDAO implements FlightHasEmployeeDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(int flightId, String[] selectedEmployees) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_FLIGHT_HAS_EMPLOYEE);

			for (int i = 0; i < selectedEmployees.length; i++) {

				ps.setInt(1, flightId);
				ps.setInt(2, Integer.parseInt(selectedEmployees[i]));

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
	public List<FlightHasEmployee> viewAllFlightHasEmployee() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<FlightHasEmployee> teams = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_FLIGHT_HAS_EMPLOYEE);

			while (rs.next()) {

				FlightHasEmployee info = new FlightHasEmployee();

				info.setFlightId(rs.getInt(1));
				info.setEmployeeId(rs.getInt(2));
				info.setName(rs.getString(3));
				info.setRole(rs.getString(4));
				info.setDate(rs.getDate(5));
				info.setFlightInfoId(rs.getInt(6));
				info.setFlightNumber(rs.getString(7));
				info.setFromPlace(rs.getString(8));
				info.setToPlace(rs.getString(9));

				teams.add(info);
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
		return teams;
	}

	@Override
	public List<FlightHasEmployee> viewFlightWithoutEmployee() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<FlightHasEmployee> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_FLIGHT_WITHOUT_EMPLOYEE);

			while (rs.next()) {

				FlightHasEmployee info = new FlightHasEmployee();

				info.setFlightId(rs.getInt(1));
				info.setDate(rs.getDate(2));
				info.setFlightInfoId(rs.getInt(3));
				info.setFlightNumber(rs.getString(4));
				info.setFromPlace(rs.getString(5));
				info.setToPlace(rs.getString(6));

				flights.add(info);
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
	public List<FlightHasEmployee> viewAllFreeEmployee(Date date) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		List<FlightHasEmployee> employees = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_ALL_FREE_EMPLOYEES);

			ps.setDate(1, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {

				FlightHasEmployee info = new FlightHasEmployee();

				info.setEmployeeId(rs.getInt(1));
				info.setName(rs.getString(2));
				info.setRole(rs.getString(3));

				employees.add(info);
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
		return employees;
	}

	@Override
	public List<FlightHasEmployee> viewFlightWithEmployee() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<FlightHasEmployee> flights = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_FLIGHT_WITH_EMPLOYEE);

			while (rs.next()) {

				FlightHasEmployee info = new FlightHasEmployee();

				info.setFlightId(rs.getInt(1));
				info.setDate(rs.getDate(2));
				info.setFlightInfoId(rs.getInt(3));
				info.setFlightNumber(rs.getString(4));
				info.setFromPlace(rs.getString(5));
				info.setToPlace(rs.getString(6));

				flights.add(info);
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
	public FlightHasEmployee viewPlaneByFlightId(int flightId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		FlightHasEmployee newPlane = new FlightHasEmployee();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_PLAIN_BY_FLIGHT_ID);

			ps.setInt(1, flightId);

			rs = ps.executeQuery();

			if (rs.next()) {
				newPlane.setFlightNumber(rs.getString(1));
				newPlane.setNumberOfPilots(rs.getInt(2));
				newPlane.setNumberOfStewardesses(rs.getInt(3));
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
		return newPlane;
	}

	@Override
	public void delete(int flightId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_FLIGHT_WITH_EMPLOYEE);

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

	@Override
	public List<FlightHasEmployee> viewAllFreeEmployee(int flightId, Date date) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		List<FlightHasEmployee> employees = new ArrayList<>();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_ALL_FREE_EMPLOYEES_WHITH_FLIGHT);

			ps.setDate(1, sqlDate);
			ps.setInt(2, flightId);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				FlightHasEmployee info = new FlightHasEmployee();

				info.setEmployeeId(rs.getInt(1));
				info.setName(rs.getString(2));
				info.setRole(rs.getString(3));

				employees.add(info);
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
		return employees;
	}

}
