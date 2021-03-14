package by.htp.airline.service;

import java.util.List;

import by.htp.airline.entity.Plane;
import by.htp.airline.service.impl.ServiceException;

public interface PlaneService {

	Plane add(String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesse) throws ServiceException;

	List<Plane> viewAllPlane() throws ServiceException;
	
	Plane update(int planeId, String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses) throws ServiceException;

	Plane delete(int planeId) throws ServiceException;
	
	Plane unblockEdite(int planeId) throws ServiceException;

}