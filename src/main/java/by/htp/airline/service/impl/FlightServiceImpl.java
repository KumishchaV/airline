package by.htp.airline.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.FlightDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.Flight;
import by.htp.airline.service.FlightService;

public class FlightServiceImpl implements FlightService {

	private static final FlightDAO flightDAO = DAOProvider.getInstance().getFlightDAO();

	@Override
	public Flight add(Date date, int flightInfoId) throws ServiceException {

		Flight flight = new Flight();

		flight.setDate(date);
		flight.setFlightInfoId(flightInfoId);

		try {
			flightDAO.add(date, flightInfoId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flight;
	}

	@Override
	public List<Flight> viewAllFlight() throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewAllFlight();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> selectSearchMode(String fromPlace, String toPlace, Date date) throws ServiceException {

		List<Flight> flights = null;

		if ((!"".equals(fromPlace)) && (!"".equals(toPlace)) && (date == null)) {
			flights = viewSearchFromPlaceToPlace(fromPlace, toPlace);
		}

		if ((!"".equals(fromPlace)) && ("".equals(toPlace)) && (date == null)) {
			flights = viewSearchFromPlace(fromPlace);
		}

		if (("".equals(fromPlace)) && (!"".equals(toPlace)) && (date == null)) {
			flights = viewSearchToPlace(toPlace);
		}

		if (("".equals(fromPlace)) && ("".equals(toPlace)) && (date != null)) {
			flights = viewSearchByDate(date);
		}

		if ((!"".equals(fromPlace)) && ("".equals(toPlace)) && (date != null)) {
			flights = viewSearchFromPlaceByDate(fromPlace, date);
		}

		if (("".equals(fromPlace)) && (!"".equals(toPlace)) && (date != null)) {
			flights = viewSearchToPlaceByDate(toPlace, date);
		}

		if ((!"".equals(fromPlace)) && (!"".equals(toPlace)) && (date != null)) {
			flights = viewSearchFromPlaceToPlaceByDate(fromPlace, toPlace, date);
		}

		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceToPlace(String fromPlace, String toPlace) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchFromPlaceToPlace(fromPlace, toPlace);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlace(String fromPlace) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchFromPlace(fromPlace);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchToPlace(String toPlace) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchToPlace(toPlace);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchByDate(Date date) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchByDate(date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceByDate(String fromPlace, Date date) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchFromPlaceByDate(fromPlace, date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchToPlaceByDate(String toPlace, Date date) throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchToPlaceByDate(toPlace, date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public List<Flight> viewSearchFromPlaceToPlaceByDate(String fromPlace, String toPlace, Date date)
			throws ServiceException {

		List<Flight> flights;

		try {
			flights = flightDAO.viewSearchFromPlaceToPlaceByDate(fromPlace, toPlace, date);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flights;
	}

	@Override
	public Flight update(int flightId, Date date, int filling, int flightInfoId) throws ServiceException {

		Flight flight = new Flight();

		flight.setFlightId(flightId);
		flight.setDate(date);
		flight.setFilling(filling);
		flight.setFlightInfoId(flightInfoId);
		flight.setUnBlockedFlight(false);

		try {
			flightDAO.update(flightId, date, filling, flightInfoId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flight;

	}

	@Override
	public Flight delete(int flightId) throws ServiceException {

		Flight flight = new Flight();

		flight.setFlightInfoId(flightId);

		try {
			flightDAO.delete(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flight;
	}

	@Override
	public Flight unblockEdite(int flightId) throws ServiceException {

		Flight flight = new Flight();

		try {
			flight = flightDAO.viewFlightById(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		flight.setFlightId(flightId);
		flight.setStrDate(flight.getStrDate());
		flight.setFilling(flight.getFilling());
		flight.setFlightInfoId(flight.getFlightInfoId());
		flight.setUnBlockedFlight(true);

		return flight;
	}

	@Override
	public Flight viewFlightById(int flightId) throws ServiceException {

		Flight flight = new Flight();

		try {
			flight = flightDAO.viewFlightById(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flight;
	}

	@Override
	public List<String> createSeating(int flightId) throws ServiceException {

		List<String> places = new ArrayList<>();
		Flight flight = new Flight();

		try {
			flight = flightDAO.viewFlightById(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		int numberOfSeats = flight.getNumberOfSeats();

		if (numberOfSeats >= 160) {

			int rows = numberOfSeats / 6;

			for (int i = 0; i < rows; i++) {
				places.add((i + 1) + "A");
				places.add((i + 1) + "B");
				places.add((i + 1) + "C");
				places.add((i + 1) + "D");
				places.add((i + 1) + "E");
				places.add((i + 1) + "F");
			}
			System.out.println("service "+places);
		} else {
			int rows = numberOfSeats / 4;

			for (int i = 0; i < rows; i++) {
				places.add((i + 1) + "A");
				places.add((i + 1) + "B");
				places.add((i + 1) + "C");
				places.add((i + 1) + "D");
			}
			System.out.println("service "+places);
		}
		return places;
	}

}
