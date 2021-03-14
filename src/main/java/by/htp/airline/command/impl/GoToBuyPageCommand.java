package by.htp.airline.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.airline.command.Command;
import by.htp.airline.controller.JSPPageName;
import by.htp.airline.controller.RequestParameterName;
import by.htp.airline.entity.Account;
import by.htp.airline.entity.Ticket;
import by.htp.airline.service.FlightService;
import by.htp.airline.service.TicketService;
import by.htp.airline.service.impl.ServiceProvider;

public class GoToBuyPageCommand implements Command {

	private static final Logger log = Logger.getLogger(GoToBuyPageCommand.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();
	private static final FlightService flightService = ServiceProvider.getInstance().getFlightService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int flightId;
		Ticket ticket;
		List<String> places;
		List<Ticket> blockedPlaces;

		Account account;

		try {
			flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightId = 0;
		}

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				ticket = ticketService.viewTicketInfoByFlightId(flightId);

				if (ticket != null) {
					session.setAttribute("ticket", ticket);
				}

				places = flightService.createSeating(flightId);
				if (places != null) {
					session.setAttribute("places", places);
				}

				blockedPlaces = ticketService.viewTicketsByFlightId(flightId);
				if (blockedPlaces != null) {
					session.setAttribute("blockedPlaces", blockedPlaces);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.BUY_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
