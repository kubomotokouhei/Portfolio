<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Room,java.util.List" %>

<%
List<Room> roomList =
	(List<Room>)session.getAttribute("roomList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コミュニケーションツール</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/room.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>
<h1>ユーザーコミュニケーションツール</h1>
<h2>チャットロビー</h2>
<input  class="room_create" type="button" onclick="location.href='RoomCreateServlet'"  value="ルーム作成"><br><br>

<form action="RoomServlet"  method="post" >

<% int i =0; %>
<% for (Room room : roomList) { %>

		<button  class="edit_room"  type="submit" name="command" value="編集,<%= i %>" ></button>&nbsp;&nbsp;

		<button  class="room_delete"  type="submit" name="command" value="削除,<%= i %>" ></button>&nbsp;&nbsp;

		<input  type="hidden" name="room_id[<%= i %>]" value="<%= room.getRoom_id() %>">

		<span class="roomA"><%= room.getRoom_id() %></span>&nbsp;&nbsp;

		<input  type="hidden" name="mgr_id[<%= i %>]" value="<%= room.getMgr_id() %>">

		<input  type="hidden" name="room_name[<%= i %>]" value="<%= room.getRoom_name() %>">

		<span class="roomA"><%= room.getRoom_name() %></span>&nbsp;&nbsp;

		<button class="room_in" type="submit" name="command" value="入室,<%= i %>" ></button>&nbsp;&nbsp;

		<input type="hidden" name="entry[<%= i %>]" value="<%= room.isEntry() %>" >

		<input type="hidden" name="room_intro[<%= i %>]" value="<%= room.getRoom_intro() %>" >

		<span class="roomA"><%= room.getRoom_intro() %></span>&nbsp;&nbsp;

		<input type="hidden" name="user_id_1[<%= i %>]" value="<%= room.getUser_id_1() %>">
		<input type="hidden" name="user_id_2[<%= i %>]" value="<%= room.getUser_id_2() %>">
		<input type="hidden" name="user_id_3[<%= i %>]" value="<%= room.getUser_id_3() %>">
		<input type="hidden" name="user_id_4[<%= i %>]" value="<%= room.getUser_id_4() %>">
		<input type="hidden" name="user_id_5[<%= i %>]" value="<%= room.getUser_id_5() %>">

		<input type="hidden" name="room_creation[<%= i %>]" value="<%= room.getCreation_day() %>">

		<span class="roomA"><%= room.getCreation_day() %></span>&nbsp;&nbsp;<br><br>

<% i++; } %>

</form>
<a href="LoginServlet"  class="logout">ログアウト</a>
</body>
</html>