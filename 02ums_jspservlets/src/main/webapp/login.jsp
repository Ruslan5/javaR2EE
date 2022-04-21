<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
   <head>
   <title>User Servlets Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
   </head>

        <body>
            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <h2 class="text-center">User Servlets Application</h2>
                    </div>
                </nav>
            </header>
            <br>
                  <div class="container col-md-5">
                                  <div class="card">
                                      <div class="card-body">
                  <caption>
                     <h2>SingIn</h2>
                  </caption>

				<form id="login_form" action="loginController" method="post">
					<input type="hidden" name="command" value="login"/>

					<fieldset >
						<legend>Login</legend>
						<input type="text" name="login"/><br/>
						<span class="error">${error.login}</span>

					</fieldset><br/>
					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password"/>
    					<span class="error">${password_error}</span>

					</fieldset><br/>
					
					<input type="submit" value="Login">								
				</form>
                 </div>
                </div>
            </div>
	</table>
</body>
<%@ include file="/WEB-INF/style/footer.jspf" %>
</html>