package by.htp.airline.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.airline.command.Command;
import by.htp.airline.controller.RequestParameterName;
import by.htp.airline.entity.Account;
import by.htp.airline.service.TicketService;
import by.htp.airline.service.impl.ServiceProvider;

public class ConfirmPaymentCommand implements Command {

	private static final Logger log = Logger.getLogger(ConfirmPaymentCommand.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String[] ticketsId;
		Boolean check;

		Account account;

		ticketsId = request.getParameterValues(RequestParameterName.REQ_PARAM_TICKET_ID);

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");

			} else {
				check = ticketService.confirmPayment(account.getBankAccountId(), ticketsId);
				if (check == true) {
					session.setAttribute("congratulationsMessage", "Congratulations, the ticket is purchased!");
					response.sendRedirect("controller?command=go-to-client-page");
				} else {
					session.setAttribute("errorMessage",
							"Not enough money or something went wrong, the ticket was not purchased");
					response.sendRedirect("controller?command=go-to-client-page");
				}
			}
		} catch (Exception e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
