package servlet;

import java.io.IOException;
import java.util.Enumeration;
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
import model.Login;
import model.LoginLogic;
import model.Room;
import model.RoomLogic;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// 全てのセッション情報を削除する
		HttpSession session = request.getSession();
		Enumeration<String> en = session.getAttributeNames();
		String eName;
        while(en.hasMoreElements()){
      	  eName = (String)en.nextElement();
      	  session.removeAttribute(eName);
      	  System.out.println("セッションスコープ削除:" + eName);
        }

		//フォワード
		RequestDispatcher dispatcher =
			request.getRequestDispatcher
			("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String command = request.getParameter("command");

		if (!command.equals("ログイン")) {
			//ログイン処理の実行
			Login login = new Login(user_id, password);
			GetAccountLogic bo = new GetAccountLogic();
			Account registerUser = bo.execute(login);

			//ログイン処理の成否によって処理を分岐
			if (registerUser != null) {	//ログイン成功

				//セッションスコープに更新・削除するアカウント情報を保存
				HttpSession session = request.getSession();
				session.setAttribute("registerUser", registerUser);

				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("/WEB-INF/jsp/create_account_Update.jsp");
				dispatcher.forward(request, response);
			} else {		//ログイン失敗
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg","ユーザーが存在しません");
				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			//ログイン処理の実行
			Login login = new Login(user_id, password);
			LoginLogic bo = new LoginLogic();
			boolean result = bo.execute(login);

			//ログイン処理の成否によって処理を分岐
			if (result) {	//ログイン成功
				//セッションスコープにユーザーIDを保存
				HttpSession session = request.getSession();
				session.setAttribute("login", login);

				RoomLogic roomLogic = new RoomLogic();
				List<Room> roomList = roomLogic.execute(login);
				session.setAttribute("roomList", roomList);
				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("WEB-INF/jsp/room.jsp");
				dispatcher.forward(request, response);
			} else {		//ログイン失敗
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg","ユーザーが存在しません");
				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}