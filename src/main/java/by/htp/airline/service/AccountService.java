package by.htp.airline.service;

import by.htp.airline.entity.Account;
import by.htp.airline.service.impl.ServiceException;

public interface AccountService {

	Account add(String login) throws ServiceException;

	Account findAccountByUserLogin(String login) throws ServiceException;

	Account addMoney(int accountId, double addMoney) throws ServiceException;

}
