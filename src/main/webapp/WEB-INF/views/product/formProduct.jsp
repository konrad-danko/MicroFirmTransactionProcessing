<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product</title>
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
<form:form modelAttribute="product" method="post" class="border rounded shadow-lg">

    <div class="d-flex mt-3">
        <div class="form-group mx-2" style="width:3em">
            <label for="id">Id:</label>
            <form:input path="id" id="id" class="form-control" disabled="true"/>
        </div>
        <div class="form-group mx-2 flex-grow-1">
            <label for="productName">Nazwa:</label>
            <form:input path="productName" id="productName" class="form-control" disabled="${disabledParam}"/>
            <form:errors path="productName" class="text-danger"/>
        </div>
    </div>
    <br>
    <div class="d-flex">
        <div class="form-group mx-2">
            <label for="netPricePer1000">Cena netto za 1000 szt. (PLN):</label>
            <form:input path="netPricePer1000" id="netPricePer1000" class="form-control text-right" disabled="${disabledParam}"/>
            <form:errors path="netPricePer1000" class="text-danger"/>
        </div>
        <div class="form-group mx-2">
            <label for="vatRate">Stawka VAT (%):</label>
            <form:input path="vatRate" id="vatRate" class="form-control text-right" disabled="${disabledParam}"/>
            <form:errors path="vatRate" class="text-danger"/>
        </div>
        <div class="form-group mx-2">
            <label for="grossPricePer1000">Cena brutto za 1000 szt. (PLN):</label>
            <input id="grossPricePer1000" class="form-control text-right" disabled>
        </div>
    </div>
    <br>
    <br>
    <div class="ml-3">
        <p>Utworzono ${product.created.toLocalDate()} ${product.created.toLocalTime()} przez ${product.createdByUser.getLoginName()}</p>
        <p>Edytowano ${product.updated.toLocalDate()} ${product.updated.toLocalTime()} przez ${product.updatedByUser.getLoginName()}</p>
    </div>
    <br>
    <div class="form-group mb-4">
        <a href="/product/showAllProducts" class="btn btn-primary mx-3">Wróć do listy produktów</a>
        <a href="/product/editProduct/${product.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
        <a href="/product/deleteProduct/${product.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
        <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
    </div>

    <form:hidden path="createdByUser"/>
    <form:hidden path="updatedByUser"/>
    <form:hidden path="created"/>
    <form:hidden path="updated"/>

</form:form>
</div>

<script>
    const netPricePer1000InputElement = document.getElementById("netPricePer1000");
    const vatRateInputElement = document.getElementById("vatRate");
    const grossPricePer1000InputElement = document.getElementById("grossPricePer1000");

    function calculateGrossPrice(){
        let netPrice = netPricePer1000InputElement.value;
        let vatRate = vatRateInputElement.value;
        let grossPrice = netPrice * ((vatRate/100)+1);
        return grossPrice.toFixed(2);
    }

    netPricePer1000InputElement.addEventListener("change", function (){
        grossPricePer1000InputElement.value = calculateGrossPrice();
    });
    vatRateInputElement.addEventListener("change", function (){
        grossPricePer1000InputElement.value = calculateGrossPrice();
    });
    document.addEventListener("DOMContentLoaded", function (){
        grossPricePer1000InputElement.value = calculateGrossPrice();
    });

</script>
</body>
</html>
