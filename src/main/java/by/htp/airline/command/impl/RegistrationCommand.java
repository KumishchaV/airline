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
import by.htp.airline.service.impl.DataValidatorServiceException;

public class RegistrationCommand implements Command {

	private static final Logger log = Logger.getLogger(RegistrationCommand.class);
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final AccountService accountService = ServiceProvider.getInstance().getAccountService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name;
		String surname;
		String email;
		String login;
		String password;
		String password2;
		String messageIncorrectPassword;
		String messageLoginAlreadyExists;
		String messageErrorEmailAndPasswordFormat;

		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		password2 = request.getParameter(RequestParameterName.REQ_PARAM_PASS2);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);

		User user;
		Account account;

		try {
			user = userService.findUserByLogin(login);

			if (null != user) {

				messageLoginAlreadyExists = "!!! Such login already exists !!!";

				request.setAttribute("messageLoginAlreadyExists", messageLoginAlreadyExists);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else if (!password.equals(password2)) {

				messageIncorrectPassword = "!!! Password entered incorrectly !!!";

				request.setAttribute("messageIncorrectPassword", messageIncorrectPassword);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else {

				user = userService.registration(login, password, email, name, surname);

				HttpSession session = request.getSession(true);

				session.setAttribute("congratulationsMessage",
						"!!! Congratulations, you are successfully registered !!!");

				if (user.getRole().equals("client")) {
					account = accountService.add(user.getLogin());

					if (account != null) {
						session.setAttribute("account", account);
					}

					response.sendRedirect("controller?command=go-to-client-page");
				}
				if (user.getRole().equals("admin")) {

					if (user != null) {
						session.setAttribute("user", user);
					}

					response.sendRedirect("controller?command=go-to-admin-page");
				}

			}

		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		} catch (DataValidatorServiceException e) {

			messageErrorEmailAndPasswordFormat = "!!! Incorrect email or password format !!!";

			request.setAttribute("messageErrorEmailAndPasswordFormat", messageErrorEmailAndPasswordFormat);

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
