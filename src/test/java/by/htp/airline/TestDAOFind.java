package by.htp.airline;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.airline.DAO.UserDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.DAO.pool.ConnectionPool;
import by.htp.airline.entity.User;

public class TestDAOFind {

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
	public void findUserByLogin() throws DAOException {

		User user;
		String login;
		login = "zzz";

		user = userDAO.findUserByLogin(login);

		Assert.assertEquals(user.getLogin(), login);

	}
}
