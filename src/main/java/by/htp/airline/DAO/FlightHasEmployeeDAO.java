package by.htp.airline.DAO;

import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.FlightHasEmployee;

public interface FlightHasEmployeeDAO {

	void add(int flightId, String[] selectedEmployees) throws DAOException;
	
	List<FlightHasEmployee> viewAllFlightHasEmployee() throws DAOException;

	List<FlightHasEmployee> viewFlightWithoutEmployee() throws DAOException;
	
	List<FlightHasEmployee> viewFlightWithEmployee() throws DAOException;
	
	FlightHasEmployee viewPlaneByFlightId(int flightId) throws DAOException;
	
	List<FlightHasEmployee> viewAllFreeEmployee(Date date) throws DAOException;
	
	List<FlightHasEmployee> viewAllFreeEmployee(int flightId, Date date) throws DAOException;
		
	void delete(int flightId) throws DAOException;
}
