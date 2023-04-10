<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/view/localizationVars.jsp" %>


<div class="wrapper">
	<div class="newstitle">
		<spring:message code="local.header.title"/><br/>
		<div class="newstitle-admin">
			<spring:message code="local.header.login_and_password_for_admin"/><br/>
			<spring:message code="local.header.login_and_password_for_user"/>
		</div>
	</div>
	
	<div class="welcome-title">	
		<c:if test="${not (sessionScope.welcome eq 'guest')}">
			<spring:message code="local.header.welcome"/>
			<c:out value="${sessionScope.welcome}" />!
			<spring:message code="local.header.status"/>
				<c:if test="${(sessionScope.role eq 'admin')}">
					<spring:message code="local.header.admin"/>
				</c:if>
				<c:if test="${(sessionScope.role eq 'redactor')}">
					<spring:message code="local.header.redactor"/>
				</c:if>
				<c:if test="${(sessionScope.role eq 'user')}">
					<spring:message code="local.header.user"/>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.session eq 'guest'}">
		</c:if>
	</div>
	
	<div class="local-link">
		<div align="right">
			<a href="?lang=en">
				<spring:message code="local.header.lang_english"/></a> &nbsp;&nbsp; 
			<a href="?lang=ru">
				<spring:message code="local.header.lang_russian"/></a><br/><br/>
		</div>
		
		<c:if test="${not (sessionScope.user eq 'active')}">
			<div align="right">
				<form action="doSignIn" method="post">
					<input type="hidden" name="command" value="do_sign_in" />
					
					<spring:message code="local.header.enter_login"/>
						<c:if test="${not (requestScope.loginInvalid eq null)}">
							<font color="red"> 
								<spring:message code="${loginInvalid}"/>
							</font> 
						</c:if>
					<input type="text" name="login" value="${loginInv}" /><br/> 
					<spring:message code="local.header.enter_password"/>
						<c:if test="${not (requestScope.passwordInvalid eq null)}">
							<font color="red"> 
								<spring:message code="${passwordInvalid}"/>
							</font> 
						</c:if>
					<input type="password" name="password" value="${passwordInv}" /><br/>

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
							<spring:message code="${AuthenticationError}"/>
						</font> 
					</c:if>
					
					<a href="goToRegistrationPage?attempt=first">
					<spring:message code="local.header.registration"/></a>
					<input type="submit" value="<spring:message code="local.header.sign_in"/>" /><br />
				</form>
					
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
			
				<form action="doSignOut" method="post">
					<input type="submit" value="<spring:message code="local.header.sign_out"/>" /><br />
				</form>
			</div>
		</c:if>
	</div>
</div>