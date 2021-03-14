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
import by.htp.airline.service.TicketService;
import by.htp.airline.service.impl.ServiceProvider;

public class SelectTicketParametersCommand implements Command {

	private static final Logger log = Logger.getLogger(SelectTicketParametersCommand.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int flightId;
		String[] selectedPlaceNumber;
		List<Ticket> tickets;
		Ticket selectTicket;
		String messageSelectError;

		Account account;

		try {
			flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightId = 0;
		}

		selectedPlaceNumber = request.getParameterValues(RequestParameterName.REQ_PARAM_PLACE_NUMBER);

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");

			} else if (selectedPlaceNumber != null) {

				selectTicket = ticketService.selectTicketParameters(flightId, account.getAccountId(),
						selectedPlaceNumber);
				if (selectTicket != null) {
					session.setAttribute("ticket", selectTicket);
				}

				tickets = ticketService.viewTicketsByFlightId(flightId, account.getAccountId());
				if (tickets != null) {
					session.setAttribute("tickets", tickets);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.BUY_PAGE);
				dispatcher.forward(request, response);

			} else {
				messageSelectError = "!!! You must select at least one seat !!!";
				request.setAttribute("messageSelectError", messageSelectError);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.BUY_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
