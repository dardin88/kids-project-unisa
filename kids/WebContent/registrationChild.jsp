<%-- 
    Document   : registrationChild
    Created on : 2-dic-2012, 16.41.53
    Author     : Lauri Giuseppe Giovanni
--%>

<%@page import="it.unisa.kids.common.DBNames"%>
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
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTales.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationChildManager.js"></script>
        <title>Registration's Child Management</title>
    </head>
    <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeRegistrationFields();
                createTableRegistrationChild();
                $("#DataNascita").datepicker({dateFormat:'yy-mm-dd'});
            });
        </script>
    <body>
        <%@include file="header.jsp" %>
        <div id="newRegistrationChildWindow" title="Inserisci Domanda di Iscrizione" style="display: inline">

            <form id="newRegistrationChildForm" name="newRegistrationChildForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div id="artefactsManagement">
                        <p class="formp">
                            <label><h3>Cognome:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_SURNAME %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_SURNAME %>" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Nome:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_NAME %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_NAME %>" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Data di nascita:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE %>" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Luogo di nascita:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE %>" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Codice fiscale:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_FISCALCODE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_FISCALCODE %>" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Cittadinanza:</h3></label>
                            <input id="<%= DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP %>" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp"> 
                            <legend><h3>Fascia di utenza:</h3></legend>
                            <ul>
                                <input type="radio" id="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>" checked="checked">Full Time
                                <input type="radio" id="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>" >Part Time Mattutino
                                <input type="radio" id="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" name="<%= DBNames.ATT_REGISTRATIONCHILD_USERRANGE %>" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>">Part Time Pomeridiano
                            </ul>
                            <br><br>
                        </p>
                        <p>
                            <input type="submit" id="createNewDraftButton" value="Salva Bozza" >
                            <input type="submit" id="submitNewDraftButton" value="Sottometti Domanda" >
                            <input type="button" id="notSubmitDraftButton" value="Annulla" >
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
                               
        <div>
            <h1 style="font-size: 35px;text-align: center;">Gestione Domande d'Iscrizione</h1>
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            <div>
                <input type="button" id="newRegistrationChildButton" style="position: absolute; left: 15%" value="Inserisci Domanda di Iscrizione"/>
            </div>
            </c:if>
            <table id="registrationChildTable">
                <thead>
                    <th>Cognome:</th>
                    <th>Nome:</th>
                    <th>Codice fiscale</th>
                    <th>Fase dell'iscrizione</th>
                    <th>Operazioni</th>
                </thead>
                <tbody>
                </tbody>
            </table>            
        </div>
    </body>
</html>
