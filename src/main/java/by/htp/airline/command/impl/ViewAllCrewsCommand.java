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
import by.htp.airline.entity.FlightHasEmployee;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightHasEmployeeService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class ViewAllCrewsCommand implements Command {

	private static final Logger log = Logger.getLogger(ViewAllCrewsCommand.class);
	private static final FlightHasEmployeeService flightHasEmployeeService = ServiceProvider.getInstance()
			.getFlightHasEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<FlightHasEmployee> teams;

		List<FlightHasEmployee> flightsWithout;
		List<FlightHasEmployee> flightsWith;
		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				teams = flightHasEmployeeService.viewAllFlightHasEmployee();

				if (teams != null) {
					session.setAttribute("teams", teams);
				}

				flightsWithout = flightHasEmployeeService.viewFlightWithoutEmployee();

				if (flightsWithout != null) {
					session.setAttribute("flightsWithout", flightsWithout);
				}

				flightsWith = flightHasEmployeeService.viewFlightWithEmployee();

				if (flightsWith != null) {
					session.setAttribute("flightsWith", flightsWith);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_CREW_PAGE);
				dispatcher.forward(request, response);

				session.removeAttribute("message");
				session.removeAttribute("messageEditError");
				session.removeAttribute("messageAddError");
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");
		}

	}

}
