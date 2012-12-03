<%-- 
    Document   : createDraftRegistrationChild
    Created on : 23-nov-2012, 22.09.10
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page import="it.unisa.kids.accessManagement.accountManagement.JDBCAccountManager"%>
<%@page import="it.unisa.kids.accessManagement.accountManagement.Account"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager"%>
<%@page import="it.unisa.kids.common.DBNames"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Rimuovere il tag commento per effettuare il controllo sull'accesso effettuato
<c:if test="${sessionScope.user.getAccountType()!='Parent'} || ${sessionScope.user.getAccountType()!='Segretaria'}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Parent'} || ${sessionScope.user.getAccountType()!='Segretaria'}">
        <c:redirect url="index.jsp" />
</c:if>
--%>
<%!
    Account user;
    RegistrationChild child = new RegistrationChild();
%>
<%
    user = (Account) session.getAttribute("user");
    if(request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID) != null) {
        JDBCRegistrationChildManager registrationChildManager = JDBCRegistrationChildManager.getInstance();
        child.setId(Integer.parseInt(request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID)));
        child = registrationChildManager.search(child).get(0);
        session.setAttribute("registrationchildtoedit", child);
    } else {
        // Questo è il caso in cui qualcuno è acceduto a questa pagina senza esser passato per la visualizza
        response.sendRedirect("viewRegistrationChild.jsp");
    }
        
%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationChildManager.js"></script>
        <title>View list of registration form for a child</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Dettagli della domanda di iscrizione</h1>
        <p>
            Cognome: <%= child.getSurname() %><br>
            Nome: <%= child.getName() %><br>
            Data di nascita: <%= child.getBirthDate() %><br>
            Comune di nascita: <%= child.getBirthPlace() %><br>
            Codice fiscale: <%= child.getFiscalCode() %><br>
            Cittadinanza: <%= child.getCitizenship() %><br>
            Fascia d'utenza: <%= child.getUserRange() %><br>
        </p>
        <p>
            Data di registrazione: <%= child.getRegistrationDate() %>
            Fase dell'iscrizione: <%= child.getRegistrationPhase() %>
        </p>
            <%
                if(user.getAccountType().equals("Segreteria")) {
                    Account genitore = new Account();
                    genitore.setId(child.getParentId());
                    genitore = JDBCAccountManager.getInstance().search(genitore).get(0);
            %>
        <p>
            Cognome genitore: <%= genitore.getSurnameUser() %>
            Nome genitore: <%= genitore.getNameUser() %>
            Codice fiscale genitore: <%= genitore.getTaxCode() %> %>
        </p>
        <p>
            <input type="submit" onclick=""
        </p>
            <%
                }
            %>
        <%@include file="footer.jsp" %>
    </body>
</html>
