package by.htp.airline.service;

import java.util.Date;
import java.util.List;

import by.htp.airline.entity.Flight;
import by.htp.airline.service.impl.ServiceException;

public interface FlightService {

	Flight add(Date date, int flightInfoId) throws ServiceException;

	List<Flight> viewAllFlight() throws ServiceException;
	
	List<Flight> selectSearchMode(String fromPlace, String toPlace, Date date) throws ServiceException;
	
	List<Flight> viewSearchFromPlace(String fromPlace) throws ServiceException;
	
	List<Flight> viewSearchToPlace(String toPlace) throws ServiceException;
	
	List<Flight> viewSearchByDate(Date date) throws ServiceException;
	
	List<Flight> viewSearchFromPlaceToPlace(String fromPlace, String toPlace) throws ServiceException;
	
	List<Flight> viewSearchFromPlaceByDate(String fromPlace, Date date) throws ServiceException;
	
	List<Flight> viewSearchToPlaceByDate(String toPlace, Date date) throws ServiceException;
	
	List<Flight> viewSearchFromPlaceToPlaceByDate(String fromPlace, String toPlace, Date date) throws ServiceException;
	
	Flight update(int flightId, Date date, int filling, int flightInfoId) throws ServiceException;

	Flight delete(int flightId) throws ServiceException;
	
	Flight unblockEdite(int flightId) throws ServiceException;
	
	Flight viewFlightById(int flightId) throws ServiceException;
	
	List<String> createSeating(int flightId) throws ServiceException;
	
}
