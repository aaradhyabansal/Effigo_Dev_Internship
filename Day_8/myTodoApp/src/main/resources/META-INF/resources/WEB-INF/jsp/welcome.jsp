<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logged In</title>
</head>
<body>
<pre>${username}</pre>
<h1>Welcome, <%=request.getAttribute("username") %>! You are logged in! Your password is <%=request.getAttribute("password") %>.</h1>
<div>
    <a href="list-todos">Manage</a> Your Todos
</div>
</body>
</html>
