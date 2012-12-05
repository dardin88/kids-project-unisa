<%-- 
    Document   : registrationChild
    Created on : 2-dic-2012, 16.41.53
    Author     : Lauri Giuseppe Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Genitore'} && ${sessionScope.user.getAccountType()!='Segreteria'}">
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
        
        <title>Registration's Child Management</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="registrationChildIndex">
            <h1>Gestione Domande d'Iscrizione</h1>
            <ul>
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                <li><a href="registrationChildCreateDraft.jsp">Crea una nuova bozza</a></li>
            </c:if>
                <li><a href="registrationChildViewAll.jsp">Visualizza le domande di iscrizione</a></li>
            </ul>
        </div>
    </body>
</html>
