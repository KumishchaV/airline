package by.htp.airline.service.impl;

import by.htp.airline.DAO.EmployeeDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.Employee;

public class FlightHasEmployeeValidator {

	private static final FlightHasEmployeeValidator instance = new FlightHasEmployeeValidator();

	private static final EmployeeDAO employeeDAO = DAOProvider.getInstance().getEmployeeDAO();

	public FlightHasEmployeeValidator() {
	}

	public static FlightHasEmployeeValidator getInstance() {
		return instance;
	}

	public boolean checkPilotAndStewardesses(int numberOfPilots, int numberOfStewardesses, String[] selectedEmployees)
			throws ServiceException {

		int tempNumberOfPilots = 0;
		int tempNumberOfStewardesses = 0;

		Employee employee = new Employee();

		if (selectedEmployees != null) {
			for (int i = 0; i < selectedEmployees.length; i++) {
				try {
					employee = employeeDAO.viewById(Integer.parseInt(selectedEmployees[i]));
					if ("pilot".equals(employee.getRole())) {
						tempNumberOfPilots = tempNumberOfPilots + 1;
					}

					if ("stewardess".equals(employee.getRole())) {
						tempNumberOfStewardesses = tempNumberOfStewardesses + 1;
					}
				} catch (DAOException e) {
					throw new ServiceException(e);
				}

			}
		}
		if (tempNumberOfPilots == numberOfPilots && tempNumberOfStewardesses == numberOfStewardesses) {
			return true;
		} else {
			return false;
		}
	}
}
