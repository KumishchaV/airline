package by.htp.airline.DAO;

import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.Flight;

public interface FlightDAO {

	void add(Date date, int flightInfoId) throws DAOException;

	List<Flight> viewAllFlight() throws DAOException;
		
	List<Flight> viewSearchFromPlace(String fromPlace) throws DAOException;
	
	List<Flight> viewSearchToPlace(String toPlace) throws DAOException;
	
	List<Flight> viewSearchByDate(Date date) throws DAOException;

	List<Flight> viewSearchFromPlaceToPlace(String fromPlace, String toPlace) throws DAOException;
	
	List<Flight> viewSearchFromPlaceByDate(String fromPlace, Date date) throws DAOException;
	
	List<Flight> viewSearchToPlaceByDate(String toPlace, Date date) throws DAOException;
	
	List<Flight> viewSearchFromPlaceToPlaceByDate(String fromPlace, String toPlace, Date date) throws DAOException;
	
	Flight viewFlightById(int flightId) throws DAOException;

	void update(int flightId, Date date, int filling, int flightInfoId) throws DAOException;
	
	void updateFilling(int flightId, int filling) throws DAOException;

	void delete(int flightId) throws DAOException;

}
