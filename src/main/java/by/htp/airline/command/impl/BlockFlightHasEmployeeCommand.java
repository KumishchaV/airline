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
import by.htp.airline.entity.FlightHasEmployee;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightHasEmployeeService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class BlockFlightHasEmployeeCommand implements Command {

	private static final Logger log = Logger.getLogger(BlockFlightHasEmployeeCommand.class);
	private static final FlightHasEmployeeService flightHasEmployeeService = ServiceProvider.getInstance()
			.getFlightHasEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		FlightHasEmployee flightHasEmployee;
		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				flightHasEmployee = flightHasEmployeeService.blockFlightHasEmployee();

				if (flightHasEmployee != null) {
					session.setAttribute("flightHasEmployee", flightHasEmployee);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_CREW_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
