<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/view/localizationVars.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.ResourceBundle" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/script/validation.js"></script>
<title><spring:message code="local.base_page.header_title"/>
</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newsStyle4.css"/>" />

</head>
<body>
	<div class="page">
		<div class="header">
			<c:import url="/WEB-INF/view/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${not (sessionScope.user eq 'active')}">
				    <spring:message code="local.base_page.welcome"/>    
					<%-- <c:import url=""></c:import> --%>
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/view/menu.jsp" />
				</c:if>
		</div>

			<div class="content">

				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:import url="/WEB-INF/view/guest.jsp" />
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/view/body.jsp" />
				</c:if>
			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/view/footer.jsp" />
		</div>
	</div>
	</body>
</html>
