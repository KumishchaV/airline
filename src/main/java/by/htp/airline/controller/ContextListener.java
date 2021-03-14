package by.htp.airline.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import by.htp.airline.DAO.pool.ConnectionPool;
@WebListener
public class ContextListener implements ServletContextListener {

	private static final ConnectionPool cp = ConnectionPool.getInstance();
	private static final Logger log = Logger.getLogger(ContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		BasicConfigurator.configure();

		cp.makeConnectionPool();
		log.info("The ConnectionPool is initialized");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		cp.closeConnectionInPool();
		log.info("The ConnectionPool is closed");

	}

}
