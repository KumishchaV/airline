package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.airline.DAO.UserDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.User;

public class SQLUserDAO implements UserDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public User logination(String login, String password) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_USER_BY_LOGIN_AND_PASSWORD);

			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				newUser.setUserId(rs.getInt(1));
				newUser.setLogin(rs.getString(2));
				newUser.setPassword(rs.getString(3));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
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
		return newUser;
	}

	@Override
	public User registration(String name, String surname, String email, String login, String password, String role)
			throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_REGISTRATION_INFO);

			ps.setString(1, login);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, name);
			ps.setString(5, surname);
			ps.setString(6, role);

			ps.executeUpdate();

			rs = ps.executeQuery(FinalStringInsert.SELECT_USER_LAST_INSERT_ID);
			if (rs.next()) {
				newUser.setUserId(rs.getInt(1));
				newUser.setLogin(rs.getString(2));
				newUser.setPassword(rs.getString(3));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
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

		return newUser;
	}

	@Override
	public User findUserByLogin(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_USER_BY_LOGIN);

			ps.setString(1, login);
			rs = ps.executeQuery();

			if (rs.next()) {
				newUser.setUserId(rs.getInt(1));
				newUser.setLogin(rs.getString(2));
				newUser.setPassword(rs.getString(3));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
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
		return newUser;
	}

	@Override
	public List<User> viewAllUsers() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<User> Users = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_USERS);

			while (rs.next()) {
				User info = new User();

				info.setUserId(rs.getInt(1));
				info.setLogin(rs.getString(2));
				info.setPassword(rs.getString(3));
				info.setEmail(rs.getString(4));
				info.setName(rs.getString(5));
				info.setSurname(rs.getString(6));
				info.setRole(rs.getString(7));

				Users.add(info);
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
		return Users;
	}

	@Override
	public void EditEmailPassword(int userId, String newPassword, String newEmail) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_EMAIL_PASSWORD);
			
			ps.setString(1, newPassword);
			ps.setString(2, newEmail);
			ps.setInt(3, userId);
			
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
	public void delete(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_USER);

			ps.setInt(1, userId);
			
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
