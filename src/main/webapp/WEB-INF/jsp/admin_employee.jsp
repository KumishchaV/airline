<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AIR LowCost</title>
<link rel="stylesheet" href="css/w3.css">
</head>
<body class="w3-light-grey">
	<header>
		<div class="w3-row w3-container w3-blue">
			<div class="w3-col w3-container w3-left" style="width: 50px">
				<p></p>
			</div>
			<div class="w3-col w3-container w3-right" style="width: 220px">
				<div class="w3-row">
				</div>
				<div class="w3-row">
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.user.name != null}">
								<a href="controller?command=go-to-admin-page">${sessionScope.user.name}</a>
							</c:if>
							<c:if test="${sessionScope.user.name == null}">
								<a href="authorization">SignIn</a>
							</c:if>
						</h5>
					</div>
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.user.name != null}">
								<a href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">SignOut</a>
							</c:if>
							<c:if test="${sessionScope.user.name == null}">
								<a href="authorization">Registration</a>
							</c:if>
						</h5>
					</div>
				</div>
			</div>
			<div class="w3-rest w3-container">
				<h1>
					<a href="index.jsp"> <img src="images/plain.png" alt="plain"
						width="110" height="80" style="vertical-align: middle">AIR&nbsp;LowCost
					</a>
				</h1>
			</div>
		</div>
	</header>
	<div class="w3-responsive w3-border-bottom">
		<table class="w3-table w3-centered w3-border-bottom">
			<tr>
				<th><form action="controller" method="get">
						<input type="hidden" name="command" value="view-all-plane" />
						<button class="w3-button">Planes</button>
					</form></th>
				<th><form action="controller" method="get">
						<input type="hidden" name="command" value="view-all-employee" />
						<button class="w3-button">Employees</button>
					</form></th>
				<th><form action="controller" method="get">
						<input type="hidden" name="command" value="view-all-flight-info" />
						<button class="w3-button">Flights</button>
					</form></th>
				<th><form action="controller" method="get">
						<input type="hidden" name="command"	value="view-all-flight-with-info" />
						<button class="w3-button">Flights to departure</button>
					</form></th>
				<th><form action="controller" method="get">
						<input type="hidden" name="command" value="view-all-crews" />
						<button class="w3-button">Flight сrews</button>
					</form></th>	
				<th><form action="controller" method="get">
						<input type="hidden" name="command" value="view-all-users" />
						<button class="w3-button">Clients</button>
					</form></th>
			</tr>
		</table>
	</div>
	<br>
	<div class="w3-container w3-center">
		<h2><c:out value="${sessionScope.message}" /></h2>
	</div>
	<br>
	<div class="w3-container w3-row">
	<div class="w3-container w3-half">
		<h5>
			<strong>Add employee</strong>
		</h5>
		<form action="controller" method="post" class="w3-container">
			<input type="hidden" name="command" value="add_employee">
			<p>
				Name:&nbsp;<input onkeypress="return event.keyCode != 13;" required
					name="name" type="text" placeholder="max 45 chars" maxlength="45">
			</p>
			<select class="w3-select" name="role" required>
				<option value="" disabled selected>Select role</option>
				<option value="pilot">Pilot</option>
				<option value="stewardess">Stewardess</option>
			</select>
			<p>
				<button class="w3-button w3-grey">Enter</button>
			</p>
		</form>
	</div>
		<div class="w3-container w3-half">
			<c:if test="${requestScope.employee.unBlockedEdit}">
				<h5>
					<strong>Edit employee info</strong>
				</h5>
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="edit_employee">
						<input type="hidden" name="employee_id" type="text"
							value="<c:out value="${requestScope.employee.employeeId}" />">
					<p>
						Name:&nbsp;<input onkeypress="return event.keyCode != 13;"
							required name="name" type="text" placeholder="max 45 chars" maxlength="45"
							value="<c:out value="${requestScope.employee.name}" />">
					</p>
					<select class="w3-select" name="role" required>
						<option value="" disabled>Select role</option>
						<option value="pilot" <c:if test="${requestScope.employee.role == 'pilot'}">selected</c:if>>Pilot</option>
						<option value="stewardess" <c:if test="${requestScope.employee.role == 'stewardess'}">selected</c:if> >Stewardess</option>
					</select>
					<p>
						<button style="height:50px;width:100px" class="w3-button w3-grey">Edit</button>
					</p>
				</form>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="view-all-employee" />
				<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
			</form>
			</c:if>
		</div>
	</div>
	<c:if test="${empty sessionScope.employees}">
		<h2 class="w3-container w3-center">
			<strong>Please add an employee</strong>
		</h2>
	</c:if>	
	<c:if test="${not empty sessionScope.employees}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Employees list</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th>Personal number</th>
				<th>Name</th>
				<th>Role</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="info" items="${sessionScope.employees}">
				<tr>
					<td><c:out value="${info.employeeId}" /></td>
					<td><c:out value="${info.name}" /></td>
					<td><c:out value="${info.role}" /></td>
					<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-employee">
							<input type="hidden" name="employee_id" type="text" value="<c:out value="${info.employeeId}" />">
							<button class="w3-btn w3-black">Edit</button>
						</form></td>
						<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="delete-employee">
							<input type="hidden" name="employee_id" type="text" value="<c:out value="${info.employeeId}" />">
							<button class="w3-btn w3-black">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</c:if>
	<br>
	<form class="w3-container w3-center" action="controller" method="get">
		<input type="hidden" name="command" value="go-to-admin-page">
		<button class="w3-button w3-black">Back to admin page</button>
	</form>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<footer class="w3-container w3-cyan w3-bottom">
		<h5>Air LowCost</h5>
		<p>&copy; Copyright Vitali Kumishcha</p>
	</footer>
</body>
</html>