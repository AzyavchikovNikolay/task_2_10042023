<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="registration">
	<div class=registration-title><spring:message code="local.registration_form.registration_form"/><br/><br/>
	</div>
		<c:if test="${sessionScope.warningReg eq 'warning'}">
			<div class=registration-warning><spring:message code="local.registration_form.warning_fill_fields"/></div>
			<c:set var="warningReg" scope="session" value=""/>
		</c:if>
		<c:if test="${sessionScope.warningReg2 eq 'warning'}">
			<div class=registration-warning><spring:message code="local.registration_form.warning_user_exists"/></div>
			<c:set var="warningReg2" scope="session" value=""/>
		</c:if>
	<div class="registration-field" ><b><spring:message code="local.registration_form.enter_details"/></b></div>
	<div  align="left">
		
		<form:form action="doRegistration" modelAttribute="user" method="post">
			<form:hidden path="id" />
			<div class="registration-field"><spring:message code="local.registration_form.login"/><form:input type="text" path="login" value="${registrationData.login}"/>
				<c:if test="${(sessionScope.loginInvalid eq 'local.registration_form.login_invalid')}">
					<font color="red"> 
						<spring:message code="${loginInvalid}"/>
						<c:set var="loginInvalid" scope="session" value=""/>
					</font> 
				</c:if>
			<br/>
			</div> 
			<div class="registration-field"><spring:message code="local.registration_form.password"/><form:input type="text" path="password" value="${registrationData.password}"/>
				<c:if test="${(sessionScope.passwordInvalid eq 'local.registration_form.password_invalid')}">
					<font color="red"> 
						<spring:message code="${passwordInvalid}"/>
						<c:set var="passwordInvalid" scope="session" value=""/>
					</font> 
				</c:if>
			<br/>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.role"/>
				<form:select path="role.id">
					<!-- <form:option value="1">admin</form:option> -->
					<form:option value="2">user</form:option>
					<!-- <form:option value="3">redactor</form:option> -->
				</form:select>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.surname"/><form:input type="text" path="userDetails.surname" value="${registrationData.userDetails.surname}"/>
				<c:if test="${(sessionScope.surnameInvalid eq 'local.registration_form.surname_invalid')}">
					<font color="red"> 
						<spring:message code="${surnameInvalid}"/>
						<c:set var="surnameInvalid" scope="session" value=""/>
					</font>
				 </c:if>
			<br/>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.name"/><form:input type="text" path="userDetails.name" value="${registrationData.userDetails.name}"/>
				<c:if test="${(sessionScope.nameInvalid eq 'local.registration_form.name_invalid')}">
					<font color="red"> 
						<spring:message code="${nameInvalid}"/>
						<c:set var="nameInvalid" scope="session" value=""/>
					</font> 
				</c:if>
			<br/>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.phone"/><form:input type="text" path="userDetails.phone" value="${registrationData.userDetails.phone}"/>
				<c:if test="${(sessionScope.phoneInvalid eq 'local.registration_form.phone_invalid')}">
					<font color="red"> 
						<spring:message code="${phoneInvalid}"/>
						<c:set var="phoneInvalid" scope="session" value=""/>
					</font> 
				</c:if>
			<br/>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.email"/><form:input type="text" path="userDetails.email" value="${registrationData.userDetails.email}"/>
				<c:if test="${(sessionScope.emailInvalid eq 'local.registration_form.email_invalid')}">
					<font color="red">
						<spring:message code="${emailInvalid}"/>
						<c:set var="emailInvalid" scope="session" value=""/>
					</font> 
				</c:if>
			<br/>
			</div>
			<div class="registration-field"><spring:message code="local.registration_form.birthday"/><form:input type="text" path="userDetails.birthday" value="${registrationData.userDetails.birthday}"/>
				<c:if test="${(sessionScope.birthdayInvalid eq 'local.registration_form.birthday_invalid')}">
					<font color="red"> 
						<spring:message code="${birthdayInvalid}"/>
						<c:set var="birthdayInvalid" scope="session" value=""/>
					</font>
				</c:if>
			<br/>
			</div>
			<div class="registration-field">
				<input type="submit" value="<spring:message code="local.registration_form.confirm"/>"/>
			</div>
		</form:form>

		<div class="registration-field">
			
				<form action="goToMainPage" method="get">
					<input type="submit" value="<spring:message code="local.registration_form.go_to_main_page"/>"/>

				</form>
				
				<!--<form action="controller" method="post">
							<input type="hidden" name="command" value="return_to_base_page">
							<input type="submit" value="<spring:message code="local.registration_form.go_to_main_page"/>"> 
				</form>	-->		
		</div>
	</div>
</div>