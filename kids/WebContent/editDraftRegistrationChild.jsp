<%-- 
    Document   : editDraftRegistrationChild
    Created on : 02-dic-2012, 08.00.03
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page import="org.apache.taglibs.standard.tag.common.core.RedirectSupport"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild"%>
<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager"%>
<%@page import="it.unisa.kids.common.DBNames"%>
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
<%!
    RegistrationChild rc = new RegistrationChild();
%>
<%
    if(request.getParameter("RegistrationChildId") != null) {
        JDBCRegistrationChildManager registrationChildManager = JDBCRegistrationChildManager.getInstance();
        rc.setId(Integer.parseInt(request.getParameter("RegistrationChildId")));
        rc = registrationChildManager.search(rc).get(0);
        session.setAttribute("registrationchildtoedit", rc);
    } else {
        // Questo è il caso in cui qualcuno è acceduto a questa pagina senza esser passato per la visualizza
        response.sendRedirect("viewRegistrationChild.jsp");
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
        <title>Edit draft registration form for a child</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Modifica la bozza di domanda di iscrizione per un bambino</h1>
        <form class="inputForm" method="post" action="ServletEditDraftRegistrationChild">
            <fieldset>
                Cognome: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_SURNAME %>" type="text" value="<%= rc.getSurname() %>"><br>
                Nome: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_NAME %>" type="text" value="<%= rc.getName() %>"><br>
                Data di nascita: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE %>" type="datetime" value="<%= rc.getBirthDate() %>"><br>
                Luogo di nascita: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE %>" type="text" value="<%= rc.getBirthPlace() %>"><br>
                Codice fiscale: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_FISCALCODE %>" type="text" value="<%= rc.getFiscalCode() %>"><br>
                Cittadinanza: <input id="<%= DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP %>" type="text" value="<%= rc.getCitizenship() %>"><br>
                Fascia di utenza:
                <select id="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>">
                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>" 
<%
    if(rc.getUserRange() == null || rc.getUserRange().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME)) {
        %>
        selected="selected"
<%
    }
%>
                        >Full Time</option>
                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>" 
<%
    if(rc.getUserRange() != null && rc.getUserRange().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM)) {
        %>
        selected="selected"
<%
    }
%>
                        >Part Time Mattutino</option>
                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>" 
<%
    if(rc.getUserRange() != null && rc.getUserRange().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM)) {
        %>
        selected="selected"
<%
    }
%>
                        >Part Time Pomeridiano</option>
                </select>
                <input id="editDraft" type="submit" value="Salva Modifiche" >
            </fieldset>
            </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
