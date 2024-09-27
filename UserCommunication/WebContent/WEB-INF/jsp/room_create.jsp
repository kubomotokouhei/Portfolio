<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,java.util.List" %>

<%
List<Account> accountList =
	(List<Account>)session.getAttribute("accountList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム作成機能</title>
<link type="text/css" rel="stylesheet" href="css/room_create.css">
</head>
<body>
<h2>ルーム作成</h2>
<form action="RoomCreateServlet" method="post">
    <input type="hidden" name="action" value="create">
    ルーム名: <input type="text" name="room_name" required><br>
    説明文: <textarea name="room_intro" required></textarea><br>

    フリールーム設定をする:
    はい<input type = "radio"name="entry"value="false" checked>
    いいえ<input type = "radio"name="entry"value="true"><br>
    招待する人(5人まで)<br>
<select class="box" name="user_id" >
  <option value="">--招待（1人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_name()%></option>
<% ; } %>

</select><br><br>
<select class="box" name="user_id" >
  <option value="">--招待（2人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_name()%></option>
<% ; } %><br><br>

</select><br><br>
<select class="box" name="user_id" >
  <option value="">--招待（3人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_name()%></option>
<% ; } %><br><br>

</select><br><br>
<select class="box" name="user_id" >
  <option value="">--招待（4人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_name()%></option>
<% ; } %><br><br>

</select><br><br>
<select class="box" name="user_id" >
  <option value="">--招待（5人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_name()%></option>
<% ; } %><br><br>

</select><br><br>

    <button class="room_create"  type="submit">ルーム作成</button>
    </form>


    <br><form action="RoomServlet" method="get"><button class="back_button"  type="submit"></button></form>
</body>
</html>