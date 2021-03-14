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
import by.htp.airline.service.EmployeeService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class EditEmployeeCommand implements Command {

	private static final Logger log = Logger.getLogger(EditEmployeeCommand.class);
	private static final EmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int employeeId;
		String name;
		String role;

		try {
			employeeId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_EMPLOYEE_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			employeeId = 0;
		}
		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		role = request.getParameter(RequestParameterName.REQ_PARAM_ROLE);

		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				employeeService.update(employeeId, name, role);

				session.setAttribute("message", "Employee is successfully edited!");
				response.sendRedirect("controller?command=view_all_employee");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
