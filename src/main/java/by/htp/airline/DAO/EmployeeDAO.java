package by.htp.airline.DAO;

import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.Employee;

public interface EmployeeDAO {

	void add(String name, String role) throws DAOException;
	
	List<Employee> viewAllEmployee() throws DAOException;
	
	Employee viewById(int employeeId) throws DAOException;
	
	void update(int employeeId, String name, String role) throws DAOException;

	void delete(int employeeId) throws DAOException;

}
