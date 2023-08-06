<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>
<head>
<title>Order History</title>
</head>
<body>
	<div align=center>
		<h1>Order History</h1>
		</div>
		<table border="1" width="500" align="center">
			<tr>
				<th>Order ID</th>
				<th>Product Name</th>
			</tr>

			<%
			ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orderHistory");
			for (Order o : orders) {
			%>
			<tr>
				<td><%=o.getOID()%></td>
				<td><%=o.getProductName()%></td>
			</tr>
			<%
			}
			%>

		</table>
		<h1></h1>
		<div align=center>
			<h3>
				<a href="UserServlet">Back to homepage</a>
			</h3>
		</div>
</body>
</html>

