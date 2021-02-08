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

    <div class="d-flex justify-content-center">
        <div style="width:70%">
            <div class="clearfix">
                <h2 class="float-left ml-3">Produkty, Ceny za 1000 szt.(PLN)</h2>
                <a href="/product/addProduct" class="float-right btn btn-success mr-3">Dodaj nowy produkt</a>
            </div>

            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <%--<th class="text-right">Id</th>--%>
                    <th>Nazwa</th>
                    <th class="text-right">Netto</th>
                    <th class="text-right">VAT (%)</th>
                    <th class="text-right">Brutto</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allProducts}" var="product">
                    <tr>
                        <%--<td class="text-right">${product.id}</td>--%>
                        <td><a href="/product/showProduct/${product.id}">${product.productName}</a></td>
                        <td class="text-right">${product.netPricePer1000}</td>
                        <td class="text-right">${product.vatRate}</td>
                        <td class="text-right"></td>
                            <%--<td class="text-right" id="grossPricePer1000">${String.format("%.2f", (product.netPricePer1000 * ((product.vatRate+100)/100))).replaceAll(",", ".")}</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    const trElements = document.querySelectorAll("tbody tr");

    trElements.forEach(function (element){
        const grossPricePer1000Element = element.lastElementChild;
        const vatRateElement = grossPricePer1000Element.previousElementSibling;
        const netPricePer1000Element = vatRateElement.previousElementSibling;

        const netPrice = netPricePer1000Element.innerText;
        const vatRate = vatRateElement.innerText;
        const grossPrice = netPrice * ((vatRate/100)+1);

        grossPricePer1000Element.innerText = grossPrice.toFixed(2);
    });
</script>
</body>
</html>
