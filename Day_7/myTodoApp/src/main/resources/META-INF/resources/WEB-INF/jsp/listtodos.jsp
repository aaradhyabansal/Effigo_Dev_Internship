<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome </title>
</head>
<body>
<div class="container">
<h1>Welcome ${username}, to Todos Page!</h1>
<div>Your Todos are
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
        <tr>
        <td>${todo.id}</td>
        <td>${todo.description}</td>
        <td>${todo.targetDate}</td>
        <td>${todo.done}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
</div>
</body>
</html>
