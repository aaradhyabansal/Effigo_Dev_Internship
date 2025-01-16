<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
</head>
<body>
<%--<h1>Welcome, ${username} to Login Page!</h1>--%>
<form method="post">
    Name:<input type="text" name="username">
    Password:<input type="password" name="password">
    <input type="submit">
</form>
</body>
</html>
