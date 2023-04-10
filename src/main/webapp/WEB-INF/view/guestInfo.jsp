<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.guest_info.guest_info"/><br/>
<div class="body-title">
	<a href="goToMainPage">
	<spring:message code="local.guest_info.news"/></a>
	<spring:message code="local.guest_info.latest_news"/>
</div>

<form action="command.do?method=delete" method="post">
	<c:forEach var="news" items="${listNews}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}"/>
				</div>
				<div class="news-date">
					<c:out value="${news.dateOfCreate}"/>
				</div>
				
				<div class="news-content">
					<c:out value="${news.brief}"/>
				</div>
			</div>
		</div>
		
	</c:forEach>
	
	<div class="no-news">
		<c:if test="${listNews eq nule}">
			<spring:message code="local.guest_info.no_news"/>
		</c:if>
	</div>
</form>
