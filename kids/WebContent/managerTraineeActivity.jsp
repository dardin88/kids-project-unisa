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
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/fullcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/managerTraineeActivity.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">
        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function() {
                createCalendar();
                activePage();
                createTable();
                initializeLinksManager();
                
            });
        </script>
        <title>Inserisci Attività</title>
    </head>
    <c:if test="${requestScope.message!=null}">
        <div id="confirm" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="managerTraineeActivity.jsp">
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
    <div id="activityInformation" title="Attivit&agrave">
        <form id="modifyInformation"action="RemoveTraineeActivity" method="post">
            <table style="padding-bottom: 20px;">
                <input type="hidden" name="id" id="id">
                <tr><td>Tirocinante</td><td><input type="text" id="TraineeRegister" name="MatricolaTirocinante" value="" disabled size="30"></td></tr>
                <tr><td>Attivit&agrave</td><td><input type="text" id="TraineeActivity" name="Attivita" value="" disabled size="30"></td></tr>
                <tr><td>Data</td><td><input type="text" id="DateActivity"name="Data" value="" disabled size="30"></td></tr>
                <tr><td>Descrizione</td><td><textarea  id="Description"name="Descrizione" value="" disabled resizable="false" cols="29" rows="6" style="resize: none"></textarea></td></tr>
                <tr><td>Ora di inzio</td><td><input type="text" id="StartTimeActivity"name="OraInizio" value="" disabled size="30"></td></tr>
                <tr><td>Ora di fine</td><td><input type="text" id="EndTimeActivity"name="OraFine" value="" disabled size="30"></td></tr> 
                <tr><td>Giudizio</td><td><textarea id="Opinion" name="Giudizio" value="" disabled cols="29" rows="6" style="resize: none"></textarea></td></tr>

            </table>
            <input type="submit" value="Salva" id="saveButton" style="visibility: hidden"><input type="button" value="Modifica" onclick="modifyDialog()" id="modifyButton"><input type="submit" name="removeActivity" id="removeActivity" value="Rimuovi"style="float:right">
        </form>
    </div>
    <div id="insertTraineeActivity" style="display: inline;" title="Inserisci Attivit&agrave">
        <form id="nomeForm" method="post" name="nomeForm">
            Nome:<input type="text" name="Nome" id="Nome" onkeyup="search()">
            Cognome:<input type="text" name="Cognome" id="Cognome" onkeyup="search()">
        </form>
        <form id="information" class="cmxform" method="post" action="InsertTraineeActivity">
            <table id="table" style="width:570px">
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
                <tr><td>Data*</td><td><input type="text" name="Data" disabled id="date"></td></tr>
                <tr><td>Nome attivit&agrave*</td><td><input type="text" name="Nome" readonly="true" id="name"></td></tr>
                <tr><td>Descrizione</td><td><textarea name="Descrizione" rows="5" cols="30" readonly="true" id="description"></textarea></td></tr>
                <tr><td>Ora di inizio*</td><td><input type="text" name="OraInizio" id="startTime"disabled></td></tr>
                <tr><td>Ora di fine*</td><td><input type="text"  name="OraFine"  id="endTime" disabled></td></tr>
                <tr><td>Giudizio</td><td><textarea name="Giudizio" rows="5" cols="30" readonly="true" id="opinion"></textarea></td></tr>

            </table>
            <p style="float:left;">I campi contrasseganati con * sono obbligatori</p>
            <input type="submit" id="send" name="Invia" value="Inserisci" style="float:right;">
        </form>

    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="traineesManagement">
            <input type="button" style=""id="insertTraineeActivityButton" name="InsertTraineeActivityButton" value="Inserisci Attivit&agrave" onclick="openDialogInsertTraineeActivity()">
            <div id="calendar" style="width:760px;padding-left: 50px;padding-top: 50px;" ></div>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
