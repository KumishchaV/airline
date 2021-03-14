package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.airline.DAO.BankAccountDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.BankAccount;

public class SQLBankAccountDAO implements BankAccountDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public BankAccount add() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		BankAccount bankAccount = new BankAccount();

		try {
			con = connectionPool.take();
			ps = con.prepareStatement(FinalStringInsert.INSERT_BANK_ACCOUNT);
			ps.executeUpdate();

			rs = ps.executeQuery(FinalStringInsert.SELECT_BANK_ACCOUNT_LAST_INSERT_ID);
			if (rs.next()) {
				bankAccount.setBankAccountId(rs.getInt(1));
				bankAccount.setValue(rs.getDouble(2));
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
		return bankAccount;
	}

	@Override
	public BankAccount findBankAccountById(int bankAccountId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		BankAccount bankAccount = new BankAccount();

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_BANK_ACCOUNT_BY_ID);

			ps.setInt(1, bankAccountId);
			rs = ps.executeQuery();

			if (rs.next()) {
				bankAccount.setBankAccountId(rs.getInt(1));
				bankAccount.setValue(rs.getDouble(2));
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
		return bankAccount;
	}

	@Override
	public void addMoney(int bankAccountId, double addMoney) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_BANK_ACCOUNT_UP);

			ps.setDouble(1, addMoney);
			ps.setInt(2, bankAccountId);
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
	public void deductMoney(int bankAccountId, double deductMoney) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_BANK_ACCOUNT_DOWN);

			ps.setDouble(1, deductMoney);
			ps.setInt(2, bankAccountId);
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
