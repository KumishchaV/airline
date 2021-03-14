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
import by.htp.airline.entity.FlightInfo;
import by.htp.airline.entity.Plane;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightInfoService;
import by.htp.airline.service.PlaneService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class ViewAllFlightInfoCommand implements Command {

	private static final Logger log = Logger.getLogger(ViewAllFlightInfoCommand.class);
	private static final FlightInfoService flightInfoService = ServiceProvider.getInstance().getFlightInfoService();
	private static final PlaneService planeService = ServiceProvider.getInstance().getPlaneService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
				planes = planeService.viewAllPlane();

				if (planes != null) {
					session.setAttribute("planesToFlight", planes);
				}

				flightInfos = flightInfoService.viewAllFlightInfo();

				if (flightInfos != null) {
					session.setAttribute("flightInfos", flightInfos);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_FLIGHT_INFO_PAGE);
				dispatcher.forward(request, response);

				session.removeAttribute("message");
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
