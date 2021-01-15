<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@include file="../header.jsp"%>

    <div class="clearfix">
        <h2 class="float-left ml-3">Użytkownicy</h2>
        <a href="/user/addUser" class="float-right btn btn-success mr-3">Dodaj nowego użytkownika</a>
    </div>

    <table class="table table-bordered table-hover table-sm shadow-lg">
        <thead class="bg-primary text-white">
        <tr>
            <th>Id</th>
            <th>LoginName</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Hasło ?????</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><a href="/user/showUser/${user.id}">${user.loginName}</a></td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
