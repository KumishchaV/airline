package by.htp.airline.command.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.airline.command.Command;
import by.htp.airline.controller.RequestParameterName;
import by.htp.airline.entity.User;
import by.htp.airline.service.FlightService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class EditFlightCommand implements Command {

	private static final Logger log = Logger.getLogger(EditFlightCommand.class);
	private static final FlightService flightService = ServiceProvider.getInstance().getFlightService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int flightId;
		String date;
		int filling;
		int flightInfoId;

		try {
			flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightId = 0;
		}

		date = request.getParameter(RequestParameterName.REQ_PARAM_DATE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date utilDate;
		Date utilDate2;

		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			log.log(Level.ERROR, "ParseException", e);
			utilDate = null;
		}

		try {
			utilDate2 = sdf2.parse(date);
		} catch (ParseException e) {
			log.log(Level.ERROR, "ParseException", e);
			utilDate2 = null;
		}

		if (utilDate == null) {
			utilDate = utilDate2;
		}

		try {
			filling = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FILLING));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			filling = 0;
		}
		try {
			flightInfoId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_INFO_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			flightInfoId = 0;
		}

		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				flightService.update(flightId, utilDate, filling, flightInfoId);

				session.setAttribute("message", "Flight is successfully edited!");
				response.sendRedirect("controller?command=view-all-flight-with-info");
			}
			
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
