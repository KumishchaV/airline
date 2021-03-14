package by.htp.airline.service.impl;

import java.util.List;

import by.htp.airline.DAO.PlaneDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.Plane;
import by.htp.airline.service.PlaneService;

public class PlaneServiceImpl implements PlaneService {

	private static final PlaneDAO planeDAO = DAOProvider.getInstance().getPlaneDAO();

	@Override
	public Plane add(String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses)
			throws ServiceException {

		Plane plane = new Plane();

		plane.setPlaneType(planeType);
		plane.setNumberOfSeats(numberOfSeats);
		plane.setNumberOfPilots(numberOfPilots);
		plane.setNumberOfStewardesses(numberOfStewardesses);

		try {
			planeDAO.add(planeType, numberOfSeats, numberOfPilots, numberOfStewardesses);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return plane;
	}

	@Override
	public List<Plane> viewAllPlane() throws ServiceException {

		List<Plane> planes;

		try {
			planes = planeDAO.viewAllPlane();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return planes;
	}

	@Override
	public Plane update(int planeId, String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses)
			throws ServiceException {

		Plane plane = new Plane();

		plane.setPlaneId(planeId);
		plane.setPlaneType(planeType);
		plane.setNumberOfSeats(numberOfSeats);
		plane.setNumberOfPilots(numberOfPilots);
		plane.setNumberOfStewardesses(numberOfStewardesses);
		plane.setUnBlockedEdit(false);

		try {
			planeDAO.update(planeId, planeType, numberOfSeats, numberOfPilots, numberOfStewardesses);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return plane;
	}

	@Override
	public Plane delete(int planeId) throws ServiceException {
		
		Plane plane = new Plane();

		plane.setPlaneId(planeId);

		try {
			planeDAO.delete(planeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return plane;
	}

	@Override
	public Plane unblockEdite(int planeId) throws ServiceException {
		
		Plane plane = new Plane();

		try {
			plane = planeDAO.viewById(planeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		plane.setPlaneId(planeId);
		plane.setPlaneType(plane.getPlaneType());
		plane.setNumberOfSeats(plane.getNumberOfSeats());
		plane.setNumberOfPilots(plane.getNumberOfPilots());
		plane.setNumberOfStewardesses(plane.getNumberOfStewardesses());
		plane.setUnBlockedEdit(true);
		return plane;
	}

}
