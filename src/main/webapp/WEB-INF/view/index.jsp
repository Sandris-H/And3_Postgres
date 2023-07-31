<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>People</title>
</head>

<body>
<h1><%= "Hello people from Postgresql!" %></h1><br />
<h2><%= "All people" %></h2><br/>

<c:forEach var="person" items="${requestScope.people}">
    <ul>
        <li>Name: <c:out value="${person.name}"/></li>
        <li>Surname: <c:out value="${person.surname}"/></li>
        <li>Age: <c:out value="${person.age}"/></li>

        <form method="post" action="<c:url value='/delete'/>">
            <input type="number" hidden name="id" value="${person.id}" />
            <input type="submit" name="delete" value="Delete"/>
        </form>

        <form method="get" action="<c:url value='/update'/>">
            <input type="number" hidden name="id" value="${person.id}" />
            <input type="submit" value="Edit"/>
        </form>
    </ul>
</c:forEach>

<br/>
<h2>Create new person</h2>
<form method="post" action="/tomcat_postgres/add">
    Name<label><input type="text" name="name"></label><br/>
    Surname<label><input type="text" name="surname"></label><br/>
    Age<label><input type="number" name="age"></label><br/>
    <input type="submit" value="Create" name="Create">
</form>



</body>
</html>