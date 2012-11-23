<%-- 
    Document   : account
    Created on : 23-nov-2012, 14.57.05
    Author     : tonino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <title>Kids Registrazione Account</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <body id="bodyRegistration">
        <%@include file="header.jsp"%>
        
        <form id="registrationForm" action="Registrati" method="post">
            <fieldset id="registrationFieldSet">
                <div>
                    <input id="accountName" class="registrationField" type="text" name="name" onfocus="enableTypingName()" onblur="initializeName()">
                    <input id="accountSurname" class="registrationField" type="text" name="surname" onfocus="enableTypingSurname()" onblur="initializeSurname()">
                    <input id="dateOfBirth" class="registrationField" type="text" name="dateOfBirth" onfocus="">
                    
                    <input type="button" id="registrationButton" value="Registrati" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>