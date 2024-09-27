package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountNameCheck;
import model.InputCheck;
import model.InsertAccountCheck;
import model.InsertAccountLogic;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AccountInsert")
public class AccountInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//確認画面からの判断項目
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("confirm");

		if (action == null){
			//セッションスコープに初期情報を保存
			Account registerUser = new Account("", "", "", "");
			HttpSession session = request.getSession();
			session.setAttribute("registerUser", registerUser);
			request.setAttribute("errorMsg",null);
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/create_account_Insert.jsp");
			dispatcher.forward(request, response);
		} else
		//登録確認画面からの遷移の処理
		if (action.equals("登録")) {

			//セッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			Account registerUser = (Account)session.getAttribute("registerUser");

			//登録処理実行
			InsertAccountLogic bo = new InsertAccountLogic();
			boolean result = bo.execute(registerUser);
			if (result) {
				request.setAttribute("errorMsg",null);
				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("/WEB-INF/jsp/login.jsp"); // ←ここにメインメニューのjsp入れる
				dispatcher.forward(request, response);
			} else { //DBエラーの処理
				request.setAttribute("errorMsg","登録処理を中断しました");
				//フォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher
						("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}

		} else { //"戻る"ボタンの処理
			request.setAttribute("errorMsg",null);
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/create_account_Insert.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//確認画面からの判断項目
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("command");

		//画面リセットの処理
		if (action.equals("リセット")) {
			//セッションスコープに初期情報を保存
			Account registerUser = new Account("", "", "", "");
			HttpSession session = request.getSession();
			session.setAttribute("registerUser", registerUser);
			request.setAttribute("errorMsg",null);
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/create_account_Insert.jsp");
			dispatcher.forward(request, response);
		} else {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String mailadress = request.getParameter("mailadress");

		//セッションスコープに登録するアカウント情報を保存
		Account registerUser = new Account(user_id, user_name, password, mailadress);
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//長さ・タイプの入力チェック
		InputCheck inputCheck = new InputCheck();
		String errorMessage = "";
		errorMessage += inputCheck.getLengthError(user_id, 4,  10, "user_id");
		errorMessage += inputCheck.getLengthError(user_name, 1,  20, "user_name");
		errorMessage += inputCheck.getLengthError(password, 4,  10, "password");
		errorMessage += inputCheck.getLengthError(mailadress, 5,  30, "mailadress");
		errorMessage += inputCheck.getDigitAlphabetError(user_id, "user_id");
		errorMessage += inputCheck.getDigitAlphabetError(password, "password");
		//ハンドルネーム重複チェック
		AccountNameCheck accountNameCheck = new AccountNameCheck();
		if (accountNameCheck.execute(registerUser) != false) {
			errorMessage += "ハンドルネームが他のユーザーと重複しています";
		}
		//エラーの場合画面にエラー内容を表示
		if(!errorMessage.equals("")) {
		    // request オブジェクトにエラーメッセージを設定
			request.setAttribute("errorMsg", errorMessage);
			//フォワード
			RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/create_account_Insert.jsp");
			dispatcher.forward(request, response);
		} else {

		//ユーザー重複チェック
		InsertAccountCheck bo = new InsertAccountCheck();
		boolean result = bo.execute(registerUser);

		if (result != false) { //重複
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg",
						"IDが重複するため登録できません");
			//フォワード
			RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/create_account_Insert.jsp");
			dispatcher.forward(request, response);
		} else {
		//メッセージをリクエストスコープに保存
		request.setAttribute("errorMsg","");
		//フォワード
		RequestDispatcher dispatcher =
			request.getRequestDispatcher
			("/WEB-INF/jsp/account_Insert_Confirm.jsp");
		dispatcher.forward(request, response);
		}}}
	}
}