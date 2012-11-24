<%-- 
    Document   : formationScienceRequest
    Created on : 19-nov-2012, 14.13.10
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Delegato scienze della formazione'}">
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
        <link rel="stylesheet" type="text/css" href="css/formationScienceNotifications.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
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
                buildRequestOfficeDelegateTable();
                initializeLinksManager();
                buildResponseFromTraineesTable();
                tab();
                createCalendar();
            });
        </script>
    </head>
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
                                <th>Data</th>
                                <th>Tirocinante</th>
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
