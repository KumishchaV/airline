package by.htp.airline.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.airline.DAO.EmployeeDAO;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.Employee;

public class SQLEmployeeDAO implements EmployeeDAO {

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(String name, String role) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.INSERT_EMPLOYEE);

			ps.setString(1, name);
			ps.setString(2, role);

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
	public List<Employee> viewAllEmployee() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Employee> employees = new ArrayList<>();

		try {
			con = connectionPool.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.FIND_ALL_EMPLOYEE);

			while (rs.next()) {
				Employee info = new Employee();
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
			if (st != null) {
				try {
					st.close();

				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			connectionPool.release(con);

		}
		return employees;
	}

	@Override
	public Employee viewById(int employeeId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Employee newEmployee = new Employee();
		
		try {
			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.FIND_EMPLOYEE_BY_ID);

			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				newEmployee.setName(rs.getString(2));
				newEmployee.setRole(rs.getString(3));
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
		return newEmployee;
	}

	@Override
	public void update(int employeeId, String name, String role) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.UPDATE_EMPLOYEE);
			
			ps.setString(1, name);
			ps.setString(2, role);
			ps.setInt(3, employeeId);
			
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
	public void delete(int employeeId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.take();

			ps = con.prepareStatement(FinalStringInsert.DEL_EMPLOYEE);

			ps.setInt(1, employeeId);
			
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
