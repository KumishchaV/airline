<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AIR LowCost - Authorization Page</title>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename = "localization.local" var="loc"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle = "${loc}" key="local.logination" var="logination"/>
<fmt:message bundle = "${loc}" key="local.enter.login_password" var="login_password"/>
<fmt:message bundle = "${loc}" key="local.enter.data" var="data"/>
<fmt:message bundle = "${loc}" key="local.registration" var="registration"/>
<fmt:message bundle = "${loc}" key="local.authorization" var="authorization"/>
<fmt:message bundle = "${loc}" key="local.signIn" var="signIn"/>
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
				<div class="w3-row"></div>
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
	<br>
	<div class="w3-container w3-border-bottom w3-center">
		<h2>
			<strong>${authorization}</strong>
		</h2>
	</div>
	<div class="w3-container w3-row">
		<div class="w3-container w3-half">
			<h3>
				<strong>${logination}</strong>
			</h3>
			<p>${login_password}</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="logination"> <input
					onkeypress="return event.keyCode != 13;" name="login" required
					class="w3-input" type="text" placeholder="Login" maxlength="45">
				<h3><c:out value="${messageLoginNotFound}" /></h3>
				<br> <input onkeypress="return event.keyCode != 13;"
					name="password" required class="w3-input" type="password"
					placeholder="Password" maxlength="45">
				<h3><c:out value="${messageWrongPassword}" /></h3>
				<br>
				<button class="w3-button w3-black">${signIn}</button>
			</form>
		</div>

		<div class="w3-container w3-half">
			<h3>
				<strong>${registration}</strong>
			</h3>
			<h4><c:out value="${requestScope.messageLoginAlreadyExists}" />
				<c:out value="${requestScope.messageErrorEmailAndPasswordFormat}" />
				<c:out value="${requestScope.messageIncorrectPassword}" />
			</h4>
			<p>${data}</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="registration"> <input
					onkeypress="return event.keyCode != 13;" name="login" required
					class="w3-input" type="text" placeholder="Login" maxlength="45"><br>
				<input onkeypress="return event.keyCode != 13;" name="password"
					required class="w3-input" type="password" placeholder="Password" maxlength="45"><br>
				<input onkeypress="return event.keyCode != 13;" name="password2"
					required class="w3-input" type="password"
					placeholder="Password the second time" maxlength="45"><br> <input
					onkeypress="return event.keyCode != 13;" name="email" required
					class="w3-input" type="text" placeholder="E-mail" maxlength="45"><br>
				<input onkeypress="return event.keyCode != 13;" name="name" required
					class="w3-input" type="text" placeholder="Name" maxlength="45"><br> <input
					onkeypress="return event.keyCode != 13;" name="surname" required
					class="w3-input" type="text" placeholder="Surname" maxlength="45"><br>
				<button class="w3-button w3-black">${registration}</button>
			</form>
		</div>
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