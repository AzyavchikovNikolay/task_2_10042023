<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
			<spring:message code="local.menu.news"/>
		</div>
	</div>
	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(/images/img.jpg); text-align: left;">
				<li style="padding-left: 5px;">
				<a href="goToNewsList">
					<spring:message code="local.menu.news_list"/></a><br/>
				</li>
			
				<c:if test="${sessionScope.role eq 'admin'}">
				<li style="padding-left: 5px;">
				<a href="goToAddNewsForm">
					<spring:message code="local.menu.add_news"/></a><br/>
				</li>
				</c:if>
				
				<c:if test="${sessionScope.role eq 'redactor'}">
				<li style="padding-left: 5px;">
				<a href="goToAddNewsForm">
					<spring:message code="local.menu.add_news"/></a><br/>
				</li>
				</c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<div style="height: 25px;"></div>
</div>