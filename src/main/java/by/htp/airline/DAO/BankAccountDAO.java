package by.htp.airline.DAO;

import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.entity.BankAccount;

public interface BankAccountDAO {

	BankAccount add() throws DAOException;
	
	BankAccount findBankAccountById(int bankAccountId) throws DAOException;
	
	void addMoney(int bankAccountId, double addMoney) throws DAOException;
	
	void deductMoney(int bankAccountId, double deductMoney) throws DAOException;
}
