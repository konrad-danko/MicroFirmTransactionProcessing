<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>TransItem</title>
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
            <%--<form:form modelAttribute="transItem" method="post" class="border rounded shadow-lg">--%>

                <%--Tabelka z inputami--%>
                <div class="d-flex justify-content-start">
                    <div class="m-3" style="width:auto">
                        <table class="table table-bordered table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th class="text-center">Data transakcji</th>
                                <th class="text-center">Data sprzedaży</th>
                                <th class="text-center">Faktura</th>
                                <th>Klient</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="align-middle">
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 188px;">
                                        <fmt:parseDate value="${transaction.transactionDate}" pattern="yyyy-MM-dd" var="originalTransactionDate"/>
                                        <fmt:formatDate value="${originalTransactionDate}" var="formattedTransactionDate" pattern="dd.MM.yyyy"/>
                                        <div class="mx-2 mt-2 text-center">${formattedTransactionDate}</div>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 188px;">
                                        <fmt:parseDate value="${transaction.sellDate}" pattern="yyyy-MM-dd" var="originalSellDate"/>
                                        <fmt:formatDate value="${originalSellDate}" var="formattedSellDate" pattern="dd.MM.yyyy"/>
                                        <div class="mx-2 mt-2 text-center">${formattedSellDate}</div>
                                    </div>
                                </td>
                                <td class="text-center align-middle">
                                    <div class="d-flex">
                                        <div class="mx-3" style="margin-top: 12px">
                                            <input type="checkbox" disabled ${  transaction.issueInvoice  ?  "checked"  :  ""  }>
                                        </div>
                                        <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 100px;">
                                            <div class="mx-2 mt-2" id="invoiceNoDivElement">${transaction.invoiceNo}</div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef;">
                                        <div class="mx-4 mt-2">${transaction.customer.getCustomerName()}</div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <%--Tabelka z transitems--%>
                <div class="d-flex justify-content-center">
                    <div class="m-3" style="width:100%">
                        <table class="table table-bordered table-hover table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th class="align-middle">Id</th>
                                <th class="align-middle">Nazwa produktu</th>
                                <th class="text-right align-middle">Ilość (szt.)</th>
                                <th class="text-center align-middle">Cena netto (1000 szt.)</th>
                                <th class="text-right align-middle">Kwota netto</th>
                                <th class="text-right align-middle">Kwota VAT</th>
                                <th class="text-right align-middle">Kwota brutto</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${allTransItemsExisting}" var="transItemExisting">
                                <tr>
                                    <td>${transItemExisting.id}</td>
                                    <td>${transItemExisting.product.getProductName()}</td>
                                    <td class="text-right">${transItemExisting.quantity}</td>
                                    <td class="text-right">${transItemExisting.netPricePer1000}</td>
                                    <td class="text-right">${transItemExisting.netAmount}</td>
                                    <td class="text-right">${transItemExisting.vatAmount}</td>
                                    <td class="text-right">${transItemExisting.grossAmount}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td>${transItem.product.getProductName()}</td>
                                <%--<td>
                                    <form:select path="product" class="form-control" disabled="false">
                                        <form:option label="--Wybierz produkt--" value="0"/>
                                        <form:options items="${allProducts}" itemLabel="productName" itemValue="id"/>
                                    </form:select>
                                    <form:errors path="product" class="text-danger"/>
                                </td>--%>
                                <td class="text-right">${transItem.quantity}</td>
                                <%--<td>
                                    <form:input type="number" path="quantity" class="text-right form-control" disabled="false" value="${transItem.quantity}"/>
                                    <form:errors path="quantity" class="text-danger"/>
                                </td>--%>
                                <td class="text-right">${transItem.netPricePer1000}</td>
                                <td class="text-right">${transItem.netAmount}</td>
                                <td class="text-right">${transItem.vatAmount}</td>
                                <td class="text-right">${transItem.grossAmount}</td>
                                <td class="text-center">
                                    <a href="/transaction/showTransaction/${transaction.id}" class="badge badge-success">OK</a>
                                </td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr class="font-weight-bold" style="background-color: #e9ecef">
                                <td class="text-left" colspan="3">
                                    <a href="/transItem/addTransItem/${transaction.id}" class="badge badge-success ml-3">Dodaj nowy</a>
                                </td>
                                <td class="text-right">Razem:</td>
                                <td class="text-right">${transaction.totalNetAmount}</td>
                                <td class="text-right">${transaction.totalVatAmount}</td>
                                <td class="text-right">${transaction.totalGrossAmount}</td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

                <%--Tabelka z detalami płatności--%>
                <div class="d-flex justify-content-end">
                    <div class="m-3" >
                        <table class="table table-bordered table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th class="text-center">Sposób zapłaty</th>
                                <th class="text-center">Data płatności</th>
                                <th class="text-center">Kwota do zapłaty (słownie)</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 199px;">
                                        <div class="mx-2 mt-2">${transaction.paymentType.getDescription()}</div>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 188px;">
                                        <fmt:parseDate value="${transaction.paymentDueDate}" pattern="yyyy-MM-dd" var="originalPaymentDueDate"/>
                                        <fmt:formatDate value="${originalPaymentDueDate}" var="formattedPaymentDueDate" pattern="dd.MM.yyyy"/>
                                        <div class="mx-2 mt-2 text-center">${formattedPaymentDueDate}</div>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <div class="border rounded" style="height: 38px; background-color: #e9ecef;">
                                        <div class="mx-2 mt-2">${transaction.paymentAmountInWords}</div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <%--Blok z przyciskami i "audit trial-em"--%>
                <div class="d-flex mb-1">
                    <div class="form-group">
                        <a href="/transaction/showAllTransactions" class="btn btn-primary mx-3">Wróć do listy transakcji</a>
                        <a href="/transaction/editTransaction/${transaction.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                        <a href="/transaction/deleteTransaction/${transaction.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
                        <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
                    </div>

                    <div class="mx-3">
                        <div>
                            <c:if test="${not empty formattedCreated}">
                                Utworzono ${formattedCreated} przez ${transaction.createdByUser.getLoginName()}
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${not empty formattedUpdated}">
                                Edytowano ${formattedUpdated} przez ${transaction.updatedByUser.getLoginName()}
                            </c:if>
                        </div>
                    </div>
                </div>

               <%-- <form:hidden path="id"/>
                <form:hidden path="transaction" id="transactionHiddenElement"/>
                <form:hidden path="product" id="productHiddenElement"/>
                <form:hidden path="quantity" id="quantityHiddenElement"/>
                <form:hidden path="netPricePer1000" id="netPricePer1000HiddenElement"/>
                <form:hidden path="netAmount" id="netAmountHiddenElement"/>
                <form:hidden path="vatAmount" id="vatAmountHiddenElement"/>
                <form:hidden path="grossAmount" id="grossAmountHiddenElement"/>--%>

            </form:form>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
