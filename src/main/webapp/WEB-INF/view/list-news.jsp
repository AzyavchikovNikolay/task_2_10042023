<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>

<html>

<head>
<title>List News</title>

<!-- reference our style sheet -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - News Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Customer -->

			<input type="button" value="Add News"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Title</th>
					<th>Brief</th>
					<th>Content</th>
					<th>Date of create</th>
					<th>Status of news id</th>
					<th>User author id</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempNews" items="${news}">

					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/news/showFormForUpdate">
						<c:param name="newsId" value="${tempNews.id}" />
					</c:url>

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/news/delete">
						<c:param name="newsId" value="${tempNews.id}" />
					</c:url>

					<tr>
						<td>${tempNews.title}</td>
						<td>${tempNews.brief}</td>
						<td>${tempNews.content}</td>
						<td>${tempNews.dateOfCreate}</td>
						<td>${tempNews.statusOfNews.id}</td>
						<td>${tempNews.user.id}</td>

						<td>
							<!-- display the update link --> 
							<a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>









