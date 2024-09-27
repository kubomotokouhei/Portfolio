package model;

public class Login {
	private String user_id;		//ユーザーID
	private String password; 	//パスワード

	public Login() {}
	public Login(String user_id) {
		this.user_id = user_id;
	}
	public Login(String user_id, String password) {
		this.user_id = user_id;
		this.password = password;
	}


	public String getUser_id() { return user_id; }
	public String getPassword() { return password; }
}