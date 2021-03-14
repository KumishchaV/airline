<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AIR LowCost - Error</title>
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
								<a href="controller?command=go-to-client-page">${sessionScope.account.name}</a>
							</c:if>
							<c:if test="${sessionScope.user.role == 'admin'}">
								<a href="controller?command=go-to-admin-page">${sessionScope.user.name}</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<c:if test="${sessionScope.user.name == null}">
									<a href="authorization">SignIn</a>
								</c:if>
							</c:if>
						</h5>
					</div>
					<div class="w3-col w3-container w3-half">
						<h5>
							<c:if test="${sessionScope.account.name != null}">
								<a href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">SignOut</a>
							</c:if>
							<c:if test="${sessionScope.user.name != null}">
								<a href="controller?command=go-to-index-page&message=!!! We will be glad to see you again !!!">SignOut</a>
							</c:if>
							<c:if test="${sessionScope.account.name == null}">
								<c:if test="${sessionScope.user.name == null}">
									<a href="authorization">Registration</a>
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
	<br>
	<br>
	<h1 class="w3-container w3-center">!!! Something went wrong, check back later !!!</h1>
	<h2 class="w3-container w3-center">
		<c:out value="${requestScope.errorMessage}" />
	</h2>
	<br>
	<form class="w3-container w3-center" action="controller" method="get">
		<input type="hidden" name="command" value="go-to-index-page">
	    <input type="submit" value="to Home page">
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