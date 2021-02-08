<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Customers</title>
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
                <h2 class="float-left ml-3">Klienci</h2>
                <a href="/customer/addCustomer" class="float-right btn btn-success mr-3">Dodaj nowego klienta</a>
            </div>

            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <%--<th class="text-right">Id</th>--%>
                    <th>Nazwa</th>
                    <th class="text-center">NIP</th>
                    <th class="text-center">Kod pocztowy</th>
                    <th>Miejscowość</th>
                    <th>Ulica</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allCustomers}" var="customer">
                    <tr>
                        <%--<td class="text-right">${customer.id}</td>--%>
                        <td><a href="/customer/showCustomer/${customer.id}">${customer.customerName}</a></td>
                        <td class="text-center">${customer.customerNIP}</td>
                        <td class="text-center">${customer.customerPostCode}</td>
                        <td>${customer.customerCity}</td>
                        <td>${customer.customerStreet}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
