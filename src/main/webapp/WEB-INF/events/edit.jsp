<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" />
		<meta charset="ISO-8859-1">
	<title>Event Planner</title>
</head>
	<body>
		<div class="container">
			<h2>Edit <strong>${ event.name }</strong></h2>
			<form:form action="/events/${ event.id }" method="post" modelAttribute="event">
				<input type="hidden" name="_method" value="put">
				<form:hidden value="${ userId }" path="planner"/>
				<div class="form-group">
					<form:label path="name">Name</form:label>
					<form:errors path="name"></form:errors>
					<form:input class="form-control" path="name"></form:input>
				</div>
				<div class="form-group">
					<form:label path="eventDate">Date</form:label>
					<form:errors path="eventDate"></form:errors>
					<form:input class="form-control" type="date" path="eventDate"></form:input>
				</div>
				 <div class="form-group">
			        <form:label path="city">City</form:label>
			        <form:errors path="city"/>
			        <form:input class="form-control" path="city" />
			    </div>
			    <div class="form-group">
			        <form:label path="state">State</form:label>
			        <form:errors path="state"/>
					<form:select class="form-control" path="state">
					<c:forEach items="${ states }" var="state">
						<c:choose>
							<c:when  test="${ state.equals(event.state) }">
								<option selected value="${ state }">${ state }</option>
							</c:when>
							<c:otherwise>
								<option value="${ state }">${ state }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</form:select>
			    </div>
			    <button>Update Event</button>
			</form:form>
		</div>
	</body>
</html>