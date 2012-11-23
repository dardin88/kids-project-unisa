<%-- 
    Document   : index
    Created on : 13-nov-2012, 14.15.12
    Author     : dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user!=null}">
    <c:redirect url="artefactsTable.jsp" />
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
        <script type="text/javascript" src="js/loginManager.js"></script>
        <title>Kids Traceability Indexer</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeLoginFields();
            });
        </script>
    </head>
    <body id="bodyLogin">
        <div id="loginHeader">
            <img id="loginImage" src="img/logo.png"/>
            <div id="loginTitle">Gestione link di tracciabilità - Kids Project</div>
        </div>
        <form id="loginForm" action="Login" method="post">
            <div id="errorBox">${requestScope.error}</div>
            <fieldset id="loginFieldSet">
                <div>
                    <input id="username" class="loginField" type="text" name="username" onfocus="enableTypingUsername()" onblur="initializeUsername()">
                </div>
                <div>
                    <input id="falsePassword" class="loginField" type="text" name="fpassword" onfocus="enableTypingPassword()">
                    <input id="password" class="loginField" type="password" name="password" onblur="initializePassword()">
                    <input type="button" id="loginButton" value="Login" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>