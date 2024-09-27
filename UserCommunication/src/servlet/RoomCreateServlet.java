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
import model.InsertRoomListLogic;
import model.Login;
import model.Room;
import model.RoomLogic;

/**
 * Servlet implementation class RoomCreateServlet
 */
@WebServlet("/RoomCreateServlet")
public class RoomCreateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String room_create = request.getParameter("room_create");

		GetAccountListLogic accountLogic = new GetAccountListLogic();

		List<Account> accountList = GetAccountListLogic.execute();
		HttpSession session = request.getSession();
		session.setAttribute("accountList", accountList);



		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/room_create.jsp");
		dispatcher.forward(request,response);

		System.out.println("calls doGet()");//calls doGet()
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String room_name = request.getParameter("room_name");
		String room_intro = request.getParameter("room_intro");
		String strEntry = request.getParameter("entry");
		String[] user_ids = request.getParameterValues("user_id");
		/*String user_id_2 = request.getParameter("user_id");
		String user_id_3 = request.getParameter("user_id");
		String user_id_4 = request.getParameter("user_id");
		String user_id_5 = request.getParameter("user_id");
		*/
		boolean entry = Boolean.valueOf(strEntry);

		String[] user_id = new String[5];

		for(int i=0; i<5; i++) {
			user_id[i] = null;

			if(user_ids!=null && i<user_ids.length){
				user_id[i] = user_ids[i];
			}
		}


		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");

		Room room = new Room(login.getUser_id(),room_name,room_intro,entry,user_id[0],user_id[1],user_id[2],user_id[3],user_id[4]);
		InsertRoomListLogic insertRoomListLogic = new InsertRoomListLogic();
		insertRoomListLogic.execute(room);



		RoomLogic roomLogic =
			new RoomLogic();
		List<Room> roomList = roomLogic.execute(login);
		session.setAttribute("roomList",roomList);

		RequestDispatcher dispatcher =
			request.getRequestDispatcher("WEB-INF/jsp/room.jsp");
		dispatcher.forward(request,response);

	}
}

