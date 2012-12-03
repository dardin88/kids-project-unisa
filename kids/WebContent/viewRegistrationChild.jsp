<%-- 
    Document   : createDraftRegistrationChild
    Created on : 23-nov-2012, 22.09.10
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page import="it.unisa.kids.accessManagement.accountManagement.Account"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager"%>
<%@page import="it.unisa.kids.common.DBNames"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Rimuovere il tag commento per effettuare il controllo sull'accesso effettuato
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Parent'} || ${sessionScope.user.getAccountType()!='Segretaria'}">
        <c:redirect url="index.jsp" />
</c:if>
--%>
<%!
    List<RegistrationChild> rcList;
%>
<%
    Account user = (Account) session.getAttribute("user");
    JDBCRegistrationChildManager registrationChildManager = JDBCRegistrationChildManager.getInstance();
    RegistrationChild child = new RegistrationChild();
    if(user.getAccountType().equals("Parent")) {
        // Imposto i parametri di ricerca
        child.setParentId(user.getId());
        rcList = registrationChildManager.search(child);
    }
    if(user.getAccountType().equals("Segreteria")) {
        // Imposto i parametri di ricerca
        child.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED);
        rcList = registrationChildManager.search(child);
    }
%>
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
        <title>View list of registration form for a child</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Crea una nuova bozza di domanda di iscrizione per un bambino</h1>
        <form class="inputForm" method="post" action="ServletCreateDraftRegistrationChild">
            <fieldset>
                <table>
                </table>
                <input id="createDraft" type="submit" value="Crea bozza di domanda di iscrizione" >
            </fieldset>
            </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
