<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Reports</title>
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

    <div class="d-flex justify-content-start">
        <div class="d-flex mt-2">
            <div class="mx-4 my-auto">
                <h2>Raport "Transakcje niezapłacone"</h2>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-start">
        <div style="width:100%">
            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <th class="text-right">Id</th>
                    <th>Klient</th>
                    <th class="text-center">Faktura</th>
                    <th class="text-center">Data transakcji</th>
                    <th class="text-center">Data płatności</th>
                    <th class="text-right">Brutto</th>
                    <th class="text-right">Wpłacono</th>
                    <th>Sposób zapłaty</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${transactionList}" var="transaction">
                    <tr>
                        <td class="text-right"><a href="/transaction/showTransaction/${transaction.id}">${transaction.id}</a></td>
                        <td>${transaction.customer.getCustomerName()}</td>
                        <td class="text-center">${transaction.invoiceNo}</td>
                        <td class="text-center">
                            <fmt:parseDate value="${transaction.transactionDate}" pattern="yyyy-MM-dd" var="originalTransactionDate"/>
                            <fmt:formatDate value="${originalTransactionDate}" var="formattedTransactionDate" pattern="dd.MM.yyyy"/>
                                ${formattedTransactionDate}
                        </td>
                        <td class="text-center ${today > transaction.paymentDueDate ? "text-danger": ""}">
                            <fmt:parseDate value="${transaction.paymentDueDate}" pattern="yyyy-MM-dd" var="originalPaymentDueDate"/>
                            <fmt:formatDate value="${originalPaymentDueDate}" var="formattedPaymentDueDate" pattern="dd.MM.yyyy"/>
                                ${formattedPaymentDueDate}
                        </td>
                        <td class="text-right">${transaction.totalGrossAmount}</td>
                        <td class="text-right">${transaction.amountPaid}</td>
                        <td>${transaction.paymentType.getDescription()}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="font-weight-bold" style="background-color: #e9ecef">
                    <td class="text-right" colspan="5">Razem:</td>
                    <td class="text-right">${totalGrossAmount}</td>
                    <td class="text-right">${totalAmountPaid}</td>
                    <td></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
</html>
