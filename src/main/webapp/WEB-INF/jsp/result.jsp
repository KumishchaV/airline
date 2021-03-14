<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Searching results - AIR LowCost</title>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename = "localization.local" var="loc"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.find" var="find"/> 
<fmt:message bundle = "${loc}" key="local.locbutton.name.view_all" var="view_all"/>
<fmt:message bundle = "${loc}" key="local.registration" var="registration"/>
<fmt:message bundle = "${loc}" key="local.signIn" var="signIn"/>
<fmt:message bundle = "${loc}" key="local.signOut" var="signOut"/>
<fmt:message bundle = "${loc}" key="local.from" var="from"/>
<fmt:message bundle = "${loc}" key="local.to" var="to"/>
<fmt:message bundle = "${loc}" key="local.date" var="date"/>
<fmt:message bundle = "${loc}" key="local.results" var="results"/>
<fmt:message bundle = "${loc}" key="local.table.flight_number" var="flight_number"/>
<fmt:message bundle = "${loc}" key="local.table.flight_date" var="flight_date"/>
<fmt:message bundle = "${loc}" key="local.table.from" var="from_place"/>
<fmt:message bundle = "${loc}" key="local.table.to" var="to_place"/>
<fmt:message bundle = "${loc}" key="local.table.filling" var="filling"/>
<fmt:message bundle = "${loc}" key="local.table.price" var="price"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.buy" var="buy_button"/>

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
				<div class="w3-container w3-center">
				<div class="w3-col w3-container w3-half">
					<form action="controller" method="get">
        				<input type="hidden" name="command" value="local" />
      					<input type="hidden" name="local" value="ru" />
      					<button class="w3btn w3-ripple w3-indigo">${ru_button}</button>
					</form>
					</div>
					<div class="w3-col w3-container w3-half">
					<form action="controller" method="get">
						<input type="hidden" name="command" value="local" />
        				<input type="hidden" name="local" value="en" />
        				<button class="w3btn w3-ripple w3-indigo">${en_button}</button>
					</form>
					</div>
					</div>
				</div>
				<div class="w3-row">
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.account.name != null}">
								<a href="controller?command=go-to-client-page">${sessionScope.account.name}</a>
							</c:if>
							<c:if test="${sessionScope.user.role == 'admin'}">
								<a href="controller?command=go-to-admin-page">${sessionScope.user.name}</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<c:if test="${sessionScope.user.name == null}">
									<a href="authorization">${signIn}</a>
								</c:if>
							</c:if>
						</h5>
					</div>
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.account.name != null}">
								<a href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">${signOut}</a>
							</c:if>
							<c:if test="${sessionScope.user.role == 'admin'}">
								<a href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">${signOut}</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<c:if test="${sessionScope.user.name == null}">
									<a href="authorization">${registration}</a>
								</c:if>
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
	<form action="controller" method="get"
		class="w3-container" style="text-align: center">
		<input type="hidden" name="command" value="searching_results">
		<p>
			<strong>${from}:</strong>&nbsp;<input type="text" name="from_place" placeholder="For example Minsk" maxlength="45"/>
			<strong> ${to}:</strong>&nbsp;<input type="text" name="to_place" placeholder="For example Vilnius" maxlength="45"/>
			<strong> ${date}:</strong>&nbsp;<input type="date" name="date"	placeholder="yyyy-mm-dd"
			pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"/>
			<button class="w3-button w3-black">
				<img src="images/find.png" alt="find" width="20" height="20"
					style="vertical-align: middle">&nbsp;${find}
			</button>
		</p>
	</form>
	<form action="controller" method="get"
		class="w3-container w3-border-bottom" style="text-align: center">
		<input type="hidden" name="command" value="view-all-available-flights">
		<p>
			<button class="w3-button w3-black">${view_all}</button>
		</p><br>
	</form>
	<c:if test="${empty flights}">
		<h2 class="w3-container w3-center">
			<c:out value="${requestScope.message}" />
		</h2>
	</c:if>
	<c:if test="${not empty flights}">
		<div class="w3-container w3-center">
			<table
				class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
				<caption>
					<h3>
						<strong>${results}</strong>
					</h3>
				</caption>
				<thead>
					<tr class="w3-blue-grey">
						<th style="display: none;">id</th>
						<th>${flight_number}</th>
						<th>${flight_date}</th>
						<th>${from_place}</th>
						<th>${to_place}</th>
						<th>${filling}</th>
						<th>${price}</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="info" items="${flights}">
					<tr><c:if test="${info.date > info.nowDate}">
						<td style="display: none;"><c:out value="${info.flightId}" /></td>
						<td><c:out value="${info.flightNumber}" /></td>
						<td><c:out value="${info.date}" /></td>
						<td><c:out value="${info.fromPlace}" /></td>
						<td><c:out value="${info.toPlace}" /></td>
						<td><c:out value="${info.filling}" />/<c:out
								value="${info.numberOfSeats}" /></td>
						<td><c:out value="${info.startingPrice}" /></td>
						<td>
						<c:if test="${info.filling < info.numberOfSeats}">
							<c:if test="${sessionScope.account.role == 'client'}">
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="go-to-buy-page">
								<input type="hidden" name="flight_id" type="text" value="<c:out value="${info.flightId}" />">
								<button class="w3-btn w3-black">${buy_button}</button>
							</form>
							</c:if>
						</c:if>
						</td>
					</c:if></tr>
				</c:forEach>
				
			</table>
			
		</div>
	</c:if>
	<c:if test="${flights.size() == 0}">
		<h2 class="w3-container w3-center">Nothing found, please search	again</h2>
	</c:if>
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