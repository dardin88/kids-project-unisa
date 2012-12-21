<%-- 
    Document   : classification
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
        <script type="text/javascript" src="js/classificationManager.js"></script>
        <script type="text/javascript" src="js/classificationTables.js"></script>
        <script type="text/javascript" src="js/classificationCriteria.js"></script>

        <title>Kids</title>

    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initClassificationPage();
            initCriteriaWindow();
            createTableClassification();
        });
    </script>

    <body>
        <%@include file="header.jsp" %>

        <%-- CORPO PRINCIPALE DELLA PAGINA: PULSANTI E TABELLA CON ELENCO DELLE GRADUATORIE --%>
        <div id="classificationContentPage">
            <div id="classificationSubmit" style="margin-bottom: 30px;">
                <input type="hidden" id="classificationSelectedId" />
                <input type="hidden" id="user" value="${sessionScope.user.getAccountType()}"/>
                <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                    <input type="button" id="classificationButtonOpenWindowCreateNew" onClick="openAddClassificationWindow();" value="Crea una nuova graduatoria" />
                    <input type="button" id="classificationOpenCriteriaWindow" onClick="openCriteriaWindow();" value="Gestisci criteri" />
                </c:if>
            </div>
            <div id="classificationDisplayTable">
                <table id="classificationTable">
                    <thead>
                    <th>Data</th>
                    <th>Nome</th>
                    <th>Stato</th>
                    <th>Operazioni</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- FINE CORPO PRINCIPALE DELLA PAGINA: PULSANTI E TABELLA CON ELENCO DELLE GRADUATORIE --%>

        <%-- FINESTRA DI INSERIMENTO DI UNA NUOVA GRADUATORIA --%>
        <div id="classificationAddWindow" name="classificationAddWindow" title="Crea una nuova graduatoria" style="display: inline">
            <form id="classificationAddWindowForm" name="classificationAddWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div><br>
                        <p>
                            <label>Nome</label>
                            <input type="text" id="classificationAddWindowNome" name="classificationAddWindowNome"  maxlength="30"/>
                            <label class="error" style="display:inline;" id="classificationAddWindowNomeError" name="classificationAddWindowNomeError"></label>
                        </p><br>
                        <p>
                            <input style="width: 200px" type="button" id="classificationAddWindowSave" onClick="saveNewGraduatoria();" value="Crea" />
                            <input style="width: 200px" type="button" id="classificationAddWindowUndo" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        <%-- FINE FINESTRA DI INSERIMENTO DI UNA NUOVA GRADUATORIA --%>

        <%-- FINESTRA DI MODIFICA DI UNA GRADUATORIA --%>
        <div id="classificationModifyWindow" name="classificationModifyWindow" title="Modifica Graduatoria" style="display: inline">
            <form id="classificationModifyForm" name="classificationModifyForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p>
                            <input type="hidden" id="classificationModifyWindowId" name="classificationModifyWindowId" />
                        </p>
                        <p>
                            <label>Data:</label>
                            <input type="text" readonly="readonly" id="classificationModifyWindowData" disabled="disabled" name="classificationModifyWindowData" />
                        </p>
                        <p>
                            <label>Status:</label>
                            <input type="text" readonly="readonly" id="classificationModifyWindowStatus" disabled="disabled" name="classificationModifyWindowStatus" />
                        </p>
                        <p>
                            <label>Nome</label>
                            <input type="text" id="classificationModifyWindowNome" name="classificationModifyWindowNome" maxlength="30"/>
                            <label class="error" style="display:inline;" id="classificationModifyWindowNomeError"></label>
                        </p>
                        <p>
                            <input type="button" id="classificationModifyWindowSave" onClick="saveModifyGraduatoria();" value="Salva Moifiche" />
                            <input type="button" id="classificationModifyWindowUndo" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        <%-- FINE FINESTRA DI MODIFICA DI UNA GRADUATORIA --%>

        <%-- FINESTRA DI VISUALE IN DETTAGLIO DELLA GRADUATORIA E DEI SUOI RISULTATI --%>
        <div id="classificationDisplay" style="display: none;">
            <div id="classificationDisplayInfo" name="classificationFormWindowInfo">
                <p class="formp"> 
                <legend><h3 style="float: left; margin-right: 2%">Data Creazione:</h3></legend>
                <input id="classificationDisplayData" name="classificationDisplayData" disabled="disabled" type="text">
                </p>
                <p class="formp"> 
                <legend><h3 style="float: left; margin-right: 2%">Nome:</h3></legend>
                <input id="classificationDisplayNome" name="classificationDisplayNome" disabled="disabled" type="text">
                </p>
                <p class="formp"> 
                <legend><h3 style="float: left; margin-right: 2%">Status:</h3></legend>
                <input id="classificationDisplayStatus" name="classificationDisplayStatus" disabled="disabled" type="text">
                </p>
            </div><br>
            <div id="classificationDisplaySubmit" style="margin-bottom: 10px;">
                <input id="classificationDisplayId" name="classificationDisplayId" type="hidden">
                <input type="button" id="classificationCloseDetailsButton" name="classificationCloseDetailsButton" onClick="closeDetailsClassification();" value="Torna all'elenco delle grauatorie" />
                <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                    <input type="button" id="classificationAggiornaResultsButton" name="classificationAggiornaResultsButton" onClick="updateResultClassification();" value="Ricalcola esiti" />
                    <input type="button" id="classificationRendiProvvisoriaButton" name="classificationRendiProvvisoriaButton" onClick="openWindowToProvvisoriaFromDetails();" value="Rendi provvisoria" />
                    <input type="button" id="classificationRendiDefinitivaButton" name="classificationRendiDefinitivaButton" onClick="openWindowToDefinitivaFromDetails();" value="Rendi definitiva" />
                </c:if>
            </div><br>
            <div id="classificationDisplayResultTable">
                <table id="classificationResultTable">
                    <thead>
                    <th>Posizione</th>
                    <th>Codice Fiscale</th>
                    <th>Cognome</th>
                    <th>Nome</th>
                    <th>Punteggio</th>
                    <th>Esito</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- FINE FINESTRA DI VISUALE IN DETTAGLIO DELLA GRADUATORIA E DEI SUOI RISULTATI --%>

        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>
        <div id="classificationConfirmWindow" name="classificationConfirmWindow" title="Conferma operazione" style="display: inline">
            <div>
                <h3 id="classificationConfirmWindowText" name="classificationConfirmWindowText"></h3>
                <p>
                    <input type="button" id="classificationConfirmWindowConfirmButton" value="Conferma" />
                    <input type="button" id="classificationConfirmWindowUndoButton" value="Annulla" />
                </p>
            </div>
        </div>
        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>

        <%-- FINESTRA DI AVVISO (ALERT) --%>
        <div id="classificationAlertWindow" name="classificationAlertWindow" title="" style="display: inline">
            <div>
                <h3 id="classificationAlertWindowText" name="classificationAlertWindowText"></h3>
                <p>
                    <input type="button" id="classificationAlertWindowOkButton" value="OK" />
                </p>
            </div>
        </div>
        <%-- FINE FINESTRA DI AVVISO (ALERT) --%>


        <%-- GESTIONE DEI CRITERI DI VALUTAZIONE DELLA GRADUATORIA --%>
        <div id="classificationCriteriaWindow" name="classificationCriteriaWindow" title="Criteri di valutazione della graduatoria" style="display: none">
            <div style="margin-bottom: 30px;">
                <input type="button" id="classificationCloseCriteriaButton" name="classificationCloseCriteriaButton" onClick="closeCriteriaWindow();" value="Torna all'elenco delle grauatorie" />
                <input type="button" id="classificationAddCriterion" onClick="openAddCriteriaWindow();" value="Aggiungi Nuovo Criterio" />
            </div>
            <div>
                <table id="classificationCriteriaTable">
                    <thead>
                    <th>Descrizione</th>
                    <th>Campo</th>
                    <th>Operando</th>
                    <th>Condizione</th>
                    <th>Peso</th>
                    <th>Attivo</th>
                    <th>Operazioni</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>

        <div id="classificationAddCriterionWindow" name="classificationAddCriterionWindow" title="Visuale di inserimento di un nuovo criterio" style="display: inline">
            <form id="classificationAddCriterionForm" name="classificationAddCriterionForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <h3>Descrizione:</h3>
                        <p>
                            <input type="text" id="classificationNewCriterionDescrizione" name="classificationNewCriterionDescrizione" maxlength="40"/>
                            <label class="error" style="display:inline;" id="classificationNewCriterionDescrizioneError"></label>
                        </p>
                        <h3>Campo:</h3>
                        <p>
                            <select id="classificationNewCriterionCampo" name="classificationNewCriterionCampo">
                                <option id="selectCampoEmpty" value="">--Seleziona campo del db da valutare--</option>
                                <option value="">--Non ci sono scelte possibili--</option>
                            </select>
                            <label class="error" style="display:inline;" id="classificationNewCriterionCampoError"></label>
                        </p>
                        <h3>Operando:</h3>
                        <p>
                            <select id="classificationNewCriterionOperando" name="classificationNewCriterionOperando">
                                <option id="selectOperandoEmpty" value="">--Seleziona operando--</option>
                                <option id="selectOperando\<" value="<">minore (<)</option>
                                <option id="selectOperando\<\=" value="<=">non maggiore (<=)</option>
                                <option id="selectOperando\=" value="==">uguale (=)</option>
                                <option id="selectOperando\>\=" value=">=">non minore (>=)</option>
                                <option id="selectOperando\>" value=">">maggiore (>)</option>
                                <option id="selectOperando\!\=" value="!=">diverso (!=)</option>
                            </select>
                            <label class="error" style="display:inline;" id="classificationNewCriterionOperandoError"></label>
                        </p>
                        <h3>Condizione:</h3>
                        <p>
                            <input type="text" id="classificationNewCriterionCondizione" name="classificationNewCriterionCondizione" maxlength="20" />
                            <label class="error" style="display:inline;" id="classificationNewCriterionCondizioneError"></label>
                        </p>
                        <h3>Peso</h3>
                        <p>
                            <input type="text" id="classificationNewCriterionPeso" name="classificationNewCriterionPeso" />
                            <label class="error" style="display:inline;" id="classificationNewCriterionPesoError"></label>
                        </p>
                        <p>
                            <input type="button" id="classificationAddCriterionSubmit" onClick="addCriterion();" value="Inserisci" />
                            <input type="button" id="classificationAddCriterionUndoSubmit" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        <%-- FINE GESTIONE DEI CRITERI DI VALUTAZIONE DELLA GRADUATORIA --%>

        <%@include file="footer.jsp" %>
    </body>
</html>