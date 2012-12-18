<%-- 
    Document   : classeModify
    Created on : 10-dic-2012, 9.18.03
    Author     : Antonio Porcelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.getAccountType()!='Responsabile Asilo'}">
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
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/classModify.js"></script>

        <title>Kids</title>

        <jsp:include page="/GetClass" /> 
        <script type="text/javascript">      
            $(document).ready(function() {
                initializeModifyClassFields();
            });
        </script>
    </head>

    <%@include file="header.jsp"%>

    <body id="bodyRegistration">

        <%
            String cercamiNeiSogni = "modify";
            session.setAttribute("cercamiNeiSogni", cercamiNeiSogni);
        %>
        <form id="modifyClassForm" class="cmxform"  action="UpdateClassBean" method="post">
            <div id="artefactsManagement">
                <div id="modifyClassName">
                    <input type="hidden" id="classId" name="classId"  value="${id}">
                    <label>Nome classe</label>
                    <input type="text" id="className"  name="className" value="${Nome}"/>
                </div>
                <div id="childrenTableId">
                    <table id="childrenTable" style="width:95%;">
                        <thead>
                            <tr>
                                <th>Nome bambino</th>
                                <th>Cognome bambino</th>
                                <th>Operazione</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div id="educatorTableId">
                    <table id="educatorTable" style="width:95%;">
                        <thead>
                            <tr>
                                <th>Nome educatore</th>
                                <th>Cognome educatore</th>
                                <th>Operazione</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <input class="classButton" type="button" value="Indietro" id="backClassButton" onclick="window.location.replace('class.jsp');"/>
            <c:if test="${requestScope.Stato == 'bozza'}">
                <input type="submit" id="draftClassButton" name="draftClassButton" class="classButton" value="Salva bozza" />
            </c:if>
            <input type="submit" id="submitClassButton" name="submitClassButton" class="classButton" value="Sottometti" />
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>