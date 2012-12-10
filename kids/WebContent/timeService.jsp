<%-- 
    Document   : timeService
    Created on : 6-dic-2012, 10.13.44
    Author     : stefanoferrante
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <link rel="stylesheet" type="text/css" href="css/TableTools.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/TableTools.min.js"></script>
        <script type="text/javascript" src="js/timeService.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>


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
                            <table id="notifyTable">
                                <thead>
                                    <tr>
                                        <th>Orarii di Apertura</th>
                                        <th>Orari di chiusura</th>
                                       
                                    </tr>
                                </thead>

                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div id="requestTime">
                        <form id="requestTimeForm" class="cmxform" method="post" action="requestTime">
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
                            <form id="InsertTimeForm" method="post" action="InsertTimeService">
                                <h1>Orario di servizio</h1>
                                <textarea name="orarioDiServizio" rows="20" cols="100" id="TextAreaTimeService"></textarea>
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
