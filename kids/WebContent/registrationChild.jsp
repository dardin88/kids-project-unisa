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
<c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            $("#registrationChildOpenNewDraftWindowButton").button();
            $("#registrationChildOpenNewDraftWindowButton").click(function() {
                openInsertNewRegistrationChildWindow();
            });
            $("#createNewDraftRegistrationChildButton").button();
            $("#createNewDraftRegistrationChildButton").click(function() {
                createNewDraftRegistrationChildAction();
            });
            $("#saveEditDraftRegistrationChildButton").button();
            $("#saveEditDraftRegistrationChildButton").click(function() {
                saveEditDraftRegistrationChildAction();
            });
            $("#submitDraftRegistrationChildButton").button();
            $("#submitDraftRegistrationChildButton").click(function() {
                submitDraftRegistrationChildAction();
            });
            $("#completeDraftRegistrationChildButton").button();
            $("#completeDraftRegistrationChildButton").click(function() {
                completeDraftRegistrationChildAction();
            });
</c:if>
<c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
            $("#confirmCompletedViewDetailsDraftButton").button();
            $("#confirmCompletedViewDetailsDraftButton").click(function() {
                confirmCompletingDraftRegistrationChildAction();
            });
</c:if>
        });
        
    </script>
    
    <body>
        <%@include file="header.jsp" %>
        
        <div id="registrationChildFormWindow" title="" style="display: inline">
            <form id="registrationChildFormWindowForm" name="registrationChildFormWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <div id="registrationChildRegistrationInfo" name="registrationChildRegistrationInfo" style="display: inline">
                            <p class="formp"> 
                                <legend><h3>Data di iscrizione:</h3></legend>
                                <input class="details" id="registrationChildFormWindowDataIscrizione" name="registrationChildFormWindowDataIscrizione" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Fase della domanda di iscrizione:</h3></legend>
                                <input class="details" id="registrationChildFormWindowFaseIscrizione" name="registrationChildFormWindowFaseIscrizione" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                        </div>
                        <div id="registrationChildStandardField" name="registrationChildStandardField">
                            <p class="formp">
                                <label><h3>Cognome:</h3></label>
                                <input class="details" id="registrationChildFormWindowCognome" name="registrationChildFormWindowCognome" type="text" maxlength="25" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp">
                                <label><h3>Nome:</h3></label>
                                <input class="details" id="registrationChildFormWindowNome" name="registrationChildFormWindowNome" type="text" maxlength="25" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp">
                                <label><h3>Data di nascita:</h3></label>
                                <input class="details" id="registrationChildFormWindowDataNascita" name="registrationChildFormWindowDataNascita" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp">
                                <label><h3>Comune di nascita:</h3></label>
                                <input class="details" id="registrationChildFormWindowComuneNascita" name="registrationChildFormWindowComuneNascita" maxlength="20" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp">
                                <label><h3>Codice fiscale:</h3></label>
                                <input class="details" id="registrationChildFormWindowCodiceFiscale" name="registrationChildFormWindowCodiceFiscale" maxlength="16" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp">
                                <label><h3>Cittadinanza:</h3></label>
                                <input class="details" id="registrationChildFormWindowCittadinanza" name="registrationChildFormWindowCittadinanza" type="text" maxlength="20" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Fascia di utenza:</h3></legend>
                                <select id="registrationChildFormWindowFasciaUtenza" name="registrationChildFormWindowFasciaUtenza" class="details">
                                    <option id="registrationChildSelectUserRangeEmpty" value="">--Selezionare la fascia di utenza--</option>
                                    <option id="registrationChildSelectUserRangeFullTime" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_FULLTIME %>">Servizio a tempo pieno</option>
                                    <option id="registrationChildSelectUserRangePartTimeAM" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEAM %>">Servizio a tempo ridotto (solo Mattina)</option>
                                    <option id="registrationChildSelectUserRangePartTimePM" value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_USERRANGE_PARTTIMEPM %>">Servizio a tempo ridotto (solo Pomeriggio)</option>
                                </select>
                            </p>
                        </div>
                        <div id="registrationChildAdvancedField" name="registrationChildAdvancedField" style="display: inline">
                            <p class="formp"> 
                                <legend><h3>Malattie:</h3></legend>
                                <input class="details" id="registrationChildFormWindowMalattie" name="registrationChildFormWindowMalattie" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Vaccinazioni:</h3></legend>
                                <input class="details" id="registrationChildFormWindowVaccinazioni" name="registrationChildFormWindowVaccinazioni" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Autorizza il trattamento dei dati personali contenuti nel mio curriculum vitae in base art. 13 del D. Lgs. 196/2003?</h3></legend>
                                <select id="registrationChildFormWindowDichiarazionePrivacy" name="registrationChildFormWindowDichiarazionePrivacy" class="details">
                                    <option id="registrationChildSelectPrivacyEmpty" value="">--Selezionare la risposta--</option>
                                    <option id="registrationChildSelectPrivacySi" value="si">Si</option>
                                    <option id="registrationChildselectPrivacyNo" value="no">No</option>
                                    <option id="registrationChildselectPrivacyForse" value="in_parte">In parte</option>
                                </select>
                            </p>
                        </div>
                        <div id="registrationChildSegretaryField" name="registrationChildSegretaryField" style="display: inline">
                            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                            <p class="formp"> 
                                <legend><h3>Sono stati fornite le malattie?</h3></legend>
                                <select id="registrationChildFormWindowIsSetMalattie" name="registrationChildFormWindowIsSetMalattie" class="details">
                                    <option value="">--Selezionare la risposta--</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_YES %>">Si</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_NO %>" selected="selected">No</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_MAYBE %>">In parte</option>
                                </select>
                            </p>
                            <p class="formp"> 
                                <legend><h3>Sono state fornite le vaccinazioni?</h3></legend>
                                <select id="registrationChildFormWindowIsSetVaccinazioni" name="registrationChildFormWindowIsSetVaccinazioni" class="details">
                                    <option value="">--Selezionare la risposta--</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_YES %>">Si</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_NO %>" selected="selected">No</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_MAYBE %>">In parte</option>
                                </select>
                            </p>
                            <p class="formp"> 
                                <legend><h3>Sono stati accettati i diritti sulla privacy?</h3></legend>
                                <select id="registrationChildFormWindowIsSetDichiarazionePrivacy" name="registrationChildFormWindowIsSetDichiarazionePrivacy" class="details">
                                    <option value="">--Selezionare la risposta--</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_YES %>">Si</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_NO %>" selected="selected">No</option>
                                    <option value="<%= DBNames.ATT_REGISTRATIONCHILD_ENUM_ISSET_MAYBE %>">In parte</option>
                                </select>
                            </p>
                            </c:if>
                        </div>
                        <div id="registrationChildSubmitField">
                            <p>
                                <br><br>
                                <input type="hidden" id="registrationChildFormWindowId" />
                                <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                                <input type="submit" id="createNewDraftRegistrationChildButton" value="Salva Bozza" />
                                <input type="submit" id="saveEditDraftRegistrationChildButton" value="Salva Modifiche" />
                                <input type="submit" id="submitDraftRegistrationChildButton" value="Sottometti Domanda" />
                                <input type="submit" id="completeDraftRegistrationChildButton" value="Completa Domanda" />
                                </c:if>
                                <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                                <input type="submit" id="confirmCompletedViewDetailsDraftButton" value="Conferma completamento Domanda" />
                                </c:if>
                                <input type="button" id="registrationChildFormWindowUndoButton" value="Annulla" />
                            </p>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <div id="registrationChildConfirmWindow" name="registrationChildConfirmWindow" title="Conferma operazione" style="display: inline">
            <form id="registrationChildConfirmWindowForm" name="registrationChildConfirmWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <h3 id="registrationChildConfirmWindowTitle" name="registrationChildConfirmWindowTitle"></h3>
                        <p>
                            <input type="submit" id="registrationChildConfirmWindowConfirmButton" value="Conferma" />
                            <input type="button" id="registrationChildConfirmWindowUndoButton" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>


        <div id="registrationChildAlertWindow" name="registrationChildAlertWindow" title="" style="display: inline">
            <form id="registrationChildAlertWindowForm" name="registrationChildAlertWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <h3 id="registrationChildAlertWindowTitle" name="registrationChildAlertWindowTitle"></h3>
                        <p>
                            <input type="submit" id="registrationChildAlertWindowOkButton" value="OK" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>

        <div id="registrationChildContent">
            <input type="hidden" id="user" name="user" value="${sessionScope.user.getAccountType()}" style="display:none" />
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            <div>
                <input type="button" id="registrationChildOpenNewDraftWindowButton" value="Inserisci Domanda di Iscrizione"/>
            </div>
            </c:if>
            <div>
                <table id="registrationChildTable">
                    <thead>
                        <th>Codice Fiscale</th>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Fase dell'iscrizione</th>
                        <th>Operazioni</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    <%@include file="footer.jsp" %>
    </body>
</html>