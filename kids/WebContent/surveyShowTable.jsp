<%-- 
    Document   : surveyShowTable
    Created on : Dec 9, 2012, 7:28:20 PM
    Author     : felice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${sessionScope.user.getAccountType()!='Segreteria' && sessionScope.user.getAccountType()!='Genitore'}">
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

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/compiledSurveyShowTable.js"></script>
        <title>Kids</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeLinksManager();
                activePage();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <input id ="userid" type="hidden"value="${sessionScope.user.getId()}">
        <div id="surveyManagement">
            <div id="questionariTable">
                <table id="linkTable">
                    <thead>
                        <tr>
                            <th>ID Questionario</th>
                            <th>Link</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
