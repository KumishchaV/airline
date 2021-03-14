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
import by.htp.airline.entity.User;
import by.htp.airline.service.UserService;
import by.htp.airline.service.impl.ServiceProvider;

public class ViewAllUsersCommand implements Command {

	private static final Logger log = Logger.getLogger(ViewAllUsersCommand.class);
	private static final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<User> users;
		User user;

		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				users = userService.viewAllUsers();

				if (users != null) {
					session.setAttribute("users", users);
				}
				session.removeAttribute("unblock");

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_USERS_INFO_PAGE);
				dispatcher.forward(request, response);
			}

			session.removeAttribute("congratulationsMessage");

		} catch (Exception e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
