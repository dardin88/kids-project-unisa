<%-- 
    Document   : traineeConvocation
    Created on : 1-dic-2012, 16.39.49
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Tirocinante'}">
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
        <script type="text/javascript" src="js/traineeConvocation.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                buildTraineeConvocationTable();
                
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="traineeConvocationManagement">
            <h1 align="center" style="font-size: 20px;">Convocazioni Tirocinante</h1>

            <table id="traineeConvocationTable">
                <thead>
                    <tr>
                        <th>Attivit&agrave</th>
                        <th>Data</th>
                        <th>Ora inizio</th>
                        <th>Ora fine</th>
                        <th>Partecipazione</th>

                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
