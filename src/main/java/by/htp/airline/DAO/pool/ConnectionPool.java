package by.htp.airline.DAO.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConnectionPool {
	
	private static final Logger log = Logger.getLogger(ConnectionPool.class);

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	private BlockingQueue<Connection> connections;
	private static final ConnectionPool instanse = new ConnectionPool();

	private ConnectionPool(){
		//makeConnectionPool();
	}

	public void makeConnectionPool() throws ConnectionPoolException{

		DBResourceManager dBResourceManager = DBResourceManager.getInstance();
		driverName = dBResourceManager.getValue(DBParameter.DB_DRIVER);
		url = dBResourceManager.getValue(DBParameter.DB_URL);
		user = dBResourceManager.getValue(DBParameter.DB_USER);
		password = dBResourceManager.getValue(DBParameter.DB_PASSWORD);

		try {
			poolSize = Integer.parseInt(dBResourceManager.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			log.log(Level.ERROR, "NumberFormatException", e);
			poolSize = 5;
		}

		try {
			Class.forName(driverName);
			connections = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection con = DriverManager.getConnection(url, user, password);
				connections.add(con);
			}

		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException(e);
		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		}

		log.info("connection is made");
	}

	public Connection take() throws InterruptedException {
		return connections.take();
	}

	public void release(Connection con) throws ConnectionPoolException{
		try {
			if (con != null) {
				con.setAutoCommit(true);
				connections.add(con);
			} else {
				log.info("connection == null");
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		}
	}

	public static ConnectionPool getInstance() {
		return instanse;
	}

	public void closeConnectionInPool() throws ConnectionPoolException{

		Iterator<Connection> iter = connections.iterator();

		while (iter.hasNext()) {
			try {

				iter.next().close();
			} catch (SQLException e) {
				throw new ConnectionPoolException(e);
			}
		}
		log.info("connection is closed");
	}
}
