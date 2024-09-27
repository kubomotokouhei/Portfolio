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
import model.Login;
import model.Room;
import model.RoomLogic;
import model.UpdateRoomListLogic;
/**
 * Servlet implementation class EditRoom
 */
@WebServlet("/EditRoom")
public class EditRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetAccountListLogic accountLogic = new GetAccountListLogic();
		HttpSession session = request.getSession();
		RoomLogic roomLogic = new RoomLogic();
		Login login = (Login)session.getAttribute("login");
		List<Room> roomList = roomLogic.execute(login);
		List<Account> accountList = GetAccountListLogic.execute();

		session.setAttribute("roomList", roomList);


		session.setAttribute("accountList", accountList);




		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editRoom.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("aaaaa");
		String strRoom_id = request.getParameter("room_id");
		String room_name = request.getParameter("room_name");
		String room_intro = request.getParameter("room_intro");
		String strEntry = request.getParameter("entry");
		String[] user_ids = request.getParameterValues("user_id");
		int room_id = Integer.parseInt(strRoom_id);
		/*String user_id_2 = request.getParameter("user_id");
		String user_id_3 = request.getParameter("user_id");
		String user_id_4 = request.getParameter("user_id");
		String user_id_5 = request.getParameter("user_id");
		*/
		System.out.println("bbbbb");
		boolean entry = Boolean.valueOf(strEntry);
		System.out.println("strEntry");
		System.out.println(strEntry);
		System.out.println("entry");
		System.out.println(entry);
		String[] user_id = new String[5];

		for(int i=0; i<5; i++) {
			user_id[i] = null;

			if(user_ids!=null && i<user_ids.length){
				user_id[i] = user_ids[i];
			}
		}
		System.out.println("cccccc");

		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");

		Room room = new Room(room_id,login.getUser_id(),room_name,room_intro,entry,user_id[0],user_id[1],user_id[2],user_id[3],user_id[4]);
		UpdateRoomListLogic updateRoomListLogic = new UpdateRoomListLogic();
		updateRoomListLogic.execute(room);
		System.out.println("ｄｄｄｄｄｄ");




		RoomLogic roomLogic = new RoomLogic();
		List<Room> roomList = roomLogic.execute(login);

		session.setAttribute("roomList",roomList);
		System.out.println("ffffffffff");
		RequestDispatcher dispatcher =
			request.getRequestDispatcher("WEB-INF/jsp/room.jsp");
		dispatcher.forward(request,response);


	}

}
