package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountDAO {
	//データベース接続の設定
	private final String JDBC_URL =
		"jdbc:mysql://localhost:3306/communication?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<Account> findALL() {
		List<Account> accountList = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;

		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
				("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection
				(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT * FROM ACCOUNT";
			stmt = conn.prepareStatement(sql);

			//SELECT文を実行し、結果表を取得
			rs = stmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				String user_id 	= rs.getString("USER_ID");
				String user_name 	= rs.getString("USER_NAME");
				String password 	= rs.getString("PASSWORD");
				String mailadress 	= rs.getString("MAILADRESS");
				Account account = new Account(user_id, user_name, password, mailadress);
				accountList.add(account);
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;

		}
		return accountList;
	}

	public Account findByLogin(Login login) {
		Account account = null;
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
				("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection
				(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT * FROM ACCOUNT"
					+ " WHERE USER_ID = ? AND PASSWORD = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, login.getUser_id());
			stmt.setString(2, login.getPassword());

			//SELECT文を実行し、結果表を取得
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//ユーザーが存在したらデータを取得
				//そのユーザーを表すAccountインスタンスを生成
				String user_id 		= rs.getString("USER_ID");
				String user_name 	= rs.getString("USER_NAME");
				String password 	= rs.getString("PASSWORD");
				String mailadress 	= rs.getString("MAILADRESS");
				account = new Account(user_id, user_name, password, mailadress);
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;

		}
		return account;
	}

	public String findByMsgName(String user_id) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
				("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection
				(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT * FROM ACCOUNT"
					+ " WHERE USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_id);

			//SELECT文を実行し、結果表を取得
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//ユーザーが存在したらデータを取得
				String user_name = rs.getString("USER_NAME");
				return user_name;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public boolean findByCheck(Account account) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
				("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection
				(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID  = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getUser_id());

			//SELECT文を実行し、結果表を取得
			ResultSet result = stmt.executeQuery();

			//データ存在チェック
			if (result.next()) {
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		//ユーザーが存在しなかったらOK
		return false;
	}

	public boolean insertAccount(Account account) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備
			String sql = "INSERT INTO ACCOUNT SELECT ?, ?, ?, ? WHERE NOT EXISTS(SELECT 1 FROM ACCOUNT WHERE USER_ID  = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			//INSERT文の「?」値を設定しSQL構文を完成させる
			stmt.setString(1, account.getUser_id());
			stmt.setString(2, account.getUser_name());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getMailadress());
			stmt.setString(5, account.getUser_id());

	        //INSERT文を実行(resultには追加された桁数が入る)
			int result = stmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateAccount(Account account) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//UPDATE文の準備
			String sql = "UPDATE ACCOUNT SET USER_ID=?, USER_NAME=?, PASSWORD=?, MAILADRESS=? WHERE USER_ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			//UPDATE文の「?」値を設定しSQL構文を完成させる
			stmt.setString(1, account.getUser_id());
			stmt.setString(2, account.getUser_name());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getMailadress());
			stmt.setString(5, account.getUser_id());

	        //UPDATE文を実行(resultには更新された桁数が入る)
			int result = stmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteAccount(Account account) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//DELETE文の準備
			String sql = "DELETE FROM ACCOUNT WHERE USER_ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			//DELETE文の「?」値を設定しSQL構文を完成させる
			stmt.setString(1, account.getUser_id());

	        //DELETE文を実行(resultには更新された桁数が入る)
			int result = stmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean findByNameCheck(Account account) {
		//JDBCドライバーを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException
				("JDBCドライバーを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection
				(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文を準備
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID != ? AND USER_NAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getUser_id());
			stmt.setString(2, account.getUser_name());

			//SELECT文を実行し、結果表を取得
			ResultSet result = stmt.executeQuery();

			//データ存在チェック
			if (result.next()) {
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		//ユーザーが存在しなかったらOK
		return false;
	}
}
