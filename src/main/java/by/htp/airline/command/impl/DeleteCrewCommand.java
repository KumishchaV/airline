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
import by.htp.airline.service.FlightHasEmployeeService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class DeleteCrewCommand implements Command {

	private static final Logger log = Logger.getLogger(DeleteCrewCommand.class);
	private static final FlightHasEmployeeService flightHasEmployeeService = ServiceProvider.getInstance()
			.getFlightHasEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int flightId;

		try {
			flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightId = 0;
		}

		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				flightHasEmployeeService.delete(flightId);

				session.setAttribute("message", "Crew is successfully deleted!");
				response.sendRedirect("controller?command=view_all_crews");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
