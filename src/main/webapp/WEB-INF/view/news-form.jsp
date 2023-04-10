<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Save News</title>

	<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
	
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/news-form.css"/>" />
	
	
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - News Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save News</h3>
	
		<form:form action="saveNews" modelAttribute="news" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path="title" /></td>
					</tr>

					<tr>
						<td><label>Brief:</label></td>
						<td><form:input path="brief" /></td>
					</tr>

					<tr>
						<td><label>Content:</label></td>
						<td><form:input path="content" /></td>
					</tr>

					<tr>
						<td><label>Date of create:</label></td>
						<td><form:input path="dateOfCreate" /></td>
					</tr>
					
					<tr>
						<td><label>Status of news id:</label></td>
						<td><form:input path="statusOfNewsId" /></td>
					</tr>
					
					<tr>
						<td><label>User author id:</label></td>
						<td><form:input path="userAuthorId" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	
		<div style="clear; both;"></div>
		<p>
			<a href="list">Back to List</a>
		</p>
	</div>
</body>
</html>










