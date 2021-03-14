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

public class GoToPayPageCommand implements Command {

	private static final Logger log = Logger.getLogger(GoToPayPageCommand.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String[] priorityRegistration;
		String[] priorityBoarding;
		String[] luggage;
		List<Ticket> tickets;

		Account account;

		priorityRegistration = request.getParameterValues(RequestParameterName.REQ_PARAM_PRIORITY_REGISTRATION);
		priorityBoarding = request.getParameterValues(RequestParameterName.REQ_PARAM_PRIORITY_BOARDING);
		luggage = request.getParameterValues(RequestParameterName.REQ_PARAM_LUGGAGE);

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");

			} else {

				tickets = ticketService.verificationTicket(account.getAccountId(), priorityRegistration,
						priorityBoarding, luggage);
				if (tickets != null) {
					session.setAttribute("tickets", tickets);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.PAY_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
