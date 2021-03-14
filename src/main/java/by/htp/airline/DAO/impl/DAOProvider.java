package by.htp.airline.DAO.impl;

import by.htp.airline.DAO.AccountDAO;
import by.htp.airline.DAO.BankAccountDAO;
import by.htp.airline.DAO.EmployeeDAO;
import by.htp.airline.DAO.FlightDAO;
import by.htp.airline.DAO.FlightHasEmployeeDAO;
import by.htp.airline.DAO.FlightInfoDAO;
import by.htp.airline.DAO.PlaneDAO;
import by.htp.airline.DAO.TicketDAO;
import by.htp.airline.DAO.UserDAO;

public class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();

	private final UserDAO userDAO = new SQLUserDAO();
	private final BankAccountDAO bankAccountDAO = new SQLBankAccountDAO();
	private final AccountDAO accountDAO = new SQLAccountDAO();
	private final PlaneDAO planeDAO = new SQLPlaneDAO();
	private final FlightInfoDAO flightInfoDAO = new SQLFlightInfoDAO();
	private final EmployeeDAO employeeDAO = new SQLEmployeeDAO();
	private final FlightDAO flightDAO = new SQLFlightDAO();
	private final FlightHasEmployeeDAO flightHasEmployeeDAO = new SQLFlightHasEmployeeDAO();
	private final TicketDAO ticketDAO = new SQLTicketDAO();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BankAccountDAO getBankAccountDAO() {
		return bankAccountDAO;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public PlaneDAO getPlaneDAO() {
		return planeDAO;
	}

	public FlightInfoDAO getFlightInfoDAO() {
		return flightInfoDAO;
	}
	
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public FlightDAO getFlightDAO() {
		return flightDAO;
	}

	public TicketDAO getTicketDAO() {
		return ticketDAO;
	}

	public FlightHasEmployeeDAO getFlightHasEmployeeDAO() {
		return flightHasEmployeeDAO;
	}

}
