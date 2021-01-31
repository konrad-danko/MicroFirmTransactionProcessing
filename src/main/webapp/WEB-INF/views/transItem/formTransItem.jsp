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
            <form:form modelAttribute="transItem" method="post" class="border rounded shadow-lg">

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
                                            <div class="mx-2 mt-2">${transaction.invoiceNo}</div>
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
                            <c:forEach items="${allTransItemsExisting}" var="transItemExisting" varStatus="counter">
                                <tr>
                                    <td class="text-center">${counter.count}</td>
                                    <td>${transItemExisting.product.getProductName()}</td>
                                    <td class="text-right">${transItemExisting.quantity}</td>
                                    <td class="text-right">${transItemExisting.netPricePer1000}</td>
                                    <td class="text-right">${transItemExisting.netAmount}</td>
                                    <td class="text-right">${transItemExisting.vatAmount}</td>
                                    <td class="text-right">${transItemExisting.grossAmount}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <form:hidden path="id"/>
                                <form:hidden path="transaction"/>

                                <td></td>
                                <td>
                                    <form:select path="product" id="productSelectElement" style="background-color: white" class="form-control-sm" disabled="false">
                                        <form:option label="--Wybierz produkt--" value="0"/>
                                        <form:options items="${allProducts}" itemLabel="productName" itemValue="id"/>
                                    </form:select>
                                    <br>
                                    <form:errors path="product" class="text-danger"/>
                                </td>
                                <td class="text-right">
                                    <form:input path="quantity" id="quantityInputElement" type="number" min="1" class="text-right form-control-sm" style="max-width: 120px" readonly="false" value="${transItem.quantity}"/>
                                    <form:errors path="quantity" class="text-danger"/>
                                </td>
                                <td class="text-right">
                                    <form:input path="netPricePer1000" id="netPricePer1000InputElement" value="${transItem.netPricePer1000}" class="text-right form-control-sm" style="background-color: #e9ecef; max-width: 120px;" readonly="true"/>
                                    <form:errors path="netPricePer1000" class="text-danger"/>
                                </td>
                                <td class="text-right">
                                    <form:input path="netAmount" id="netAmountInputElement" value="${transItem.netAmount}" class="text-right form-control-sm" style="background-color: #e9ecef; max-width: 120px;" readonly="true"/>
                                </td>
                                <td class="text-right">
                                    <form:input path="vatAmount" id="vatAmountInputElement" value="${transItem.vatAmount}" class="text-right form-control-sm" style="background-color: #e9ecef; max-width: 120px;" readonly="true"/>
                                </td>
                                <td class="text-right">
                                    <form:input path="grossAmount" id="grossAmountInputElement" value="${transItem.grossAmount}" class="text-right form-control-sm" style="background-color: #e9ecef; max-width: 120px;" readonly="true"/>
                                </td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr class="font-weight-bold" style="background-color: #e9ecef">
                                <td class="text-left" colspan="3">
                                    <a href="/transaction/showTransaction/${transaction.id}" class="btn btn-primary mx-3">Anuluj dodanie</a>
                                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3">
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
            </form:form>
        </div>
    </div>
</div>

<script>

    const productSelectElement = document.getElementById("productSelectElement");
    const quantityInputElement = document.getElementById("quantityInputElement");
    const netPricePer1000InputElement = document.getElementById("netPricePer1000InputElement");
    const netAmountInputElement = document.getElementById("netAmountInputElement");
    const vatAmountInputElement = document.getElementById("vatAmountInputElement");
    const grossAmountInputElement = document.getElementById("grossAmountInputElement");

    let vatRate = 0;

    productSelectElement.addEventListener("change", function (){
        <c:forEach items="${allProducts}" var="product">
            if("${product.id}"== productSelectElement.value){
                vatRate = "${product.vatRate}";
                netPricePer1000InputElement.value = "${product.netPricePer1000}";
            }
        </c:forEach>
        netAmountInputElement.value = (quantityInputElement.value * netPricePer1000InputElement.value / 1000).toFixed(2);
        vatAmountInputElement.value = (netAmountInputElement.value * vatRate / 100).toFixed(2);
        grossAmountInputElement.value = (netAmountInputElement.value*1 + vatAmountInputElement.value*1).toFixed(2);
    });

    quantityInputElement.addEventListener("change", function (){
        netAmountInputElement.value = (quantityInputElement.value * netPricePer1000InputElement.value / 1000).toFixed(2);
        vatAmountInputElement.value = (netAmountInputElement.value * vatRate / 100).toFixed(2);
        grossAmountInputElement.value = (netAmountInputElement.value*1 + vatAmountInputElement.value*1).toFixed(2);
    });

</script>
</body>
</html>
