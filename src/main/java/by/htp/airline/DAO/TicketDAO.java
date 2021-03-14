package by.htp.airline.DAO;

import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.Ticket;

public interface TicketDAO {

	Ticket viewTicketInfoByFlightId(int flightId) throws DAOException;
	
	Ticket viewTicketInfoById(int ticketId) throws DAOException;

	void blockSelectedTicket(int flightId, int accountId, double newPrice, String[] selectedPlaceNumber) throws DAOException;
	
	List<Ticket> viewTicketsByFlightId(int flightId) throws DAOException;
	
	List<Ticket> viewTicketsByFlightId(int flightId, int accountId) throws DAOException;
	
	List<Ticket> verificationTicket(int accountId, String[] priorityRegistration, String[] priorityBoarding, String[] luggage) throws DAOException;
	
	List<Ticket> viewTicketsByAccountId(int accountId) throws DAOException;
	
	List<Ticket> confirmPayment(String[] ticketsId) throws DAOException;
	
	void unBlockTicket(int accountId) throws DAOException;
	
	void delete(int ticketId) throws DAOException;
}
