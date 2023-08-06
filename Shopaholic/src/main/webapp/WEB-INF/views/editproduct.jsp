<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Product</title>
</head>
<body>
	<div align="center">
		<header>Change product info below:</header>
		<h1></h1>
		<form action="EditProductServlet" method="post">
			<table style="with: 80%">
				<tr>
					<td>PID</td>
					<td><input type="text" name="PID" /></td>
				</tr>
				<tr>
					<td>ProductName</td>
					<td><input type="text" name="ProductName" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="Price" /></td>
				</tr>
				<tr>
			</table>
			<h1></h1>
			<input type="submit" value="Save" /> 
			<input type="submit" value="Cancel" name="Cancel" />
		</form>
</body>
</html>