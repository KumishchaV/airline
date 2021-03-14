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
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class DeleteTicketCommand implements Command {

	private static final Logger log = Logger.getLogger(DeleteTicketCommand.class);
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int ticketId;

		try {
			ticketId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_TICKET_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			ticketId = 0;
		}

		Account account;

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				ticketService.delete(account.getBankAccountId(), ticketId);

				session.setAttribute("message", "The ticket is successfully returned!");
				response.sendRedirect("controller?command=go-to-client-page");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
