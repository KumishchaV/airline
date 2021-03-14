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

public class UnBlockEditEmailPasswordCommand implements Command {

	private static final Logger log = Logger.getLogger(UnBlockEditEmailPasswordCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int userId;
		boolean unblock;

		try {
			userId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_USER_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			userId = 0;
		}

		User user;
		User userUnblock = new User();

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				unblock = true;
				session.setAttribute("unblock", unblock);

				if (userId == 0) {
					userId = user.getUserId();
				}
				userUnblock.setUserId(userId);
				userUnblock.setRole(user.getRole());
				session.setAttribute("userUnblock", userUnblock);

				if (user.getRole().equals("admin")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_USERS_INFO_PAGE);
					dispatcher.forward(request, response);
				}
				if (user.getRole().equals("client")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
					dispatcher.forward(request, response);
				}
			}
			
		} catch (Exception e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
