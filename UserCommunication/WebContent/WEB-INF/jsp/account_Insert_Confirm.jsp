<!-- 下記の内容で登録します -->
<!-- 9/18更新　 -->
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
<link type="text/css" rel="stylesheet" href="css/account_insert_confirm.css">
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
	<h2>下記の内容で登録します</h2>

	<form action="AccountInsert" method="get" >
		<br>
		<div class="text">
			<div class="text_i"> ユーザーID</div>
			<div class="input"><%=registerUser.getUser_id()%></div>
		</div>
		<div class="text">
			<div class="text_i">ハンドルネーム</div>
			<div class="input"><%=registerUser.getUser_name()%></div>
		</div>
		<div class="text">
			<div class="text_i">パスワード</div>
			<div class="input"><%=registerUser.getPassword()%></div>
		</div>
		<div class="text">
			<div class="text_i">メールアドレス</div>
			<div class="input"><%=registerUser.getMailadress()%></div>
		</div>

		<div class="button">
			<input type="submit" value="登録" name="confirm" class="login">
			<input type="submit" value="戻る" name="confirm" class="back">
		</div>
	</form>

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
	<a href="LoginServlet">ログイン画面へ戻る</a>

</body>
</html>