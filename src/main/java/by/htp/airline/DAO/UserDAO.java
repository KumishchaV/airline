package by.htp.airline.DAO;

import java.util.List;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.User;

public interface UserDAO {

	User logination(String login, String password) throws DAOException;

	User registration(String name, String surname, String email, String login, String password, String role) throws DAOException;

	User findUserByLogin(String login) throws DAOException;
	
	List<User> viewAllUsers() throws DAOException;
	
	void EditEmailPassword(int userId, String newPassword, String newEmail) throws DAOException;
	
	void delete(int userId) throws DAOException;

}
