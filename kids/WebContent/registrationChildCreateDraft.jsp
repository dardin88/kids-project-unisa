<%-- 
    Document   : createDraftRegistrationChild
    Created on : 23-nov-2012, 22.09.10
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page import="it.unisa.kids.common.DBNames"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Genitore'}">
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
        <script type="text/javascript" src="js/registrationChildManager.js"></script>
        <title>Create a new draft registration form for a child</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">
        <h1 id="titleReg" align="center">Crea una nuova bozza di domanda di iscrizione per un bambino</h1>
        <form  id="registrationForm" class="cmxform" method="post" action="ServletCreateDraftRegistrationChild">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <p>
                        <label>Cognome:</label>
                        <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_SURNAME %>" type="text" >
                    </p>
                    <p>
                        Nome: <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_NAME %>" type="text" >
                    </p>
                    <p>
                        Data di nascita: <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE %>" type="datetime" >
                    </p>
                    <p>
                        Luogo di nascita: <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE %>" type="text" >
                    </p>
                    <p>
                        Codice fiscale: <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_FISCALCODE %>" type="text" >
                    </p>
                    <p>
                        Cittadinanza: <input class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP %>" type="text" >
                    </p>
                    <p>
                        Fascia di utenza:
                        <select class="registrationField" id="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>">
                            <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>" selected="selected">Full Time</option>
                            <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>">Part Time Mattutino</option>
                            <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>">Part Time Pomeridiano</option>
                        </select>
                    </p>
                    <input id="registrationButton" id="createDraft" type="submit" value="Crea bozza di domanda di iscrizione" >
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
