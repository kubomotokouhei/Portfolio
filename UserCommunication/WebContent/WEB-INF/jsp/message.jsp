<!-- 9/18更新　-->
<!-- 9/19更新　 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Message,model.Account,model.Room,java.util.List"%>


<%
	List<Message> messageList = (List<Message>) session.getAttribute("messageList");
	System.out.println("messageList : " + messageList);

	Account account = (Account) session.getAttribute("account");
	System.out.println("account : " + account);

	Room room = (Room) session.getAttribute("room");
	System.out.println("room : " + room);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トーク画面</title>
</head>
<link type="text/css" rel="stylesheet" href="css/message.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">

<body onload="location.href='#jump'">
	<div id="chat_header">
			<form action="RoomServlet" method="get">
				<input type=submit value="退出" class="back" >

			</form>
		<div class="chat_room_name" style="text-align: center">
			<%=room.getRoom_name()%>
		</div>



	</div>

	<form action="MessageServlet" method="post">
		<div id="chat_message">
			<%	for (Message message : messageList) {
				if (account.getUser_id().equals(message.getUser_id())) { %>
				<!-- 自分以外のユーザーは右寄せ表示 -->
				<div class="rightbox">
					<div class="rightinfo">
						<%=message.getUser_name()%>
						<%="/" + message.Creation_day()%>
					</div>
					<br>
					<div class="righttext">
						<%=message.getMessage()%>
					</div>
				</div>
				<div class="chat_clear"></div>

			<!-- 自分以外のユーザーは左寄せ表示 -->
				<% } else { %>
					<div class="leftbox">
						<div class="leftinfo">
							<%=message.getUser_name()%>
							<%="/" + message.Creation_day()%>
						</div>
						<div class="lefttext">
							<%=message.getMessage()%>
						</div>
					</div>
					<div class="chat_clear"></div>

				<%
					}
				%>
			<%
				}
			%>

			<div id="jump"></div>

		</div>
		<!-- テキストボックス、送信ボタン -->
		<div id="chat_send">
			<textarea name="message"></textarea>
			<input type=submit value="送信" class="send_btn">
			<input type=submit value="更新" class="reload">
		</div>
	</form>

</body>

</html>