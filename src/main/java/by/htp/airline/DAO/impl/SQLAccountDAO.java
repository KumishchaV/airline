package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.airline.DAO.AccountDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.Account;

public class SQLAccountDAO implements AccountDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public Account add(int bankAccountId, int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Account account = new Account();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_ACCOUNT);

			ps.setInt(1, bankAccountId);
			ps.setInt(2, userId);

			ps.executeUpdate();

			rs = ps.executeQuery(FinalStringInsert.SELECT_ACCOUNT_LAST_INSERT_ID);
			if (rs.next()) {
				account.setAccountId(rs.getInt(1));
				account.setBankAccountId(rs.getInt(2));
				account.setUserId(rs.getInt(3));
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
		return account;
	}

	@Override
	public Account findAccountByUserId(int userId) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Account account = new Account();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_ACCOUNT_BY_USER_ID);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				account.setAccountId(rs.getInt(1));
				account.setBankAccountId(rs.getInt(2));
				account.setUserId(rs.getInt(3));
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
		return account;
	}

}
