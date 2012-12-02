<%-- 
    Document   : delegatePage
    Created on : 23-nov-2012, 16.13.10
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${sessionScope.user == null || sessionScope.user.getAccountType() != 'Segreteria'}">
    <c:redirect url="index.jsp" />
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>

        <title> Segreteria- Kids Project</title>

        <script type="text/javascript">
            $(document).ready(function() {
                initializeLinksManager();
                messageDialog();
            });
        </script>
    </head>

    <body>
        <%@include file="header.jsp" %>

        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="secretaryPage.jsp">
                    <fieldset>
                        <p class="formp">
                            <label for="confirmButton" class="requirementLabel">${requestScope.message}</label>
                        </p>
                        <p class="formp">
                            <input type="submit" class="confirmButton" id="confirmButton" value="ok"/>
                        </p>
                    </fieldset>
                </form>
            </div>
        </c:if>

        <div id="description" style="padding-top:20px;padding-left:20px;">
            <h1 style="font-size: 35px;text-align: center;">Gestione Segreteria</h1>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
