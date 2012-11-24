<%-- 
    Document   : insertTrainee
    Created on : 16-nov-2012, 15.36.49
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Delegato scienze della formazione'}">
        <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/insertTrainee.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/insertTrainee.js"></script>
        <title>Inserisci Tirocinante</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                messageDialog();
            });
        </script>
    </head>
    <c:if test="${requestScope.message!=null}">
        <div id="confirm" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="insertTrainee.jsp">
                <fieldset>
                    <p class="formp">
                        <label class="requirementLabel">${requestScope.message}</label>
                    </p>
                    <p class="formp">
                        <input type="submit" class="confirmButton" id="confirmButton" value="ok"/>

                    </p>
                </fieldset>
            </form>
        </div>
    </c:if>
    <body>
        <%@include file="header.jsp" %>
        <div id="insert" style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Inserisci dati Tirocinante</h1>
            <form id="information" action="InsertTrainee" method="post">
                <input type="hidden" name="AccountDelegato" value="${sessionScope.user.getId()}">
                <table>
                    <tr><td>Matricola*</td><td><input id="Matricola" type="text" name="Matricola" ></td></tr>
                    <tr><td> Nome*</td><td><input type="text" name="Nome" id="Nome"></td></tr>
                    <tr><td>Cognome*</td><td><input type="text" name="Cognome" id="Cognome"></td></tr>
                    <tr><td>Data di nascita*</td><td><input type="date" name="DataNascita" id="DataNascita"></td></tr>
                    <tr><td>Città di nascita*</td><td><input type="text" name="CittaNascita" id="CittaNascita"></td></tr>
                    <tr><td>Città di residenza*</td><td><input type="text" name="CittaResidenza" id="CittaResidenza"></td></tr>
                    <tr><td>Indirizzo*</td><td><input type="text" name="Indirizzo" id="Indirizzo"></td></tr>
                    <tr><td>CAP*</td><td><input type="text" name="CAP" id="CAP"></td></tr>
                    <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonico" id="NumeroTelfonico"></td></tr>
                    <tr><td>Email*</td><td><input type="text" name="Email" id="Email"></td></tr>
                </table>
                <p>I campi contrasseganati con * sono obbligatori</p>
                <input id="insertButton" type="submit" name="submit" value="Inserisci" onClick="controlla()" >
            </form>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
