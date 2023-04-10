<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="body-title">
	<a href="goToNewsList">
	<spring:message code="local.addNews.news"/></a>
	<spring:message code="local.addNews.adding_news"/>
</div>

	<c:if test="${sessionScope.add eq 'warning'}">
		<div class=registration-warning><spring:message code="local.addNews.warning"/></div>
		<c:set var="add" scope="session" value=""/>
	</c:if>

	<form:form action="doAddNews" modelAttribute="news" method="post">
		<form:hidden path="id" />
			
			<div class="add-table-margin">
				<table class="news_text_format">
					
					<!--
					<tr>
						<td class="space_around_title_text"><c:out value="${news_id}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="newsId" value=""/>
							</div>
						</td>
					</tr>
					-->
						
					<tr>
						<td class="space_around_title_text"><spring:message code="local.addNews.news_title"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="2" cols="50" path="title"/>
							</div>
						</td>
					</tr>	
					
					<!--
					<tr>
						<td class="space_around_title_text"><c:out value="${news_date}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="newsDateEdit" value=""/>
							</div>
						</td>
					</tr>	
					-->
					
					<tr>
						<td class="space_around_title_text"><spring:message code="local.addNews.news_brief"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="5" cols="50" path="brief"/>
							</div>
						</td>
					</tr>	
					<tr>
						<td class="space_around_title_text"><spring:message code="local.addNews.news_content"/></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<form:textarea rows="8" cols="50" path="content"/>
							</div>
						</td>
					</tr>
					<tr>
						<td class="space_around_title_text"><spring:message code="local.addNews.news_status"/></td>
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
				<input type="submit" value="<spring:message code="local.addNews.add_news"/>"/>
			</div>
		</form:form>

		<form action="goToNewsList" method="get">
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.addNews.back_to_news_list"/>"/>
			</div>
		</form>