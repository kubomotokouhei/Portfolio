package model;

import java.io.Serializable;

public class Message implements Serializable{
	private int message_id;
	private int room_id;
	private String user_id;
	private String message;
	private String creation_day;
	private String user_name; //message項目にはないが、表示に必要なため、beansには追加
							//messageDAOのfindAll()にてleft joinを使用してset
							//表示用コンストラクタとgetter追加

	public Message() {}

	//データベース書き込み用（DAO用）のコンストラクタmessage_idが不要（自動採番）
	public Message(int room_id,String user_id,String message) {
		this.room_id = room_id;
		this.user_id = user_id;
		this.message = message;
	}

	//表示用のコンストラクタ
	public Message(int message_id,int room_id,String user_id,String message,String creation_day,String user_name) {
		this.message_id = message_id;
		this.room_id = room_id;
		this.user_id = user_id;
		this.message = message;
		this.creation_day = creation_day;
		this.user_name = user_name;
	}

	//getter
	public int getMessage_id() {
		return message_id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getMessage() {
		return message;
	}
	public String Creation_day() {
		return creation_day;
	}
	public String getUser_name() {
		return user_name;
	}


}
