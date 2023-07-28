<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Product Here</title>
</head>
<body>
	<div align="center">
		<h1>Product Register Form</h1>
        <form action="ProductServlet" method="post">
			<table style="with: 80%">
				<tr>
					<td>PID</td>
					<td><input type="text" name="PID" /></td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="ProductName" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="Price" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>