<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>
<head>
<title>Shopping Cart</title>
</head>
<body>
	<div align=center>
		<h1>Shopping Cart</h1>
	</div>
	<table border="1" width="500" align="center">
		<tr>
			<th>PID</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Delete</th>
		</tr>

		<%
		ArrayList<Product> cartitems = (ArrayList<Product>) request.getSession().getAttribute("cartdata");
		for (Product p : cartitems) {
		%>
		<tr>
			<td><%=p.getPID()%></td>
			<td><%=p.getProductName()%></td>
			<td>$<%=p.getPrice()%></td>
			<td><a href="DeleteFromCartServlet?PID=<%=p.getPID()%>">Delete</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>

	<div align=center>
		<h3>
			<a href="ViewOrdersServlet">Order</a>
		</h3>


		<p>
			<a href="UserServlet">Continue Shopping</a>
		</p>
	</div>
</body>
</html>
