<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>
<head>
<meta charset="UTF-8">
<title>User Home Page</title>
</head>
<body>
	<div align=center>
		<div align=center>
			<h1>Welcome, <%=request.getAttribute("UserName")%> to Shopaholic Home Page!</h1>
			<h2>Products Available</h2>
			<table border="1" width="500" align="center">
				<th>Product ID</th>
				<th>Product Name</th>
				<th>ProductType</th>
				<th>Price</th>
				<th>Add</th>

				<%
			ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productdata");
				for (Product p: products) {
			%>
				<tr>
					<td><%=p.getPID()%></td>
					<td><%=p.getProductName()%></td>
					<td><%=p.getProductType()%></td>
					<td>$<%=p.getPrice()%></td>
					<td><a
						href="AddToCartServlet?pid=<%=p.getPID()%>&productname=<%=p.getProductName()%>&price=<%=p.getPrice()%>">Add</a></td>

				</tr>
				<% 
			}%>
			</table>
			<h3>
				<a href="ReviewServlet">Go to Reviews</a>
			</h3>
			<h3>
				<a href="EditCartServlet">Go to Cart</a>
			</h3>
		</div>
		<a href="LoginServlet">Log Out</a>
	</div>
</body>
</html>