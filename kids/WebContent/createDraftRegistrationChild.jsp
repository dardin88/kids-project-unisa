<%-- 
    Document   : createDraftRegistrationChild
    Created on : 23-nov-2012, 22.09.10
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Rimuovere il tag commento per effettuare il controllo sull'accesso effettuato
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Parent'}">
        <c:redirect url="index.jsp" />
</c:if>
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationChildManager.js"></script>
        <title>Create a new draft registration form for a child</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Crea una nuova bozza di domanda di iscrizione per un bambino</h1>
        <form class="inputForm" method="post">
            <fieldset>
                <label for="surname">Cognome
                    <input id="surname" type="text" ></label>
                <label for="name">Nome
                    <input id="name" type="text" ></label>
                <label for="fiscalCode">Codice fiscale
                    <input id="fiscalCode" type="text" ></label>
                <label for="bornDate">Data di nascita
                    <input id="bornDate" type="datetime" ></label>
                <label for="birthPlace">Luogo di nascita
                    <input id="birthPlace" type="text" ></label>
                <label for="citizenship">Cittadinanza
                    <input id="citizenship" type="text" ></label>
                <input id="createDraft" type="submit" value="Crea bozza di domanda di iscrizione" >
            </fieldset>
            </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
