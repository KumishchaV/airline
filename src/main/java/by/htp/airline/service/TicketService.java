package by.htp.airline.service;

import java.util.List;

import by.htp.airline.entity.Ticket;
import by.htp.airline.service.impl.ServiceException;

public interface TicketService {

	Ticket viewTicketInfoByFlightId(int flightId) throws ServiceException;

	Ticket selectTicketParameters(int flightId, int accountId, String[] selectedPlaceNumber) throws ServiceException;

	List<Ticket> verificationTicket(int accountId, String[] priorityRegistration, String[] priorityBoarding,
			String[] luggage) throws ServiceException;
	
	List<Ticket> viewTicketsByAccountId(int accountId) throws ServiceException;

	List<Ticket> viewTicketsByFlightId(int flightId) throws ServiceException;

	List<Ticket> viewTicketsByFlightId(int flightId, int accountId) throws ServiceException;
	
	Boolean confirmPayment(int bankAccountId, String[] ticketsId) throws ServiceException;
	
	Ticket unBlockTicket(int flightId, int accountId, List<Ticket> tickets) throws ServiceException;
	
	Ticket delete(int bankAccountId, int ticketId) throws ServiceException;
}
