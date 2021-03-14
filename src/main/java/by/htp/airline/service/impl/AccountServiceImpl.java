package by.htp.airline.service.impl;

import by.htp.airline.DAO.AccountDAO;
import by.htp.airline.DAO.BankAccountDAO;
import by.htp.airline.DAO.UserDAO;
import by.htp.airline.DAO.impl.DAOException;
import by.htp.airline.DAO.impl.DAOProvider;
import by.htp.airline.entity.Account;
import by.htp.airline.entity.BankAccount;
import by.htp.airline.entity.User;
import by.htp.airline.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private static final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
	private static final BankAccountDAO bankAccountDAO = DAOProvider.getInstance().getBankAccountDAO();
	private static final AccountDAO accountDAO = DAOProvider.getInstance().getAccountDAO();

	@Override
	public Account add(String login) throws ServiceException {

		Account account = new Account();
		User user = new User();
		BankAccount bankAccount = new BankAccount();

		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e1) {
			throw new ServiceException(e1);
		}
		
		try {
			bankAccount = bankAccountDAO.add();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		try {
			account = accountDAO.add(bankAccount.getBankAccountId(), user.getUserId());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		account.setAccountId(account.getAccountId());
		account.setBankAccountId(bankAccount.getBankAccountId());
		account.setBalance(bankAccount.getValue());
		account.setUserId(user.getUserId());
		account.setName(user.getName());
		account.setSurname(user.getSurname());
		account.setLogin(user.getLogin());
		account.setPassword(user.getPassword());
		account.setEmail(user.getEmail());
		account.setRole(user.getRole());

		return account;
	}

	@Override
	public Account findAccountByUserLogin(String login) throws ServiceException {
		
		User user = new User();
		Account account = new Account();
		BankAccount bankAccount = new BankAccount();
		
		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e1) {
			throw new ServiceException(e1);
		}
		
		try {
			account = accountDAO.findAccountByUserId(user.getUserId());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		try {
			bankAccount = bankAccountDAO.findBankAccountById(account.getBankAccountId());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		account.setAccountId(account.getAccountId());
		account.setBankAccountId(bankAccount.getBankAccountId());
		account.setBalance(bankAccount.getValue());
		account.setUserId(user.getUserId());
		account.setName(user.getName());
		account.setSurname(user.getSurname());
		account.setLogin(user.getLogin());
		account.setPassword(user.getPassword());
		account.setEmail(user.getEmail());
		account.setRole(user.getRole());

		return account;
	}

	@Override
	public Account addMoney(int userId, double addMoney) throws ServiceException {
		
		Account account = new Account();
		BankAccount bankAccount = new BankAccount();
		
		try {
			account = accountDAO.findAccountByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		try {
			bankAccountDAO.addMoney(account.getBankAccountId(), addMoney);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		account.setBalance(bankAccount.getValue());
		
		return account;
	}

}
