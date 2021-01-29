<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>TransItems</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">


<%--

    <div class="d-flex justify-content-center">
        <div style="width:100%">
           &lt;%&ndash; <form:form modelAttribute="transItem" method="post" class="border rounded shadow-lg">&ndash;%&gt;
                <table class="table table-bordered table-hover table-sm">
                    &lt;%&ndash;<thead class="bg-primary text-white">&ndash;%&gt;
                    <thead class="thead-light">
                    <tr>
                        <th class="align-middle">Id</th>
                        <th class="align-middle">Nazwa produktu</th>
                        <th class="text-right align-middle">Ilość (szt.)</th>
                        <th class="text-center align-middle">Cena netto (1000 szt.)</th>
                        <th class="text-right align-middle">Kwota netto</th>
                        <th class="text-right align-middle">Kwota VAT</th>
                        <th class="text-right align-middle">Kwota brutto</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allTransItems}" var="transItem">
                        <tr>
                            <td>${transItem.id}</td>
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
                    <tr class="bg-light text-dark font-weight-bold">
                        <td class="text-left" colspan="3">
                            <a href="/transItem/addTransItem" class="btn btn-success btn-sm ml-3">Dodaj nowy</a>
                        </td>
                        <td class="text-right">Razem:</td>
                        <td class="text-right">1233.00</td>
                        <td class="text-right">852.00</td>
                        <td class="text-right">2359.00</td>
                    </tr>
                    </tfoot>
                </table>
         &lt;%&ndash;   </form:form>&ndash;%&gt;
        </div>
    </div>
--%>

    

</div>
</body>
</html>
