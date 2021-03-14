package by.htp.airline.service.impl;

import java.util.List;

import by.htp.airline.DAO.FlightInfoDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.FlightInfo;
import by.htp.airline.service.FlightInfoService;

public class FlightInfoServiceImpl implements FlightInfoService {

	private static final FlightInfoDAO flightInfoDAO = DAOProvider.getInstance().getFlightInfoDAO();

	@Override
	public FlightInfo add(String flightNumber, String fromPlace, String toPlace, String flightTime,
			double startingPrice, int planeId) throws ServiceException {

		FlightInfo flightInfo = new FlightInfo();

		flightInfo.setFlightNumber(flightNumber);
		flightInfo.setFromPlace(fromPlace);
		flightInfo.setToPlace(toPlace);
		flightInfo.setFlightTime(flightTime);
		flightInfo.setStartingPrice(startingPrice);
		flightInfo.setPlaneId(planeId);

		try {
			flightInfoDAO.add(flightNumber, fromPlace, toPlace, flightTime, startingPrice, planeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flightInfo;
	}

	@Override
	public List<FlightInfo> viewAllFlightInfo() throws ServiceException {

		List<FlightInfo> flightInfos;

		try {
			flightInfos = flightInfoDAO.viewAllFlightInfo();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flightInfos;
	}

	@Override
	public FlightInfo update(int flightInfoId, String flightNumber, String fromPlace, String toPlace, String flightTime,
			double startingPrice, int planeId) throws ServiceException {

		FlightInfo flightInfo = new FlightInfo();

		flightInfo.setFlightInfoId(flightInfoId);
		flightInfo.setFlightNumber(flightNumber);
		flightInfo.setFromPlace(fromPlace);
		flightInfo.setToPlace(toPlace);
		flightInfo.setFlightTime(flightTime);
		flightInfo.setStartingPrice(startingPrice);
		flightInfo.setPlaneId(planeId);

		flightInfo.setUnBlockedFlightInfo(false);

		try {
			flightInfoDAO.update(flightInfoId, flightNumber, fromPlace, toPlace, flightTime, startingPrice, planeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flightInfo;
	}

	@Override
	public FlightInfo delete(int flightInfoId) throws ServiceException {
		
		FlightInfo flightInfo = new FlightInfo();

		flightInfo.setFlightInfoId(flightInfoId);

		try {
			flightInfoDAO.delete(flightInfoId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flightInfo;
	}

	@Override
	public FlightInfo unblockEdite(int flightInfoId) throws ServiceException {

		FlightInfo flightInfo = new FlightInfo();

		try {
			flightInfo = flightInfoDAO.viewFlightInfoById(flightInfoId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		flightInfo.setFlightInfoId(flightInfoId);
		flightInfo.setFlightNumber(flightInfo.getFlightNumber());
		flightInfo.setFromPlace(flightInfo.getFromPlace());
		flightInfo.setToPlace(flightInfo.getToPlace());
		flightInfo.setFlightTime(flightInfo.getFlightTime());
		flightInfo.setStartingPrice(flightInfo.getStartingPrice());
		flightInfo.setPlaneId(flightInfo.getPlaneId());
		flightInfo.setUnBlockedFlightInfo(true);
		
		return flightInfo;
	}


}
