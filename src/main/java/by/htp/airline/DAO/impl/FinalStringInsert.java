package by.htp.airline.DAO.impl;

public final class FinalStringInsert {

	private FinalStringInsert() {
	}

	public final static String INSERT_REGISTRATION_INFO = "INSERT INTO user_info (login, password, email, name, surname, role) VALUES(?,?,?,?,?,?)";
	public final static String INSERT_BANK_ACCOUNT = "INSERT INTO bank_account (balance) VALUES(0)";
	public final static String INSERT_ACCOUNT = "INSERT INTO client_account (bank_account_id, user_info_id) VALUES(?,?)";
	public final static String INSERT_PLAIN_INFO = "INSERT INTO plane (plane_type, number_of_seats, number_of_pilots, number_of_stewardesses) VALUES(?,?,?,?)";
	public final static String INSERT_FLIGHT_INFO = "INSERT INTO flight_info (flight_number, from_place, to_place, flight_time, starting_price, plane_id) VALUES(?,?,?,?,?,?)";
	public final static String INSERT_EMPLOYEE = "INSERT INTO employee (name, role) VALUES(?,?)";
	public final static String INSERT_FLIGHT = "INSERT INTO flight (date, time, filling, flight_info_id) VALUES(?,?,?,?)";
	public final static String INSERT_FLIGHT_HAS_EMPLOYEE = "INSERT INTO flight_has_employee (flight_id, employee_id) VALUES(?,?)";
	public final static String BLOCK_SELECTED_TICKET = "INSERT INTO ticket (flight_id, client_id, final_price, place_number) VALUES(?,?,?,?)";

	public final static String SELECT_USER_LAST_INSERT_ID = "SELECT * FROM user_info WHERE id = LAST_INSERT_ID()";
	public final static String SELECT_BANK_ACCOUNT_LAST_INSERT_ID = "SELECT * FROM bank_account WHERE id = LAST_INSERT_ID()";
	public final static String SELECT_ACCOUNT_LAST_INSERT_ID = "SELECT * FROM client_account WHERE id = LAST_INSERT_ID()";

	public final static String FIND_USER_BY_LOGIN = "SELECT * FROM user_info WHERE login=?";
	public final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user_info WHERE login=? AND password=?";
	public final static String FIND_ACCOUNT_BY_USER_ID = "SELECT * FROM client_account WHERE user_info_id=?";
	public final static String FIND_BANK_ACCOUNT_BY_ID = "SELECT * FROM bank_account WHERE id=?";
	public final static String FIND_PLAIN_BY_ID = "SELECT * FROM plane WHERE id=?";
	public final static String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id=?";
	public final static String FIND_FLIGHT_INFO_BY_ID = "SELECT * FROM flight_info WHERE id=?";
	public final static String FIND_FLIGHT_BY_ID = "SELECT flight.id, flight.date, flight.time, flight.filling, flight.flight_info_id, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE flight.id=?";
	public final static String FIND_PLAIN_BY_FLIGHT_ID = "SELECT flight_info.flight_number, plane.number_of_pilots, plane.number_of_stewardesses FROM plane JOIN flight_info ON plane.id = flight_info.plane_id JOIN flight ON flight_info.id = flight.flight_info_id  WHERE flight.id=?";
	public final static String FIND_TICKET_INFO_BY_FLIGHT_ID = "SELECT flight.id, flight.date, flight.time, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.flight_time, flight_info.starting_price, plane.plane_type FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE flight.id=?";
	public final static String FIND_TICKETS_BY_FLIGHT_ID = "SELECT id, place_number FROM ticket WHERE flight_id=? ORDER BY place_number";
	public final static String FIND_TICKET_INFO_BY_ID = "SELECT id, final_price, flight_id FROM ticket WHERE id=?";

	public final static String FIND_TICKETS_BY_FLIGHT_ID_AND_ACCOUNT_ID = "SELECT id, place_number FROM ticket WHERE flight_id=? AND client_id=? AND ticket_number IS NULL ORDER BY place_number";
	public final static String FIND_TICKETS_BY_ACCOUNT_ID = "SELECT ticket.id, ticket.place_number, ticket.luggage, ticket.priority_boarding, ticket.priority_registration, ticket.final_price, flight.date, flight.time, flight_info.flight_number, flight_info.from_place, flight_info.to_place FROM ticket JOIN flight ON ticket.flight_id = flight.id JOIN flight_info ON flight_info.id = flight.flight_info_id WHERE client_id=? AND ticket_number IS NULL ORDER BY place_number";
	public final static String FIND_PURCHASED_TICKETS_BY_ACCOUNT_ID = "SELECT ticket.id, ticket.place_number, ticket.luggage, ticket.priority_boarding, ticket.priority_registration, ticket.final_price, flight.date, flight.time, flight_info.flight_number, flight_info.from_place, flight_info.to_place, ticket.ticket_number FROM ticket JOIN flight ON ticket.flight_id = flight.id JOIN flight_info ON flight_info.id = flight.flight_info_id WHERE client_id=? AND ticket_number IS NOT NULL ORDER BY flight.date, flight.time";
	public final static String FIND_TICKETS_NUMBER_BY_TICKET_ID = "SELECT ticket_number, final_price FROM ticket WHERE id=?";

