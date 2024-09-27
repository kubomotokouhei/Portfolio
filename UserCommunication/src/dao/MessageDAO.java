package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import model.Message;
import model.Room;

@WebServlet("/MessageDAO")
public class MessageDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/communication?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	//全レコードを取得する	findAllメソッド
	public List<Message> findAll(Room room) {
		List<Message> messageList = new ArrayList<>();
		//↑型

		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Connection DBMSへの接続切断を行う/DriverManager DBMSへの接続準備を行う
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文
			String sql = "SELECT * FROM MESSAGE LEFT JOIN ACCOUNT ON MESSAGE.USER_ID=ACCOUNT.USER_ID "
					+ "WHERE ROOM_ID = ? ORDER BY MESSAGE_ID ASC";
			//String sql = "SELECT * FROM MESSAGE LEFT JOIN ACCOUNT ON MESSAGE.USER_ID=ACCOUNT.USER_ID "
			//		+ "WHERE ROOM_ID = " + room.getRoom_id() + " ORDER BY MESSAGE_ID ASC";
			//WHERE ROOM_ID = ? 追記
			// MESSAGE_ID, ROOM_ID, MESSAGE.USER_ID, MESSAGE, CREATION_DAY, ACCOUNT.USER_ID, USER_NAME, PASSWORD, MAILADRESS;
			// "SELECT MESSAGE_ID, ROOM_ID, MESSAGE.USER_ID, USER_NAME, MESSAGE, CREATION_DAY FROM MESSAGE LEFT JOIN ACCO
			//PrepardStatement SQL文の送信を行う

			PreparedStatement pStmt = conn.prepareStatement(sql);

			//sql構文の中にある？の数だけ書く。？に代入するための文
			pStmt.setInt(1, room.getRoom_id());

			//System.out.println("room.getRoom_id() :" + room.getRoom_id());
			System.out.println(sql + ": " + room.getRoom_id());

			//ResultSet DBMS（データベース）から検索結果を受け取る
			ResultSet rs = pStmt.executeQuery();

			//ArrayListに格納
			while (rs.next()) {
				int message_id = rs.getInt("MESSAGE_ID");
				int room_id = rs.getInt("ROOM_ID");
				String user_name = rs.getString("USER_NAME");
				String user_id = rs.getString("USER_ID");
				String message = rs.getString("MESSAGE");
				String creation_day = rs.getString("CREATION_DAY");

				//if(room_id == 1) {
				//javabeansを使って、messageList（⇛ArrayListに格納することに繋がる）情報を追加
				Message ms = new Message(message_id, room_id, user_id, message, creation_day, user_name);
				messageList.add(ms);
				/*System.out.println("message_id :" + message_id);
				System.out.println("room_id : " + room_id);
				System.out.println("user_id :" + user_id);
				System.out.println("message :" + message);
				System.out.println("creation_day :" + creation_day);*/
				//}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return messageList;
	}

	//レコードを追加する createメゾット
	public boolean create(Message message, Room room) {

		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//INSERT文
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO MESSAGE(ROOM_ID,USER_ID,MESSAGE) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			System.out.println("sql : " + sql);

			pStmt.setInt(1, room.getRoom_id());
			pStmt.setString(2, message.getUser_id());
			pStmt.setString(3, message.getMessage());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
