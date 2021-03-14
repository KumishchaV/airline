package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.airline.DAO.FlightInfoDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.FlightInfo;

public class SQLFlightInfoDAO implements FlightInfoDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(String flightNumber, String fromPlace, String toPlace, String flightTime, double startingPrice,
			int planeId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_FLIGHT_INFO);

			ps.setString(1, flightNumber);
			ps.setString(2, fromPlace);
			ps.setString(3, toPlace);
			ps.setString(4, flightTime);
			ps.setDouble(5, startingPrice);
			ps.setInt(6, planeId);

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
	public List<FlightInfo> viewAllFlightInfo() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<FlightInfo> flightInfos = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_FLIGHT_INFO);

			while (rs.next()) {
				FlightInfo flightInfo = new FlightInfo();

				flightInfo.setFlightInfoId(rs.getInt(1));
				flightInfo.setFlightNumber(rs.getString(2));
				flightInfo.setFromPlace(rs.getString(3));
				flightInfo.setToPlace(rs.getString(4));
				flightInfo.setFlightTime(rs.getString(5));
				flightInfo.setStartingPrice(rs.getDouble(6));
				flightInfo.setPlaneId(rs.getInt(7));

				flightInfos.add(flightInfo);
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
		return flightInfos;
	}

	@Override
	public FlightInfo viewFlightInfoById(int flightInfoId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		FlightInfo newFlightInfo = new FlightInfo();
		
		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_FLIGHT_INFO_BY_ID);

			ps.setInt(1, flightInfoId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				newFlightInfo.setFlightNumber(rs.getString(2));
				newFlightInfo.setFromPlace(rs.getString(3));
				newFlightInfo.setToPlace(rs.getString(4));
				newFlightInfo.setFlightTime(rs.getString(5));
				newFlightInfo.setStartingPrice(rs.getDouble(6));
				newFlightInfo.setPlaneId(rs.getInt(7));
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
		return newFlightInfo;
	}

	@Override
	public void update(int flightInfoId, String flightNumber, String fromPlace, String toPlace, String flightTime,
			double startingPrice, int planeId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_FLIGHT_INFO);
			
			ps.setString(1, flightNumber);
			ps.setString(2, fromPlace);
			ps.setString(3, toPlace);
			ps.setString(4, flightTime);
			ps.setDouble(5, startingPrice);
			ps.setInt(6, planeId);
			ps.setInt(7, flightInfoId);
			
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
	public void delete(int flightInfoId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_FLIGHT_INFO);

			ps.setInt(1, flightInfoId);
			
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
