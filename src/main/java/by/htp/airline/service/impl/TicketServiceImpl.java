package by.htp.airline.service.impl;

import java.util.Date;
import java.util.List;

import by.htp.airline.DAO.BankAccountDAO;
import by.htp.airline.DAO.FlightDAO;
import by.htp.airline.DAO.TicketDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.BankAccount;
import by.htp.airline.entity.Ticket;
import by.htp.airline.service.TicketService;

public class TicketServiceImpl implements TicketService {

	private static final TicketDAO ticketDAO = DAOProvider.getInstance().getTicketDAO();
	private static final BankAccountDAO bankAccountDAO = DAOProvider.getInstance().getBankAccountDAO();
	private static final FlightDAO flightDAO = DAOProvider.getInstance().getFlightDAO();

	@Override
	public Ticket viewTicketInfoByFlightId(int flightId) throws ServiceException {

		Ticket ticketInfo = new Ticket();

		try {
			ticketInfo = ticketDAO.viewTicketInfoByFlightId(flightId);
			ticketInfo.setPlacesVisible(true);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return ticketInfo;
	}

	@Override
	public Ticket selectTicketParameters(int flightId, int accountId, String[] selectedPlaceNumber)
			throws ServiceException {

		Ticket ticketParameters = new Ticket();
		double newPrice;
		int filling;

		try {
			ticketParameters = ticketDAO.viewTicketInfoByFlightId(flightId);

			ticketParameters.setPlacesVisible(false);
			ticketParameters.setBookedPlacesVisible(true);
			ticketParameters.setAccountId(accountId);

			newPrice = priceByDate(ticketParameters.getStartingPrice(), ticketParameters.getDeparting());

			ticketDAO.blockSelectedTicket(flightId, accountId, newPrice, selectedPlaceNumber);

			filling = selectedPlaceNumber.length;
			flightDAO.updateFilling(flightId, filling);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return ticketParameters;
	}

	@Override
	public List<Ticket> viewTicketsByFlightId(int flightId) throws ServiceException {

		List<Ticket> tickets;

		try {
			tickets = ticketDAO.viewTicketsByFlightId(flightId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tickets;
	}

	@Override
	public List<Ticket> viewTicketsByFlightId(int flightId, int accountId) throws ServiceException {

		List<Ticket> tickets;

		try {
			tickets = ticketDAO.viewTicketsByFlightId(flightId, accountId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tickets;
	}

	@Override
	public List<Ticket> verificationTicket(int accountId, String[] priorityRegistration, String[] priorityBoarding,
			String[] luggage) throws ServiceException {

		List<Ticket> tickets;

		try {
			tickets = ticketDAO.verificationTicket(accountId, priorityRegistration, priorityBoarding, luggage);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tickets;
	}

	@Override
	public Boolean confirmPayment(int bankAccountId, String[] ticketsId) throws ServiceException {

		double sum = 0;
		int temp = 0;
		int ticketId;
		int filling;
		BankAccount tempSum;
		List<Ticket> tickets;
		Ticket ticket;

		try {
			tickets = ticketDAO.confirmPayment(ticketsId);

			for (int i = 0; i < tickets.size(); i++) {
				sum = sum + tickets.get(i).getFinalPrice();
				if (tickets.get(i).getTicketNumber() != 0) {
					temp = temp + 1;
				}
			}

			tempSum = bankAccountDAO.findBankAccountById(bankAccountId);

			if (tempSum.getValue() > sum) {
				bankAccountDAO.deductMoney(bankAccountId, sum);
				
			} else {
				ticketId = Integer.parseInt(ticketsId[0]);
				ticket = ticketDAO.viewTicketInfoById(ticketId);

				filling = 0 - ticketsId.length;
				flightDAO.updateFilling(ticket.getFlightId(), filling);
				
				for (int i = 0; i < ticketsId.length; i++) {
					ticketDAO.delete(Integer.parseInt(ticketsId[i]));
				}
				
				temp = 0;
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		if (ticketsId.length == temp) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Ticket> viewTicketsByAccountId(int accountId) throws ServiceException {

		List<Ticket> tickets;

		try {
			tickets = ticketDAO.viewTicketsByAccountId(accountId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tickets;
	}

	private double priceByDate(double startingPrice, Date date) {

		double newPrice;
		long result;
		Date nowDate = new Date();

		result = (date.getTime() - nowDate.getTime()) / 1000 / 60 / 60 / 24;

		if (result > 30) {
			newPrice = startingPrice;
		} else if (result <= 30 && result > 7) {
			newPrice = startingPrice * 1.1;
		} else if (result <= 7 && result >= 0) {
			newPrice = startingPrice * 1.3;
		} else {
			newPrice = startingPrice;
		}
		
		newPrice= ((int)(100 * newPrice)) / 100.0;
		return newPrice;
	}

	@Override
	public Ticket unBlockTicket(int flightId, int accountId, List<Ticket> tickets) throws ServiceException {

		Ticket ticketParameters = new Ticket();
		int filling;

		try {
			ticketDAO.unBlockTicket(accountId);

			filling = 0 - tickets.size();
			flightDAO.updateFilling(flightId, filling);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return ticketParameters;
	}

	@Override
	public Ticket delete(int bankAccountId, int ticketId) throws ServiceException {

		double tempSum;
		Ticket ticket;

		try {
			ticket = ticketDAO.viewTicketInfoById(ticketId);

			flightDAO.updateFilling(ticket.getFlightId(), -1);

			tempSum = ticket.getFinalPrice() / 2;
			
			tempSum= ((int)(100 * tempSum)) / 100.0;

			bankAccountDAO.addMoney(bankAccountId, tempSum);

			ticketDAO.delete(ticketId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return ticket;
	}
}
