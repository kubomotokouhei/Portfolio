<!-- 9/18更新　 -->
<!-- 9/19更新　 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<%@ page import="java.util.Enumeration"%>
<%
	// 全てのセッション情報を削除する
	HttpSession sessions = request.getSession();
	Enumeration<String> en = sessions.getAttributeNames();
	String eName;
	while (en.hasMoreElements()) {
		eName = (String) en.nextElement();
		sessions.removeAttribute(eName);
		System.out.println("セッションスコープ削除:" + eName);
	}
%>

<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>コミュニケーションツール</title>
</head>
<link type="text/css" rel="stylesheet" href="css/login.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">
<body>
	<br>
	<h1>ユーザーコミュニケーションツール</h1>
	<h2>ログイン画面</h2>

	<form action="LoginServlet" method="post">

		<div class="text id">
			<br>
			<input type="text" name="user_id" placeholder="ユーザーID">
		</div>
		<div class="text pass">
			<input type="password" name="password" placeholder="パスワード">
		</div>

		<div class="button">
			<input type="submit" name="command" value="ログイン" class="login">
			<input type="submit" name="command" value="ｱｶｳﾝﾄ情報変更" class="newaccount">
		</div>
	</form>

	<a href="AccountInsert">新規作成はこちら</a>


	<%
		if (errorMsg != null) {
	%>
	<p>
		<font color=#FF0000><%=errorMsg%></font>
	</p>
	<%
		}
	%>
	<br>
</body>
</html>