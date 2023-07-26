<%--
  Created by IntelliJ IDEA.
  User: ahanc
  Date: 24.07.2023
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Testing </h1>
<p>
    <%@ page import="java.util.Date" %>
    <%=
    new Date()
    %>
    <%
        String name = request.getParameter("name");
    %>

    <%=
    "Hello, " + name
    %>
</p>
</body>
</html>
