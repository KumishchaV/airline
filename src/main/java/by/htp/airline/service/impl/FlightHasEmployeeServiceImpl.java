package by.htp.airline.service.impl;

import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.FlightHasEmployeeDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.FlightHasEmployee;
import by.htp.airline.service.FlightHasEmployeeService;

public class FlightHasEmployeeServiceImpl implements FlightHasEmployeeService {

	private final FlightHasEmployeeDAO flightHasEmployeeDAO = DAOProvider.getInstance().getFlightHasEmployeeDAO();
	private final FlightHasEmployeeValidator validator = FlightHasEmployeeValidator.getInstance();

	@Override
	public FlightHasEmployee add(int flightId, int numberOfPilots, int numberOfStewardesses, String[] selectedEmployees)
			throws ServiceException, DataValidatorServiceException {

		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();

		if (!validator.checkPilotAndStewardesses(numberOfPilots, numberOfStewardesses, selectedEmployees)) {
			throw new DataValidatorServiceException();
		}

		try {
			flightHasEmployeeDAO.add(flightId, selectedEmployees);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		flightHasEmployee.setUnBlockedAdd(false);

		return flightHasEmployee;
	}

	@Override
	public List<FlightHasEmployee> viewAllFlightHasEmployee() throws ServiceException {

		List<FlightHasEmployee> teams;

		try {
			teams = flightHasEmployeeDAO.viewAllFlightHasEmployee();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return teams;
	}

	@Override
	public List<FlightHasEmployee> viewFlightWithoutEmployee() throws ServiceException {

		List<FlightHasEmployee> flights;

		try {
			flights = flightHasEmployeeDAO.viewFlightWithoutEmployee();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<FlightHasEmployee> viewAllFreeEmployee(Date date) throws ServiceException {

		List<FlightHasEmployee> employees;

		try {
			employees = flightHasEmployeeDAO.viewAllFreeEmployee(date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employees;
	}

	@Override
	public List<FlightHasEmployee> viewFlightWithEmployee() throws ServiceException {

		List<FlightHasEmployee> flights;

		try {
			flights = flightHasEmployeeDAO.viewFlightWithEmployee();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public FlightHasEmployee unblockEdite(int flightId) throws ServiceException {

		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();
		try {
			flightHasEmployee = flightHasEmployeeDAO.viewPlaneByFlightId(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		flightHasEmployee.setFlightId(flightId);
		flightHasEmployee.setFlightNumber(flightHasEmployee.getFlightNumber());
		flightHasEmployee.setNumberOfPilots(flightHasEmployee.getNumberOfPilots());
		flightHasEmployee.setNumberOfStewardesses(flightHasEmployee.getNumberOfStewardesses());

		flightHasEmployee.setUnBlockedEdit(true);

		return flightHasEmployee;
	}

	@Override
	public FlightHasEmployee viewPlaneByFlightId(int flightId) throws ServiceException {

		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();

		try {
			flightHasEmployee = flightHasEmployeeDAO.viewPlaneByFlightId(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		flightHasEmployee.setFlightId(flightId);
		flightHasEmployee.setFlightNumber(flightHasEmployee.getFlightNumber());
		flightHasEmployee.setNumberOfPilots(flightHasEmployee.getNumberOfPilots());
		flightHasEmployee.setNumberOfStewardesses(flightHasEmployee.getNumberOfStewardesses());

		flightHasEmployee.setUnBlockedAdd(true);

		return flightHasEmployee;
	}
	
	@Override
	public FlightHasEmployee blockFlightHasEmployee() throws ServiceException {


		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();
		
		flightHasEmployee.setUnBlockedAdd(false);
		flightHasEmployee.setUnBlockedEdit(false);
		
		return flightHasEmployee;
	}

	@Override
	public FlightHasEmployee update(int flightId, int numberOfPilots, int numberOfStewardesses,
			String[] selectedEmployees) throws ServiceException, DataValidatorServiceException {

		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();

		if (!validator.checkPilotAndStewardesses(numberOfPilots, numberOfStewardesses, selectedEmployees)) {
			throw new DataValidatorServiceException();
		}

		try {
			flightHasEmployeeDAO.delete(flightId);
		} catch (DAOException e1) {
			throw new ServiceException(e1);
		}

		try {
			flightHasEmployeeDAO.add(flightId, selectedEmployees);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		flightHasEmployee.setUnBlockedEdit(false);

		return flightHasEmployee;
	}

	@Override
	public FlightHasEmployee delete(int flightId) throws ServiceException {

		FlightHasEmployee flightHasEmployee = new FlightHasEmployee();

		flightHasEmployee.setFlightId(flightId);

		try {
			flightHasEmployeeDAO.delete(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flightHasEmployee;
	}

	@Override
	public List<FlightHasEmployee> viewAllFreeEmployee(int flightId, Date date) throws ServiceException {
		List<FlightHasEmployee> employees;

		try {
			employees = flightHasEmployeeDAO.viewAllFreeEmployee(flightId, date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employees;
	}

	
}
