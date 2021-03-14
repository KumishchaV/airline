package by.htp.airline.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.airline.command.Command;
import by.htp.airline.controller.JSPPageName;

public class GoToIndexNoSessionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.INDEX_PAGE);
		dispatcher.forward(request, response);
		
		session.invalidate();
	}

}
