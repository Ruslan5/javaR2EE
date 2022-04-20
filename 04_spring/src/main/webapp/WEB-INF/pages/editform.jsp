<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="controller?command=logout" class="navbar-brand"> Logout </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/main/list" class="nav-link">Users list</a></li>
        </ul>
    </nav>
</header>
<br>

<c:set var="title" value="user"/>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <caption>
                <h2>Edit User </h2>
            </caption>

            <form id="add_form" action="<%=request.getContextPath()%>/main/editp/${login}" method="post">
                <fieldset>
                    <legend>Login</legend>
                    <input type="text" name="login" value="${user.login}" readonly><br/>
                </fieldset>
                <br/>

                <fieldset>
                    <legend>Password</legend>
                    <input type="password" name="password"  placeholder="Edit user"/>
                    <span style="color: red" class="error">${errors.get(0)}</span>
                </fieldset>
                <br/>

                <fieldset>
                    <legend>Email</legend>
                    <input type="email" name="email" value="${user.email}" placeholder="Edit user"/>
                    <span style="color: red" class="error">${errors.get(1)}</span>
                </fieldset>
                <br/>

                <fieldset>
                    <legend>firstname</legend>
                    <input type="text" name="firstName" value="${user.firstName}" placeholder="Edit user"/>
                    <span style="color: red" class="error">${errors.get(2)}</span>
                </fieldset>
                <br/>

                <legend>lastName</legend>
                <input value="${user.lastName}" type="text" name="lastName" placeholder="Edit user"/>
                <span style="color: red" class="error">${errors.get(3)}</span>
                </fieldset><br/>

                <legend>Birthday</legend>
                <input type="date" name="birthday" value="${user.birthday}" placeholder="Edit user"/>
                <span style="color: red" class="error">${errors.get(4)}</span>
                </fieldset><br/>

                <legend>Role</legend>
                <select name="role" size="1">
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                </select>
                <br><br>
                <button type="submit" class="btn btn-success" value="update"> Edit</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/style/footer.jspf" %>
</html>