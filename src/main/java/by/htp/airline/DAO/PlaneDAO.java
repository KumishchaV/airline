package by.htp.airline.DAO;

import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.Plane;

public interface PlaneDAO {

	void add(String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses) throws DAOException;

	List<Plane> viewAllPlane() throws DAOException;
	
	Plane viewById(int planeId) throws DAOException;
	
	void update(int planeId, String planeType, int numberOfSeats, int numberOfPilots, int numberOfStewardesses) throws DAOException;

	void delete(int planeId) throws DAOException;

}