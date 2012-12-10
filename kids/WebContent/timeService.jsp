<%-- 
    Document   : timeService
    Created on : 6-dic-2012, 10.13.44
    Author     : stefanoferrante
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" type="text/css" href="css/template.css" >
        <script type="text/javascript" src="js/functions.js"></script>
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>   
        <script type="text/javascript" src="js/timeService.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">
        <script type="text/javascript" src="js/fullcalendar.js"></script>
        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">

        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Orario di servizio</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializetimeServicePage();
                showTimeService();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="timeService.jsp">
                    <fieldset>
                        <p class="formp">
                            <label class="requirementLabel">${requestScope.message}</label>
                        </p>
                        <p class="formp">
                            <input type="submit" class="confirmButton" id="confirmButton" value="OK">
                        </p>
                    </fieldset>
                </form>
            </div>
        </c:if>

        <div id="timeserviceManagement">    <%--/generale--%>
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                <div id="timeserviceTab">      <%--div tab jQuery--%>
                    <ul>
                        <li><a href="#visualTime"><span class="TimeTab">Visualizza Orari di servizio</span></a></li>
                        <li><a href="#RequestTime"><span class="TimeTab">Richiesta Orari di servizio</span></a></li>

                    </ul>
                    <div id="visualTime">
                        <div id="notifyTimeService">
                            <textarea readonly style="resize:none"name="orarioDiServizio" rows="20" cols="100" id="TextAreaTimeService"></textarea>
                        </div>
                    </div>

                    <div id="RequestTime">
                        <form id="requestTimeForm" class="cmxform" method="post" action="requestTime">
                            <table>
                                <tr><td>Seleziona servizio</td><td> <select name="type" id="type">
                                            <option value="PreAccoglienza">Pre-accoglienza</option>
                                            <option value="PostAccoglienza">Post-accoglienza</option>
                                            <option value="ProlungamentoOrario">Prolungamento Orario di base</option>
                                        </select></td></tr>
                                <tr><td>Giorno</td><td><select name="giorno" id="day">
                                            <option value="Lunedi">Luned&igrave</option>
                                            <option value="Martedi">Marted&igrave</option>
                                            <option value="Mercoledi">Mercoled&igrave</option>
                                            <option value="Giovedi">Gioved&igrave</option>
                                            <option value="Venerdi">Venerd&igrave</option>
                                        </select>
                                    </td></tr>
                                <tr><td>Ora</td><td><input type="text" name="Ora" id="Time"></td></tr>
                                <tr><td>Data</td><td><input type="text" name="Data" id="Date"></td></tr>
                            </table>
                            <input type="submit" name="SendRequest" value="Invia Richiesta" id="SendRequest">
                    </div>
                </div> <%--chiusura div per jquery Genitore --%>   
            </c:if>   
            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}"> 
                <div id="timeserviceTab">      <%--div tab jQuery--%>
                    <ul>
                        <li><a href="#InsertTime"><span class="TimeTab">Orari di servizio</span></a></li>
                        <li><a href="#notifyTimeService"><span class="TimeTab">Notifiche</span></a></li>
                    </ul>
                    <div id="InsertTime">
                        <form id="InsertTimeForm" method="post" action="UpdateTimeService">
                            <h1>Orario di servizio</h1>
                            <input type="hidden" id="idNews" name="idNews">
                            <textarea name="orarioDiServizio" rows="20" cols="100" id="TextAreaTimeService" style="resize:none;display:block"></textarea>
                            <input type="submit" name="Insert" value="Salva" id="InsertTimeServiceButton">
                        </form>
                    </div>

                    <div id="notifyTimeService">
                        <table id="notifyTable">
                            <thead>
                                <tr>
                                    <th>N.</th>
                                    <th>Notifiche</th>
                                    <th>Operazioni</th>
                                </tr>
                            </thead>

                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div> 
            </c:if>
        </div> 

        <%@include file="footer.jsp" %>
    </body>
</html>
