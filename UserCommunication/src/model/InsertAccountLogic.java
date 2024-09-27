package model;

import dao.AccountDAO;

public class InsertAccountLogic {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.insertAccount(account);
	}
}