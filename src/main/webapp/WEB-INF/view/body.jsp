<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.presentation eq 'newsList' }">
	<c:import url="/WEB-INF/view/newsList.jsp" />
</c:if>

<c:if test="${sessionScope.presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/view/viewNews.jsp" />
</c:if>

<c:if test="${sessionScope.presentation eq 'edit' }">
	<c:import url="/WEB-INF/view/editNews.jsp" />
</c:if>

<c:if test="${sessionScope.presentation eq 'add' }">
	<c:import url="/WEB-INF/view/addNews.jsp" />
</c:if>
