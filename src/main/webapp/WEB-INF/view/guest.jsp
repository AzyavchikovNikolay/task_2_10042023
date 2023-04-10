<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not (sessionScope.presentation eq 'registration')}">
	<c:import url="/WEB-INF/view/guestInfo.jsp" />
</c:if>

<c:if test="${sessionScope.presentation eq 'registration' }">
	<c:import url="/WEB-INF/view/registrationForm.jsp" />
</c:if>