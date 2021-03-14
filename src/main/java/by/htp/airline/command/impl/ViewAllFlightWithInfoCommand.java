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
import by.htp.airline.entity.Flight;
import by.htp.airline.entity.FlightInfo;
import by.htp.airline.entity.Plane;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightInfoService;
import by.htp.airline.service.FlightService;
import by.htp.airline.service.PlaneService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class ViewAllFlightWithInfoCommand implements Command {

	private static final Logger log = Logger.getLogger(ViewAllFlightWithInfoCommand.class);
	private static final FlightService flightService = ServiceProvider.getInstance().getFlightService();
	private static final FlightInfoService flightInfoService = ServiceProvider.getInstance().getFlightInfoService();
	private static final PlaneService planeService = ServiceProvider.getInstance().getPlaneService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Flight> flights;
		List<FlightInfo> flightInfos;
		List<Plane> planes;
		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {

				flightInfos = flightInfoService.viewAllFlightInfo();

				if (flightInfos != null) {
					session.setAttribute("flightInfos", flightInfos);
				}

				planes = planeService.viewAllPlane();

				if (planes != null) {
					session.setAttribute("planes", planes);
				}

				flights = flightService.viewAllFlight();

				if (flights != null) {
					session.setAttribute("flights", flights);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_FLIGHT_TO_DEPARTURE);
				dispatcher.forward(request, response);

				session.removeAttribute("message");
			}

		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
