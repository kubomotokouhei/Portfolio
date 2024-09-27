package model;

import java.io.Serializable;

public class Room implements Serializable{
	private int room_id;
	private String mgr_id;
	private String room_name;
	private String room_intro;
	private boolean entry;
	private String user_id_1;
	private String user_id_2;
	private String user_id_3;
	private String user_id_4;
	private String user_id_5;
	private String creation_day;

	public Room() {}
	public Room(int room_id, String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2, String user_id_3, String user_id_4, String user_id_5,
					String creation_day) {
		this.room_id = room_id;
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.user_id_3 = user_id_3;
		this.user_id_4 = user_id_4;
		this.user_id_5 = user_id_5;
		this.creation_day = creation_day;

	}
	public Room(int room_id, String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2, String user_id_3, String user_id_4, String user_id_5
					) {
		this.room_id = room_id;
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.user_id_3 = user_id_3;
		this.user_id_4 = user_id_4;
		this.user_id_5 = user_id_5;


	}
	public Room(String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2, String user_id_3, String user_id_4, String user_id_5) {
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.user_id_3 = user_id_3;
		this.user_id_4 = user_id_4;
		this.user_id_5 = user_id_5;
	}
	public Room(String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2, String user_id_3, String user_id_4) {
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.user_id_3 = user_id_3;
		this.user_id_4 = user_id_4;

	}
	public Room(String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2, String user_id_3) {
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.user_id_3 = user_id_3;


	}
	public Room(String mgr_id, String room_name, String room_intro, boolean entry,
			String user_id_1, String user_id_2) {
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;



	}
	public Room(String mgr_id, String room_name, String room_intro, boolean entry
			) {
		this.mgr_id = mgr_id;
		this.room_name = room_name;
		this.room_intro = room_intro;
		this.entry = entry;

	}
	public Room(int room_id) {
		this.room_id = room_id;

	}


	public int getRoom_id() {
		return room_id;
	}

	public String getMgr_id() {
		return mgr_id;
	}

	public String getRoom_intro() {
		return room_intro;
	}

	public String getRoom_name() {
		return room_name;
	}

	public boolean isEntry() {
		return entry;
	}

	public String getUser_id_1() {
		return user_id_1;
	}

	public String getUser_id_2() {
		return user_id_2;
	}

	public String getUser_id_3() {
		return user_id_3;
	}

	public String getUser_id_5() {
		return user_id_5;
	}

	public String getUser_id_4() {
		return user_id_4;
	}

	public String getCreation_day() {
		return creation_day;
	}


}
