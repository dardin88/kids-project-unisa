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
        
        <form id="registrationForm" action="ModifyAccount2" method="post">
            <fieldset id="registrationFieldSet">
                <div>
                    
                    Indirizzo Residenza: <input id="viaResidence" class="registrationField" type="text" name="ViaResidence">
                    C.A.P. Residenza : <input id="capResidence" class="registrationField" type="text" name="CapResidence">
                    Numero di telefono: <input id="telephoneNumber" class="registrationField" type="text" name="Telefono">
                    Numero di cellulare: <input id="cellularNumber" class="registrationField" type="text" name="Cellulare">
                    Fax: <input id="fax" class="registrationField" type="text" name="Fax">
                    Indirizzo email: <input id="email" class="registrationField" type="text" name="Cellulare">
                    Comune di Domicilio:<input id="municipalityDomicilie" class="registrationField" type="text" name="ComuneDomicilio">
                    Provincia di Domicilio:<input id="provinceDomicilie" class="registrationField" type="text" name="ProvinciaDomicilio">
                    Indirizzo Domicilio:<input id="viaDomicile" class="registrationField" type="text" name="ViaDomicilio">
                   
                    
                    
                    <input type="submit" id="registrationButton" value="Completa Registrazione" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>