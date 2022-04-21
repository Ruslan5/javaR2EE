<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
            <title>User Servlets Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>

                    </div>

                    <ul class="navbar-nav">

                    </ul>
                </nav>
            </header>

           <br>
            <div class="container col-md-5">
                <div class="card">
                <div class="card-body">

				<form id="add_form" action="${pageContext.request.contextPath}/registration" method="post">

					<caption>
                      <h2>  Registration page </h2>
                    </caption>

					<fieldset >
						<legend>Login</legend>
						<input type="text" name="login"/><br/>
						<span style="color: red" class="error">${errors.get(0)}</span>
					</fieldset><br/>

					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password"/>
                        <span style="color: red" class="error">${errors.get(1)}</span>
					</fieldset><br/>

					<fieldset>
                    <legend>Email</legend>
                    <input type="email" name="email"/>
                        <span style="color: red" class="error">${errors.get(2)}</span>
                    </fieldset><br/>

                    <fieldset>
                    <legend>firstname</legend>
                    <input type="text" name="firstName"/>
                        <span style="color: red" class="error">${errors.get(3)}</span>
                    </fieldset><br/>

                    <fieldset>
					<legend>lastName</legend>
                      <input type="text" name="lastName"/>
                        <span style="color: red" class="error">${errors.get(4)}</span>
                      </fieldset><br/>

                      <fieldset>
                      <legend>Birthday</legend>
                          <input type="date" value="1922-01-01" min="1922-01-01" name="birthday"/>
                          <span style="color: red" class="error">${errors.get(5)}</span>
                      </fieldset><br/>

                    <fieldset>
                     <legend>Role</legend>
                    <select name="role" size="1">
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                    </select>
                     </fieldset><br/>
                     <br>
                               <div class="g-recaptcha" data-sitekey="6LcShpIeAAAAAFjcf61czi0kCd7UBz5wAG3dQF7k"></div>
                    <span style="color: red" class="error">${captchaError}</span>
                    <br>
					<input type="submit"  class="btn btn-success"  value="registration">
				</form>
			</div>
		</div>
	</div>
</body>
 <%@ include file="/WEB-INF/style/footer.jspf" %>
</html>