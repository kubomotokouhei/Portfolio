package model;

import java.io.Serializable;

public class Account implements Serializable{
	private String user_id;		//ユーザーID
	private String user_name; 	//ハンドルネーム
	private String password; 	//パスワード
	private String mailadress; 	//メールアドレス

	public Account() {}
	public Account(String user_id) {
		this.user_id = user_id;
	}
	public Account(String user_id, String user_name) {
		this.user_id = user_id;
		this.user_name = user_name;
	}
	public Account(String user_id, String user_name, String password,
			String mailadress) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.mailadress = mailadress;
	}

	public String getUser_id() { return user_id; }
	public String getUser_name() { return user_name; }
	public String getPassword() { return password; }
	public String getMailadress() { return mailadress; }
}
