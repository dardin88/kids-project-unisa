<%-- 
    Document   : classe
    Created on : 3-dic-2012, 13.07.01
    Author     : tonino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
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
        <title>Classe - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <body id="bodyRegistration">
        
        <h1  id="titleReg" align="center">Classe</h1>
        
        <form id="registrationForm" action="AddClassBeanServlet" method="post">
            <fieldset id="registrationFieldSet">
                <div>
                    Nome: <input id="className" class="registrationField" type="text" name="name">
                    <input type="button" id="registrationButton" value="Crea" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
