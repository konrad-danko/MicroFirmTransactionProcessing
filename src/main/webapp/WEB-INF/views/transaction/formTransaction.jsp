<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Transaction</title>
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
        <div style="width:100%">
            <h5>${headerMessage}${empty transaction.id ? "" : " nr "}${transaction.id}</h5>
            <form:form modelAttribute="transaction" method="post" class="border rounded shadow-lg">
                <div class="d-flex mt-3">
                    <div class="form-group mx-1" style="width:10em">
                        <label for="transactionDate">Data transakcji:</label>
                        <form:input type="date" path="transactionDate" id="transactionDate" class="form-control" readonly="true" value="${transaction.transactionDate}"/>  <%--disabled="true"--%>
                    </div>
                    <div class="form-group mx-1" style="width:10em">
                        <label for="sellDate">Data sprzedaży:</label>
                        <form:input type="date" path="sellDate" id="sellDate" class="form-control" disabled="${disabledParam}" value="${transaction.sellDate}"/>
                        <form:errors path="sellDate" class="text-danger"/>
                    </div>
                    <div class="form-group mx-1 flex-shrink-1">
                        <label for="customer">Klient:</label>
                        <form:select id="customer" path="customer" class="form-control" disabled="${disabledParam}">
                            <form:option label="--Wybierz klienta--" value="0"/>
                            <form:options items="${allCustomers}" itemLabel="customerName" itemValue="id"/>
                        </form:select>
                    </div>
                    <div class="form-group form-check mr-1" style="width:9em">
                        <label for="invoiceNo">
                            <label class="form-check-label">
                                Faktura<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><form:checkbox path="issueInvoice" class="form-check-input" disabled="${disabledParam}"/>
                            </label>
                        </label>
                        <form:input path="invoiceNo" id="invoiceNo" class="form-control" readonly="true"/>
                    </div>


<%--                    <div class="form-group mx-1">
                        <label for="paymentType">Sposób zapłaty 1:</label>
                        <form:select id="paymentType" path="paymentType" class="form-control" disabled="${disabledParam}">
                            <form:option label="${transaction.paymentType.getDescription()}" value="${transaction.paymentType.ordinal()}"/>
                            <c:forEach items="${allPaymentTypes}" var="paymentType">
                                <form:option label="${paymentType.getDescription()}" value="${paymentType.ordinal()}"/>
                            </c:forEach>
                        </form:select>
                    </div>--%>
                    <div class="form-group mx-1">
                        <label for="paymentType">Sposób zapłaty:</label>
                        <form:select id="paymentType" path="paymentType" items="${allPaymentTypes}" class="form-control" disabled="${disabledParam}" itemLabel="description"  />  <%--itemValue="${transaction.paymentType.ordinal()}"--%>
                        <form:errors path="paymentType" class="text-danger"/>
                    </div>
<%--                    <div class="form-group mx-1">
                        <label for="paymentType">RR 3:</label>
                        <form:select id="paymentType" path="paymentType" items="${allPaymentTypes}" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="paymentType" class="text-danger"/>
                    </div>--%>



                    <div class="form-group mx-1" style="width:10em">
                        <label for="paymentDueDate">Data płatności:</label>
                        <form:input type="date" path="paymentDueDate" id="paymentDueDate" class="form-control" disabled="${disabledParam}" value="${transaction.paymentDueDate}"/>
                        <form:errors path="paymentDueDate" class="text-danger"/>
                    </div>
                </div>
                <br>
                <div class="d-flex">
                    <div class="form-group mx-2">
                        <%--tu damy transitems--%>
                    </div>
                </div>
                <div class="d-flex">
                    <div class="form-group mx-2" style="width:9em">
                        <label for="totalNetAmount">Suma netto:</label>
                        <form:input path="totalNetAmount" id="totalNetAmount" class="form-control text-right" readonly="true"/>
                    </div>
                    <div class="form-group mx-2" style="width:9em">
                    <label for="totalVatAmount">Suma VAT:</label>
                        <form:input path="totalVatAmount" id="totalVatAmount" class="form-control text-right" readonly="true"/>
                    </div>
                    <div class="form-group mx-2" style="width:9em">
                    <label for="totalGrossAmount">Suma brutto:</label>
                        <form:input path="totalGrossAmount" id="totalGrossAmount" class="form-control text-right" readonly="true"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="paymentAmountInWords">Do zapłaty:</label>
                        <form:input path="paymentAmountInWords" id="paymentAmountInWords" class="form-control" readonly="true"/>
                    </div>
                </div>

                <div class="d-flex">
                    <div class="form-group mx-1">
                        <a href="/transaction/showAllTransactions" class="btn btn-primary mx-1">Wróć do listy transakcji</a>
                        <a href="/transaction/editTransaction/${transaction.id}" class="btn btn-warning mx-1 ${editBtnVisibleParam}">Edytuj</a>
                        <a href="/transaction/deleteTransaction/${transaction.id}" class="btn btn-danger mx-1 ${delBtnVisibleParam}">Usuń</a>
                        <input type="submit" value="Zatwierdź" class="btn btn-success mx-1 ${submitBtnVisibleParam}">
                    </div>

                    <div class="ml-4">
                        <c:if test="${not empty transaction.created}">
                            Utworzono ${transaction.created.toLocalDate()} ${transaction.created.toLocalTime()} przez ${transaction.createdByUser.getLoginName()},&nbsp&nbsp&nbsp
                        </c:if>
                        <c:if test="${not empty transaction.updated}">
                            Edytowano ${transaction.updated.toLocalDate()} ${transaction.updated.toLocalTime()} przez ${transaction.updatedByUser.getLoginName()}
                        </c:if>
                    </div>
                </div>

                <form:hidden path="id"/>
                <form:hidden path="firmData"/>
                <form:hidden path="createdByUser"/>
                <form:hidden path="updatedByUser"/>
                <form:hidden path="created"/>
                <form:hidden path="updated"/>

            </form:form>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
