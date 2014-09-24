<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title></title>
</head>
<body>
	<form action="<c:url value='/connect/facebook'/>" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<p>You haven't created any connections with Facebook yet. Click
			the button to create a connection between your account and your
			Facebook profile. (You'll be redirected to Facebook where you'll be
			asked to authorize the connection.)</p>
		<p>
			<button type="submit">
				<img
					src="<c:url value='/static/new/img/social/botao-conectar-com-facebook.png' />" />
			</button>
		</p>
	</form>
</body>
</html>