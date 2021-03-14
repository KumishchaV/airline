package by.htp.airline.command.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import by.htp.airline.controller.RequestParameterName;
import by.htp.airline.entity.Flight;
import by.htp.airline.service.FlightService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class GoToSearchingResultsPageCommand implements Command {

	private static final Logger log = Logger.getLogger(GoToSearchingResultsPageCommand.class);
	private static final FlightService flightService = ServiceProvider.getInstance().getFlightService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String fromPlace;
		String toPlace;
		String date;
		List<Flight> flights;

		fromPlace = request.getParameter(RequestParameterName.REQ_PARAM_FROM_PLACE);
		toPlace = request.getParameter(RequestParameterName.REQ_PARAM_TO_PLACE);
		date = request.getParameter(RequestParameterName.REQ_PARAM_DATE);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate = null;

		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			log.log(Level.ERROR, "ParseException", e);
			utilDate = null;
		}

		HttpSession session = request.getSession(false);

		try {
			if (("".equals(fromPlace)) && ("".equals(toPlace)) && ("".equals(date))) {
				String message;

				message = "!!! Enter your search data !!!";

				request.setAttribute("message", message);
				session.setAttribute("flights", null);

			} else {

				flights = flightService.selectSearchMode(fromPlace, toPlace, utilDate);

				if (flights != null) {
					session.setAttribute("flights", flights);
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SEARCHING_RESULTS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
