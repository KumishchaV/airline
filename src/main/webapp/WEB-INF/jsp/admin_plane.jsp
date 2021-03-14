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
		<h2><c:out value="${sessionScope.message}" /></h2>
	</div>
	<br>
	<div class="w3-container w3-row">
	<div class="w3-container w3-half">
		<h5>
			<strong>Add plane</strong>
		</h5>
		<form action="controller" method="post" class="w3-container">
			<input type="hidden" name="command" value="add_plane">
			<p>
				Plane&nbsp;type(name):&nbsp;<input
					onkeypress="return event.keyCode != 13;" required name="plane_type"
					type="text" placeholder="max 45 chars" maxlength="45">
			</p>
			<p>
				Number&nbsp;of&nbsp;seats:&nbsp;<input
					onkeypress="return event.keyCode != 13;" required
					name="number_of_seats" type="text"
					placeholder="min 100 max 999" pattern="[1-9][0-9]{2}"><br>
				Note: in aircraft with 160 seats and less - 4 seats in a row, otherwise - 6 seats in a row 
			</p>
			<p>
				Number&nbsp;of&nbsp;pilots:&nbsp;<input
					onkeypress="return event.keyCode != 13;" required
					name="number_of_pilots" type="text"
					placeholder="min 2 max 5" pattern="[2-5]">
			</p>
			<p>
				Number&nbsp;of&nbsp;stewardesses:&nbsp; <input
					onkeypress="return event.keyCode != 13;" required
					name="number_of_stewardesses" type="text"
					placeholder="min 3 max 29" pattern="([1-2][0-9])|[3-9]">
			</p>
			<p>
				<button class="w3-button w3-grey">Add plane</button>
			</p>
		</form>
	</div>
	<div class="w3-container w3-half">
		<c:if test="${requestScope.plane.unBlockedEdit}">
			<h5>
				<strong>Edit plane info</strong>
			</h5>
			<form action="controller" method="post" class="w3-container">
				<input type="hidden" name="command" value="edit_plane">
				<input type="hidden" name="plane_id" type="text"
						value="<c:out value="${requestScope.plane.planeId}" />">
				<p>

					Plane&nbsp;type(name):&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="plane_type" type="text" placeholder="max 45 chars" maxlength="45"
						value="<c:out value="${requestScope.plane.planeType}" /> ">
				</p>
				<p>
					Number&nbsp;of&nbsp;seats:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="number_of_seats" type="text"
						placeholder="min 100 max 999" pattern="[1-9][0-9]{2}"
						value="<c:out value="${requestScope.plane.numberOfSeats}" />"><br>
				Note: in aircraft with 160 seats and less - 4 seats in a row, otherwise - 6 seats in a row
				<p>
					Number&nbsp;of&nbsp;pilots:&nbsp;<input
						onkeypress="return event.keyCode != 13;" required
						name="number_of_pilots" type="text"
						placeholder="min 2 max 5" pattern="[2-5]"
						value="<c:out value="${requestScope.plane.numberOfPilots}" />">
				</p>
				<p>
					Number&nbsp;of&nbsp;stewardesses:&nbsp; <input
						onkeypress="return event.keyCode != 13;" required
						name="number_of_stewardesses" type="text"
						placeholder="min 3 max 29" pattern="([1-2][0-9])|[3-9]"
						value="<c:out value="${requestScope.plane.numberOfStewardesses}" />">
				</p>
				<p>
					<button style="height:50px;width:100px" class="w3-button w3-grey">Edit</button>
				</p>
			</form>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="view-all-plane" />
				<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
			</form>
		</c:if>
	</div>
	</div>
	<c:if test="${empty sessionScope.planes}">
		<h2 class="w3-container w3-center">
			<strong>Please add a plane</strong>
		</h2>
	</c:if>	
	<c:if test="${not empty sessionScope.planes}">
		<div class="w3-container w3-center">
			<table
				class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
				<caption>
					<h3>
						<strong>Planes</strong>
					</h3>
				</caption>
				<tr class="w3-blue-grey">
					<th style="display: none;">id</th>
					<th>Type of plane</th>
					<th>Number of seats</th>
					<th>Required number of pilots</th>
					<th>Required number of stewardesses</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="info" items="${sessionScope.planes}">
					<tr>
						<td style="display: none;"><c:out value="${info.planeId}" /></td>
						<td><c:out value="${info.planeType}" /></td>
						<td><c:out value="${info.numberOfSeats}" /></td>
						<td><c:out value="${info.numberOfPilots}" /></td>
						<td><c:out value="${info.numberOfStewardesses}" /></td>
						<td><form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-plane">
								<input type="hidden" name="plane_id" type="text" value="<c:out value="${info.planeId}" />">
								<button class="w3-btn w3-black">Edit</button>
							</form></td>
						<td><form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="delete-plane">
								<input type="hidden" name="plane_id" type="text" value="<c:out value="${info.planeId}" />">
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