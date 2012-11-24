<%-- 
    Document   : informationRequest
    Created on : 20-nov-2012, 15.17.56
    Author     : utente
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
        <link rel="stylesheet" type="text/css" href="css/requestInformation.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/requests&responses.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <jsp:include page="/GetRequest" />

        <title>Richiesta</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div  style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Richiesta</h1>
        <table>
                        <tr><td>Numero Tirocinanti</td><td><input id="NumeroTirocinanti" type="text" name="NumeroTirocinanti" value="${numeroTirocinanti}"  readonly="true" ></td></tr>
                        <tr><td>Data</td><td><input type="text" name="Data" id="Data" value="${data}" readonly="true" ></td></tr>
                        <tr><td>Ora inizio</td><td><input type="text" name="OraInizio" id="OraInizio" value="${oraInizio}"readonly="true" ></td></tr>
                        <tr><td>Ora fine</td><td><input type="text" name="OraFine" id="OraFine" value="${oraFine}" readonly="true" ></td></tr>
                        <tr><td>Attivita</td><td><input type="text" name="Attivita" id="Attivita" value="${attivita}" readonly="true" ></td></tr>
                        

                    </table>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
