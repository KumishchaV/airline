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

public class TestDAOAdd {
	
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
	public void addNewUser() throws DAOException {

		User user;

		User info = new User();
		info.setName("Ihar");
		info.setSurname("Akinfeev");
		info.setEmail("ihar@gmail.com");
		info.setLogin("zzz");  
		info.setPassword("1234");
		
		user = userDAO.registration(info.getName(), info.getSurname(), info.getEmail(), info.getLogin(), info.getPassword(), "client");

		Assert.assertEquals(user.getName(), info.getName());

	}
	
}
