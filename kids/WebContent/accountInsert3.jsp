<%-- 
    Document   : account
    Created on : 23-nov-2012, 14.57.05
    Author     : tonino ft. gianma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
<c:if test="${sessionScope.id >=0}">
     <c:if test="${sessionScope.user.getAccountType()=='Genitore'}"> 
        <c:redirect url="newsGenitorePage.jsp" />
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationManager.js"></script>
        <title>Registrazione Account - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">
        
        
        <h1  id="titleReg" align="center">Form di Registrazione</h1>
        
        <form id="registrationForm" action="ModifyAccoun3" method="post">
            <fieldset id="registrationFieldSet">
                <div>
                    Cap Domicilio : <input id="capDomicilie" class="registrationField" type="text" name="CapDomicilio">
                    Titolo di Studio : <input id="qualification" class="registrationField" type="text" name="TitoloStudio">
                    Tipo di Account <input id="accountType" class="registrationField" type="text" name="accountType" value="TipoAccount">
                    Situazione Familiaria : <input id="familySituation" class="registrationField" type="text" name="SituazioneFamiliaria">
                    Reddito : <input id="income" class="registrationField" type="text" name="Reddito">
                    Scadenza Contratto : <input id="contractExpirationDate" class="registrationField" type="text" name="ScadenzaContratto">
                    Facoltà <input id="faculty" class="registrationField" type="text" name="Facolta">
                    Data di Immatricolazione : <input id="registrationDate" class="registrationField" type="text" name="DataRegistrazione">
                    Tipologia Genitore : <input id="typeParent" class="registrationField" type="text" name="TipoAccount" /> 
                    Matricola : <input type="text" id="register" name="Matricola"  class="registrationField" />
                    
                    <input type="submit" id="registrationButton" value="Completa Registrazione" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
