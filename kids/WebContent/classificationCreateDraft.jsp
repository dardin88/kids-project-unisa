<%-- 
    Document   : createDraftRegistrationChild
    Created on : 02-dic-2012, 22.09.10
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page import="it.unisa.kids.common.DBNames"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Segreteria'}">
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
        <title>Create a new classification's draft</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <body id="bodyRegistration">
    <%@include file="header.jsp" %>
        <h1 id="titleReg" align="center">Crea una nuova bozza di graduatoria</h1>
        <form  id="registrationForm" class="cmxform" method="post" action="ServletCreateDraftClassification">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <p>
                        Nome:
                        <input class="registrationField" id="<%= DBNames.ATT_CLASSIFICATION_NAME %>" type="text" >
                    </p>
                    <table>
                        <thead>
                        <th>Cognome:</th>
                        <th>Nome:</th>
                        <th>Codice Fiscale:</th>
                        <th>Risultato:</th>
                        </thead>
                    </table>
                    <input id="registrationButton" id="createDraft" type="submit" value="Crea bozza" >
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
