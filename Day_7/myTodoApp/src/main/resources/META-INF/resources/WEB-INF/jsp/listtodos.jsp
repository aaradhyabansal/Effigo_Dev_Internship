<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome ${username}</title>
</head>
<body>
<div class="container">
<h1>Welcome, to Todos Page!</h1>
<div>Tour Todos are         </div>
<table class="table">
    <thead>
    <tr>
    <th>id</th>
    <th>Description</th>
    <th>Target Date</th>
    <th>Is Done?</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
        <tr>${todo.id}</tr>
        <tr>${todo.description}</tr>
        <tr>${todo.targetDate}</tr>
        <tr>${todo.done}</tr>
    </c:forEach>
    </tbody>
</table>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
</body>
</html>
