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
import by.htp.airline.service.AccountService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class AddMoneyCommand implements Command {

	private static final Logger log = Logger.getLogger(AddMoneyCommand.class);
	private static final AccountService accountService = ServiceProvider.getInstance().getAccountService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		double addMoney;

		try {
			addMoney = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_ADD_MONEY));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			addMoney = 0;
		}

		Account account;

		HttpSession session = request.getSession(false);

		try {
			account = (Account) session.getAttribute("account");

			if (null == account) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				accountService.addMoney(account.getUserId(), addMoney);

				session.setAttribute("message", "Money is successfully added!");
				response.sendRedirect("controller?command=go-to-client-page");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
