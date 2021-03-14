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
import by.htp.airline.entity.User;
import by.htp.airline.service.UserService;
import by.htp.airline.service.impl.DataValidatorServiceException;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class EditEmailPasswordCommand implements Command {

	private static final Logger log = Logger.getLogger(EditEmailPasswordCommand.class);
	private static final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int userId;
		String newEmail;
		String oldPassword;
		String newPassword;
		String newPassword2;
		String messageIncorrectPassword;
		String messageErrorEmailAndPasswordFormat;

		try {
			userId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_USER_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			userId = 0;
		}

		oldPassword = request.getParameter(RequestParameterName.REQ_PARAM_OLD_PASS);
		newPassword = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		newPassword2 = request.getParameter(RequestParameterName.REQ_PARAM_PASS2);
		newEmail = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);

		User user;
		User hashPassword;

		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");

		try {

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");

			} else if (!newPassword.equals(newPassword2)) {

				messageIncorrectPassword = "!!! Second password entered incorrectly !!!";
				request.setAttribute("messageIncorrectPassword", messageIncorrectPassword);

				if (user.getRole().equals("admin")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_USERS_INFO_PAGE);
					dispatcher.forward(request, response);
				}
				if (user.getRole().equals("client")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
					dispatcher.forward(request, response);
				}

			} else {
				if (oldPassword == null && user.getRole().equals("admin")) {

					if (userId == 0) {
						userId = user.getUserId();
					}
					userService.editEmailPassword(userId, newPassword, newEmail);
					session.setAttribute("congratulationsMessage",
							"!!! Email and password are successfully edited !!!");

					session.removeAttribute("unblock");
					session.removeAttribute("userUnblock");

					if (user.getRole().equals("client")) {
						response.sendRedirect("controller?command=go-to-client-page");
					}
					if (user.getRole().equals("admin")) {
						response.sendRedirect("controller?command=view-all-users");
					}
				} else {

					hashPassword = userService.hashEnterPassword(oldPassword);

					if (hashPassword.getPassword().equals(user.getPassword())) {
						if (userId == 0) {
							userId = user.getUserId();
						}
						userService.editEmailPassword(userId, newPassword, newEmail);
						session.setAttribute("congratulationsMessage",
								"!!! Email and password are successfully edited !!!");

						session.removeAttribute("unblock");
						session.removeAttribute("userUnblock");

						if (user.getRole().equals("client")) {
							response.sendRedirect("controller?command=go-to-client-page");
						}
						if (user.getRole().equals("admin")) {
							response.sendRedirect("controller?command=view-all-users");
						}

					} else {
						messageIncorrectPassword = "!!! The current password was entered incorrectly !!!";
						request.setAttribute("messageIncorrectPassword", messageIncorrectPassword);

						if (user.getRole().equals("admin")) {
							RequestDispatcher dispatcher = request
									.getRequestDispatcher(JSPPageName.ADMIN_USERS_INFO_PAGE);
							dispatcher.forward(request, response);
						}
						if (user.getRole().equals("client")) {
							RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
							dispatcher.forward(request, response);
						}
					}
				}
			}

		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		} catch (DataValidatorServiceException e) {

			messageErrorEmailAndPasswordFormat = "!!! Incorrect email or password format !!!";

			request.setAttribute("messageErrorEmailAndPasswordFormat", messageErrorEmailAndPasswordFormat);

			if (user.getRole().equals("admin")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_USERS_INFO_PAGE);
				dispatcher.forward(request, response);
			}
			if (user.getRole().equals("client")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
				dispatcher.forward(request, response);
			}
		}
	}

}
