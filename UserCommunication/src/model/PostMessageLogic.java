package model;
//メッセージの投稿に関する処理をするモデル


import dao.MessageDAO;

public class PostMessageLogic{
	public void execute(Message ms,Room room) {
		MessageDAO dao = new MessageDAO();
		dao.create(ms,room);
	}

}