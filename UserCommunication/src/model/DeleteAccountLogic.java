package model;

import dao.AccountDAO;

public class DeleteAccountLogic {
	public boolean execute(Account account) {

		AccountDAO dao = new AccountDAO();
		return dao.deleteAccount(account);
	}
}
