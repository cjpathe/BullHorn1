<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bootstrap.jsp"></jsp:include>
<title>Add New User</title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<h2>Add New User </h2>
<form action="Adduser" method="post">
<input type="hidden" name="action" value="adduser">
<h2>User Name: <input type="text" name="username" value="${username}"/></h2>
<h2>Email: <input type="text" name="useremail" value="${useremail}"/></h2>
<h2>Password: <input type="text" name="userpassword" value="${userpassword}"/></h2>
<h2>Motto: <input type="text" name="usermotto" value="${usermotto}"/></h2>
<input type="submit" value="Add User"/>
</form>

</body>
</html>