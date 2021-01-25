<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

                <div class="d-flex justify-content-center"> <%--tu damy tabelkę z inputami--%>
                    <div class="m-3" style="width:100%">
                        <table class="table table-bordered table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th class="text-center">Data transakcji</th>
                                <th class="text-center">Data sprzedaży</th>
                                <th>Klient</th>
                                <th class="text-center" colspan="4">Faktura</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <form:input type="date" path="transactionDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.transactionDate}"/>
                                </td>      <%--readonly="true"--%>
                                <td>
                                    <form:input type="date" path="sellDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.sellDate}"/>
                                </td>
                                <td>
                                    <form:select path="customer" class="form-control" disabled="${disabledParam}">
                                        <form:option label="--Wybierz klienta--" value="0"/>
                                        <form:options items="${allCustomers}" itemLabel="customerName" itemValue="id"/>
                                    </form:select>
                                </td>
                                <td class="text-center align-middle">
                                    <form:checkbox path="issueInvoice" disabled="${disabledParam}"/>
                                </td>
                                <td class="text-center align-middle">
                                        ${transaction.invoiceNo}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="d-flex">
                    <%@include file="../transItem/allTransItems.jsp"%>
                        <%--tu dajemy transitems--%>
                </div>

                <div class="d-flex justify-content-centerQQQQ"> <%--tu damy tabelkę z inputami--%>
                    <div class="m-3" style="width: auto">
                        <table class="table table-bordered table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th>Sposób zapłaty</th>
                                <th class="text-center">Data płatności</th>
                                <th class="text-center">Kwota do zapłaty (słownie)</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <form:select path="paymentType" items="${allPaymentTypes}" class="form-control" disabled="${disabledParam}" itemLabel="description"  />
                                    <form:errors path="paymentType" class="text-danger"/>
                                </td>
                                <td>
                                    <form:input type="date" path="paymentDueDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.paymentDueDate}"/>
                                    <form:errors path="paymentDueDate" class="text-danger"/>
                                </td>
                                <td class="text-center align-middle">
                                    <div class="mx-3">
                                            ${transaction.paymentAmountInWords}
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="d-flex mb-1">
                    <div class="form-group">
                        <a href="/transaction/showAllTransactions" class="btn btn-primary mx-3">Wróć do listy transakcji</a>
                        <a href="/transaction/editTransaction/${transaction.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                        <a href="/transaction/deleteTransaction/${transaction.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
                        <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
                    </div>

                    <div class="mx-3">
                        <div>
                            <c:if test="${not empty transaction.created}">
                                <fmt:parseDate value="${transaction.created}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="originalCreated"/>
                                <fmt:formatDate value="${originalCreated}" var="formattedCreated" pattern="dd.MM.yyyy 'o' HH:mm:ss"/>
                                Utworzono ${formattedCreated} przez ${transaction.createdByUser.getLoginName()}
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${not empty transaction.updated}">
                                <fmt:parseDate value="${transaction.updated}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="originalUpdated"/>
                                <fmt:formatDate value="${originalUpdated}" var="formattedUpdated" pattern="dd.MM.yyyy 'o' HH:mm:ss"/>
                                Edytowano ${formattedUpdated} przez ${transaction.updatedByUser.getLoginName()}
                            </c:if>
                        </div>
                    </div>
                </div>

                <form:hidden path="id"/>
                <form:hidden path="firmData"/>
                <form:hidden path="invoiceNo"/>
                <form:hidden path="totalNetAmount"/>
                <form:hidden path="totalVatAmount"/>
                <form:hidden path="totalGrossAmount"/>
                <form:hidden path="paymentAmountInWords"/>
                <form:hidden path="createdByUser"/>
                <form:hidden path="updatedByUser"/>
                <form:hidden path="created"/>
                <form:hidden path="updated"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
