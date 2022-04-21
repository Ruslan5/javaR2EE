<!DOCTYPE html>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/META-INF/tags/myTag.tld" prefix="RM" %>

<html>
		 <head>
                    <title>User Servlets Application</title>
                    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
                </head>

                <body>

                    <header>
                        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                            <div>
                                <a href="controller?command=logout" class="navbar-brand"> LogOut</a>
                            </div>

                            <ul class="navbar-nav">
                                <li><a href="<%=request.getContextPath()%>/controller?command=userList" class="nav-link">Users list</a></li>
                            </ul>
                        </nav>
                    </header>


                     <div class="row">

                                    <div class="container">
                                        <h3 class="text-center">List of Users</h3>
                                        <hr>
                                        <div class="container text-left">
                         <a href="controller?command=addPage" class="btn btn-success">Add
                                                  New User</a>
                                        </div>

                                        <br>


     <table class="table table-bordered">
        <thead>
			<form id="userList" action="controller">
				<input type="hidden" name="command" value="userList"/>
						<tr>
						    <td>ID</td>
							<td>Login</td>
							<td>Password</td>
							<td>Email</td>
							<td>Firstname</td>
							<td>Lastname</td>
							<td>Birthday</td>
							<td>ROLE</td>
							<td>Delete</td>
							<td>Edit</td>
						</tr>
			</form>
		</thead>


		<tbody>
		 <RM:ListUsers users="${usersList}">
         	    <jsp:attribute name="printlogin">
         	 </jsp:attribute>
         	 </RM:ListUsers>
        </tbody>

	 </table>

	      </div>
		</div>
</body>

		<%@ include file="/WEB-INF/style/footer.jspf" %>
</html>
