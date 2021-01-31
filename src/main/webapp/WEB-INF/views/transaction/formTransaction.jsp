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

                <%--Tabelka z inputami--%>
                <div class="d-flex justify-content-start">
                    <div class="mx-3 mt-3" style="width:auto">
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
                                <td>
                                    <form:input type="date" path="transactionDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.transactionDate}"/>
                                </td>
                                <td>
                                    <form:input type="date" path="sellDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.sellDate}"/>
                                </td>
                                <td class="text-center align-middle">
                                    <div class="d-flex">
                                        <div class="mx-3" style="margin-top: 12px">
                                            <form:checkbox path="issueInvoice" disabled="${disabledParam}" id="issueInvoiceCheckboxElement"/>
                                        </div>
                                        <div class="border rounded" style="height: 38px; background-color: #e9ecef; width: 100px;">
                                            <div class="mx-2 mt-2 d-none" id="invoiceNoDivElement">${transaction.invoiceNo}</div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <form:select path="customer" class="form-control" disabled="${disabledParam}">
                                        <form:option label="--Wybierz klienta--" value="0"/>
                                        <form:options items="${allCustomers}" itemLabel="customerName" itemValue="id"/>
                                    </form:select>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <%--Tabelka z transitems--%>
                <div class="d-flex justify-content-center">
                    <div class="mx-3" style="width:100%">
                        <table class="table table-bordered table-hover table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th class="align-middle">L.p.</th>
                                <th class="align-middle">Nazwa produktu</th>
                                <th class="text-right align-middle">Ilość (szt.)</th>
                                <th class="text-center align-middle">Cena netto (1000 szt.)</th>
                                <th class="text-right align-middle">Kwota netto</th>
                                <th class="text-right align-middle">Kwota VAT</th>
                                <th class="text-right align-middle">Kwota brutto</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${allTransItems}" var="transItem" varStatus="counter">
                                <tr>
                                    <script>
                                        $(document).ready(function(){
                                            $('[data-toggle="tooltip"]').tooltip({title: "<div class='bg-danger text-white font-weight-bold border border-danger rounded-sm'>&nbspUsuń tą pozycję z listy&nbsp</div>", html: true, placement: "top"});
                                        });
                                    </script>
                                    <td class="text-center">
                                        <a href="/transItem/deleteTransItem/${transItem.id}" data-toggle="tooltip">${counter.count}</a>
                                    </td>
                                    <td>${transItem.product.getProductName()}</td>
                                    <td class="text-right">${transItem.quantity}</td>
                                    <td class="text-right">${transItem.netPricePer1000}</td>
                                    <td class="text-right">${transItem.netAmount}</td>
                                    <td class="text-right">${transItem.vatAmount}</td>
                                    <td class="text-right">${transItem.grossAmount}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr class="font-weight-bold" style="background-color: #e9ecef">
                                <td class="text-left" colspan="3">
                                    <a href="/transItem/addTransItem/${transaction.id}" class="btn btn-success ml-3 ${addTransItemBtnVisibleParam}">Dodaj nową pozycję</a>
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
                    <div class="mx-3" >
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
                                    <form:select path="paymentType" items="${allPaymentTypes}" class="form-control" disabled="${disabledParam}" itemLabel="description"  />
                                    <form:errors path="paymentType" class="text-danger"/>
                                </td>
                                <td>
                                    <form:input type="date" path="paymentDueDate" class="text-center form-control" disabled="${disabledParam}" value="${transaction.paymentDueDate}"/>
                                    <form:errors path="paymentDueDate" class="text-danger"/>
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

                    <div class="ml-auto mr-5">
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

                <form:hidden path="id"/>
                <form:hidden path="firmData"/>
                <form:hidden path="invoiceNo" id="invoiceNoHiddenElement"/>
                <form:hidden path="totalNetAmount"/>
                <form:hidden path="totalVatAmount"/>
                <form:hidden path="totalGrossAmount"/>
                <form:hidden path="paymentAmountInWords"/>
                <form:hidden path="createdByUser"/>
                <form:hidden path="updatedByUser"/>
            </form:form>
        </div>
    </div>
</div>

<script>
    const issueInvoiceCheckboxElement = document.getElementById("issueInvoiceCheckboxElement");
    const invoiceNoDivElement = document.getElementById("invoiceNoDivElement");

    if (issueInvoiceCheckboxElement.checked){
        invoiceNoDivElement.classList.remove("d-none");
    } else {
        invoiceNoDivElement.classList.add("d-none");
    }

    issueInvoiceCheckboxElement.addEventListener("click", function (){
        if (issueInvoiceCheckboxElement.checked){
            invoiceNoDivElement.classList.remove("d-none");
        } else {
            invoiceNoDivElement.classList.add("d-none");
        }
    });


</script>
</body>
</html>
