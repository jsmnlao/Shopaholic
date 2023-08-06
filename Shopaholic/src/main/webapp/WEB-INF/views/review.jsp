<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%@ page import="shopaholicjava.*"%>
<head>
<meta charset="UTF-8">
<title>Product Reviews</title>
</head>
<body>
	<div align=center>
		<h1>Product Review Page</h1>
		<table border="1" width="500" align="center">
			<th>Review ID</th>
			<th>Product ID</th>
			<th>Author</th>
			<th>Product Name</th>
			<th>Description</th>
			<th>Star (From 1 to 5 Stars)</th>

			<%
			ArrayList<Review> reviews = (ArrayList<Review>) request.getAttribute("reviewdata");
			for (Review r : reviews) {
			%>
			<tr>
				<td><%=r.getRID()%></td>
				<td><%=r.getPID()%></td>
				<td><%=r.getAuthor()%></td>
				<td><%=r.getProductName()%></td>
				<td><%=r.getReviewDescription()%></td>
				<td><%=r.getStars()%> star</td>
			</tr>
			<%
			}
			%>
		</table>

		<h2>Add Review</h2>
		<form action="ReviewServlet" method="post">
			<input type="hidden" name="action" value="add"> <label
				for="RID">Review ID</label> <input type="text" name="RID" required><br>
			<label for="PID">Product ID</label> <input type="text" name="PID"
				required><br> <label for="Author">Author</label> <input
				type="text" name="Author" required><br> <label
				for="ProductName">Product Name</label> <input type="text"
				name="ProductName" required><br> <label
				for="Description">Description</label> <input type="text"
				name="Description" required><br> <label for="Star">Star</label>
			<input type="number" name="Star" required><br>
			<button type="submit">Add Review</button>
		</form>
		<h1></h1>

		<h1></h1>
		<a href="UserServlet">Go back to homepage</a>
	</div>
</body>
</html>