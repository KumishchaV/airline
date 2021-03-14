package by.htp.airline.service.impl;

import java.util.List;

import by.htp.airline.DAO.EmployeeDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.Employee;
import by.htp.airline.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	private static final EmployeeDAO employeeDAO = DAOProvider.getInstance().getEmployeeDAO();

	@Override
	public Employee add(String name, String role) throws ServiceException {
		
		Employee employee = new Employee();
		
		employee.setName(name);
		employee.setRole(role);

		try {
			employeeDAO.add(name, role);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employee;
	}

	@Override
	public List<Employee> viewAllEmployee() throws ServiceException {
		
		List<Employee> employees;

		try {
			employees = employeeDAO.viewAllEmployee();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employees;
	}

	@Override
	public Employee update(int employeeId, String name, String role) throws ServiceException {
		
		Employee employee = new Employee();

		employee.setEmployeeId(employeeId);
		employee.setName(name);
		employee.setRole(role);
		employee.setUnBlockedEdit(false);

		try {
			employeeDAO.update(employeeId, name, role);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employee;
	}

	@Override
	public Employee delete(int employeeId) throws ServiceException {
		
		Employee employee = new Employee();

		employee.setEmployeeId(employeeId);

		try {
			employeeDAO.delete(employeeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employee;
	}

	@Override
	public Employee unblockEdite(int employeeId) throws ServiceException {

		Employee employee = new Employee();

		try {
			employee = employeeDAO.viewById(employeeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		employee.setEmployeeId(employeeId);
		employee.setName(employee.getName());
		employee.setRole(employee.getRole());
		employee.setUnBlockedEdit(true);
		return employee;
	}

}
