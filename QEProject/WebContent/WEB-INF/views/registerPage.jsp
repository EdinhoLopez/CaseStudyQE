<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register!</title>
</head>
<body>

<h1>Register here!</h1>

<h2><%=request.getAttribute("error") %></h2>

<form action="${pageContext.request.contextPath}/HomeServlet/verifyRegister">

<p>Username: <input type="text" name="usernameRegister"></p>

<p>Email: <input type="text" name="emailRegister"></p>

<p>Password: <input type="text" name="passwordRegister"></p>

<p>Confirm Password: <input type="text" name="passwordConfirmRegister"></p>

<input type="submit" value="Register">

</form>


</body>
</html>