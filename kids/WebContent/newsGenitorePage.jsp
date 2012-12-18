<%-- 
    Document   : newsGenitorePage
    Created on : 26-nov-2012, 18.25.29
    Author     : Francesco Di Lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/newsGenitorePage.js"></script> 
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>   

        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/template.css" >       

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
            });
        </script>
    </head>
    <c:if test="${requestScope.message != null}">
        <div id="confirm" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="newsGenitorePage.jsp">
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
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <h1 style="font-size: 35px;text-align: center;"> Benvenuto nella vostra sezione,nel menù a sinistra avete le vostre scelte! </h1>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
