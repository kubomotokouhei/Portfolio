/*9/20 更新　*/

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
import model.GetAccountLogic;
import model.GetMessageListLogic;
import model.Login;
import model.Message;
import model.PostMessageLogic;
import model.Room;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションスコープから画面情報を個別に取得
		HttpSession session = request.getSession();
		Room room = (Room) session.getAttribute("room");

		//セッションスコープに保存する
		Login login = (Login) session.getAttribute("login"); //セッションスコープから情報を取得（user_id,passwordの情報のみ）
		GetAccountLogic getAccountLogic = new GetAccountLogic();

		Account account = getAccountLogic.execute(login);
		session.setAttribute("account", account);

		//メッセージリストを取得してセッションスコープに保存
		GetMessageListLogic getMessageListLogic = new GetMessageListLogic();
		List<Message> messageList = getMessageListLogic.execute(room);
		session.setAttribute("messageList", messageList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");

		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();

		Account account = (Account) session.getAttribute("account");
		Room room = (Room) session.getAttribute("room");
		if (message != null && message.length() != 0) {

	//メッセージをリストに追加
	//roomid,user_id,message を書き込む（messageid,creationdayはDBで自動採番されるため不要）
			Message ms = new Message(room.getRoom_id(), account.getUser_id(), message);
			PostMessageLogic postMessageLogic = new PostMessageLogic();
			postMessageLogic.execute(ms, room);
			System.out.println("メッセージをリストに追加");
		}

		//メッセージリストを取得して、セッションスコープに保存
		GetMessageListLogic getMessageListLogic = new GetMessageListLogic();
		List<Message> messageList = getMessageListLogic.execute(room);

		if ((messageList != null) && (messageList.size() != 0)) {
			System.out.println("messageListの結果　：" + messageList.get(0).getRoom_id());
		}

		session.setAttribute("messageList", messageList);
		session.setAttribute("account", account);
		session.setAttribute("room", room);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");
		dispatcher.forward(request, response);

	}

}
