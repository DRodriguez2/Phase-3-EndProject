<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/home.css">
 
<body>
	<div class="row">
		<div class="col">
<!-- 			<div class="jumbotron center bg-dark text-white"> -->
				
				<div class="container-fluid p-3 my-3 bg-dark text-white row-full center">
					<nav class="navbar navbar-expand-sm bg-dark navbar-dark border-bottom border-light py-0 fixed-top">
						<ul class="navbar-nav">
							<li class="nav-item">
								<a class="navbar-brand">${user.getUsername()}</a>
							</li>
						    <li class="nav-item">
						      <a class="nav-link" href="/logout">Logout</a>
						    </li>
					    </ul>
					</nav>
					<h2 class= "middle pt-4" >Tasks</h2>
					<div>
						<table class="table-dark table-striped table-bordered center">
							<!-- here should go some titles... -->
							<tr>
								<th>ID</th>
								<th>Task</th>
								<th>Description</th>
								<th>Severity</th>
								<th>Email</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Update</th>
								<th>Delete</th>
							</tr>
							<c:forEach items="${tasks}" var="task">
								<tr>
									<td><c:out value="${task.getId()}" /></td>
									<td><c:out value="${task.getTaskName()}" /></td>
									<td><c:out value="${task.getDescription()}" /></td>
									<td><c:out value="${task.getSeverity()}" /></td>
									<td><c:out value="${task.getEmail()}" /></td>
									<td><c:out value="${task.getStartDate()}" /></td>
									<td><c:out value="${task.getEndDate()}" /></td>
									<td><form action="/home/edit"><button type="submit" class="btn btn-primary">Select</button><input type="hidden" name="taskId"
									value="${task.getId()}" /></form></td>
									<td><form action="/home/deleteTask" method="POST"><button type="submit" class="btn btn-primary">Delete</button><input type="hidden" name="taskId"
									value="${task.getId()}" /><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /></form>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
<!-- 			</div> -->
		</div>
	</div>

	<div class="row">
		<div class="col">
			<div class="container-fluid p-3 my-3 bg-dark text-white row-full">
				<h3>Add task</h3>
				<form:form action="/home/addTask" method="POST" modelAttribute="task">
					<div class="form-group">
							
							<form:label path="taskName">Task Name</form:label>
							<form:errors path="taskName" cssClass="error"/>
							<form:input path="taskName" class="form-control" required="required"/>
							<form:label path="description">Description</form:label>
							<form:errors path="description" cssClass="error"/>
							<form:textarea rows="2" path="description" class="form-control" required="required"/>
							<form:label path="severity">Select:</form:label>
							<form:errors path="severity" cssClass="error"/>
							<form:select path="severity" required="required" class="custom-select">
								<form:option value="HIGH" label="HIGH"/>
								<form:option value="MEDIUM" label="MEDIUM"/>
								<form:option value="LOW" label="LOW"/>
							</form:select>
							<form:label path="email">Email</form:label>
							<form:errors path="email" cssClass="error"/>
							<form:input path="email" class="form-control" required="required"/>
							<form:label path="startDate">Start Date</form:label>
							<form:errors path="startDate" cssClass="error"/>
							<form:input type="date" path="startDate" class="form-control" required="required"/>
							<form:label path="endDate">End Date</form:label>
							<form:errors path="endDate" cssClass="error"/>
							<form:input  type="date" path="endDate" class="form-control" required="required"/>	
					</div>
					<button type="submit" class="btn btn-primary">Add</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
			</div>
		</div>
		<div class="col">
			<div class="container-fluid p-3 my-3 bg-dark text-white row-full">
				<h3>Update Task</h3>
				<form:form action="/home/updateTask" method="POST" modelAttribute="editTask">
					<div class="form-group">
							
							<form:label path="taskName">Task Name</form:label>
							<form:errors path="taskName" cssClass="error"/>
							<form:input path="taskName" class="form-control" required="required" value="${editTask.getTaskName()}"/>
							<form:label path="description">Description</form:label>
							<form:errors path="description" cssClass="error"/>
							<form:textarea rows="2" path="description" class="form-control" required="required" value="${editTask.getDescription()}"/>
							<form:label path="severity">Select:</form:label>
							<form:errors path="severity" cssClass="error"/>
							<form:select path="severity" required="required" class="custom-select"  value="${editTask.getSeverity()}">
								<form:option value="HIGH" label="HIGH"/>
								<form:option value="MEDIUM" label="MEDIUM"/>
								<form:option value="LOW" label="LOW"/>
							</form:select>
							<form:label path="email">Email</form:label>
							<form:errors path="email" cssClass="error"/>
							<form:input path="email" class="form-control" required="required"  value="${editTask.getEmail()}"/>
							<form:label path="startDate">Start Date</form:label>
							<form:errors path="startDate" cssClass="error"/>
							<form:input type="date" path="startDate" class="form-control" required="required"  value="${editTask.getStartDate()}"/>
							<form:label path="endDate">End Date</form:label>
							<form:errors path="endDate" cssClass="error"/>
							<form:input  type="date" path="endDate" class="form-control" required="required"  value="${editTask.getEndDate()}"/>	
					</div>
					<button type="submit" class="btn btn-primary ml-5">Update</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<form:input type="hidden" path="id" value="${editTask.getId()}" />
				</form:form>
				
			</div>
		</div>
	</div>
</body>
</html>