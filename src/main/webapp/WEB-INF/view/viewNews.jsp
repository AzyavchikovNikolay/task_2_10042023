<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${sessionScope.edit_result eq 'success'}">
			<div class=registration-success><spring:message code="local.viewNews.edit_success"/></div>
			<c:set var="edit_result" scope="session" value=""/>
</c:if>

<div class="body-title">
	<a href="goToNewsList">
	<spring:message code="local.viewNews.news"/></a>
	<spring:message code="local.viewNews.view_news"/>
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"><spring:message code="local.viewNews.news_title"/></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsView.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.viewNews.news_date"/></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsView.dateOfCreate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.viewNews.news_brief"/></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsView.brief}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.viewNews.news_content"/></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsView.content }" />
				</div></td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="goToEditNewsForm" method="get">
		<input type="hidden" name="id" value="${newsView.id}" />
		<input type="submit" value="<spring:message code="local.viewNews.edit"/>" />
		<c:set var="for_submit_back" scope="session" value="view_news"/>
	</form>
</div>

<div class="second-view-button">
	<form action="doDeleteNews" method="post">
		<input type="hidden" name="id" value="${newsView.id}" />
		<input type="submit" value="<spring:message code="local.viewNews.delete"/>" />
	</form>
</div>
</c:if>

<div class="first-view-button">
	<form action="goToNewsList" method="get">
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.addNews.back_to_news_list"/>"/>
			</div>
	</form>
</div>