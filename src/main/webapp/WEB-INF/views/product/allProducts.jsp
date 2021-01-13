<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products</title>
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
        <h2 class="float-left ml-3">Produkty / Cennik</h2>
        <a href="/product/addProduct" class="float-right btn btn-success mr-3">Dodaj nowy produkt</a>
    </div>

    <table class="table table-bordered table-hover table-sm shadow-lg">
        <thead class="bg-primary text-white">
        <tr>
            <th>Id</th>
            <th>Nazwa</th>
            <th class="text-right">Cena netto za 1000 szt. (PLN)</th>
            <th class="text-right">Stawka VAT (%)</th>
            <th class="text-right">Cena brutto za 1000 szt. (PLN)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allProducts}" var="product">
            <tr>
                <td>${product.id}</td>
                <td><a href="/product/showProduct/${product.id}">${product.productName}</a></td>
                <td class="text-right">${product.netPricePer1000}</td>
                <td class="text-right">${product.vatRate}</td>
                <td class="text-right">${String.format("%.2f", (product.netPricePer1000 * ((product.vatRate/100)+1))).replaceAll(",", ".")}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
