<%--
  Created by IntelliJ IDEA.
  User: mirzoiev-r
  Date: 16.02.22
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
    </head>

<body>
<table>
    <tr>
        <h1>Hi, ${login}!</h1>

    </tr>
    <tr>
    </tr>
    <tr>
    </tr>
    <tr>
        <td><a href="home.jsp">Home</a>
            <a href="<%=request.getContextPath()%>/login" class="navbar-brand"> Login</a>
            <a href="<%=request.getContextPath()%>/main/list" class="navbar-brand"> List users</a>

        </td>
    </tr>
</table>
</body>
</html>
