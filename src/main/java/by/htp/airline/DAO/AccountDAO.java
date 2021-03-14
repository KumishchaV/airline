package by.htp.airline.DAO;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.Account;

public interface AccountDAO {
	
	Account add(int bankAccountId, int userId) throws DAOException;
	
	Account findAccountByUserId(int userId) throws DAOException;

}
