<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>App Header</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark mt-1 rounded-lg">

    <ul class="navbar-nav">
        <li class="nav-item">
            <a href="/home" class="btn btn-info mx-2">Strona główna</a>
        </li>
        <li class="nav-item">
            <a href="/firmData/showFirmData" class="btn btn-info mx-2">Dane Firmy</a>
        </li>
        <li class="nav-item">
            <a href="/user/showAllUsers" class="btn btn-info mx-2">Użytkownicy</a>
        </li>
        <li class="nav-item">
            <a href="/customer/showAllCustomers" class="btn btn-info mx-2">Klienci</a>
        </li>
        <li class="nav-item">
            <a href="/product/showAllProducts" class="btn btn-info mx-2">Produkty</a>
        </li>
        <li class="nav-item">
            <a href="/transaction/showAllTransactions" class="btn btn-info mx-2">Transakcje</a>
        </li>
        <li class="nav-item">
            <a href="/report/showAllReports" class="btn btn-info mx-2">Raporty</a>
        </li>
    </ul>

    <h3 class="navbar-text mx-2">Micro Firm</h3>

    <c:if test="${not empty loginName}">
        <div class="btn-group-vertical btn-group-sm ml-auto">
            <a href="/login/editPassword" class="btn btn-secondary font-weight-bold">${loginName}</a>
            <a href="/login/logout" class="btn btn-danger">Wyloguj się</a>
        </div>
    </c:if>
    <c:if test="${empty loginName}">
        <a href="/login/login" class="btn btn-secondary ml-auto font-weight-bold">Zaloguj się</a>
    </c:if>

</nav>
<br>

</body>
</html>
