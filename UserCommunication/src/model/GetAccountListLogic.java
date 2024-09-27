package model;

import java.util.List;

import dao.AccountDAO;

public class GetAccountListLogic {
	public static List<Account> execute() {
		AccountDAO dao = new AccountDAO();
		List<Account> accountList = dao.findALL();
		return accountList;
	}
}
