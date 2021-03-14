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
import by.htp.airline.entity.User;
import by.htp.airline.service.PlaneService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class EditPlaneCommand implements Command {

	private static final Logger log = Logger.getLogger(EditPlaneCommand.class);
	private static final PlaneService planeService = ServiceProvider.getInstance().getPlaneService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int planeId;
		String planeType;
		int numberOfSeats;
		int numberOfPilots;
		int numberOfStewardesses;

		try {
			planeId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_PLANE_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			planeId = 0;
		}
		planeType = request.getParameter(RequestParameterName.REQ_PARAM_PLANE_TYPE);
		try {
			numberOfSeats = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_SEATS));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			numberOfSeats = 100;
		}
		try {
			numberOfPilots = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_PILOTS));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			numberOfPilots = 2;
		}
		try {
			numberOfStewardesses = Integer
					.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_STEWARDESSES));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			numberOfStewardesses = 5;
		}

		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				planeService.update(planeId, planeType, numberOfSeats, numberOfPilots, numberOfStewardesses);

				session.setAttribute("message", "Plane is successfully edited!");
				response.sendRedirect("controller?command=view_all_plane");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
