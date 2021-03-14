package by.htp.airline.controller;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.airline.entity.Account;
import by.htp.airline.entity.Ticket;
import by.htp.airline.service.TicketService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

@WebListener
public class SessionListener implements HttpSessionListener {

	private static final Logger log = Logger.getLogger(SessionListener.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		log.info("sessionCreated");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		int flightId;
		Ticket ticket;
		List<Ticket> tickets;
		Account account;

		HttpSession session = se.getSession();

		account = (Account) session.getAttribute("account");
		tickets = (List<Ticket>) session.getAttribute("tickets");
		ticket = (Ticket) session.getAttribute("ticket");
		
		if(ticket!=null) {
			flightId = ticket.getFlightId();
		}else {
			flightId = 0;
		}
		
		if (tickets.get(0).getTicketNumber() != 0) {
			tickets.clear();
		}

		try {
			ticketService.unBlockTicket(flightId, account.getAccountId(), tickets);
		} catch (ServiceException e) {
			log.log(Level.ERROR, "ServiceException", e);
		}

		log.info("sessionDestroyed");

	}

}
