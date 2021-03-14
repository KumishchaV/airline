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
import by.htp.airline.service.FlightInfoService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class AddFlightInfoCommand implements Command {

	private static final Logger log = Logger.getLogger(AddFlightInfoCommand.class);
	private static final FlightInfoService flightInfoService = ServiceProvider.getInstance().getFlightInfoService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String flightNumber;
		String fromPlace;
		String toPlace;
		String flightTime;
		double startingPrice;
		int planeId;

		flightNumber = request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_NUMBER);
		fromPlace = request.getParameter(RequestParameterName.REQ_PARAM_FROM_PLACE);
		toPlace = request.getParameter(RequestParameterName.REQ_PARAM_TO_PLACE);
		flightTime = request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_TIME);
		try {
			startingPrice = Double.parseDouble(request.getParameter(RequestParameterName.REQ_PARAM_STARTING_PRICE));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			startingPrice = 0;
		}
		try {
			planeId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_PLANE_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			planeId = 1;
		}

		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				flightInfoService.add(flightNumber, fromPlace, toPlace, flightTime, startingPrice, planeId);

				session.setAttribute("message", "Flight info is successfully added!");
				response.sendRedirect("controller?command=view_all_flight_info");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
