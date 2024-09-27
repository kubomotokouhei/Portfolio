<!-- アカウント情報変更画面 -->
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
<link type="text/css" rel="stylesheet" href="css/create_account_update.css">

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
	<h2>アカウント情報変更画面、、、</h2>

	<form action="AccountUpdate" method="post">
		<div class="text">
			<div class="text_i">ユーザーID</div>
			<input type="text" name="user_id" value="<%=registerUser.getUser_id()%>" readonly>
		</div>
		<div class="text">
			<div class="text_i">ハンドルネーム</div>
			<input type="text" name="user_name" value="<%=registerUser.getUser_name()%>">
		</div>
		<div class="text">
			<div class="text_i">パスワード</div>
			<input type="password"  name="password" value="<%=registerUser.getPassword()%>">
		</div>
		<div class="text">
			<div class="text_i">メールアドレス</div>
			<input type="email"  name="mailadress" value="<%=registerUser.getMailadress()%>">
		</div>

		<button name="btn" value="更新" type="Submit">更新確認</button>
		<button name="btn" value="削除" type="Submit">削除</button>
		<br>
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