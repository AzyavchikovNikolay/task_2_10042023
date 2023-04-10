<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${sessionScope.registration eq 'success'}">
			<div class=registration-success><spring:message code="local.news_list.registration_success"/></div>
			<c:set var="registration" scope="session" value=""/>
</c:if>

<c:if test="${sessionScope.add_news eq 'success'}">
			<div class=registration-success><spring:message code="local.addNews.add_news_success"/></div>
			<c:set var="add_news" scope="session" value=""/>
</c:if>

<c:if test="${sessionScope.delete_selected_news eq 'warning'}">
			<div class=registration-warning><spring:message code="local.news_list.delete_selected_news_warning"/></div>
			<c:set var="delete_selected_news" scope="session" value=""/>
</c:if>

<c:if test="${sessionScope.delete_selected_news eq 'success'}">
			<div class=registration-success><spring:message code="local.news_list.delete_selected_news_success"/></div>
			<c:set var="delete_selected_news" scope="session" value=""/>
</c:if>

<c:if test="${sessionScope.delete_news eq 'success'}">
			<div class=registration-success><spring:message code="local.news_list.delete_news_success"/></div>
			<c:set var="delete_news" scope="session" value=""/>
</c:if>

<c:if test="${sessionScope.session_warning eq 'warning'}">
			<div class=registration-warning><spring:message code="local.news_list.session_warning"/></div>
			<c:set var="session_warning" scope="session" value=""/>
</c:if>

<div class="body-title">
	<a href="goToNewsList">
	<spring:message code="local.news_list.news"/></a>
	<spring:message code="local.news_list.news_list"/>
</div>

<form action="doDeleteSelectedNews" method="post">
	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="delete2">
			<input type="submit" value="<spring:message code="local.news_list.delete_selected_news"/>"/>
		</div>
	</c:if>
	<c:if test="${sessionScope.role eq 'redactor'}">
		<div class="delete2">
			<input type="submit" value="<spring:message code="local.news_list.delete_selected_news"/>"/>
		</div>
	</c:if>
	<c:forEach var="news" items="${listNews}">	
		<c:if test="${sessionScope.role eq 'admin'}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
					<c:if test="${news.statusOfNews.statusNews eq 'new'}">
						<div class="news-status-new">				
							<spring:message code="local.news_list.news_status_new"/>	
						</div>
					</c:if>
					<c:if test="${news.statusOfNews.statusNews eq 'published'}">
						<div class="news-status-published">
							<spring:message code="local.news_list.news_status_published"/>
						</div>
					</c:if>
					<c:if test="${news.statusOfNews.statusNews eq 'remote'}">
						<div class="news-status-remote">
							<spring:message code="local.news_list.news_status_remote"/>
						</div>
					</c:if>
					
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.dateOfCreate}"/>
				</div>	
				
				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
							<a href="goToEditNewsForm?id=${news.id}"><spring:message code="local.news_list.editlink"/></a>
							<c:set var="for_submit_back" scope="session" value="news_list"/>
						</c:if>
							
						<a href="goToViewNews?id=${news.id}"><spring:message code="local.news_list.viewlink"/></a>
						
						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="checkbox" name="idNews" value="${news.id}" />
						</c:if>
					</div>
				</div>
				
			</div>
		</div>
		</c:if>
		
		<c:if test="${sessionScope.role eq 'redactor'}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
					<c:if test="${news.statusOfNews.statusNews eq 'new'}">
						<div class="news-status-new">
							<spring:message code="local.news_list.news_status_new"/>
						</div>
					</c:if>
					<c:if test="${news.statusOfNews.statusNews eq 'published'}">
						<div class="news-status-published">
							<spring:message code="local.news_list.news_status_published"/>
						</div>
					</c:if>
					<c:if test="${news.statusOfNews.statusNews eq 'remote'}">
						<div class="news-status-remote">
							<spring:message code="local.news_list.news_status_remote"/>
						</div>
					</c:if>
					
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.dateOfCreate}"/>
				</div>	
				
				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'redactor'}">
							<a href="goToEditNewsForm?id=${news.id}"><spring:message code="local.news_list.editlink"/></a>
							<c:set var="for_submit_back" scope="session" value="news_list"/>
						</c:if>
							
						<a href="goToViewNews?id=${news.id}"><spring:message code="local.news_list.viewlink"/></a>
						
						<c:if test="${sessionScope.role eq 'redactor'}">
							<input type="checkbox" name="idNews" value="${news.id}" />
						</c:if>
					</div>
				</div>
				
			</div>
		</div>
		</c:if>
		
		<c:if test="${sessionScope.role eq 'user'}">
		<c:if test="${news.statusOfNews.statusNews eq 'published'}">
		
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
					
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.dateOfCreate}"/>
				</div>	
				
				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<a href="goToViewNews?id=${news.id}"><spring:message code="local.news_list.viewlink"/></a>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		</c:if>
		
	</c:forEach>
	
	<div class="no-news">
		<c:if test="${listNews eq null}">
		<spring:message code="local.news_list.no_news"/>
	</c:if>
	</div>
</form>