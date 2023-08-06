<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Merchant Main Page</title>
</head>
<body>
	<div align="center">
		<h1>
			Welcome,<%=request.getSession().getAttribute("MerchantName")%>!
		</h1>

		<h2>Your Products</h2>
		<table border="1" width="500" align="center">
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Product Type</th>
				<th>Price</th>
				<th>Delete</th>
			</tr>
			<%
			ArrayList<Product> merchantproducts = (ArrayList<Product>) request.getSession().getAttribute("merchantdata");
			for (Product p : merchantproducts) {
			%>
			<tr>
				<td><%=p.getPID()%></td>
				<td><%=p.getProductName()%></td>
				<td><%=p.getProductType()%></td>
				<td>$<%=p.getPrice()%></td>
				<td><a href="DeleteMerchantProductServlet?PID=<%=p.getPID()%>">Delete</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>

		<h2>Add New Product</h2>
		<form action="MerchantServlet" method="post">
			<input type="hidden" name="action" value="add"> 
			<label for="PID">PID</label> 
			<input type="text" name="PID" required><br>
			<label for="productName">Product Name</label> 
			<input type="text" name="productName" required><br> 
			<label for="ProductType">Product Type</label> 
			<input type="text" name="ProductType" required><br> 
			<label for="price">Price</label>
			<input type="number" name="price" step="1" required><br>
			<button type="submit">Add Product</button>
		</form>
		<h1></h1>
		<a href="LoginServlet">Logout</a>
	</div>
</body>
</html>