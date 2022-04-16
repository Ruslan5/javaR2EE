<!DOCTYPE html>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html"%>
<%@ page import="java.util.*"%>

<html>
		 <head>
                    <title>User Servlets Application</title>
                    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
                </head>
                <body>
                    <header>
                        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                      <h2>Users page<h2/>
                        </nav>
                    </header>
 <div class="row">

            <div class="container">
               <h3 class="text-center">Hello, ${user.login}!
                                <p>click  <a href="controller?command=logout">here</a> to logout.
                </h3>
                </div>
                </div>
                </body>

  		<%@ include file="/WEB-INF/style/footer.jspf" %>

</html>