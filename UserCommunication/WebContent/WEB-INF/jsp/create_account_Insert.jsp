<!-- アカウント作成画面 -->
<!-- 9/18更新　 -->
<!-- 9/19更新　 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account, java.util.List"%>
<%
	//リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
	//セッションスコープの内容を取得
	Account registerUser = (Account) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コミュニケーションツール</title>
</head>
<link type="text/css" rel="stylesheet" href="css/create_account_insert.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">

<body>
	<br>
	<h1>ユーザーコミュニケーションツール</h1>
	<h2>アカウント作成画面</h2>

	<form action="AccountInsert" method="post">

		<div class="text id">
			<br>
			<input type="text" name="user_id"	value="<%=registerUser.getUser_id()%>" placeholder="ユーザーID (半角英数記号 4～10文字)" >
		</div>
		<div class="text name">
			<input type="text" name="user_name" value="<%=registerUser.getUser_name()%>" placeholder="ハンドルネーム (全角 1～6文字)">
		</div>
		<div class="text pass">
			<input type ="password" name="password"value="<%=registerUser.getPassword()%>" placeholder="パスワード (半角英数記号 4～10文字)">
		</div>
		<div class="text mail">
		<input type="email" name="mailadress" value="<%=registerUser.getMailadress()%>" placeholder="メールアドレス(5～30文字) ">
		</div>

		<div class="button">
			<input type="submit" value="登録確認" name="command" class="login">
			<input type="submit" value="リセット" name="command" class="reset">
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