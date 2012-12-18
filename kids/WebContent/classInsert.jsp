<%-- 
    Document   : classe
    Created on : 23-nov-2012, 14.57.05
    Author     : Antonio Porcelli
--%>

<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild"%>
<%@page import="it.unisa.kids.accessManagement.accountManagement.Account"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.getAccountType()!='Responsabile Asilo'}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.id >=0}">
    <c:if test="${sessionScope.user.getAccountType()=='Genitore'}"> 
        <c:redirect url="newsGenitorePage.jsp" />
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/classInsert.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                initializeInsertFields();
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <% String cercamiNeiSogni = "insert";
            session.setAttribute("cercamiNeiSogni", cercamiNeiSogni);
        %>

        <form id="insertClassForm" class="cmxform"  action="AddClassBean" method="post">
            <div id="artefactsManagement">
                <div>
                    <label>Nome</label>
                    <input id="name" class="classNameField" type="text" name="Nome">
                </div>
                <input id="state" type="hidden" name="Stato"  value="bozza">
                <div id="childrenTableId">
                    <table id="childrenTable">
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
                    <table id="educatorTable">
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
            <input type="submit" name="saveClassButton" id="saveClassButton" class="biggerClassButton" value="Salva Bozza" />
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>