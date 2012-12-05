<%-- 
    Document   : insertTraineeRequest
    Created on : 25-nov-2012, 16.27.41
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
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <script type="text/javascript" src="js/functions.js"></script>
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>   
        <script type="text/javascript" src="js/managerTraineeRequest.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">
        <script type="text/javascript" src="js/fullcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">

        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function() {

                activePage();
                initializeLinksManager();
                createCalendar();
                
            });
        </script>
        <title>Inserisci richiesta di tirocinanti</title>
    </head>
    <div id="requestInformation" title="Attivit&agrave">
        <form id="modifyInformation"action="RemoveTraineeRequest" method="post">
            <table style="padding-bottom: 20px;">
                <input type="hidden" name="id" id="idRequest">
                <tr><td>Numero di tirocinanti*</td><td><input type="text" name="NumeroTirocinanti" id="TraineeNumber"disabled></td></tr>
                <tr><td>Data*</td><td><input type="text" name="Data" id="DateRequest"disabled></td></tr>
                <tr><td>Attivit&agrave*</td><td><input type="text" name="Attivita" id="Activity"disabled></td></tr>
                <tr><td>Ora di inizio*</td><td><input type="text" name="OraInizio" id="StartTimeRequest"disabled></td></tr>
                <tr><td>Ora di fine*</td><td><input type="text" name="OraFine" id="EndTimeRequest"disabled></td></tr>

            </table>
            <input type="submit" value="Salva" id="saveButton" style="visibility: hidden"><input type="button" value="Modifica" onclick="modifyDialog()" id="modifyButton"><input type="submit" name="removeRequest" id="removeRequest" value="Rimuovi"style="float:right">
        </form>
    </div>
    <div id="insertTraineeRequest" title="Richiesta di tirocinanti" style="display:inline">
        <form id="information"action="InsertTraineeRequest" method="post">
            <table>
                <input type="hidden" name="AccountDelegato" value="${sessionScope.user.getId()}">
                <tr><td>Numero di tirocinanti*</td><td><input type="text" name="NumeroTirocinanti" id="traineeNumber"></td></tr>
                <tr><td>Data*</td><td><input type="text" name="Data" id="date"></td></tr>
                <tr><td>Attivit&agrave*</td><td><input type="text" name="Attivita" id="activity"></td></tr>
                <tr><td>Ora di inizio*</td><td><input type="text" name="OraInizio" id="startTime"></td></tr>
                <tr><td>Ora di fine*</td><td><input type="text" name="OraFine" id="endTime"></td></tr>
            </table>
            <input id="send" type="submit" name="Invia" value="Invia" style="float:right">

        </form>
    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="traineeRequestManagement" >
            <input type="button" value="Inserisci richiesta di tirocinanti" id="InsertTraineeRequest" onClick="openInsertTraineeRequestDialog()">
            <div id="calendar" style="height:500px ;width: 600px;padding-top: 20px;">
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
