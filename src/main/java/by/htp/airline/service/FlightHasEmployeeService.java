package by.htp.airline.service;

import java.util.Date;
import java.util.List;

import by.htp.airline.entity.FlightHasEmployee;
import by.htp.airline.service.impl.DataValidatorServiceException;
import by.htp.airline.service.impl.ServiceException;

public interface FlightHasEmployeeService {
	
	FlightHasEmployee add(int flightId, int numberOfPilots, int numberOfStewardesses, String[] selectedEmployees) throws ServiceException, DataValidatorServiceException;
	
	List<FlightHasEmployee> viewAllFlightHasEmployee() throws ServiceException;

	List<FlightHasEmployee> viewFlightWithoutEmployee() throws ServiceException;
	
	List<FlightHasEmployee> viewFlightWithEmployee() throws ServiceException;
	
	List<FlightHasEmployee> viewAllFreeEmployee(Date date) throws ServiceException;
	
	List<FlightHasEmployee> viewAllFreeEmployee(int flightId,Date date) throws ServiceException;
	
	FlightHasEmployee unblockEdite(int flightId) throws ServiceException;
	
	FlightHasEmployee viewPlaneByFlightId(int flightId) throws ServiceException;
	
	FlightHasEmployee blockFlightHasEmployee() throws ServiceException;
	
	FlightHasEmployee update(int flightId, int numberOfPilots, int numberOfStewardesses, String[] selectedEmployees) throws ServiceException, DataValidatorServiceException;
	
	FlightHasEmployee delete(int flightId) throws ServiceException;
}

