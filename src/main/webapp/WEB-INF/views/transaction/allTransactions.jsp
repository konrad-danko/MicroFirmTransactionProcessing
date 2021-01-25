<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Transactions</title>
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
        <div style="width:95%">
            <div class="clearfix">
                <h2 class="float-left ml-3">Transakcje</h2>
                <a href="/transaction/addTransaction" class="float-right btn btn-success mr-3">Dodaj nową transakcję</a>
            </div>

            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <th>Id</th>
                    <th>Klient</th>
                    <th class="text-center">Data transakcji</th>
                    <th class="text-right">Netto</th>
                    <th class="text-right">VAT</th>
                    <th class="text-right">Brutto</th>
                    <th>Sposób zapłaty</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allTransactions}" var="transaction">
                    <tr>
                        <td><a href="/transaction/showTransaction/${transaction.id}">${transaction.id}</a></td>
                        <td>${transaction.customer.getCustomerName()}</td>
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
