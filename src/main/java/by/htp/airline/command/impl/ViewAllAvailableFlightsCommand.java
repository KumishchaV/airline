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
import by.htp.airline.service.FlightService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class ViewAllAvailableFlightsCommand implements Command {

	private static final Logger log = Logger.getLogger(ViewAllAvailableFlightsCommand.class);
	private static final FlightService flightService = ServiceProvider.getInstance().getFlightService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Flight> flights;

		HttpSession session = request.getSession(false);

		try {
			flights = flightService.viewAllFlight();
			if (flights != null) {
				session.setAttribute("flights", flights);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SEARCHING_RESULTS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}
	}

}
