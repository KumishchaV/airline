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
			<input type="hidden" name="command" value="add_flight">
			<p>
				Date&nbsp;and&nbsp;time:&nbsp;<input
					onkeypress="return event.keyCode != 13;" required name="date"
					type="datetime-local" placeholder="YYYY-MM-DD HH:MM" 
					pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))\s[0-2]?[0-3][:][0-5][0-9]">
			</p>
			<p>
				<select class="w3-select" name="flight_info_id" required>
					<option value="" disabled selected>Select flight</option>
					<c:forEach var="info" items="${sessionScope.flightInfos}">
						<option value="${info.flightInfoId}">${info.flightNumber}: ${info.fromPlace} - ${info.toPlace},
							<c:forEach var="info2" items="${sessionScope.planes}">
							<c:if test="${info.planeId == info2.planeId}"> ${info2.planeType}, ${info2.numberOfSeats}</c:if>
							</c:forEach> seats</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<button class="w3-button w3-grey">Enter</button>
			</p>
		</form>
	</div>
	<div class="w3-container w3-half">
		<c:if test="${requestScope.flight.unBlockedFlight}">
			<h5>
				<strong>Edit flight details</strong>
			</h5>
			<form action="controller" method="post" class="w3-container">
				<input type="hidden" name="command" value="edit_flights">
					<p>
						<input type="hidden" name="flight_id" type="text"
							value="<c:out value="${requestScope.flight.flightId}" />">
					</p>
					<p>
						Date&nbsp;and&nbsp;time:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required name="date"
						type="datetime-local" value="<c:out value="${requestScope.flight.strDate}" />" placeholder="YYYY-MM-DD HH:MM" 
					pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))\s[0-2]?[0-3][:][0-5][0-9]">
					</p>
					<p>
						<select class="w3-select" name="flight_info_id" required>
							<option value="" disabled>Select flight</option>
							<c:forEach var="info" items="${sessionScope.flightInfos}">
								<option value="${info.flightInfoId}"
								<c:if test="${info.flightInfoId == flight.flightInfoId}">selected</c:if>>${info.flightNumber}:
									${info.fromPlace} - ${info.toPlace},
									<c:forEach var="info2" items="${sessionScope.planes}">
										<c:if test="${info.planeId == info2.planeId}"> ${info2.planeType}, ${info2.numberOfSeats}</c:if>
									</c:forEach> seats
								</option>
							</c:forEach>
						</select>
					</p>
					<p>
						<button style="height:50px;width:100px" class="w3-button w3-grey">Edit</button>
					</p>
				</form>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="view-all-flight-with-info" />
					<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
				</form>
			</c:if>
		</div>
	</div>
	<c:if test="${empty sessionScope.flights}">
		<h2 class="w3-container w3-center">
			<strong>Please add information about flight</strong>
		</h2>
	</c:if>	
	<c:if test="${not empty sessionScope.flights}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Upcoming flights</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th style="display: none;">id</th>
				<th>Flight number</th>
				<th>Flight date</th>
				<th>From place</th>
				<th>To place</th>
				<th>Flight filling</th>
				<th>Starting price, $</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="info" items="${sessionScope.flights}">
				<tr> <c:if test="${info.date > info.nowDate}">
					<td style="display: none;"><c:out value="${info.flightId}" /></td>
					<td><c:out value="${info.flightNumber}" /></td>
					<td><c:out value="${info.date}" /></td>
					<td><c:out value="${info.fromPlace}" /></td>
					<td><c:out value="${info.toPlace}" /></td>
					<td><c:out value="${info.filling}" />/<c:out value="${info.numberOfSeats}" /></td>
					<td><c:out value="${info.startingPrice}" /></td>
					<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-flight">
							<input type="hidden" name="flight_id" type="text" value="<c:out value="${info.flightId}" />">
							<button class="w3-btn w3-black">Edit</button>
						</form></td>
						<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="delete-flight">
							<input type="hidden" name="flight_id" type="text" value="<c:out value="${info.flightId}" />">
							<button class="w3-btn w3-black">Delete</button>
						</form></td>
			</c:if>	</tr>
			</c:forEach>
		</table>
	</div>
	<br>
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Completed flights</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th style="display: none;">id</th>
				<th>Flight number</th>
				<th>Flight date</th>
				<th>From place</th>
				<th>To place</th>
				<th>Flight filling</th>
				<th>Starting price, $</th>
				<th></th>
			</tr>
				<c:forEach var="info" items="${sessionScope.flights}">
					<tr>
						<c:if test="${info.date < info.nowDate}">
							<td style="display: none;"><c:out value="${info.flightId}" /></td>
							<td><c:out value="${info.flightNumber}" /></td>
							<td><c:out value="${info.date}" /></td>
							<td><c:out value="${info.fromPlace}" /></td>
							<td><c:out value="${info.toPlace}" /></td>
							<td><c:out value="${info.filling}" />/<c:out value="${info.numberOfSeats}" /></td>
							<td><c:out value="${info.startingPrice}" /></td>
							<td><form action="controller" method="post">
									<input type="hidden" name="command" class="w3-btn w3-black"
										value="delete-flight">
										<input type="hidden" name="flight_id" type="text"
										value="<c:out value="${info.flightId}" />">
									<button class="w3-btn w3-black">Delete</button>
								</form></td>
						</c:if>
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