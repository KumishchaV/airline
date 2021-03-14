package by.htp.airline.command.impl;

import java.io.IOException;
import java.util.Date;
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
import by.htp.airline.entity.Account;
import by.htp.airline.entity.Ticket;
import by.htp.airline.service.AccountService;
import by.htp.airline.service.TicketService;
import by.htp.airline.service.impl.ServiceProvider;

public class GoToClientPageCommand implements Command {

	private static final Logger log = Logger.getLogger(GoToClientPageCommand.class);
	private static final AccountService accountService = ServiceProvider.getInstance().getAccountService();
	private static final TicketService ticketService = ServiceProvider.getInstance().getTicketService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Ticket> tickets;
		Date nowDate = new Date();

		Account account;

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				account = accountService.findAccountByUserLogin(account.getLogin());
				session.setAttribute("account", account);

				tickets = ticketService.viewTicketsByAccountId(account.getAccountId());
				if (tickets != null) {
					session.setAttribute("tickets", tickets);
				}

				session.setAttribute("nowDate", nowDate);
				session.removeAttribute("unblock");

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
				dispatcher.forward(request, response);
			}
			session.removeAttribute("congratulationsMessage");
			session.removeAttribute("errorMessage");
			session.removeAttribute("message");

		} catch (Exception e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
