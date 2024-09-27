package model;

import dao.AccountDAO;

public class AccountNameCheck {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.findByNameCheck(account);
	}
}