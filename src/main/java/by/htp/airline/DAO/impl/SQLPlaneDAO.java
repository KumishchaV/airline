package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.airline.DAO.PlaneDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.Plane;

public class SQLPlaneDAO implements PlaneDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses)
			throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_PLAIN_INFO);

			ps.setString(1, planeType);
			ps.setInt(2, numberOfSeats);
			ps.setInt(3, numberOfPilots);
			ps.setInt(4, numberOfStewardesses);

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
	public List<Plane> viewAllPlane() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Plane> planes = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_PLAIN);

			while (rs.next()) {
				Plane info = new Plane();
				info.setPlaneId(rs.getInt(1));
				info.setPlaneType(rs.getString(2));
				info.setNumberOfSeats(rs.getInt(3));
				info.setNumberOfPilots(rs.getInt(4));
				info.setNumberOfStewardesses(rs.getInt(5));

				planes.add(info);
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
		return planes;
	}

	@Override
	public Plane viewById(int planeId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Plane newPlane = new Plane();
		
		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_PLAIN_BY_ID);

			ps.setInt(1, planeId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				newPlane.setPlaneType(rs.getString(2));
				newPlane.setNumberOfSeats(rs.getInt(3));
				newPlane.setNumberOfPilots(rs.getInt(4));
				newPlane.setNumberOfStewardesses(rs.getInt(5));
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
	public void update(int planeId, String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_PLAIN);
			
			ps.setString(1, planeType);
			ps.setInt(2, numberOfSeats);
			ps.setInt(3, numberOfPilots);
			ps.setInt(4, numberOfStewardesses);
			ps.setInt(5, planeId);
			
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
	public void delete(int planeId) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_PLAIN);

			ps.setInt(1, planeId);
			
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
