<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Password</title>
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
    <br>
    <br>
    <div class="d-flex justify-content-center">
        <div class="d-flex justify-content-center border rounded-lg shadow-lg" style="width:50em">
            <form method="post" class="my-5">
                <h2>${headerMessage}</h2>
                <br>
                <div class="form-group">
                    <label for="loginName">Nazwa użytkownika:</label>
                    <input type="text" name="loginName" id="loginName" class="form-control"  placeholder="Wpisz nazwę użytkownika">
                </div>
                <div class="form-group">
                    <label for="oldPassword">Dotychczasowe hasło:</label>
                    <input type="password" name="oldPassword" id="oldPassword" class="form-control"  placeholder="Wpisz dotychczasowe hasło">
                </div>
                <div class="form-group">
                    <label for="newPassword">Nowe hasło:</label>
                    <input type="password" name="newPassword" id="newPassword" class="form-control"  placeholder="Wpisz nowe hasło">
                </div>
                <br>
                <div class="d-flex justify-content-center">
                    <input type="submit" value="Zatwierdź" class="btn btn-success">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
