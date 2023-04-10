<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/view/localizationVars.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<spring:message code="local.error_page"/>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_base_page"/>
		<div class="registration-field">
			<input type="submit" value="${back}"/>
		</div>
	</form>
