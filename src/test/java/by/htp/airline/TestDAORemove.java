package by.htp.airline;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.airline.DAO.UserDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.User;

public class TestDAORemove {
	
	private static final ConnectionPool cp = ConnectionPool.getInstance();
	private static final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

	@BeforeClass
	public static void take() {
		cp.makeConnectionPool();
	}

	@AfterClass
	public static void close() {
		cp.closeConnectionInPool();
	}

	@Test
	public void deleteUser() throws DAOException {

		User user;
		String login;
		List<User> users;
		
		int actualSize;
		int expectedSize;
		
		login = "zzz";

		users = userDAO.viewAllUsers();
		actualSize = users.size();
		
		user = userDAO.findUserByLogin(login);
		userDAO.delete(user.getUserId());
		
		users = userDAO.viewAllUsers();
		expectedSize = users.size() + 1;

		Assert.assertEquals(actualSize, expectedSize);
	}

}
