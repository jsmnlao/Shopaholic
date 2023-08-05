<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>

<head>
<meta charset="ISO-8859-1">
<title>Admin Page of Shopaholic</title>
</head>
<body>
	<div align="center">
		<h1>Shopaholic Admin Page</h1>
		<h2>User Table</h2>
		<table border="1" width="500" align="center">
			<th><b>UID</b></th>
			<th><b>FirstName</b></th>
			<th><b>LastName</b></th>
			<th><b>UserName</b></th>
			<th><b>Delete</b></th>
			<th><b>Edit</b></th>
			<%
			ArrayList<User> users = (ArrayList<User>) request.getAttribute("userdata");
			for (User u : users) {
			%>
			<tr>
				<td><%=u.getUID()%></td>
				<td><%=u.getFirstName()%></td>
				<td><%=u.getLastName()%></td>
				<td><%=u.getUserName()%></td>
				<td><a href="DeleteUserServlet?UID=<%=u.getUID()%>">Delete</a>
				</td>
				<td><a href="EditUserServlet?UID=<%=u.getUID()%>">Edit</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>
		<h1></h1>
		
		<table border="1" width="500" align="center">
		<h2>Merchant Table</h2>
			<th><b>MID</b></th>
			<th><b>FirstName</b></th>
			<th><b>LastName</b></th>
			<th><b>UserName</b></th>
			<th><b>Delete</b></th>
			<th><b>Edit</b></th>
			<%
			ArrayList<Merchant> merchants = (ArrayList<Merchant>) request.getAttribute("merchantdata");
			for (Merchant m : merchants) {
			%>
			<tr>
				<td><%=m.getMID()%></td>
				<td><%=m.getFirstName()%></td>
				<td><%=m.getLastName()%></td>
				<td><%=m.getUserName()%></td>
				<td><a href="DeleteMerchantServlet?MID=<%=m.getMID()%>">Delete</a>
				</td>
				<td><a href="EditMerchantServlet">Edit</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>
		<h1></h1>
		
		<table border="1" width="500" align="center">
		<h2>Product Table</h2>
			<th><b>PID</b></th>
			<th><b>ProductName</b></th>
			<th> ProductType </th>
			<th><b>Price</b></th>
			<th><b>Delete</b></th>
			<th><b>Edit</b></th>
			<%
			ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productdata");
			for (Product p : products) {
			%>
			<tr>
				<td><%=p.getPID()%></td>
				<td><%=p.getProductName()%></td>
				<td><%=p.getProductType()%></td>
				<td>$<%=p.getPrice()%></td>
				<td><a href="DeleteProductServlet?PID=<%=p.getPID()%>">Delete</a>
				</td>
				<td><a href="EditProductServlet">Edit</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>
		<h1></h1>
		
		<table border="1" width="500" align="center">
		<h2>Order Table</h2>
			<th><b>OID</b></th>
			<th><b>CID</b></th>
			<th> ProductName </th>
			<th> Delete </th>
			<%
			ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orderdata");
			for (Order o : orders) {
			%>
			<tr>
				<td><%=o.getOID()%></td>
				<td><%=o.getCID()%></td>
				<td><%=o.getProductName()%></td>
				<td><a href="DeleteOrderServlet?OID=<%=o.getOID()%>">Delete</a>
			</tr>
			<%
			}
			%>
		</table>
		<h1></h1>
		
		<a href="LoginServlet">Log Out</a>
	</div>
</body>
</html>