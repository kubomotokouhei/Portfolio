package model;

import dao.RoomDAO;

public class UpdateRoomListLogic {

public boolean execute(Room room) {
	RoomDAO dao = new RoomDAO();

	return dao.updateRoom(room);
}
}
