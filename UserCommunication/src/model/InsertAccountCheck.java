package model;

import dao.AccountDAO;

public class InsertAccountCheck {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.findByCheck(account);
	}
}
