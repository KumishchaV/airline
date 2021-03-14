package by.htp.airline.service;

import java.util.List;

import by.htp.airline.entity.Employee;
import by.htp.airline.service.impl.ServiceException;

public interface EmployeeService {
	
	Employee add(String name, String role) throws ServiceException;

	List<Employee> viewAllEmployee() throws ServiceException;
	
	Employee update(int employeeId, String name, String role) throws ServiceException;

	Employee delete(int employeeId) throws ServiceException;
	
	Employee unblockEdite(int employeeId) throws ServiceException;
}
