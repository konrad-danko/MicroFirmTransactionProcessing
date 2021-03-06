<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Home Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="header.jsp" %>

    <div class="d-flex justify-content-center">
        <div class="border rounded shadow-lg bg-light text-dark">
            <h2 class="m-3 text-center">${firmData.firmName}</h2>
        </div>
    </div>
    <br>
    <br>

    <div class="d-flex justify-content-center">
        <div style="width:90%">
            <div class="d-flex justify-content-start">
                <h3 class="ml-3">Najnowsze transakcje</h3>
            </div>

            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <th class="text-right">Id</th>
                    <th>Klient</th>
                    <th class="text-center">Faktura</th>
                    <th class="text-center">Data transakcji</th>
                    <th class="text-right">Netto</th>
                    <th class="text-right">VAT</th>
                    <th class="text-right">Brutto</th>
                    <th>Sposób zapłaty</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${mostRecent10Transactions}" var="transaction">
                    <tr>
                        <td class="text-right"><a href="/transaction/showTransaction/${transaction.id}">${transaction.id}</a></td>
                        <td>${transaction.customer.getCustomerName()}</td>
                        <td class="text-center">${transaction.invoiceNo}</td>
                        <td class="text-center">
                            <fmt:parseDate value="${transaction.transactionDate}" pattern="yyyy-MM-dd" var="originalTransactionDate"/>
                            <fmt:formatDate value="${originalTransactionDate}" var="formattedTransactionDate" pattern="dd.MM.yyyy"/>
                                ${formattedTransactionDate}
                        </td>
                        <td class="text-right">${transaction.totalNetAmount}</td>
                        <td class="text-right">${transaction.totalVatAmount}</td>
                        <td class="text-right">${transaction.totalGrossAmount}</td>
                        <td>${transaction.paymentType.getDescription()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    






</div>
</body>
</html>
