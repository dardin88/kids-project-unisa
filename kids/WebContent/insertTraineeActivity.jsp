<%-- 
    Document   : delegatePage
    Created on : 23-nov-2012, 16.13.10
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Segreteria'}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <link rel="stylesheet" type="text/css" href="css/delegatePage.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/insertTraineeActivity.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/template.css">

        <script type="text/javascript" src="js/functions.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function() {

                activePage();
                createTable();
                initializeLinksManager();
            });
        </script>
        <title>Inserisci Attività</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="traineesManagement">
            <form id="nomeForm" method="post" name="nomeForm">
                Nome:<input type="text" name="Nome" id="Nome" onkeyup="search()">
                Cognome:<input type="text" name="Cognome" id="Cognome" onkeyup="search()">
            </form>
            <form id="information" method="post" action="InsertTraineeActivity">
                <table id="table">
                    <thead>
                        <tr>
                            <th>Scelta</th>
                            <th>Matricola</th>
                            <th>Nome</th>
                            <th>Cognome</th>

                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <table style="padding-top: 20px;">
                    <input type="hidden" name="AccountDelegato" value="${sessionScope.user.getId()}">
                    <tr><td>Data*</td><td><input type="date" name="Data" readonly="true" id="date"></td></tr>
                    <tr><td>Nome attivit&agrave*</td><td><input type="text" name="Nome" readonly="true" id="name"></td></tr>
                    <tr><td>Descrizione</td><td><textarea name="Descrizione" rows="5" cols="30" readonly="true" id="description"></textarea></td></tr>
                    <tr><td>Ora di inizio*</td><td><input type="time" name="OraInizio" readonly="true" id="startTime"></td></tr>
                    <tr><td>Ora di fine*</td><td><input type="time" name="OraFine" readonly="true" id="endTime"></td></tr>

                </table>
                <p style="float:left;">I campi contrasseganati con * sono obbligatori</p>
                <input type="submit" id="send" name="Invia" value="Iserisci" style="float:right;"></input>
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
