<%-- 
    Document   : accountInformation
    Created on : 27-nov-2012, 16.08.07
    Author     : Gianmarco
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Genitore'}">
        <c:redirect url="index.jsp" />
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
         <jsp:include page="/GetAccount" />
           <script type="text/javascript">
            $(document).ready(function() {
               
                initializeLinksManager();
            });
        </script>
    </head>
    <body>
        
       <%@include file="header.jsp" %>
        <div  style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Dati Genitore</h1>
            <% if (request.getParameter("Modifica") == null) {%>
            <form method="post" action="">
                <% } else {%>
                <form id="information" method="post" action="ModifyAccount">
                    <% }%>

                    <table>
                        <tr><td>Matricola*</td><td><input id="id" type="text" name="id" value="${id}" readonly="true"></td></tr>
                        <tr><td>Nome*</td><td><input type="text" name="Nome" id="Nome" value="${nome}" <% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Cognome*</td><td><input type="text" name="Cognome" id="Cognome" value="${cognome}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Data di nascita*</td><td><input type="text" name="DataNascita" id="DataNascita" value="${dataNascita}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Comune di nascita*</td><td><input type="text" name="CittaNascita" id="ComuneNascita" value="${comuneNascita}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Comune di residenza*</td><td><input type="text" name="CittaResidenza" id="ComuneResidenza" value="${comuneResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>CAP Residenza*</td><td><input type="text" name="CAPResidenza" id="capResidenza" value="${capResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Comune Residenza*</td><td><input type="text" name="ComuneResidenza" id="comuneResidenza" value="${comuneResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Provincia Residenza*</td><td><input type="text" name="ProvinciaResidenza" id="ProvinciaResidenza" value="${provinciaResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Numero Civico Residenza*</td><td><input type="text" name="numCivRes" id="numCivRes" value="${numeroCivicoResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Via Residenza*</td><td><input type="text" name="viaResidenza" id="viaResidenza" value="${viaResidenza}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>CAP Domicilio*</td><td><input type="text" name="CAPDomicilio" id="CAPDomicilio" value="${capDomicilio}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Comune Domicilio*</td><td><input type="text" name="ComuneDomicilio" id="ComuneDomicilio" value="${comuneDomicilio}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Provincia Domicilio*</td><td><input type="text" name="ProvinciaDomicilio" id="ProvinciaDomicilio" value="${provinciaDomicilio}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Numero Civico Domicilio*</td><td><input type="text" name="numCivDom" id="numCivDom" value="${numeroCivicoDomicilio}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Via Domicilio*</td><td><input type="text" name="viaDomicilio" id="viaDomicilio" value="${viaDomicilio}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>CAP*</td><td><input type="text" name="CAP" id="CAP" value="${CAP}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonico" id="NumeroTelfonico" value="${numeroTelefonico}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Cellulare </td><td><input type="text" name="NumeroTelefonico" id="NumeroCellulare" value="${numeroCellulare}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Email*</td><td><input type="text" name="Email" id="Email" value="${email}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>Fax</td><td><input type="text" name="Fax" id="Fax" value="${fax}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>
                        <tr><td>SituazioneFamiliare</td><td><input type="text" name="SituazioneFamiliare" id="SituazioneFamiliare" value="${situazioneFamiliare}"<% if (request.getParameter("Modifica") == null) {%> readonly="true" <%}%>></td></tr>

                    </table>
                    <% if (request.getParameter("Modifica") == null) {%>
                    <input type="submit" name="Modifica" value="Modifica" id="modifyButton"></input>
                    <input type="button" name="Elimina" value="Elimina" id="eliminaButton" onclick="removeAccount(id.value)"></input>
                    <% } else {%>
                    <input type="submit" name="Modifica" value="Salva Modifiche" id="modifyButton"></input>
                    <% }%>

                </form>
    <c:if test="${sessionScope.user!=null}">
        <c:if test="${sessionScope.user.getTypeAccount()=='Genitore'}"> 
                <form method="post" action="index.jsp">
                    <input type="submit" name="Indietro" value="Indietro" id="genericButton"></input>
                </form>
        </c:if>
        <c:if test="${sessionScope.user.getTypeAccount()=='Admin'}">        
                <form method="post" action="tableAccount.jsp">
                    <input type="submit" name="Indietro" value="Indietro" id="genericButton"></input>
                </form>
                
                <div id="removeAccount" title="Titolo della finestra">
                      <P> <h3> Attenzione! </h3> <br> vuoi davvero eliminare questo Account?<BR>
                      N.B. una volta confermato la scelta non potrai più tornare indietro!</P>
                </div>
                
        </c:if> 
    </c:if>
                

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
