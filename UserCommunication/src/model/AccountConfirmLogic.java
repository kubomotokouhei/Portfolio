package model;

import dao.AccountDAO;

public class AccountConfirmLogic {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.findByCheck(account);
	}
}
