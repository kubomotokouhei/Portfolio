package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Login;
import model.Room;

public class RoomDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/COMMUNICATION?serverTimezone=JST";

	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<Room> findAll(Login login) {
		List<Room> roomList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM ROOM WHERE MGR_ID = ? OR (ENTRY = 0 OR USER_ID_1 = ?  OR USER_ID_2 = ? OR USER_ID_3 = ? OR USER_ID_4 = ? OR USER_ID_5 = ?) ORDER BY ROOM_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, login.getUser_id());
			pStmt.setString(2, login.getUser_id());
			pStmt.setString(3, login.getUser_id());
			pStmt.setString(4, login.getUser_id());
			pStmt.setString(5, login.getUser_id());
			pStmt.setString(6, login.getUser_id());

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int room_id = rs.getInt("ROOM_ID");
				String mgr_id = rs.getString("MGR_ID");
				String room_name = rs.getString("ROOM_NAME");
				String room_intro = rs.getString("ROOM_INTRO");
				boolean entry = rs.getBoolean("ENTRY");
				String user_id_1 = rs.getString("USER_ID_1");
				String user_id_2 = rs.getString("USER_ID_2");
				String user_id_3 = rs.getString("USER_ID_3");
				String user_id_4 = rs.getString("USER_ID_4");
				String user_id_5 = rs.getString("USER_ID_5");
			    String creation_day = rs.getString("CREATION_DAY");;

				Room room = new Room(room_id,mgr_id,room_name,room_intro, entry, user_id_1, user_id_2, user_id_3, user_id_4, user_id_5, creation_day);
				roomList.add(room);
			}

		}
		catch(SQLException e) {
				e.printStackTrace();
				return null;
		}
		return roomList;
	}


	public boolean insertRoom(Room room) {
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
			String sql = "INSERT INTO ROOM (MGR_ID,ROOM_NAME,ROOM_INTRO,ENTRY,USER_ID_1,USER_ID_2,USER_ID_3 ,USER_ID_4,USER_ID_5 )VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			//INSERT文の「?」値を設定しSQL構文を完成させる

			stmt.setString(1, room.getMgr_id());
			stmt.setString(2, room.getRoom_name());
			stmt.setString(3, room.getRoom_intro());
			stmt.setBoolean(4, room.isEntry());
			stmt.setString(5, room.getUser_id_1());
			stmt.setString(6, room.getUser_id_2());
			stmt.setString(7, room.getUser_id_3());
			stmt.setString(8, room.getUser_id_4());
			stmt.setString(9, room.getUser_id_5());


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

	public boolean deleteRoom(Room room) {
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
			String sql = "DELETE FROM ROOM WHERE ROOM_ID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			//INSERT文の「?」値を設定しSQL構文を完成させる

			stmt.setInt(1, room.getRoom_id());



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
	public boolean updateRoom(Room room) {
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
			String sql ="UPDATE ROOM SET mgr_id= ?, room_name = ?, room_intro =?, entry=?, user_id_1=?, user_id_2=?, user_id_3=?, user_id_4=?, user_id_5=? WHERE room_id=?";


			PreparedStatement stmt = conn.prepareStatement(sql);

			//INSERT文の「?」値を設定しSQL構文を完成させる

			stmt.setString(1, room.getMgr_id());
			stmt.setString(2, room.getRoom_name());
			stmt.setString(3, room.getRoom_intro());
			stmt.setBoolean(4, room.isEntry());
			stmt.setString(5, room.getUser_id_1());
			stmt.setString(6, room.getUser_id_2());
			stmt.setString(7, room.getUser_id_3());
			stmt.setString(8, room.getUser_id_4());
			stmt.setString(9, room.getUser_id_5());
			stmt.setInt(10, room.getRoom_id());
			System.out.println(room.getRoom_id());

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



}