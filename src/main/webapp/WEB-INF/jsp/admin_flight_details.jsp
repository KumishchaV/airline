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
				<strong>Add flight details</strong>
			</h5>
			<form action="controller" method="post" class="w3-container">
				<input type="hidden" name="command" value="add_flight_info">
				<p>
					Flight&nbsp;number:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="fligh_number" type="text" placeholder="ALXXXXX"
						pattern="[A][L]\d{4}">
				</p>
				<p>
					From&nbsp;place:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="from_place" type="text" placeholder="max 45 chars" maxlength="45">
				</p>
				<p>
					To&nbsp;place:&nbsp;<input onkeypress="return event.keyCode != 13;"
						required name="to_place" type="text" placeholder="max 45 chars" maxlength="45">
				</p>
				<p>
					Time&nbsp;in&nbsp;flight:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="flight_time" type="text" placeholder="Format is 4:30:12" 
						pattern="[0-2]?[0-3][:][0-5][0-9][:][0-5][0-9]">
				</p>
				<p>
					Starting&nbsp;price:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="starting_price" type="text" placeholder="30.20"
						pattern="\d+(\.\d{0,2})?">&nbsp;$
				</p>
				<p>
					<select class="w3-select" name="plane_id" required>
						<option value="" disabled selected>Select plane</option>
						<c:forEach var="info" items="${sessionScope.planesToFlight}">
							<option value="${info.planeId}">${info.planeType} - ${info.numberOfSeats} seats</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<button class="w3-button w3-grey">Enter</button>
				</p>
			</form>
		</div>
		<div class="w3-container w3-half">
			<c:if test="${requestScope.flightInfo.unBlockedFlightInfo}">
				<h5>
					<strong>Edit flight details</strong>
				</h5>
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="edit_flight_info">
					<p>
						<input type="hidden" name="flight_info_id" type="text"
							value="<c:out value="${requestScope.flightInfo.flightInfoId}" />">
					</p>
					<p>
						Flight&nbsp;number:&nbsp;<input
							onkeypress="return event.keyCode != 13;" required
							name="fligh_number" type="text"
							value="<c:out value="${requestScope.flightInfo.flightNumber}" />"
							placeholder="ALXXXXX" pattern="[A][L]\d{4}">
					</p>
					<p>
						From&nbsp;place:&nbsp;<input
							onkeypress="return event.keyCode != 13;" required
							name="from_place" type="text" placeholder="max 45 chars" maxlength="45"
							value="<c:out value="${requestScope.flightInfo.fromPlace}" />">
					</p>
					<p>
						To&nbsp;place:&nbsp;<input
							onkeypress="return event.keyCode != 13;" required name="to_place"
							type="text" placeholder="max 45 chars" maxlength="45" 
							value="<c:out value="${requestScope.flightInfo.toPlace}" />">
					</p>
					<p>
						Time&nbsp;in&nbsp;flight:&nbsp;<input
							onkeypress="return event.keyCode != 13;" required
							name="flight_time" type="text"
							value="<c:out value="${requestScope.flightInfo.flightTime}" />"
							placeholder="Format is 4:30:12" pattern="[0-2]?[0-3][:][0-5][0-9][:][0-5][0-9]">
					</p>
					<p>
						Starting&nbsp;price:&nbsp;<input
							onkeypress="return event.keyCode != 13;" required
							name="starting_price" type="text"
							value="<c:out value="${requestScope.flightInfo.startingPrice}" />"
							placeholder="30.20" pattern="\d+(\.\d{0,2})?">&nbsp;$
					</p>
					<p>
						<select class="w3-select" name="plane_id" required>
							<option value="" disabled>Select plane</option>
							<c:forEach var="info" items="${sessionScope.planesToFlight}">
								<option value="${info.planeId}"
									<c:if test="${info.planeId == requestScope.flightInfo.planeId}">selected</c:if>>${info.planeType}
									- ${info.numberOfSeats} seats</option>
							</c:forEach>
						</select>
					</p>
					<p>
						<button style="height:50px;width:100px" class="w3-button w3-grey">Edit</button>
					</p>
				</form>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="view_all_flight_info" />
				<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
			</form>
			</c:if>
		</div>
	</div>
	<c:if test="${empty sessionScope.flightInfos}">
		<h2 class="w3-container w3-center">
			<strong>Please add information about flight</strong>
		</h2>
	</c:if>	
	<c:if test="${not empty sessionScope.flightInfos}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Flight details</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th style="display: none;">id</th>
				<th>Flight Number</th>
				<th>From place</th>
				<th>To place</th>
				<th>Time in flight</th>
				<th>Starting price, $</th>
				<th>Plane type / Seats</th>
				<th></th>
				<th></th>
			</tr>
				<c:forEach var="info" items="${sessionScope.flightInfos}">
					<tr>
						<td style="display: none;"><c:out
								value="${info.flightInfoId}" /></td>
						<td><c:out value="${info.flightNumber}" /></td>
						<td><c:out value="${info.fromPlace}" /></td>
						<td><c:out value="${info.toPlace}" /></td>
						<td><c:out value="${info.flightTime}" /></td>
						<td><c:out value="${info.startingPrice}" /></td>
						<td><c:forEach var="info2" items="${sessionScope.planesToFlight}">
								<c:if test="${info.planeId == info2.planeId}">${info2.planeType} / ${info2.numberOfSeats}</c:if>
							</c:forEach></td>
						<td><form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"
									value="unblock-edit-flight-info"> <input type="hidden"
									name="flight_info_id" type="text"
									value="<c:out value="${info.flightInfoId}" />">
								<button class="w3-btn w3-black">Edit</button>
							</form></td>
						<td><form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"
									value="delete-flight-info"> <input type="hidden"
									name="flight_info_id" type="text"
									value="<c:out value="${info.flightInfoId}" />">
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