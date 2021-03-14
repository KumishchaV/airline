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
	<div class="w3-container w3-center">
		<h2 class="w3-green">
			<c:out value="${sessionScope.congratulationsMessage}" />
		</h2>
		<h4 class="w3-red">
			<c:out value="${requestScope.messageErrorEmailAndPasswordFormat}" />
			<c:out value="${requestScope.messageIncorrectPassword}" />
		</h4>
	</div>
	<br>
	<div class="w3-container w3-center">
			<table
				class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
				<caption>
					<h3>
						<strong>List of clients</strong>
					</h3>
				</caption>
				<tr class="w3-blue-grey">
					<th >Login</th>
					<th>Login</th>
					<th>Name</th>
					<th>Surname</th>
					<th>Email</th>
					<th>Role</th>
					<th></th>
				</tr>
				<c:forEach var="info" items="${sessionScope.users}">
					<tr>
						<td><c:out value="${info.login}" /></td>
						<td><c:out value="${info.name}" /></td>
						<td><c:out value="${info.surname}" /></td>
						<td><c:out value="${info.email}" /></td>
						<td><c:out value="${info.role}" /></td>
						<td>
						<c:if test="${empty sessionScope.unblock}">
						<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-email-password">
								<input type="hidden" name="user_id" type="text"	value="<c:out value="${info.userId}" />">
								<button class="w3-btn w3-black">Edit email and password</button>
							</form>
						</c:if>
						<c:if test="${sessionScope.unblock == true}">
							<c:if test="${sessionScope.userUnblock.userId == info.userId}">
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="edit-email-password">
								<input type="hidden" name="user_id" type="text"	value="<c:out value="${info.userId}" />">
								<c:if test="${info.role == 'admin'}">
								<p>Current Password<br>
									<input	onkeypress="return event.keyCode != 13;" required
										name="old_password" type="text" placeholder="max 45 chars"
										maxlength="45">
								</p>
								</c:if>
								<p>New Password<br>
									<input	onkeypress="return event.keyCode != 13;" required
										name="password" type="text" placeholder="max 45 chars"
										maxlength="45">
								</p>
								<p>New Password the second time<br>
									<input	onkeypress="return event.keyCode != 13;" required
										name="password2" type="text" placeholder="max 45 chars"
										maxlength="45">
								</p>
								<p>New Email<br>
									<input	onkeypress="return event.keyCode != 13;" required
										name="email" type="text" placeholder="max 45 chars"
										maxlength="45" value="<c:out value="${info.email}" />">
								</p>
								<button class="w3-btn w3-black">Save</button>
							</form>
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="view-all-users">
								<p><button class="w3-btn w3-black">Cancel</button></p>
							</form>
							</c:if>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
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