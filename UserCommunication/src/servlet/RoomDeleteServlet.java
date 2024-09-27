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
import model.DeleteRoomListLogic;
import model.GetAccountListLogic;
import model.Login;
import model.Room;
import model.RoomLogic;

/**
 * Servlet implementation class RoomDeleteServlet
 */
@WebServlet("/RoomDeleteServlet")
public class RoomDeleteServlet extends HttpServlet {
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




		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteRoom.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("aaaaa");
		String strRoom_id = request.getParameter("room_id");

		int room_id = Integer.parseInt(strRoom_id);


		System.out.println("cccccc");

		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");

		Room room = new Room(room_id);
		DeleteRoomListLogic deleteRoomListLogic = new DeleteRoomListLogic();
		deleteRoomListLogic.execute(room);
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
