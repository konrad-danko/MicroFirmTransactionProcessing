<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
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

    <div class="d-flex justify-content-center">
        <div style="width:90%">
            <h2>${headerMessage}</h2>
            <form:form modelAttribute="user" method="post" class="border rounded shadow-lg">
                <div class="d-flex mt-3">
                    <div class="form-group mx-2" style="width:3em">
                        <label for="id">Id:</label>
                        <form:input path="id" id="id" class="form-control" disabled="true"/>
                    </div>
                    <div class="form-group mx-2">
                        <label for="loginName">LoginName:</label>
                        <form:input path="loginName" id="loginName" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="loginName" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2">
                        <label for="firstName">Imię:</label>
                        <form:input path="firstName" id="firstName" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firstName" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2">
                        <label for="lastName">Nazwisko:</label>
                        <form:input path="lastName" id="lastName" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="lastName" class="text-danger"/>
                    </div>
                    <form:hidden path="password"/>
                </div>
                <br>
                <br>
                <br>
                <div class="form-group mb-4">
                    <a href="/user/showAllUsers" class="btn btn-primary mx-3">Wróć do listy użytkowników</a>
                    <a href="/user/editUser/${user.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                    <a href="/user/deleteUser/${user.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
