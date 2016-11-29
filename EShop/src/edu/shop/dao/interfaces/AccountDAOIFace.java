package edu.shop.dao.interfaces;

import java.util.Date;
import java.util.List;

import edu.shop.entity.Account;


public interface AccountDAOIFace {
	List<Account> getAccounts();
	Account getByID(long ID);
	Account getByUserID(long userID);
	Account createAccount(Account account);
	Account updateBalance(long id, double ammount);
	void updateLastLoginDate(long id, Date today);

}
