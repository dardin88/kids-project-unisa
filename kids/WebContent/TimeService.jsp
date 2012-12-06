<%-- 
    Document   : TimeService
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


        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>

        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/TimeService.js"></script>
        <title>Orario di servizio</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializeTimeServicePage();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="TimeService.jsp">
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
        <h1>Orario di Servizio</h1>
        <div id="timeserviceManagement">    <%--/generale--%>
            <div id="timeserviceTab">      <%--div tab jQuery--%>
                <ul>
                    <li><a href="#InsertTime"><span class="TimeTab">Inserisci Orari di servizio</span></a></li>

                </ul>
                <div id="InsertTime">
                    <form id="InsertTimeForm" class="cmxform" method="post" action="InsertTime">
                        <h1>Inserisci Orario di apertura</h1>
                        <textarea name="testo" rows="10" cols="100">
          
                        </textarea>
                        <h1>Inserisci Orario di chiusura</h1>  
                        <textarea name="testo" rows="10" cols="100">
          
                        </textarea>
                    </form>
                </div>    
            </div> 
        </div> 

        <%@include file="footer.jsp" %>
    </body>
</html>
