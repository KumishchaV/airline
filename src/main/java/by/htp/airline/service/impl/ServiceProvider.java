package by.htp.airline.service.impl;

import by.htp.airline.service.AccountService;
import by.htp.airline.service.EmployeeService;
import by.htp.airline.service.FlightHasEmployeeService;
import by.htp.airline.service.FlightInfoService;
import by.htp.airline.service.FlightService;
import by.htp.airline.service.PlaneService;
import by.htp.airline.service.TicketService;
import by.htp.airline.service.UserService;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();

	private final UserService userService = new UserServiceImpl();
	private final AccountService accountService = new AccountServiceImpl();

	private final PlaneService planeService = new PlaneServiceImpl();
	private final FlightInfoService flightInfoService = new FlightInfoServiceImpl();
	private final EmployeeService employeeService = new EmployeeServiceImpl();
	private final FlightService flightService = new FlightServiceImpl();
	private final FlightHasEmployeeService flightHasEmployeeService = new FlightHasEmployeeServiceImpl();
	private final TicketService ticketService = new TicketServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public PlaneService getPlaneService() {
		return planeService;
	}

	public FlightInfoService getFlightInfoService() {
		return flightInfoService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public FlightService getFlightService() {
		return flightService;
	}

	public FlightHasEmployeeService getFlightHasEmployeeService() {
		return flightHasEmployeeService;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

}
