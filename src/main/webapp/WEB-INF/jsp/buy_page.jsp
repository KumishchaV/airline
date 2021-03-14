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

	<div class="w3-container w3-center w3-border-bottom">
		<h3>
			<strong>From <c:out value="${sessionScope.ticket.fromPlace}" />
				- To <c:out value="${sessionScope.ticket.toPlace}" />
			</strong>
		</h3>

		<h5>
			Departing at&nbsp;&nbsp;-&nbsp;&nbsp;Flight time&nbsp;&nbsp;-&nbsp;&nbsp;Arriving at
		</h5>
		<h3>
			<strong>
			<c:out value="${sessionScope.ticket.departing}" />
			-
			<c:out value="${sessionScope.ticket.flightTime}" />
			-
			<c:out value="${sessionScope.ticket.arriving}" />
			</strong>
		</h3>
		<h4>
			<strong>
			Flight number:
			<c:out value="${sessionScope.ticket.flightNumber}" />
			, Plane:
			<c:out value="${sessionScope.ticket.planeType}" />
			</strong>
		</h4>
	</div>
	<br>
	
	<div class="w3-container w3-center">
	<h3  class="w3-red">
		<strong><c:out value="${requestScope.messageSelectError}" /></strong>
	</h3>
		<c:if test="${sessionScope.ticket.placesVisible}">
			<h3>
				<strong>Choose a place(places)</strong>
			</h3>
			<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="select-ticket-parameters">
					<input type="hidden" name="flight_id" type="text" value="<c:out value="${sessionScope.ticket.flightId}" />">
					
					<c:if test="${sessionScope.places.size() >= 160}">
					<table class="w3-table w3-centered">
						<tr class="w3-border-bottom">
							<th style="width:30%"></th>
							<th style="width:4%">A</th>
							<th style="width:4%">B</th>
							<th style="width:4%">C</th>
							<th style="width:20px"></th>
							<th style="width:4%">D</th>
							<th style="width:4%">E</th>
							<th style="width:4%">F</th>
							<th style="width:30%"></th>
						</tr>
						<tr>
							<th style="width:30%"></th>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="0" step="6" varStatus="index">
								<p>
								
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="1" step="6" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="2" step="6" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:20px">
							</td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="3" step="6" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="4" step="6" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="5" step="6" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<th style="width:30%"></th>
						</tr>
					</table>
					</c:if>
					
					<c:if test="${sessionScope.places.size() < 160}">
					<table class="w3-table w3-centered">
						<tr class="w3-border-bottom">
							<th style="width:40%"></th>
							<th style="width:4%">A</th>
							<th style="width:4%">B</th>
							<th style="width:20px"></th>
							<th style="width:4%">C</th>
							<th style="width:4%">D</th>
							<th style="width:40%"></th>
						</tr>
						<tr>
							<th style="width:40%"></th>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="0" step="4" varStatus="index">
								<p>
								
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="1" step="4" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:20px">
							</td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="2" step="4" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							<td style="width:4%"><c:forEach var="info" items="${sessionScope.places}" begin="3" step="4" varStatus="index">
								<p>
									<input class="w3-check" type="checkbox" name="place_number" value="<c:out value="${info}" />"
									<c:forEach var="info2" items="${sessionScope.blockedPlaces}">
									<c:if test="${info2.placeNumber == info}">disabled</c:if>
									</c:forEach>>
									<label>
										<c:out value="${info}" />
									</label>
								</p>
								</c:forEach></td>
							
							<th style="width:40%"></th>
						</tr>
					</table>
					</c:if>
					
					<p>
						<button style="height:50px;width:100px" class="w3-button w3-black">Next</button>
					</p>
				</form>	
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="go-to-client-page">
					<button style="height:50px;width:100px" class="w3-button w3-black">Back</button>
				</form>
		</c:if>
	</div>
	<div class="w3-container w3-center">
	<c:if test="${sessionScope.ticket.bookedPlacesVisible}">
		<h3>
			<strong>Select additional options</strong>
		</h3>
			<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="go-to-pay-page">
					<input type="hidden" name="flight_id" type="text" value="<c:out value="${sessionScope.ticket.flightId}" />">
					<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
						<tr class="w3-blue-grey">
							<th>Place number</th>
							<th>Priority registration<br>(+15$)</th>
							<th>Priority boarding<br>(+10$)</th>
							<th>Luggage<br>(+20$)</th>
						</tr>
					<c:forEach var="info" items="${sessionScope.tickets}">					
						<tr>
							<th><c:out value="${info.placeNumber}" /></th>
							<th>
								<input class="w3-check" type="checkbox" name="priority_registration" value="<c:out value="${info.ticketId}" />">
							</th>
							<th>
								<input class="w3-check" type="checkbox" name="priority_boarding" value="<c:out value="${info.ticketId}" />">
							</th>
							<th>
								<input class="w3-check" type="checkbox" name="luggage" value="<c:out value="${info.ticketId}" />">
							</th>
						</tr>
					</c:forEach>
					</table>
					<p>
					<button style="height:50px;width:100px" class="w3-button w3-black">Next</button>
					</p>
				</form>	
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="back-to-buy-page">
					<input type="hidden" name="flight_id" type="text" value="<c:out value="${sessionScope.ticket.flightId}" />">
					<button class="w3-button w3-black">Back to choose a place</button>
				</form>
		</c:if>
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