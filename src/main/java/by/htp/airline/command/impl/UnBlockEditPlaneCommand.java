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
import by.htp.airline.entity.Plane;
import by.htp.airline.entity.User;
import by.htp.airline.service.PlaneService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class UnBlockEditPlaneCommand implements Command {

	private static final Logger log = Logger.getLogger(UnBlockEditPlaneCommand.class);
	private static final PlaneService planeService = ServiceProvider.getInstance().getPlaneService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int planeId;

		try {
			planeId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_PLANE_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			planeId = 0;
		}

		Plane plane;
		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				plane = planeService.unblockEdite(planeId);

				if (plane != null) {
					request.setAttribute("plane", plane);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_PLANE_PAGE);
				dispatcher.forward(request, response);
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
