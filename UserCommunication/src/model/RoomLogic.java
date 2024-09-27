package model;

import java.util.List;

import dao.RoomDAO;

public class RoomLogic {
	public  List<Room> execute(Login login) {
		RoomDAO dao = new RoomDAO();
		List<Room> roomList = dao.findAll(login);
		return roomList;
	}
}
