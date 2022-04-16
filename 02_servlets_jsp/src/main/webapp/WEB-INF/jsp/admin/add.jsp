<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<c:set var="title" value="add" />
<head>
            <title>User Servlets Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="controller?command=logout" class="navbar-brand"> Logout </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/controller?command=userList" class="nav-link">Users list</a></li>
                    </ul>
                </nav>
            </header>

           <br>
            <div class="container col-md-5">
                <div class="card">
                <div class="card-body">

				<form id="add_form" action="controller" method="post">

					<input type="hidden" name="command" value="add"/>

					<caption>
                      <h2>  Add New User </h2>
                    </caption>

					<fieldset >
						<legend>Login</legend>
						<input type="text" name="login"/><br/>
						<span class="error">${login_error}</span>
					</fieldset><br/>

					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password"/>
						<span class="error">${password_error}</span>
					</fieldset><br/>

					<fieldset>
                    <legend>Email</legend>
                    <input type="email" name="email"/>
                    <span class="error">${email_error}</span>
                    </fieldset><br/>

                    <fieldset>
                    <legend>firstname</legend>
                    <input type="text" name="firstName"/>
                    <span class="error">${firstName_error}</span>
                    </fieldset><br/>

                    <fieldset>
					<legend>lastName</legend>
                      <input type="text" name="lastName"/>
                      <span class="error">${lastName_error}</span>
                      </fieldset><br/>

                      <fieldset>
                      <legend>Birthday</legend>
                          <input type="date" value="1922-01-01" min="1922-01-01" name="birthday"/>
                      <span class="error">${birthday_error}</span>
                      </fieldset><br/>

                    <fieldset>
                     <legend>Role</legend>
                    <select name="role" size="1">
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                    </select>
                     </fieldset><br/>
                     <br><br>
					<input type="submit"  class="btn btn-success"  value="add">
				</form>
			</div>
		</div>
	</div>
</body>
 <%@ include file="/WEB-INF/style/footer.jspf" %>
</html>