<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Login Here</title>
</head>
<body>
	<div align="center">
		<h1>Welcome to Shopaholic! Please login below.</h1>
		<form action="LoginServlet" method="POST">
			<table style="with: 80%">
				<tr>
					<td>ID</td>
					<td><input type="text" name="ID" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="FirstName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="LastName" /></td>
				</tr>
				<tr>
					<td>User Name</td>
					<td><input type="text" name="UserName" /></td>
				</tr>
				<tr>
					<td>UserPassword</td>
					<td><input type="password" name="UserPassword" /></td>
				</tr>
				<tr>
					<td>Select UserType</td>
					<td><select name="UserType">
							<option value="User">User</option>
							<option value="Merchant">Merchant</option>
							<option value="Admin">Admin</option>
					</select>
			</table>
			<h1></h1>
			<input type="submit" value="Login" />
		</form>
		<h1></h1>
		<a href="createaccount.jsp" >No account? Create an account!</a>
	</div>
</body>
</html>