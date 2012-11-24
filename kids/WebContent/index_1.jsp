<%-- 
    Document   : index
    Created on : 13-nov-2012, 14.15.12
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user!=null}">
    <c:if test="${sessionScope.user.getTypeAccount()=='Tirocinante'}">
        <c:redirect url="traineePage.jsp" />
    </c:if>
     <c:if test="${sessionScope.user.getTypeAccount()=='Delegato scienze della formazione'}">
        <c:redirect url="formationSciencePage.jsp" />
    </c:if>
    <c:if test="${sessionScope.user.getTypeAccount()=='Delegato Ufficio'}">
        <c:redirect url="delegatePage.jsp" />
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css"
              href="css/overcast/jquery-ui-1.8.18.custom.css">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/loginManager.js"></script>
        <title>Kids Training</title>
        <script type="text/javascript">
            $(function() {
                initializeLoginFields();
            });
        </script>
    </head>
    <body id="bodyLogin">
        <div id="loginHeader">
            <img id="loginImage" src="img/logo.png"/>
            <div id="loginTitle">Gestione Tirocini </div>
        </div>
        <form id="loginForm" action="Access" method="post">
            <div id="errorBox">${requestScope.error}</div>
            <fieldset id="loginFieldSet">
                <div>
                    <input id="username" class="loginField" type="text" name="Nickname" onfocus="enableTypingUsername()" onblur="initializeUsername()">
                </div>
                <div>
                    <input id="falsePassword" class="loginField" type="text" name="fpassword" onfocus="enableTypingPassword()">
                    <input id="password" class="loginField" type="password" name="Password" onblur="initializePassword()">
                    <input type="button" id="loginButton" value="Login" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
