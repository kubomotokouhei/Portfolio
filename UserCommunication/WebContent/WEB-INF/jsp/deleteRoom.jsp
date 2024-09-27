<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,java.util.List"
import="model.Room,java.util.List,model.Login" %>

<%
Room room =
(Room)session.getAttribute("room");
Login login =
(Login)session.getAttribute("login");
List<Account> accountList =
	(List<Account>)session.getAttribute("accountList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーム削除</title>
<link type="text/css" rel="stylesheet" href="css/room_delete.css">
</head>
<body>
<h2>ルーム削除</h2>
<%String mgr = room.getMgr_id(); if(mgr.equals(login.getUser_id())) { %>
<form action="RoomDeleteServlet" method="post">
    <input type="hidden" name="room_id" value="<%= room.getRoom_id() %>">
   <h2>本当に<%=room.getRoom_name()%>を削除しますか</h2><br>



    <button class="room_delete" type="submit">ルーム削除</button>
    </form>
    <%-- <a href ="Deleteser"> --%>
<%}else{ %>
<h2>管理者ではないので<%=room.getRoom_name()%>を削除出来ません</h2>
<% }%><br>

   <form action="RoomServlet" method="get"><button class="back_button" type="submit"></button></form>
</body>
</html>