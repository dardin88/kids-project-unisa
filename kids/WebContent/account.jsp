<%-- 
    Document   : account
    Created on : 23-nov-2012, 14.57.05
    Author     : tonino ft. gianma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
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
    <body id="bodyRegistration">
        <%@include file="header.jsp"%>
        
        <h1  id="title" align="center">Form di Registrazione</h1>
        
        <form id="registrationForm" action="Registrati" method="post">
            <fieldset id="registrationFieldSet">
                <div>
                    <input id="accountName" class="registrationField" type="text" name="name">
                    <input id="accountSurname" class="registrationField" type="text" name="surname">
                    <input id="dateOfBirth" class="registrationField" type="text" name="dateOfBirth">
                    <input id="placeOfBirth" class="registrationField" type="text" name="placeOfBirth">
                    <input id="taxCode" class="registrationField" type="text" name="taxCode">
                    <input id="citizenship" class="registrationField" type="text" name="citizenship">
                    <input id="municipalityResidence" class="registrationField" type="text" name="municipalityResidence">
                    <input id="provinceResidence" class="registrationField" type="text" name="provinceResidence">
                    <input id="viaResidence" class="registrationField" type="text" name="viaResidence">
                    <input id="streetNumberResidence" class="registrationField" type="text" name="streetNumberResidence">
                    <input id="telephoneNumber" class="registrationField" type="text" name="telephoneNumber">
                    <input id="cellularNumber" class="registrationField" type="text" name="cellularNumber">
                    <input id="fax" class="registrationField" type="text" name="fax">
                    <input id="email" class="registrationField" type="text" name="cellularNumber">
                    <input id="viaDomicile" class="registrationField" type="text" name="viaDomicile">
                    <input id="streetNumberDomicilie" class="registrationField" type="text" name="streetNumberDomicilie">
                    <input id="municipalityDomicilie" class="registrationField" type="text" name="municipalityDomicilie">
                    <input id="provinceDomicilie" class="registrationField" type="text" name="provinceDomicilie">
                    <input id="capResidence" class="registrationField" type="text" name="capResidence">
                    <input id="capDomicilie" class="registrationField" type="text" name="capDomicilie">
                    <input id="qualification" class="registrationField" type="text" name="qualification">
                    <input id="accountType" class="registrationField" type="text" name="accountType">
                    <input id="familySituation" class="registrationField" type="text" name="familySituation">
                    <input id="income" class="registrationField" type="text" name="income">
                    <input id="contractExpirationDate" class="registrationField" type="text" name="contractExpirationDate">
                    <input id="faculty" class="registrationField" type="text" name="faculty">
                    <input id="registrationDate" class="registrationField" type="text" name="registrationDate">
                    
                    
                    <input type="button" id="registrationButton" value="Registrati" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>