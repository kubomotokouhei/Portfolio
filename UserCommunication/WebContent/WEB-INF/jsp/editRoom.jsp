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
<title>ルーム修正</title>
<link type="text/css" rel="stylesheet" href="css/edit_room.css">
</head>
<body>
<h2>ルーム修正</h2>
<form action="EditRoom" method="post">
    <input type="hidden" name="action" value="create">
		<input type = "hidden" name="room_id" value = <%=room.getRoom_id() %>>
		<%String mgr = room.getMgr_id(); if(mgr.equals(login.getUser_id())) { %>
    ルーム名: <input type="text" name="room_name" value = <%=room.getRoom_name() %> required ><br>
    説明文: <textarea type= "text" name="room_intro" value= <%=room.getRoom_intro()%> required><%=room.getRoom_intro()%></textarea><br>
<%}else{ %>ルーム名:管理者ではないので編集出来ません <input type="text" name="room_name" value = <%=room.getRoom_name() %> readonly ><br>
説明文: 管理者のみ編集出来ません<textarea type= "text" name="room_intro" value= <%=room.getRoom_intro()%> readonly><%=room.getRoom_intro()%></textarea><br>
<% } %>
    フリールーム設定をする:
    はい<input type = "radio"name="entry"value="false" checked>
    いいえ<input type = "radio"name="entry"value="true">

    前回設定:<%if(room.isEntry()==false){ %> はい<% }else{%>いいえ<% }%><br>
    招待する人(5人まで)<br>
<select class="box" name="user_id" >
  <option value="">--招待（1人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_id()%></option>
<% ; } %>

</select>前回設定:<%=room.getUser_id_1() %><br><br>

<select class="box" name="user_id" >
  <option value="">--招待（2人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_id()%></option>
<% ; } %><br><br>

</select>前回設定:<%=room.getUser_id_2() %><br><br>

<select class="box" name="user_id" >
  <option value="">--招待（3人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_id()%></option>
<% ; } %><br><br>

</select>前回設定:<%=room.getUser_id_3() %><br><br>

<select class="box" name="user_id" >
  <option value="">--招待（4人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_id()%></option>
<% ; } %><br><br>

</select>前回設定:<%=room.getUser_id_4() %><br><br>

<select class="box" name="user_id" >
  <option value="">--招待（5人目）--</option>
   <% for (Account account : accountList) { %>
		<option  value="<%= account.getUser_id() %>"><%=account.getUser_id()%></option>
<% ; } %><br><br>

</select>前回設定:<%=room.getUser_id_5() %><br><br>

    <button class="edit_room" type="submit">ルーム修正</button>
    </form>

	<br><a href ="Deleteser">
    <form action="RoomServlet" method="get"><button class="back_button" type="submit">戻る</button></form>
</body>
</html>