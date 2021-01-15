<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Customer</title>
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

    <h2>${headerMessage}</h2>
    <form:form modelAttribute="customer" method="post" class="border rounded shadow-lg">

        <div class="d-flex mt-3">
            <div class="form-group mx-2" style="width:3em">
                <label for="id">Id:</label>
                <form:input path="id" id="id" class="form-control" disabled="true"/>
            </div>
            <div class="form-group mx-2 flex-grow-1">
                <label for="customerName">Nazwa:</label>
                <form:input path="customerName" id="customerName" class="form-control" disabled="${disabledParam}"/>
                <form:errors path="customerName" class="text-danger"/>
            </div>
        </div>
        <br>
        <div class="d-flex">
            <div class="form-group mx-2">
                <label for="customerNIP">NIP:</label>
                <form:input path="customerNIP" id="customerNIP" class="form-control" disabled="${disabledParam}"/>
                <form:errors path="customerNIP" class="text-danger"/>
            </div>
            <div class="form-group mx-2">
                <label for="customerPostCode">Kod pocztowy:</label>
                <form:input path="customerPostCode" id="customerPostCode" class="form-control" disabled="${disabledParam}"/>
                <form:errors path="customerPostCode" class="text-danger"/>
            </div>
            <div class="form-group mx-2">
                <label for="customerCity">Miejscowość:</label>
                <form:input path="customerCity" id="customerCity" class="form-control" disabled="${disabledParam}"/>
                <form:errors path="customerCity" class="text-danger"/>
            </div>
            <div class="form-group mx-2 flex-grow-1">
                <label for="customerStreet">Ulica:</label>
                <form:input path="customerStreet" id="customerStreet" class="form-control" disabled="${disabledParam}"/>
                <form:errors path="customerStreet" class="text-danger"/>
            </div>
        </div>
        <br>
        <br>
        <div class="ml-3">
            <p>Utworzono ${customer.created.toLocalDate()} ${customer.created.toLocalTime()} przez ${customer.createdByUser.getLoginName()}</p>
            <p>Edytowano ${customer.updated.toLocalDate()} ${customer.updated.toLocalTime()} przez ${customer.updatedByUser.getLoginName()}</p>
        </div>
        <br>
        <div class="form-group mb-4">
            <a href="/customer/showAllCustomers" class="btn btn-primary mx-3">Wróć do listy klientów</a>
            <a href="/customer/editCustomer/${customer.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
            <a href="/customer/deleteCustomer/${customer.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
            <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
        </div>

        <form:hidden path="createdByUser"/>
        <form:hidden path="updatedByUser"/>
        <form:hidden path="created"/>
        <form:hidden path="updated"/>

    </form:form>
</div>
</body>
</html>
