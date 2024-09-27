package model;

import dao.RoomDAO;


public class InsertRoomListLogic {
	public void execute(Room room) {
		RoomDAO dao = new RoomDAO();
		dao.insertRoom(room);
	}
}