package by.htp.airline.service;

import java.util.List;

import by.htp.airline.entity.User;
import by.htp.airline.service.impl.ServiceException;
import by.htp.airline.service.impl.DataValidatorServiceException;

public interface UserService {

	User findUserByLogin(String login) throws ServiceException;

	User logination(String login, String password) throws ServiceException;

	User registration(String login, String password, String email, String name, String surname)
			throws ServiceException, DataValidatorServiceException;

	List<User> viewAllUsers() throws ServiceException;

	User hashEnterPassword(String password) throws ServiceException;
	
	User editEmailPassword(int userId, String newPassword, String email)
			throws ServiceException, DataValidatorServiceException;
}
