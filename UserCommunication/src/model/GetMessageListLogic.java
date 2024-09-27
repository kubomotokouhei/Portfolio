package model;
//メッセージの取得に関する処理をするモデル

import java.util.List;

import dao.MessageDAO;

public class GetMessageListLogic{
	public List<Message> execute(Room room){
		MessageDAO dao = new MessageDAO();
		List<Message> messageList = dao.findAll(room);
		return messageList;
	}

}