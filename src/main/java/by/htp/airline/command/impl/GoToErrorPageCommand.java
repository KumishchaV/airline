package by.htp.airline.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.airline.command.Command;
import by.htp.airline.controller.JSPPageName;
import by.htp.airline.controller.RequestParameterName;

public class GoToErrorPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);

		String errorMessage;

		errorMessage = request.getParameter(RequestParameterName.REQ_PARAM_ERROR_MESSAGE);

		session.invalidate();

		request.setAttribute("errorMessage", errorMessage);

		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
		dispatcher.forward(request, response);

	}

}
