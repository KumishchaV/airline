package by.htp.airline.DAO;

import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.FlightInfo;

public interface FlightInfoDAO {

	void add(String flightNumber, String fromPlace, String toPlace, String flightTime, double startingPrice,
			int planeId) throws DAOException;

	List<FlightInfo> viewAllFlightInfo() throws DAOException;

	FlightInfo viewFlightInfoById(int flightInfoId) throws DAOException;

	void delete(int flightInfoId) throws DAOException;

	void update(int flightInfoId, String flightNumber, String fromPlace, String toPlace, String flightTime,
			double startingPrice, int planeId) throws DAOException;

}