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
import model.DeleteAccountLogic;
import model.InputCheck;
import model.UpdateAccountLogic;

/**
 * Servlet implementation class AccountUpdate
 */
@WebServlet("/AccountUpdate")
public class AccountUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//確認画面からの判断項目
		String action = request.getParameter("confirm");

		//更新・削除確認画面からの遷移の処理
		if (action.equals("更新")) {

			//セッションスコープからユーザー情報を取得(ログイン確認)
			HttpSession session = request.getSession();
			Account registerUser = (Account)session.getAttribute("registerUser");

			//更新処理実行
			UpdateAccountLogic bo = new UpdateAccountLogic();
			boolean result = bo.execute(registerUser);
			if (result) {
				request.setAttribute("errorMsg","更新処理が完了しました");
			} else { //DBエラーの処理
				request.setAttribute("errorMsg","更新処理を中断しました");
			}
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("削除")) {

			//セッションスコープからユーザー情報を取得(ログイン確認)
			HttpSession session = request.getSession();
			Account registerUser = (Account)session.getAttribute("registerUser");

			//削除処理実行
			DeleteAccountLogic bo = new DeleteAccountLogic();
			boolean result = bo.execute(registerUser);
			if (result) {
				request.setAttribute("errorMsg","削除処理が完了しました");
			} else { //DBエラーの処理
				request.setAttribute("errorMsg","削除処理を中断しました");
			}
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		} else { //"戻る"ボタンの処理
			request.setAttribute("errorMsg",null);
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
					("/WEB-INF/jsp/create_account_Update.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String mailadress = request.getParameter("mailadress");
		String btn = request.getParameter("btn");

		//更新・削除するアカウント情報を保存
		Account registerUser = new Account(user_id, user_name, password, mailadress);

		//セッションスコープに更新・削除するアカウント情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//長さ・タイプの入力チェック
		InputCheck inputCheck = new InputCheck();
		String errorMessage = "";
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
				("/WEB-INF/jsp/create_account_Update.jsp");
			dispatcher.forward(request, response);
		} else {

		//更新または削除の確認処理へ画面遷移
		if (btn.equals("更新")) {
			request.setAttribute("errorMsg","データの更新を実行します");
			//フォワード
			RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/account_Update_Confirm.jsp");
			dispatcher.forward(request, response);
		} else {
		if (btn.equals("削除")) {
			request.setAttribute("errorMsg","データの削除を実行します");
			//フォワード
			RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/account_Delete_Confirm.jsp");
			dispatcher.forward(request, response);
		}}}
	}
}