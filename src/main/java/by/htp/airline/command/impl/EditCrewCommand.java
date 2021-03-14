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
import by.htp.airline.entity.FlightHasEmployee;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightHasEmployeeService;
import by.htp.airline.service.impl.DataValidatorServiceException;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class EditCrewCommand implements Command {

	private static final Logger log = Logger.getLogger(EditCrewCommand.class);
	private static final FlightHasEmployeeService flightHasEmployeeService = ServiceProvider.getInstance()
			.getFlightHasEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int flightId;
		int numberOfPilots;
		int numberOfStewardesses;

		String messageEditError;

		try {
			flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightId = 0;
		}
		try {
			numberOfPilots = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_PILOTS));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			numberOfPilots = 0;
		}
		try {
			numberOfStewardesses = Integer
					.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_STEWARDESSES));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			numberOfStewardesses = 0;
		}

		String[] selectedEmployees = request.getParameterValues(RequestParameterName.REQ_PARAM_EMPLOYEE_ID);

		User user;
		FlightHasEmployee flightHasEmployee;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				flightHasEmployee = flightHasEmployeeService.update(flightId, numberOfPilots, numberOfStewardesses,
						selectedEmployees);
				if (flightHasEmployee != null) {
					session.setAttribute("flightHasEmployee", flightHasEmployee);
				}
				session.setAttribute("message", "Crew is successfully added!");
				response.sendRedirect("controller?command=view_all_crews");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		} catch (DataValidatorServiceException e) {

			messageEditError = "!!! Incorrect quantity of selected pilots or stewardesses !!!";

			session.setAttribute("messageEditError", messageEditError);

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_CREW_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
