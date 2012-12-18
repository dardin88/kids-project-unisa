<%-- 
    Document   : schedulerActivity
    Created on : 22-nov-2012, 11.18.11
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Delegato scienze della formazione'}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/schedulerActivity.js"></script>
        <script type="text/javascript" src="js/fullcalendar.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>

        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                createCalendar();
                initializeLinksManager();
            });
        </script>
    </head>
    <div id="activityInformation" title="Attivit&agrave">
        <table style="padding-bottom: 20px;">
            <tr><td>Tirocinante</td><td><input type="text" id="TraineeRegister" name="MatricolaTirocinante" value="" readonly size="30"></td></tr>
            <tr><td>Attivit&agrave</td><td><input type="text" id="TraineeActivity" name="Attivita" value="" readonly size="30"></td></tr>
            <tr><td>Data</td><td><input type="text" id="DateActivity"name="Data" value="" readonly size="30"></td></tr>
            <tr><td>Descrizione</td><td><textarea  id="Description"name="Descrizione" value="" readonly resizable="false" cols="29" rows="6" style="resize: none"></textarea></td></tr>
            <tr><td>Ora di inzio</td><td><input type="text" id="StartTimeActivity"name="OraInizio" value="" readonly size="30"></td></tr>
            <tr><td>Ora di fine</td><td><input type="text" id="EndTimeActivity"name="OraFine" value="" readonly size="30"></td></tr> 
            <tr><td>Giudizio</td><td><textarea id="Opinion" name="Giudizio" value="" readonly cols="29" rows="6" style="resize: none"></textarea></td></tr>

        </table>
    </div>
    <body>
        <%@include file="header.jsp" %>
        <h1 align="center" style="font-size:30px;padding-top: 5px;">Attività svolte dai Tirocinanti</h1>

        <div id="calendar" style="float:left;width:760px;padding-left: 50px;padding-top: 50px;" >

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
