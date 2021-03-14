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
			<c:if test="${sessionScope.flightHasEmployee.unBlockedAdd}">
				<h3>
					<c:out value="${sessionScope.messageAddError}" />
				</h3>
				<h4>
					<strong>Create crew for <c:out value="${sessionScope.flightHasEmployee.flightNumber}" /></strong>
				</h4>
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="add-crew">
					<input type="hidden" name="flight_id" type="text" value="<c:out value="${sessionScope.flightHasEmployee.flightId}" />">
					<input type="hidden" name="number_of_pilots" type="text" value="<c:out value="${sessionScope.flightHasEmployee.numberOfPilots}" />">
					<input type="hidden" name="number_of_stewardesses" type="text" value="<c:out value="${sessionScope.flightHasEmployee.numberOfStewardesses}" />">
					<table class="w3-table">
						<tr class="w3-border-bottom">
							<th>Choose from free pilots <br>
							(<c:out	value="${sessionScope.flightHasEmployee.numberOfPilots}" /> pilots required)
							</th>
							<th>Choose from free stewardesses <br> 
							(<c:out	value="${sessionScope.flightHasEmployee.numberOfStewardesses}" /> stewardesses required)
							</th>
						</tr>
						<tr>
							<td><c:forEach var="info" items="${sessionScope.freeEmployees}">
									<c:if test="${info.role == 'pilot'}">
										<p>
											<input class="w3-check" type="checkbox" name="employee_id"
												value="<c:out value="${info.employeeId}" />"> <label><c:out
													value="${info.name}" /></label>
										</p>
									</c:if>
								</c:forEach></td>
							<td><c:forEach var="info2" items="${sessionScope.freeEmployees}">
									<c:if test="${info2.role == 'stewardess'}">
										<p>
											<input class="w3-check" type="checkbox" name="employee_id"
												value="<c:out value="${info2.employeeId}" />"> <label><c:out
													value="${info2.name}" /></label>
										</p>
									</c:if>
								</c:forEach></td>
						</tr>
					</table>
					<p>
					<button style="height:50px;width:100px" class="w3-button w3-grey">Create</button>
					</p>
				</form>	
				<form action="controller" method="get">
					<input type="hidden" name="command" value="block-flight-has-employee" />
					<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
				</form>
			</c:if>
		</div>
		<div class="w3-container w3-half">
			<c:if test="${sessionScope.flightHasEmployee.unBlockedEdit}">
				<h3>
					<c:out value="${sessionScope.messageEditError}" />
				</h3>
				<h4>
					<strong>Edit crew for <c:out value="${sessionScope.flightHasEmployee.flightNumber}" /></strong>
				</h4>
				<form action="controller" method="post" class="w3-container">
					<input type="hidden" name="command" value="edit-crew">
					<input type="hidden" name="flight_id" type="text" value="<c:out value="${sessionScope.flightHasEmployee.flightId}" />">
					<input type="hidden" name="number_of_pilots" type="text" value="<c:out value="${sessionScope.flightHasEmployee.numberOfPilots}" />">
					<input type="hidden" name="number_of_stewardesses" type="text" value="<c:out value="${sessionScope.flightHasEmployee.numberOfStewardesses}" />">
					<table class="w3-table">
						<tr class="w3-border-bottom">
							<th>Choose from free pilots <br>
							(<c:out	value="${sessionScope.flightHasEmployee.numberOfPilots}" /> pilots required)
							</th>
							<th>Choose from free stewardesses <br> 
							(<c:out	value="${sessionScope.flightHasEmployee.numberOfStewardesses}" /> stewardesses required)
							</th>
						</tr>
						<tr>
							<td>
								<c:forEach var="info" items="${sessionScope.freeEmployees}">
									<c:if test="${info.role == 'pilot'}">
										<p>
											<input class="w3-check" type="checkbox" name="employee_id"
												value="<c:out value="${info.employeeId}" />"> <label><c:out
													value="${info.name}" /></label>
										</p>
									</c:if>
								</c:forEach></td>
							<td>
								<c:forEach var="info2" items="${sessionScope.freeEmployees}">
									<c:if test="${info2.role == 'stewardess'}">
										<p>
											<input class="w3-check" type="checkbox" name="employee_id"
												value="<c:out value="${info2.employeeId}" />"> <label><c:out
													value="${info2.name}" /></label>
										</p>
									</c:if>
								</c:forEach></td>
						</tr>
					</table>
					<p>
					<button style="height:50px;width:100px" class="w3-button w3-grey">Edit</button>
					</p>
				</form>	
				<form action="controller" method="get">
					<input type="hidden" name="command" value="block-flight-has-employee" />
					<button style="height:50px;width:130px" class="w3-button w3-grey">Cancel</button>
				</form>
			</c:if>
		</div>
	</div>
	<c:if test="${not empty sessionScope.flightsWithout}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Flights haven't employees</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th style="display: none;">flightId</th>
				<th>Flight number</th>
				<th>Date</th>
				<th>From place</th>
				<th>To place</th>
				<th></th>
			</tr>
			<c:forEach var="info" items="${sessionScope.flightsWithout}">
				<tr>
					<th style="display: none;"><c:out value="${info.flightId}" /></th>
					<td><c:out value="${info.flightNumber}" /></td>
					<td><c:out value="${info.date}" /></td>
					<td><c:out value="${info.fromPlace}" /></td>
					<td><c:out value="${info.toPlace}" /></td>	
					<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-add-crew">
							<input type="hidden" name="flight_id" type="text" value="<c:out value="${info.flightId}" />">
							<input type="hidden" name="date" type="text" value="<c:out value="${info.date}" />">
							<button class="w3-btn w3-black">Create a team</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	<br>
	<br>
	<br>
	<c:if test="${not empty sessionScope.flightsWith}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Flights have employees</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th style="display: none;">flightId</th>
				<th>Flight number</th>
				<th>Date</th>
				<th>From place</th>
				<th>To place</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="info3" items="${sessionScope.flightsWith}">
				<tr>
					<th style="display: none;"><c:out value="${info3.flightId}" /></th>
					<td><c:out value="${info3.flightNumber}" /></td>
					<td><c:out value="${info3.date}" /></td>
					<td><c:out value="${info3.fromPlace}" /></td>
					<td><c:out value="${info3.toPlace}" /></td>	
					<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-crew">
							<input type="hidden" name="date" type="text" value="<c:out value="${info3.date}" />">
							<input type="hidden" name="flight_id" type="text" value="<c:out value="${info3.flightId}" />">
							<button class="w3-btn w3-black">Edit</button>
						</form></td>
						<td><form action="controller" method="post">
							<input type="hidden" name="command" class="w3-btn w3-black"	value="delete-crew">
							<input type="hidden" name="flight_id" type="text" value="<c:out value="${info3.flightId}" />">
							<button class="w3-btn w3-black">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	<br>
	<c:if test="${empty sessionScope.teams}">
		<h2 class="w3-container w3-center">
			<strong>Please add a command to the flight for departure</strong>
		</h2>
	</c:if>	
	<br>
	<br>
	<c:if test="${not empty sessionScope.teams}">
		<div class="w3-container w3-center">
			<table class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
			<caption>
				<h2>
					<strong>Crews list</strong>
				</h2>
			</caption>
			<tr  class="w3-blue-grey">
				<th>Flight number</th>
				<th>Date</th>
				<th>From place</th>
				<th>To place</th>
				<th>Pilots</th>
				<th>Stewardess</th>
			</tr>
			<c:forEach var="info" items="${sessionScope.teams}">
				<tr>
					<td><c:out value="${info.flightNumber}" /></td>
					<td><c:out value="${info.date}" /></td>
					<td><c:out value="${info.fromPlace}" /></td>
					<td><c:out value="${info.toPlace}" /></td>
					<td><c:if test="${info.role == 'pilot'}"><c:out value="${info.name}" /></c:if></td>
					<td><c:if test="${info.role == 'stewardess'}"><c:out value="${info.name}" /></c:if></td>
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