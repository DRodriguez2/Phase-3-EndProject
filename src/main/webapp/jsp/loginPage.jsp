<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/home.css">

<body>
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>Login</h1>
		<form  class="pb-3" action="loginPage" method="POST">
			<div class="form-group">
				<label>Username: </label><input type="text" class="form-control" name="username"
					placeholder="user ID" required>
				<label class="pt-3">Password:</label><input type="password" class="form-control" name="password"
					placeholder="password" required>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<a class="btn btn-primary" href="/register">Register</a>
<!-- 		<form action="/register" method="GET" class="pt-3"><button type="submit" class="btn btn-primary">Register</button> -->
<!-- 		</form> -->
		<div class="pt-2">${result}</div>
	</div>
</body>
</html>