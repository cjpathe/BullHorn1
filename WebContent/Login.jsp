<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bullhorn Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="bootstrap.jsp"></jsp:include>
 </head>
 <body>
 <h2>Login to Bullhorn</h2>

<div class="container">
 <form class="form-signin" method="post" action="Login">
 <h2 class="form-signin-heading">${message}Please sign in</h2>
 <label for="inputEmail" class="sr-only">Email address</label>
 <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
 <input type="hidden" name="action" id="action" value="login"/>
 <label for="inputPassword" class="sr-only">Password</label>
 <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
 <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
 </form>
 <p>
 <a href="adduser.jsp">Not Currently a Bullhorner ? Click Here to Join</a>
 </div> <!-- /container -->

<! jsp:include page="footer.jsp"><! /jsp:include>
</body>
</html>