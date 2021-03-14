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
		<h2><c:out value="${sessionScope.congratulationsMessage}" /></h2>
	</div>
		<div class="w3-container w3-border-bottom">
			<h1>Hello ${sessionScope.user.name}!</h1>
			<br>
		</div>
		<div class="w3-container">
			<p>This is the admin part of the application, where you can add, edit, delete all available fields in the following sections of the application:</p>
			 <ul>
				 <li>planes</li>
				 <li>employees</li>
				 <li>flights</li>
				 <li>flights to departure</li>
				 <li>flight сrews</li>
				 <li>clients</li>
			</ul>
		</div>
		<div class="w3-container">
			<p>In the client side of the application, you can search for flights and buy tickets, return tickets, add money to your account and edit your password and email.</p>
			<p>The cost of the ticket is affected by the presence or absence of baggage and the right of priority check-in and boarding. Also, as the departure date approaches, the price increases:</p>
			<ul>
				<li>less than 7 days before departure by 30%</li>
				<li>from 7 days to 30 days by 10%</li>
			</ul>
		</div>
	
	
	

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