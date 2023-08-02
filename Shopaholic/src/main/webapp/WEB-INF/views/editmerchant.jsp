<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Merchant</title>
</head>
<body>
	<div align="center">
		<header>Change merchant info below:</header>
		<h1></h1>
		<form action="EditMerchantServlet" method="post">
			<table style="with: 80%">
				<tr>
					<td>MID</td>
					<td><input type="text" name="MID" /></td>
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
			</table>
			<h1></h1>
			<input type="submit" value="Save" /> 
			<input type="submit" value="Cancel" name="Cancel" />
		</form>
</body>
</html>