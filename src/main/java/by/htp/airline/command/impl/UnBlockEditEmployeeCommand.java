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
import by.htp.airline.entity.Employee;
import by.htp.airline.entity.User;
import by.htp.airline.service.EmployeeService;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.ServiceProvider;

public class UnBlockEditEmployeeCommand implements Command {

	private static final Logger log = Logger.getLogger(UnBlockEditEmployeeCommand.class);
	private static final EmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int employeeId;

		try {
			employeeId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_EMPLOYEE_ID));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			employeeId = 0;
		}

		Employee employee;
		User user;

		HttpSession session = request.getSession(false);

		try {
			user = (User) session.getAttribute("user");

			if (null == user) {
				session.setAttribute("loginMessage", "!!! Session not found,try logging again  !!!");
				response.sendRedirect("controller?command=go-to-index-no-session-page");
			} else {
				employee = employeeService.unblockEdite(employeeId);

				if (employee != null) {
					request.setAttribute("employee", employee);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_EMPLOYEE_PAGE);
				dispatcher.forward(request, response);
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, "", e);
			response.sendRedirect("controller?command=go-to-error-page");

		}

	}

}
