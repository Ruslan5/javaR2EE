<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/tags/myTag.tld" prefix="rm" %>

<html>
<head>
    <title>User Servlets Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<h1 style="text-align: right">Hi, ${login}!</h1>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/logout" class="navbar-brand"> LogOut</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/main/list" class="nav-link">Users list</a></li>
        </ul>
    </nav>
</header>


<div class="row">

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <a href="${pageContext.request.contextPath}/main/add" class="btn btn-success">Add
                New User</a>
        </div>

        <br>


        <table class="table table-bordered">
            <thead>
            <form id="userList" action="">
                <input type="hidden" name="command" value="userList"/>
                <tr>
                    <td>ID</td>
                    <td>Login</td>
                    <td>Email</td>
                    <td>Firstname</td>
                    <td>Lastname</td>
                    <td>Age</td>
                    <td>ROLE</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </form>
            </thead>

            <tbody>
                <rm:usersList users="${usersList}">
                    <tr>
                        <td>${id}</td>
                        <td>${login}</td>
                        <td>${email}</td>
                        <td>${firstName}</td>
                        <td>${lastName}</td>
                        <td>${age}</td>
                        <td>${role}</td>
                        <td> <a href="${pageContext.request.contextPath}/main/edit/${login}">Edit</a>
                        </td>
                        <td>
                        <a onclick="return confirm('Sure, you want to delete this user?')"
                           href="${pageContext.request.contextPath}/main/delete/${login}">Delete</a>
                        </td>
                    </tr>
                </rm:usersList>
            </tbody>

        </table>

    </div>
</div>
</body>

<%@ include file="/WEB-INF/style/footer.jspf" %>
</html>
