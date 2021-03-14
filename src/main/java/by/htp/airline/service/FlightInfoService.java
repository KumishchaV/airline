package by.htp.airline.service;

import java.util.List;
import by.htp.airline.entity.FlightInfo;
import by.htp.airline.service.impl.ServiceException;

public interface FlightInfoService {

	FlightInfo add(String flightNumber, String fromPlace, String toPlace, String flightTime, double startingPrice,
			int planeId) throws ServiceException;

	List<FlightInfo> viewAllFlightInfo() throws ServiceException;

	FlightInfo update(int flightInfoId, String flightNumber, String fromPlace, String toPlace, String flightTime,
			double startingPrice, int planeId) throws ServiceException;

	FlightInfo delete(int flightInfoId) throws ServiceException;

	FlightInfo unblockEdite(int flightInfoId) throws ServiceException;

}
