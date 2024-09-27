<!-- 下記のアカウントを削除します -->

<!-- 9/19更新　 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account, java.util.List"%>
<%
	//リクエストスコープのメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
	//セッションスコープの内容を取得
	Account registerUser = (Account) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<link type="text/css" rel="stylesheet" href="css/account_Delete_Confirm.css">

<head>
<meta charset="UTF-8">
<title>コミュニケーションツール</title>
</head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">

<body>
	<br>
	<h1>ユーザーコミュニケーションツール</h1>
	<h2>下記のアカウントを削除します</h2>

	<form action="AccountUpdate" method="get">
		<div class="text id">
			<div class="text_i">ユーザーID</div>
			<%=registerUser.getUser_id()%>
		</div>
		<div class="text id">
			<div class="text_i">ハンドルネーム</div>
			<%=registerUser.getUser_name()%>
		</div>
		<div class="text id">
			<div class="text_i">パスワード</div>
			<%=registerUser.getPassword()%>
		</div>
		<div class="text id">
			<div class="text_i">メールアドレス</div>
			<%=registerUser.getMailadress()%>
		</div>

		<div class="button">
			<input type="submit" value="削除" name="confirm" class="delete">
			<input type="submit" value="戻る" name="confirm" class="back">
		</div>
	</form>

	<%
		if (errorMsg != null) {
	%>
	<p><%=errorMsg%></p>
	<%
		}
	%>

	<a href="LoginServlet">ログイン画面へ戻る</a>

</body>
</html>