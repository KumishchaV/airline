package by.htp.airline.command.impl;

import java.io.IOException;

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
import by.htp.airline.entity.User;
import by.htp.airline.service.AccountService;
import by.htp.airline.service.UserService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class LoginationCommand implements Command {

	private static final Logger log = Logger.getLogger(LoginationCommand.class);
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final AccountService accountService = ServiceProvider.getInstance().getAccountService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login;
		String password;
		String messageLoginNotFound;
		String messageWrongPassword;

		User userFindLogin;
		User user;
		Account account;

		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);

		HttpSession session = request.getSession(true);

		try {

			userFindLogin = userService.findUserByLogin(login);

			user = userService.logination(login, password);

			if (null == userFindLogin) {

				messageLoginNotFound = "!!! Login not found !!!";

				request.setAttribute("messageLoginNotFound", messageLoginNotFound);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else if (user == null) {

				messageWrongPassword = "!!! Wrong password !!!";

				request.setAttribute("messageWrongPassword", messageWrongPassword);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else if (user != null) {

				if (user.getRole().equals("client")) {
					account = accountService.findAccountByUserLogin(user.getLogin());
					session.setAttribute("account", account);
					session.setAttribute("user", user);

					response.sendRedirect("controller?command=go-to-client-page");
				}
				if (user.getRole().equals("admin")) {
					session.setAttribute("user", user);

					response.sendRedirect("controller?command=go-to-admin-page");
				}
			}

		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}
}
