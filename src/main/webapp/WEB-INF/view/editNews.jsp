<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="body-title">
	<a href="goToNewsList">
	<spring:message code="local.editNews.news"/></a>
	<spring:message code="local.editNews.adding_news"/>
</div>

	<c:if test="${sessionScope.edit eq 'warning'}">
		<div class=registration-warning><spring:message code="local.editNews.warning"/></div>
		<c:set var="edit" scope="session" value=""/>
	</c:if>

	<form:form action="doEditNews" modelAttribute="news" method="post">
		<form:hidden path="id"/>
		<form:hidden path="dateOfCreate"/>
			<div class="add-table-margin">
				<table class="news_text_format">
					<tr>
						<td class="space_around_title_text"><spring:message code="local.editNews.news_title"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="2" cols="50" path="title"/>
							</div>
						</td>
					</tr>	
					
					<tr>
						<td class="space_around_title_text"><spring:message code="local.editNews.news_brief"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="5" cols="50" path="brief"/>
							</div>
						</td>
					</tr>	
					<tr>
						<td class="space_around_title_text"><spring:message code="local.editNews.news_content"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="8" cols="50" path="content"/>							
							</div>
						</td>
					</tr>	
					
					<tr>
						<td class="space_around_title_text"><spring:message code="local.editNews.current_news_status"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<c:if test="${news.statusOfNews.statusNews eq 'new'}">
									<spring:message code="local.editNews.new_news"/>
								</c:if>
								<c:if test="${news.statusOfNews.statusNews eq 'published'}">
									<spring:message code="local.editNews.published_news"/>
								</c:if>
							</div>
						</td>
					</tr>	
					
					
					<tr>
						<td class="space_around_title_text"><spring:message code="local.editNews.new_news_status"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:select path="statusOfNews.id">
									<form:option value="1"><spring:message code="local.addNews.new_news"/></form:option>
									<form:option value="2"><spring:message code="local.addNews.published_news"/></form:option>
								</form:select>
							</div>
						</td>
					</tr>	
				</table>
			</div>
				
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.editNews.edit_news"/>"/>
			</div>
		</form:form>
		
	<c:if test="${(sessionScope.for_submit_back eq 'view_news')}">	
		<form action="goToViewNews" method="get">
			<input type="hidden" name="id" value="${news.id}" />
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.editNews.back_to_news_list"/>"/>
			</div>
		</form>
	</c:if>
	
		<c:if test="${(sessionScope.for_submit_back eq 'news_list')}">	
		<form action="goToNewsList" method="post">
			<input type="hidden" name="id" value="${news.id}" />
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.editNews.back_to_news_list"/>"/>
			</div>
		</form>
	</c:if>