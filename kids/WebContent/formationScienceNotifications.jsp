<%-- 
    Document   : formationScienceRequest
    Created on : 19-nov-2012, 14.13.10
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
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/formationScienceNotifications.js"></script>
        <script type="text/javascript" src="js/fullcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Richieste</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                buildTraineeTable();
                initializeLinksManager();
                buildResponseFromTraineesTable();
                tab();
                createCalendar();
            });
        </script>
    </head>
    <div id="removeTraineeConvocationWindow" title="Rimuovi Convocazione Tirocinante" style="display: inline">
        <form id="removeTraineeConvocationForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="requirementLabel">Sei sicuro di voler eliminare questa Convocazione?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmRemoveButton" id="confirmRemoveTraineeConvocationButton" value="Si"/>
                    <input type="button" class="notConfirmRemoveButton" id="notConfirmRemoveTraineeConvocationButton" value="No"/>
                </p>
            </fieldset>
        </form>
    </div>
    <c:if test="${requestScope.message!=null}">
        <div id="error" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="formationScienceNotifications.jsp">
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
    <div id="requestInformation" title="Richiesta">
        <form method="post" action="InsertConvocation">
            <table style="padding-bottom: 20px;">
                <tr><td>Numero di tirocinanti</td><td><input type="text" id="TraineeNumber" name="NumeroTirocinanti" value="" readonly size="30"></td></tr>
                <tr><td>Data</td><td><input type="text" id="DateRequest"name="Data" value="" readonly size="30"></td></tr>
                <tr><td>Attivit&agrave</td><td><input type="text" id="Activity"name="Attivita" value="" readonly size="30"></td></tr>
                <tr><td>Ora di inzio</td><td><input type="text" id="StartTimeRequest"name="OraInizio" value="" readonly size="30"></td></tr>
                <tr><td>Ora di fine</td><td><input type="text" id="EndTimeRequest"name="OraFine" value="" readonly size="30"></td></tr> 
            </table>
            <h2 align="center"style="color:red;">Scegli tirocinanti</h2>
                    Nome:<input type="text" name="Nome" id="Nome" onkeyup="search()">
                    Cognome:<input type="text" name="Cognome" id="Cognome" onkeyup="search()">
                <table id="traineesTable" style="width:600px">
                    <thead>
                        <tr>
                            <th>Scegli</th>
                            <th>Matricola</th>
                            <th>Nome</th>
                            <th>Cognome</th>                         
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>  
            <input type="submit" name="Invia" value="Invia convocazione" id="sendConvocation" style="float:right;">


        </form>

    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="notificationsManagement">
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Richieste di Tirocinanti</a></li>
                    <li><a href="#tabs-2">Convocazioni</a></li>
                </ul>
                <div id="tabs-1">


                    <div id="calendar" style="height:500px ;width: 600px;padding-top: 20px;">
                    </div>

                </div>
                <div id="tabs-2">

                    <form id="nomeForm" method="post" name="nomeForm" style="padding-bottom: 20px;padding-top:20px;">
                        Nome:<input type="text" name="Nome" id="Nome">

                    </form>
                    <table id="responseTable" style="width:95%;">
                        <thead>
                            <tr>
                                <th>Tirocinante</th>
                                <th>Attivit&agrave</th>
                                <th>Data</th>
                                <th>Confermata</th>
                                <th>Operazioni</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>   
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>

</body>
</html>
