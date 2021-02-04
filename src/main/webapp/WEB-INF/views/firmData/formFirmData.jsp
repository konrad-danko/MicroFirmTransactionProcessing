<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Firma Data</title>
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
        <div style="width:90%">
            <h2>${headerMessage}</h2>
            <form:form modelAttribute="firmData" method="post" class="border rounded shadow-lg">
                <div class="d-flex mt-3">
                    <div class="form-group mx-2" style="width:3em">
                        <label for="id">Id:</label>
                        <form:input path="id" id="id" class="form-control" disabled="true"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmName">Nazwa:</label>
                        <form:input path="firmName" id="firmName" class="form-control" disabled="${disabledParam}" autofocus="true"/>
                        <form:errors path="firmName" class="text-danger"/>
                    </div>
                </div>
                <br>
                <div class="d-flex">
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmPostCode">Kod pocztowy:</label>
                        <form:input path="firmPostCode" id="firmPostCode" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmPostCode" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmCity">Miejscowość:</label>
                        <form:input path="firmCity" id="firmCity" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmCity" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmStreet">Ulica:</label>
                        <form:input path="firmStreet" id="firmStreet" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmStreet" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmPhone">Telefon:</label>
                        <form:input path="firmPhone" id="firmPhone" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmPhone" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmNIP">NIP:</label>
                        <form:input path="firmNIP" id="firmNIP" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmNIP" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmREGON">REGON:</label>
                        <form:input path="firmREGON" id="firmREGON" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmREGON" class="text-danger"/>
                    </div>
                </div>
                <br>
                <div class="d-flex">
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmBankName">Nazwa Banku:</label>
                        <form:input path="firmBankName" id="firmBankName" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmBankName" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="firmBankAccount">Nr rachunku bankowego:</label>
                        <form:input path="firmBankAccount" id="firmBankAccount" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="firmBankAccount" class="text-danger"/>
                    </div>
                </div>
                <br>
                <br>
                <div class="form-group mb-4">
                    <a href="/home" class="btn btn-primary mx-3">Wróć na stronę główną</a>
                    <a href="/firmData/editFirmData/${firmData.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">

                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
