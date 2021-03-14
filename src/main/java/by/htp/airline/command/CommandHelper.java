package by.htp.airline.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.airline.command.impl.LoginationCommand;
import by.htp.airline.command.impl.AddPlaneCommand;
import by.htp.airline.command.impl.BackToBuyPageCommand;
import by.htp.airline.command.impl.BlockFlightHasEmployeeCommand;
import by.htp.airline.command.impl.ConfirmPaymentCommand;
import by.htp.airline.command.impl.DeleteCrewCommand;
import by.htp.airline.command.impl.DeleteEmployeeCommand;
import by.htp.airline.command.impl.DeleteFlightCommand;
import by.htp.airline.command.impl.DeleteFlightInfoCommand;
import by.htp.airline.command.impl.DeletePlaneCommand;
import by.htp.airline.command.impl.DeleteTicketCommand;
import by.htp.airline.command.impl.EditCrewCommand;
import by.htp.airline.command.impl.EditEmailPasswordCommand;
import by.htp.airline.command.impl.EditEmployeeCommand;
import by.htp.airline.command.impl.EditFlightCommand;
import by.htp.airline.command.impl.EditPlaneCommand;
import by.htp.airline.command.impl.EditeFlightInfoCommand;
import by.htp.airline.command.impl.GoToAdminPageCommand;
import by.htp.airline.command.impl.GoToBuyPageCommand;
import by.htp.airline.command.impl.AddCrewsCommand;
import by.htp.airline.command.impl.AddEmployeeCommand;
import by.htp.airline.command.impl.AddFlightCommand;
import by.htp.airline.command.impl.AddFlightInfoCommand;
import by.htp.airline.command.impl.AddMoneyCommand;
import by.htp.airline.command.impl.GoToClientPageCommand;
import by.htp.airline.command.impl.GoToErrorPageCommand;
import by.htp.airline.command.impl.GoToIndexNoSessionCommand;
import by.htp.airline.command.impl.GoToIndexPageCommand;
import by.htp.airline.command.impl.GoToPayPageCommand;
import by.htp.airline.command.impl.GoToRegistrationPageCommand;
import by.htp.airline.command.impl.GoToSearchingResultsPageCommand;
import by.htp.airline.command.impl.LocalCommand;
import by.htp.airline.command.impl.NoSuchCommand;
import by.htp.airline.command.impl.RegistrationCommand;
import by.htp.airline.command.impl.SelectTicketParametersCommand;
import by.htp.airline.command.impl.UnBlockAddCrewsCommand;
import by.htp.airline.command.impl.UnBlockEditCrewsCommand;
import by.htp.airline.command.impl.UnBlockEditEmailPasswordCommand;
import by.htp.airline.command.impl.UnBlockEditEmployeeCommand;
import by.htp.airline.command.impl.UnBlockEditFlightCommand;
import by.htp.airline.command.impl.UnBlockEditFlightInfoCommand;
import by.htp.airline.command.impl.UnBlockEditPlaneCommand;
import by.htp.airline.command.impl.ViewAllAvailableFlightsCommand;
import by.htp.airline.command.impl.ViewAllEmplyeeCommand;
import by.htp.airline.command.impl.ViewAllFlightInfoCommand;
import by.htp.airline.command.impl.ViewAllFlightWithInfoCommand;
import by.htp.airline.command.impl.ViewAllPlaneCommand;
import by.htp.airline.command.impl.ViewAllUsersCommand;
import by.htp.airline.command.impl.ViewAllCrewsCommand;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();

	public static CommandHelper getInstance() {
		return instance;
	}

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandHelper() {
		commands.put(CommandName.LOGINATION, new LoginationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.EDIT_EMAIL_PASSWORD, new EditEmailPasswordCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CommandName.GO_TO_CLIENT_PAGE, new GoToClientPageCommand());
		commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPageCommand());
		commands.put(CommandName.GO_TO_INDEX_NO_SESSION_PAGE, new GoToIndexNoSessionCommand());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.UNBLOCK_EDIT_EMAIL_PASSWORD, new UnBlockEditEmailPasswordCommand());
		commands.put(CommandName.ADD_PLANE, new AddPlaneCommand());
		commands.put(CommandName.VIEW_ALL_PLANE, new ViewAllPlaneCommand());
		commands.put(CommandName.ADD_FLIGHT_INFO, new AddFlightInfoCommand());
		commands.put(CommandName.VIEW_ALL_FLIGHT_INFO, new ViewAllFlightInfoCommand());
		commands.put(CommandName.ADD_EMPLOYEE, new AddEmployeeCommand());
		commands.put(CommandName.VIEW_ALL_EMPLOYEE, new ViewAllEmplyeeCommand());
		commands.put(CommandName.ADD_FLIGHT, new AddFlightCommand());
		commands.put(CommandName.VIEW_ALL_FLIGHT_WITH_INFO, new ViewAllFlightWithInfoCommand());
		commands.put(CommandName.SEARCHING_RESULTS, new GoToSearchingResultsPageCommand());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
		commands.put(CommandName.EDIT_PLANE, new EditPlaneCommand());
		commands.put(CommandName.DELETE_PLANE, new DeletePlaneCommand());
		commands.put(CommandName.UNBLOCK_EDIT_PLANE, new UnBlockEditPlaneCommand());
		commands.put(CommandName.EDIT_EMPLOYEE, new EditEmployeeCommand());
		commands.put(CommandName.DELETE_EMPLOYEE, new DeleteEmployeeCommand());
		commands.put(CommandName.UNBLOCK_EDIT_EMPLOYEE, new UnBlockEditEmployeeCommand());
		commands.put(CommandName.EDIT_FLIGHT_INFO, new EditeFlightInfoCommand());
		commands.put(CommandName.DELETE_FLIGHT_INFO, new DeleteFlightInfoCommand());
		commands.put(CommandName.UNBLOCK_EDIT_FLIGHT_INFO, new UnBlockEditFlightInfoCommand());
		commands.put(CommandName.EDIT_FLIGHTS, new EditFlightCommand());
		commands.put(CommandName.UNBLOCK_EDIT_FLIGHT, new UnBlockEditFlightCommand());
		commands.put(CommandName.DELETE_FLIGHT, new DeleteFlightCommand());
		commands.put(CommandName.VIEW_ALL_CREWS, new ViewAllCrewsCommand());
		commands.put(CommandName.UNBLOCK_ADD_CREW, new UnBlockAddCrewsCommand());
		commands.put(CommandName.ADD_CREW, new AddCrewsCommand());
		commands.put(CommandName.UNBLOCK_EDIT_CREW, new UnBlockEditCrewsCommand());
		commands.put(CommandName.DELETE_CREW, new DeleteCrewCommand());
		commands.put(CommandName.EDIT_CREW, new EditCrewCommand());
		commands.put(CommandName.BLOCK_FLIGHT_HAS_EMPLOYEE, new BlockFlightHasEmployeeCommand());
		commands.put(CommandName.ADD_MONEY, new AddMoneyCommand());
		commands.put(CommandName.VIEW_ALL_AVAILABLE_FLIGHTS, new ViewAllAvailableFlightsCommand());
		commands.put(CommandName.GO_TO_BUY_PAGE, new GoToBuyPageCommand());
		commands.put(CommandName.SELECT_TICKET_PARAMETERS, new SelectTicketParametersCommand());
		commands.put(CommandName.GO_TO_PAY_PAGE, new GoToPayPageCommand());
		commands.put(CommandName.CONFIRM_PAYMENT, new ConfirmPaymentCommand());
		commands.put(CommandName.BACK_TO_BUY_PAGE, new BackToBuyPageCommand());
		commands.put(CommandName.DELETE_TICKET, new DeleteTicketCommand());
		commands.put(CommandName.VIEW_ALL_USERS, new ViewAllUsersCommand());
		commands.put(CommandName.LOCAL, new LocalCommand());

	}

	public Command getCommand(String commandName) {

		CommandName name;
		Command command;

		if (findInEnum(commandName)) {
			name = CommandName.valueOf(commandName.toUpperCase().replaceAll("-", "_"));
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;
	}

	private boolean findInEnum(String commandName) {

		for (CommandName n : CommandName.values()) {
			if (n.name().equals(commandName.toUpperCase().replaceAll("-", "_"))) {
				return true;
			}
		}
		return false;
	}
}
