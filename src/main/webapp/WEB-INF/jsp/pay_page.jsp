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
							<c:if test="${sessionScope.account.name != null}">
								${sessionScope.account.name}
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<a href="authorization">SignIn</a>
							</c:if>
						</h5>
					</div>
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.account.name != null}">
								<a
									href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">SignOut</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<a href="authorization">Registration</a>
							</c:if>
						</h5>
					</div>
				</div>
			</div>
			<div class="w3-rest w3-container">
				<h1>
					<img src="images/plain.png" alt="plain"
						width="110" height="80" style="vertical-align: middle">AIR&nbsp;LowCost
				</h1>
			</div>
		</div>
	</header>
	<br>
	<div class="w3-border-bottom">
		<div class="w3-row">
			<div class="w3-col w3-container w3-half">
				<h2>${sessionScope.account.name}, your balance is ${sessionScope.account.balance}$</h2>
			</div>
			<div class="w3-col w3-container w3-half">
			</div>
		</div>
	</div>
	<div class="w3-container w3-center">
		<h2>
			<strong>Check ticket details</strong>
		</h2>
		<form action="controller" method="post" class="w3-container">
			<input type="hidden" name="command" value="confirm-payment">
			<table
				class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
				<tr class="w3-blue-grey">
					<th style="display: none;"></th>
					<th>Flight number</th>
					<th>Departing at</th>
					<th>From</th>
					<th>To</th>
					<th>Place number</th>
					<th>Priority registration</th>
					<th>Priority boarding</th>
					<th>Luggage</th>
					<th>Total price, $</th>
				</tr>
				<c:forEach var="info" items="${sessionScope.tickets}">
					<tr>
						<td style="display: none;"><input type="hidden"
							name="ticket_id" type="text"
							value="<c:out value="${info.ticketId}" />"></td>
						<td><c:out value="${info.flightNumber}" /></td>
						<td><c:out value="${info.departing}" /></td>
						<td><c:out value="${info.fromPlace}" /></td>
						<td><c:out value="${info.toPlace}" /></td>
						<td><c:out value="${info.placeNumber}" /></td>
						<td>
							<c:if test="${info.priorityRegistration == true}">
								<strong>+</strong>
							</c:if>
							<c:if test="${info.priorityRegistration == false}">
								<strong>-</strong>
							</c:if>
						</td>
						<td>
							<c:if test="${info.priorityBoarding == true}">
								<strong>+</strong>
							</c:if>
							<c:if test="${info.priorityBoarding == false}">
								<strong>-</strong>
							</c:if>
						</td>
						<td>
							<c:if test="${info.luggage == true}">
								<strong>+</strong>
							</c:if>
							<c:if test="${info.luggage == false}">
								<strong>-</strong>
							</c:if>
						</td>
						<td><c:out value="${info.finalPrice}" /></td>
					</tr>
				</c:forEach>
			</table>
			<p>
				<button style="height: 50px; width: 100px" class="w3-button w3-black">Pay</button>
			</p>
		</form>
		<form action="controller" method="post" class="w3-container">
			<input type="hidden" name="command" value="back-to-buy-page">
			<input type="hidden" name="flight_id" type="text"
				value="<c:out value="${sessionScope.ticket.flightId}" />">
			<button class="w3-button w3-black">Back to choose a place</button>
		</form>
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