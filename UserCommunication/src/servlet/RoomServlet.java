package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.GetAccountListLogic;
import model.GetAccountLogic;
import model.GetMessageListLogic;
import model.Login;
import model.Message;
import model.Room;
import model.RoomLogic;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");
		RoomLogic roomLogic = new RoomLogic();
		List<Room> roomList = roomLogic.execute(login);
		session.setAttribute("roomList", roomList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/room.jsp");
		dispatcher.forward(request,response);
			System.out.println("calls post()");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("aaaaa");

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		//command(ボタン名+行番号)をボタン名と行番号に分割し
		//commandにボタン名、リクエストパラメータの添え字(i)に行番号を代入
		String command = request.getParameter("command");
		String[] cmds = command.split(",");
		command = cmds[0];
		int i = Integer.parseInt(cmds[1]);

		String strRoom_id = request.getParameter("room_id[" + i + "]");
		String mgr_id = request.getParameter("mgr_id[" + i + "]");
		String room_name = request.getParameter("room_name[" + i + "]");
		String room_intro = request.getParameter("room_intro[" + i + "]");
		String strEntry = request.getParameter("entry[" + i + "]");
		String user_id_1 = request.getParameter("user_id_1[" + i + "]");
		String user_id_2 = request.getParameter("user_id_2[" + i + "]");
		String user_id_3 = request.getParameter("user_id_3[" + i + "]");
		String user_id_4 = request.getParameter("user_id_4[" + i + "]");
		String user_id_5 = request.getParameter("user_id_5[" + i + "]");
		String creation_day = request.getParameter("creation_day[" + i + "]");

		System.out.println("bbbb");
		int room_id = Integer.parseInt(strRoom_id);
		boolean entry = Boolean.valueOf(strEntry);

		Room room = new Room(room_id, mgr_id, room_name, room_intro, entry,
								user_id_1, user_id_2, user_id_3, user_id_4, user_id_5, creation_day);

		System.out.println("ccccc");

		GetAccountListLogic accountLogic = new GetAccountListLogic();
		HttpSession session = request.getSession();
		RoomLogic roomLogic = new RoomLogic();
		Login login = (Login)session.getAttribute("login");
		List<Room> roomList = roomLogic.execute(login);
		List<Account> accountList = GetAccountListLogic.execute();

		//取得したデータをセッションスコープに保存
		session.setAttribute("roomList", roomList);
		session.setAttribute("accountList", accountList);

		System.out.println(room);
		session.setAttribute("room", room);
		System.out.println(room.getRoom_intro());

		//押されたボタンに応じ処理を分岐
		if(command.equals("編集")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editRoom.jsp");
			dispatcher.forward(request,response);
		}
		else if(command.equals("削除")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteRoom.jsp");
			dispatcher.forward(request,response);
		}
		else { //入室の処理
			GetAccountLogic getAccountLogic = new GetAccountLogic();
			Account account = getAccountLogic.execute(login);
			session.setAttribute("account", account);

			//メッセージリストを取得してセッションスコープに保存
			GetMessageListLogic getMessageListLogic = new GetMessageListLogic();
			List<Message> messageList = getMessageListLogic.execute(room);
			session.setAttribute("messageList",messageList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");
			dispatcher.forward(request, response);
		}
		System.out.println("calls post()");
	}
}
