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
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationChildManager.js"></script>
        <title>Registration's Child Management</title>
    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initializeRegistrationFields();
            createTableRegistrationChild();
            $("#NewDataNascita").datepicker({dateFormat:'yy-mm-dd'});
            $("#EditDataNascita").datepicker({dateFormat:'yy-mm-dd'});
        });
    </script>
    
    <body>
        <%@include file="header.jsp" %>
        <div id="newRegistrationChildWindow" title="Inserisci Domanda di Iscrizione" style="display: inline">

            <form id="newRegistrationChildForm" name="newRegistrationChildForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p class="formp">
                            <label><h3>Cognome:</h3></label>
                            <input id="NewCognome" name="NewCognome" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Nome:</h3></label>
                            <input id="NewNome" name="NewNome" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Data di nascita:</h3></label>
                            <input id="NewDataNascita" name="NewDataNascita" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Comune di nascita:</h3></label>
                            <input id="NewComuneNascita" name="NewComuneNascita" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Codice fiscale:</h3></label>
                            <input id="NewCodiceFiscale" name="NewCodiceFiscale" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Cittadinanza:</h3></label>
                            <input id="NewCittadinanza" name="NewCittadinanza" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp"> 
                            <legend><h3>Fascia di utenza:</h3></legend>
                            <ul>
                                <input type="radio" id="NewFasciaUtenza" name="NewFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>" checked="checked">Full Time
                                <input type="radio" id="NewFasciaUtenza" name="NewFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>" >Part Time Mattutino
                                <input type="radio" id="NewFasciaUtenza" name="NewFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>">Part Time Pomeridiano
                            </ul>
                            <br><br>
                        </p>
                        <p>
                            <input type="submit" id="createNewDraftButton" value="Salva Bozza" >
                            <%--<input type="submit" id="submitNewDraftButton" value="Sottometti Domanda" >--%>
                            <input type="button" id="undoNewDraftButton" value="Annulla" >
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>

        <div id="editRegistrationChildWindow" title="Modifica Domanda di Iscrizione" style="display: inline">
            <form id="editRegistrationChildForm" name="newRegistrationChildForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p class="formp">
                            <label><h3>Cognome:</h3></label>
                            <input id="EditCognome" name="EditCognome" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Nome:</h3></label>
                            <input id="EditNome" name="EditNome" type="text" size="40%" style="margin-right: 2%">
                        </p>
                        <p class="formp">
                            <label><h3>Data di nascita:</h3></label>
                            <input id="EditDataNascita" name="EditDataNascita" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Comune di nascita:</h3></label>
                            <input id="EditComuneNascita" name="EditComuneNascita" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Codice fiscale:</h3></label>
                            <input id="EditCodiceFiscale" name="EditCodiceFiscale" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp">
                            <label><h3>Cittadinanza:</h3></label>
                            <input id="EditCittadinanza" name="EditCittadinanza" type="text" size="40%" style="margin-right: 2%"> 
                        </p>
                        <p class="formp"> 
                            <legend><h3>Fascia di utenza:</h3></legend>
                            <ul>
                                <input type="radio" id="EditFasciaUtenza" name="EditFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>" checked="checked">Full Time
                                <input type="radio" id="EditFasciaUtenza" name="EditFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>" >Part Time Mattutino
                                <input type="radio" id="EditFasciaUtenza" name="EditFasciaUtenza" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>">Part Time Pomeridiano
                            </ul>
                            <br><br>
                        </p>
                        <p>
                            <input type="hidden" id="EditIdDraft" />
                            <input type="submit" id="saveEditDraftButton" value="Salva Modifiche" />
                            <input type="submit" id="submitEditDraftButton" value="Sottometti Domanda" />
                            <input type="button" id="undoEditDraftButton" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        
        <div id="viewDetailsRegistrationChildWindow" title="Dettagli della Domanda di Iscrizione" style="display: inline">
            <form id="viewDetailsRegistrationChildForm" name="viewDetailsRegistrationChildForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p class="formp">
                            <label><h3>Cognome:</h3></label>
                            <span class="details" id="viewDetailsCognome" name="viewDetailsCognome"></span>
                        </p>
                        <p class="formp">
                            <label><h3>Nome:</h3></label>
                            <span class="details" id="viewDetailsNome" name="viewDetailsNome"></span>
                        </p>
                        <p class="formp">
                            <label><h3>Data di nascita:</h3></label>
                            <span class="details" id="viewDetailsDataNascita" name="viewDetailsDataNascita"></span>
                        </p>
                        <p class="formp">
                            <label><h3>Comune di nascita:</h3></label>
                            <span class="details" id="viewDetailsComuneNascita" name="viewDetailsComuneNascita"></span>
                        </p>
                        <p class="formp">
                            <label><h3>Codice fiscale:</h3></label>
                            <span class="details" id="viewDetailsCodiceFiscale" name="viewDetailsCodiceFiscale"></span>
                        </p>
                        <p class="formp">
                            <label><h3>Cittadinanza:</h3></label>
                            <span class="details" id="viewDetailsCittadinanza" name="viewDetailsCittadinanza"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Fascia di utenza:</h3></legend>
                            <span class="details" id="viewDetailsFasciaUtenza" name="viewDetailsFasciaUtenza"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Data di iscrizione:</h3></legend>
                            <span class="details" id="viewDetailsDataIscrizione" name="viewDetailsDataIscrizione"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Fase della domanda di iscrizione:</h3></legend>
                            <span class="details" id="viewDetailsFaseIscrizione" name="viewDetailsFaseIscrizione"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Malattie:</h3></legend>
                            <span class="details" id="viewDetailsMalattie" name="viewDetailsMalattie"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Vaccinazioni:</h3></legend>
                            <span class="details" id="viewDetailsVaccinazioni" name="viewDetailsVaccinazioni"></span>
                        </p>
                        <p class="formp"> 
                            <legend><h3>Dichiarazione della privacy:</h3></legend>
                            <span class="details" id="viewDetailsDichiarazionePrivacy" name="viewDetailsDichiarazionePrivacy"></span>
                        </p>
                        <p>
                            <br><br>
                            <input type="hidden" id="viewDetailsIdDraft" />
                            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                            <input type="submit" id="confirmViewDetailsDraftButton" value="Conferma Domanda" />
                            </c:if>
                            <input type="button" id="undoViewDetailsDraftButton" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>

        <div id="confirmOperationRCWindow" title="Richiesta di conferma" style="display: inline">
            <form id="confirmOperationRCForm" name="confirmOperationRCForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <h3 id="confirmOperationRCTitle" name="confirmOperationRCTitle"></h3>
                        <p>
                            <input type="hidden" id="confirmOperationRCIdDraft" />
                            <input type="submit" id="confirmOperationRCButton" value="Conferma" />
                            <input type="button" id="undoOperationRCButton" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
                 
        <div id="registrationChildContent">
            <h1 style="font-size: 35px;text-align: center;">Gestione Domande d'Iscrizione</h1>
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            <div>
                <input type="button" id="newRegistrationChildButton" value="Inserisci Domanda di Iscrizione"/>
            </div>
            </c:if>
            <table id="registrationChildTable">
                <thead>
                    <th>Cognome</th>
                    <th>Nome</th>
                    <th>Fase dell'iscrizione</th>
                    <th>Operazioni</th>
                </thead>
                <tbody>
                </tbody>
            </table>            
        </div>
    <%@include file="footer.jsp" %>
    </body>
</html>
