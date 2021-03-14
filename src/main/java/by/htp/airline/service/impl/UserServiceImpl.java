package by.htp.airline.service.impl;

import java.util.List;

import by.htp.airline.DAO.UserDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.User;
import by.htp.airline.service.UserService;

public class UserServiceImpl implements UserService {

	private static final UserDataValidator validator = UserDataValidator.getInstance();

	private static final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

	@Override
	public User findUserByLogin(String login) throws ServiceException {
		User user;
		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User logination(String login, String password) throws ServiceException {
		User user;
		String newPassword;

		try {
			newPassword = hashPassword(password);
			user = userDAO.logination(login, newPassword);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User registration(String login, String password, String email, String name, String surname)
			throws ServiceException, DataValidatorServiceException {

		String newPassword;
		String role;
		User user = new User();

		if (!validator.checkEmailPassword(email, password)) {
			throw new DataValidatorServiceException();
		}

		if (viewAllUsers().size() != 0) {
			role = "client";
		} else {
			role = "admin";
		}

		newPassword = hashPassword(password);

		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(newPassword);
		user.setRole(role);

		try {
			user = userDAO.registration(name, surname, email, login, newPassword, role);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	private String hashPassword(String password) {

		String newPassword = password + "1";
		return newPassword;
	}

	@Override
	public List<User> viewAllUsers() throws ServiceException {

		List<User> users;

		try {
			users = userDAO.viewAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return users;
	}

	@Override
	public User editEmailPassword(int userId, String newPassword, String newEmail)
			throws ServiceException, DataValidatorServiceException {

		User user = new User();
		String hashPassword;

		if (!validator.checkEmailPassword(newEmail, newPassword)) {
			throw new DataValidatorServiceException();
		}
		
		hashPassword = hashPassword(newPassword);
		
		try {
			userDAO.EditEmailPassword(userId, hashPassword, newEmail);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public User hashEnterPassword(String password) throws ServiceException {

		User user = new User();
		String hashPassword;

		hashPassword = hashPassword(password);

		user.setPassword(hashPassword);

		return user;
	}
}