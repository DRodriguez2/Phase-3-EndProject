<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/home.css">
<body>
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>Sign Up</h1>
		<form:form  action="/register" method="POST" modelAttribute="user">
			<div class="form-group">
				<form:label path="username">User name:</form:label> 
				<form:errors path="username" cssClass="error"/>
				<form:input path="username" class="form-control" required="required"/>
				
				<form:label path="password" class="pt-3">Enter Password:</form:label> 
				<form:errors path="password" cssClass="error"/>
				<form:input path="password" class="form-control" required="required" type="password"/>
				
				<label class="pt-3">Re enter Password: </label> <input type="password" class="form-control" name="verify" required>

			</div>
			<button type="submit" class="btn btn-primary">Register</button>
			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
		<div>${result}</div>
	</div>
</body>
</html>