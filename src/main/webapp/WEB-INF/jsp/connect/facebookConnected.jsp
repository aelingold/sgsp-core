<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h3>Connected to Facebook</h3>

<form id="disconnect" action="<c:url value='/connect/facebook'/>" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="_method" value="delete" />
	<div class="formInfo">
		<p>
			SinGuia is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
</form>