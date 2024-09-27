package model;

import dao.AccountDAO;

public class GetAccountLogic {
	public Account execute(Login login) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(login);
		return account;
	}
}
