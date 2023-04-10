<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.ResourceBundle" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.addNews.news_date" var="news_date" />

<fmt:message bundle="${loc}" key="local.editNews.news_date" var="news_date" />