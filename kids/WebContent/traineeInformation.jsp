<%-- 
    Document   : traineeInformation
    Created on : 18-nov-2012, 12.29.10
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Delegato scienze della formazione'}">
        <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/traineeInformation.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/traineeInformation.js"></script>
        <jsp:include page="/GetTrainees" />
        <script type="text/javascript">
            $(document).ready(function() {
               
                initializeLinksManager();
            });
        </script>
        <title>Informazioni tirocinante</title>
    </head>
    <body>


        <%@include file="header.jsp" %>
        <div  style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Dati Tirocinante</h1>
            <% if (request.getParameter("Modifica") == null) {%>
            <form method="post" action="">
                <% } else {%>
                <form id="information" method="post" action="ModifyTrainee">
                    <% }%>

                    <table>
                        <tr><td>Matricola*</td><td><input id="Matricola" type="text" name="Matricola" value="${matricola}" <% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td> Nome*</td><td><input type="text" name="Nome" id="Nome" value="${nome}" <% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Cognome*</td><td><input type="text" name="Cognome" id="Cognome" value="${cognome}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Data di nascita*</td><td><input type="text" name="DataNascita" id="DataNascita" value="${dataNascita}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Città di nascita*</td><td><input type="text" name="CittaNascita" id="CittaNascita" value="${cittaNascita}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Città di residenza*</td><td><input type="text" name="CittaResidenza" id="CittaResidenza" value="${cittaResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Indirizzo*</td><td><input type="text" name="Indirizzo" id="Indirizzo" value="${indirizzo}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>CAP*</td><td><input type="text" name="CAP" id="CAP" value="${CAP}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonico" id="NumeroTelfonico" value="${numeroTelefonico}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Email*</td><td><input type="text" name="Email" id="Email" value="${email}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>

                    </table>
                    <% if (request.getParameter("Modifica") == null) {%>
                    <input type="submit" name="Modifica" value="Modifica" id="modifyButton"></input>
                    <% } else {%>
                    <input type="submit" name="Modifica" value="Salva Modifiche" id="modifyButton"></input>
                    <% }%>

                </form>
                <form method="post" action="trainees.jsp">
                    <input type="submit" name="Indietro" value="Indietro" id="genericButton"></input>
                </form>

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
