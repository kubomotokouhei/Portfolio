package model;

import dao.AccountDAO;

public class UpdateAccountLogic {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.updateAccount(account);
	}
}
