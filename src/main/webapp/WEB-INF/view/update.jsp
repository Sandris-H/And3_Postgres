
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title>Update person</title>
</head>
<body>
<div>Name: <c:out value="${requestScope.person.name}"/> </div>
<div>Surname: <c:out value="${requestScope.person.surname}"/> </div>
<div>Age: <c:out value="${requestScope.person.age}"/> </div>

<br />

<form method="post" action="<c:url value='/update'/>">

    <label>New name: <input type="text" name="name" /></label><br>
    <label>New surname: <input type="text" name="surname" /></label><br>
    <label>New age: <input type="text" name="age" /></label><br>

    <input type="number" hidden name="id" value="${requestScope.person.id}"/>

    <input type="submit" value="Update" name="Update"><br>
</form>
</body>
</html>
