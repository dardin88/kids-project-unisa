<%-- 
    Document   : timeService
    Created on : 6-dic-2012, 10.13.44
    Author     : stefanoferrante
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Genitore' && sessionScope.user.getAccountType()!='Segreteria'}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">              
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/timeService.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">


        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Orario di servizio</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializetimeServicePage();
                showTimeService();
                buildRequestTimeServiceParentTable();
                buildRequestTimeServiceSecretaryTable();
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
                        <li><a href="#RequestModifyTimeService"><span class="TimeTab">Richiesta Modifica Orari di servizio</span></a></li>
                    </ul>
                    <div id="visualTime">
                        <div id="notifyTimeService">
                            <textarea readonly style="resize:none"name="orarioDiServizio" rows="20" cols="100" id="TextAreaTimeService"></textarea>
                        </div>
                    </div>

                    <div id="RequestTime">
                        <input type="button" id="insertRequestTime" value="Inserisci la richiesta di orario di servizio" onclick="openInsertRequestTimeDialog()">
                        <table id="requestTimeServiceParentTable">
                            <thead>
                                <tr>
                                    <th>Servizio</th>
                                    <th>Giorno</th>
                                    <th>Confermato</th>
                                    <th>Operazioni</th>

                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div id="insertRequestTimeDialog">
                            <form id="requestTimeForm" class="cmxform" method="post" action="InsertRequestTime">
                                <table>
                                    <tr><td>Seleziona servizio*</td><td> <select name="tipo" id="type" >
                                                <option value="Pre-Accoglienza(7:30-8:00)">Pre-accoglienza(7:30-8:00)</option>
                                                <option value="Post-Accoglienza(18:00-19:30)">Post-accoglienza(18:00-19:30)</option>
                                                <option value="Prolungamento Orario di base(8:00-18:00)">Orario prolungato(8:00-18:00)</option>
                                            </select></td></tr>
                                    <tr><td>Giorno*</td><td><select name="giorno" id="day">
                                                <option value="Lunedi">Luned&igrave</option>
                                                <option value="Martedi">Marted&igrave</option>
                                                <option value="Mercoledi">Mercoled&igrave</option>
                                                <option value="Giovedi">Gioved&igrave</option>
                                                <option value="Venerdi">Venerd&igrave</option>
                                            </select>
                                        </td></tr>

                                </table>
                                <h1>* Campi obbligatori</h1>
                                <input type="submit" name="SendRequest" value="Invia Richiesta" id="SendRequest">
                                </div>
                                </div>
                    <div id="RequestModifyTimeService">
                        <div id="requestModifyTimeService">
                            <input type="button" id="insertRequestModifyTimeService" value="Inserisci richiesta di modifica orario di servizio" onclick="openInsertModifyTimeServiceRequestDialog()">
                            <div id="insertRequestModifyTimeServiceDialog">
                            <form id="requestTimeForm" class="cmxform" method="post" action="InsertRequestTime">
                                <table>
                                    

                                </table>
                                <h1>* Campi obbligatori</h1>
                                <input type="submit" name="SendRequest" value="Invia Richiesta" id="SendRequest">
                                </div>
                                </div>
                        </div>
                    </div>
                                </div> <%--chiusura div per jquery Genitore --%>   
                            </c:if>   
                            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}"> 
                                <div id="timeserviceTab">      <%--div tab jQuery--%>
                                    <ul>
                                        <li><a href="#InsertTime"><span class="TimeTab">Orari di servizio</span></a></li>
                                        <li><a href="#notifyTimeService"><span class="TimeTab">Richiesta orari di servizio</span></a></li>
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
                                                    <th>Genitore</th>
                                                    <th>Servizio</th>
                                                    <th>Giorno</th>
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
