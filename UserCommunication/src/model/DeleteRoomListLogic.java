package model;

import dao.RoomDAO;

public class DeleteRoomListLogic {
	public boolean execute(Room room) {
		RoomDAO dao = new RoomDAO();

		return dao.deleteRoom(room);
	}
}