	public final static String FIND_ALL_USERS = "SELECT * FROM user_info";
	public final static String FIND_ALL_PLAIN = "SELECT * FROM plane";
	public final static String FIND_ALL_FLIGHT_INFO = "SELECT * FROM flight_info";
	public final static String FIND_ALL_EMPLOYEE = "SELECT * FROM employee";
	public final static String FIND_ALL_FLIGHT_WITH_FLIGHT_INFO = "SELECT flight.id, flight.date, flight.time, flight.filling, flight.flight_info_id, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id ORDER BY flight.date, flight.time";
	public final static String FIND_ALL_FLIGHT_HAS_EMPLOYEE = "SELECT flight_id, employee_id, employee.name, employee.role, flight.date, flight_info.id, flight_info.flight_number, flight_info.from_place, flight_info.to_place FROM flight_has_employee JOIN employee ON employee.id = flight_has_employee.employee_id JOIN flight ON flight.id = flight_has_employee.flight_id JOIN flight_info ON flight_info.id = flight.flight_info_id  ORDER BY flight.date, flight.flight_info_id";
	public final static String FIND_ALL_FLIGHT_WITHOUT_EMPLOYEE = "SELECT flight.id, flight.date, flight_info.id, flight_info.flight_number, flight_info.from_place, flight_info.to_place FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id WHERE flight.date > CURRENT_DATE() AND flight.id NOT IN (SELECT flight_id FROM flight_has_employee) ORDER BY flight.date";
	public final static String FIND_ALL_FLIGHT_WITH_EMPLOYEE = "SELECT flight.id, flight.date, flight_info.id, flight_info.flight_number, flight_info.from_place, flight_info.to_place FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id WHERE flight.id IN (SELECT flight_id FROM flight_has_employee) ORDER BY flight.date";
	public final static String FIND_ALL_FREE_EMPLOYEES = "SELECT * FROM employee WHERE id NOT IN (SELECT employee.id FROM employee JOIN flight_has_employee ON flight_has_employee.employee_id = employee.id JOIN flight ON flight_has_employee.flight_id = flight.id WHERE flight.date=?)";
	public final static String FIND_ALL_FREE_EMPLOYEES_WHITH_FLIGHT = "SELECT * FROM employee WHERE id NOT IN (SELECT employee.id FROM employee JOIN flight_has_employee ON flight_has_employee.employee_id = employee.id JOIN flight ON flight_has_employee.flight_id = flight.id WHERE flight.date=? AND flight.id<>?)";

	public final static String FIND_FROM_PLACE_TO_PLACE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE from_place=? AND to_place=? ORDER BY flight.date, flight.time";
	public final static String FIND_FROM_PLACE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE from_place=? ORDER BY flight.date, flight.time";
	public final static String FIND_TO_PLACE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE to_place=? ORDER BY flight.date, flight.time";
	public final static String FIND_BY_DATE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE date=? ORDER BY flight.date, flight.time";
	public final static String FIND_FROM_PLACE_BY_DATE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE from_place=? AND date=? ORDER BY flight.date, flight.time";
	public final static String FIND_TO_PLACE_BY_DATE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE to_place=? AND date=? ORDER BY flight.date, flight.time";
	public final static String FIND_FROM_PLACE_TO_PLACE_BY_DATE = "SELECT flight.id, flight.date, flight.time, flight.filling, flight_info.flight_number, flight_info.from_place, flight_info.to_place, flight_info.starting_price, plane.number_of_seats FROM flight JOIN flight_info ON flight_info.id = flight.flight_info_id JOIN plane ON plane.id = flight_info.plane_id WHERE from_place=? AND to_place=? AND date=? ORDER BY flight.date, flight.time";

	public final static String UPDATE_PLAIN = "UPDATE plane SET plane_type=?, number_of_seats=?, number_of_pilots=?, number_of_stewardesses=? WHERE id=?";
	public final static String UPDATE_EMAIL_PASSWORD = "UPDATE user_info SET password=?, email=? WHERE id=?";
	
	public final static String UPDATE_EMPLOYEE = "UPDATE employee SET name=?, role=? WHERE id=?";
	public final static String UPDATE_FLIGHT_INFO = "UPDATE flight_info SET flight_number=?, from_place=?, to_place=?, flight_time=?, starting_price=?, plane_id=? WHERE id=?";
	public final static String UPDATE_FLIGHT = "UPDATE flight SET date=?, time=?, filling=?, flight_info_id=? WHERE id=?";
	public final static String UPDATE_BANK_ACCOUNT_UP = "UPDATE bank_account SET balance=balance+? WHERE id=?";
	public final static String UPDATE_BANK_ACCOUNT_DOWN = "UPDATE bank_account SET balance=balance-? WHERE id=?";
	public final static String UPDATE_PRIORITY_REGISTRATION = "UPDATE ticket SET priority_registration=?, final_price=final_price+? WHERE id=?";
	public final static String UPDATE_PRIORITY_BOARDING = "UPDATE ticket SET priority_boarding=?, final_price=final_price+? WHERE id=?";
	public final static String UPDATE_LUGGAGE = "UPDATE ticket SET luggage=?, final_price=final_price+? WHERE id=?";
	public final static String UPDATE_TICKET_NUMBER = "UPDATE ticket SET ticket_number=? WHERE id=?";
	public final static String UPDATE_FILLING = "UPDATE flight SET filling=filling+? WHERE id=?";

	public final static String DEL_PLAIN = "DELETE FROM plane WHERE id=?";
	public final static String DEL_USER = "DELETE FROM user_info WHERE id=?";
	public final static String DEL_EMPLOYEE = "DELETE FROM employee WHERE id=?";
	public final static String DEL_FLIGHT_INFO = "DELETE FROM flight_info WHERE id=?";
	public final static String DEL_FLIGHT = "DELETE FROM flight WHERE id=?";
	public final static String DEL_FLIGHT_WITH_EMPLOYEE = "DELETE FROM flight_has_employee WHERE flight_id=?";
	public final static String UNBLOCK_SELECTED_TICKET = "DELETE FROM ticket WHERE client_id=? AND ticket_number IS NULL";
	public final static String DEL_TICKET = "DELETE FROM ticket WHERE id=?";

}