<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
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

<c:set var="title" value="editUser" />

                  <div class="container col-md-5">
                                  <div class="card">
                                      <div class="card-body">

                  <caption>
                     <h2>Edit User </h2>
                  </caption>

    				<form id="add_form" action="controller" method="post">

    					<input type="hidden" name="command" value="update"/>
    					<fieldset >
    						<legend>Login</legend>
    						<input type="text" name="login" value="${editUser.login}" readonly><br/>
    					</fieldset><br/>

    					<fieldset>
    						<legend>Password</legend>
    						<input type="password" name="password" value="${editUser.getPassword()}" placeholder="Edit user"/>
    						<span class="error">${password_error}</span>
    					</fieldset><br/>

    					<fieldset>
                        <legend>Email</legend>
                        <input type="email" name="email" value="${editUser.getEmail()}" placeholder="Edit user"/>
                        <span class="error">${email_error}</span>
                        </fieldset><br/>

                        <fieldset>
                        <legend>firstname</legend>
                        <input type="text" name="firstName" value="${editUser.getFirstName()}" placeholder="Edit user"/>
                        <span class="error">${firstName_error}</span>
                        </fieldset><br/>

    					<legend>lastName</legend>
                           <input value="${editUser.getLastName()}" type="text" name="lastName" placeholder="Edit user"/>
                           <span class="error">${lastName_error}</span>
                           </fieldset><br/>

                          <legend>Birthday</legend>
                            <input type="date" name="birthday" value="${editUser.getBirthday()}" placeholder="Edit user"/>
                            <span class="error">${birthday_error}</span>
                            </fieldset><br/>

                         <legend>Role</legend>
                        <select name="role" size="1">
                        <option value="1">Admin</option>
                        <option value="2">User</option>
                        </select>
                        <br><br>
    					<button type="submit"  class="btn btn-success" value="update"> Edit </button>
    				</form>
                   </div>
                </div>
            </div>
        </body>
           <%@ include file="/WEB-INF/style/footer.jspf" %>
</html>