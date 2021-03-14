<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AIR LowCost</title>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename = "localization.local" var="loc"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.find" var="find"/> 
<fmt:message bundle = "${loc}" key="local.locbutton.name.view_all" var="view_all"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.edit_email_password" var="edit_email_password"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.add_money" var="add_money"/>
<fmt:message bundle = "${loc}" key="local.registration" var="registration"/>
<fmt:message bundle = "${loc}" key="local.signIn" var="signIn"/>
<fmt:message bundle = "${loc}" key="local.signOut" var="signOut"/>
<fmt:message bundle = "${loc}" key="local.from" var="from"/>
<fmt:message bundle = "${loc}" key="local.to" var="to"/>
<fmt:message bundle = "${loc}" key="local.date" var="date"/>
<fmt:message bundle = "${loc}" key="local.hello" var="hello"/>
<fmt:message bundle = "${loc}" key="local.balance_is" var="balance_is"/>
<fmt:message bundle = "${loc}" key="local.completed_buy" var="completed_buy"/>
<fmt:message bundle = "${loc}" key="local.table.ticket_number" var="ticket_number"/>
<fmt:message bundle = "${loc}" key="local.table.flight_number" var="flight_number"/>
<fmt:message bundle = "${loc}" key="local.table.departing" var="departing"/>
<fmt:message bundle = "${loc}" key="local.table.place_number" var="place_number"/>
<fmt:message bundle = "${loc}" key="local.table.priority_registration" var="priority_registration"/>
<fmt:message bundle = "${loc}" key="local.table.priority_boarding" var="priority_boarding"/>
<fmt:message bundle = "${loc}" key="local.table.luggage" var="luggage"/>
<fmt:message bundle = "${loc}" key="local.table.total_price" var="total_price"/>
<fmt:message bundle = "${loc}" key="local.table.return_ticket" var="return_ticket"/>
<fmt:message bundle = "${loc}" key="local.table.flight_completed" var="flight_completed"/>
<fmt:message bundle = "${loc}" key="local.money_returned" var="money_returned"/>

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
							<c:if test="${sessionScope.account.name == null}">
								<a href="authorization">${signIn}</a>
							</c:if>
						</h5>
					</div>
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.account.name != null}">
								<a
									href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">${signOut}</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<a href="authorization">${registration}</a>
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
		class="w3-container " style="text-align: center">
		<input type="hidden" name="command" value="searching_results">
		<p>
			<strong>${from}:</strong>&nbsp;<input type="text" name="from_place"
				placeholder="For example Minsk" maxlength="45"/> <strong> ${to}:</strong>&nbsp;<input
				type="text" name="to_place" placeholder="For example Vilnius" maxlength="45"/> <strong>
				${date}:</strong>&nbsp;<input type="date" name="date" placeholder="yyyy-mm-dd"
				pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" />
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
	<div class="w3-container w3-center">
		<h2 class="w3-green">
			<c:out value="${sessionScope.congratulationsMessage}" />
		</h2>
		<h2 class="w3-red">
			<c:out value="${sessionScope.errorMessage}" />
			<c:out value="${requestScope.messageErrorEmailAndPasswordFormat}" />
			<c:out value="${requestScope.messageIncorrectPassword}" />
		</h2>
		<h2 class="w3-green">
			<c:out value="${sessionScope.message}" />
		</h2>
	</div>
	<div class="w3-border-bottom">
		<div class="w3-row">
			<div class="w3-col w3-container w3-half">
				<h1>${hello} ${sessionScope.account.name}!</h1>
				<br>
				<c:if test="${empty sessionScope.unblock}">
						<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="unblock-edit-email-password">
								<button class="w3-btn w3-black">${edit_email_password}</button>
							</form>
						<br>
						</c:if>
						<c:if test="${sessionScope.unblock == true}">
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="edit-email-password">
								<p>Current Password<br>
									<input	onkeypress="return event.keyCode != 13;" required
										name="old_password" type="text" placeholder="max 45 chars"
										maxlength="45">
								</p>
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
										maxlength="45" value="<c:out value="${sessionScope.account.email}" />">
								</p>
								<button class="w3-btn w3-black">Save</button>
							</form>
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="go-to-client-page">
								<p><button class="w3-btn w3-black">Cancel</button></p>
							</form>
							<br>
						</c:if>
			</div>
			<div class="w3-col w3-container w3-half">
				<h2>${balance_is} ${sessionScope.account.balance}$</h2>
				<form action="controller" method="post">
				<input type="hidden" name="command" value="add-money">
				<input type="hidden" name="add_money" type="text" value="100">
				<button class="w3-button w3-black">${add_money}</button>
				</form>
				<br>
			</div>
		</div>
	</div>
	<br>
	<div class="w3-container w3-center">
		<h3>
			<strong>${completed_buy}</strong>
		</h3>
		<form action="controller" method="post" class="w3-container">
			<table
				class="w3-table w3-bordered w3-border w3-hoverable w3-centered">
				<tr class="w3-blue-grey">
					<th>${ticket_number}</th>
					<th>${flight_number}</th>
					<th>${departing}</th>
					<th>${from}</th>
					<th>${to}</th>
					<th>${place_number}</th>
					<th>${priority_registration}</th>
					<th>${priority_boarding}</th>
					<th>${luggage}</th>
					<th>${total_price}, $</th>
					<th>${return_ticket}*</th>
				</tr>
				<c:forEach var="info" items="${sessionScope.tickets}">
					<tr>
						<td><p><c:out value="${info.ticketNumber}" /></p></td>
						<td><p><c:out value="${info.flightNumber}" /></p></td>
						<td><p><c:out value="${info.departing}" /></p></td>
						<td><p><c:out value="${info.fromPlace}" /></p></td>
						<td><p><c:out value="${info.toPlace}" /></p></td>
						<td><p><c:out value="${info.placeNumber}" /></p></td>
						<td>
							<c:if test="${info.priorityRegistration == true}">
								<p><strong>+</strong></p>
							</c:if>
							<c:if test="${info.priorityRegistration == false}">
								<p><strong>-</strong></p>
							</c:if>
						</td>
						<td>
							<c:if test="${info.priorityBoarding == true}">
								<p><strong>+</strong></p>
							</c:if>
							<c:if test="${info.priorityBoarding == false}">
								<p><strong>-</strong></p>
							</c:if>
						</td>
						<td>
							<c:if test="${info.luggage == true}">
								<p><strong>+</strong></p>
							</c:if>
							<c:if test="${info.luggage == false}">
								<p><strong>-</strong></p>
							</c:if>
						</td>
						<td><p><c:out value="${info.finalPrice}" /></p></td>
						<td>
						<c:if test="${sessionScope.nowDate < info.departing}">
							<form action="controller" method="post">
								<input type="hidden" name="command" class="w3-btn w3-black"	value="delete-ticket">
								<input type="hidden" name="ticket_id" type="text" value="<c:out value="${info.ticketId}" />">
								<button class="w3-btn w3-black">${return_ticket}</button>
							</form>
						</c:if>
						<c:if test="${sessionScope.nowDate >= info.departing}">
							<p>${flight_completed}</p>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.tickets.size() > 0}">
				<h3><strong>* ${money_returned}</strong></h3>
			</c:if>
		</form>
		<c:if test="${sessionScope.tickets.size() == 0}">
			<h4><strong>Not bought yet</strong></h4>
		</c:if>
	</div>
	<br>


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