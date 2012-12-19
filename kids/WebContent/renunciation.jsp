<%-- 
    Document   : renunciation
    Created on : 09-dec-2012, 21.00.23
    Author     : Lauri Giuseppe Giovanni
--%>
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
        <script type="text/javascript" src="js/renunciationManager.js"></script>
        <script type="text/javascript" src="js/renunciationTables.js"></script>
        <script type="text/javascript" src="js/renunciationPrint.js"></script>

        <title>Kids</title>

    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initRenunciationPage();
            
            initRenunciationPrintComponent();
                    
        <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                createTablePossibleRenunciation();
        </c:if>
                createTableSubmittedRenunciation();
            });
        
    </script>

    <body>
        <%@include file="header.jsp" %>

        <%-- CONTENUTO PRINCIPALE DELLA PAGINA --%>
        <div id="renunciationContentPage">

            <%-- INFORMAZIONI GENERALI DELLA PAGINA UTILIZZATE NEI FILE JS --%>
            <div id="renunciationGeneralInfo" style="display: none;">
                <input type="hidden" id="user" value="${sessionScope.user.getAccountType()}" />
            </div>
            <%-- INFORMAZIONI GENERALI DELLA PAGINA UTILIZZATE NEI FILE JS --%>

            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                <%-- VISUALE DELLE POSSIBILI ISCRIZIONI PER CUI E' POSSIBILE PRESENTARE UNA RINUNCIA --%>
                <div id="renunciationPossibleDisplay" style="display: block;margin-bottom:30px;">
                    <h2 id="renunciationPossibleDisplayTitle">Iscrizioni per le quali è possibile presentare domanda di rinuncia</h2>
                    <div id="renunciationPossibleResultTable">
                        <table id="renunciationPossibleTable">
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
                <%-- FINE VISUALE DELLE POSSIBILI ISCRIZIONI PER CUI E' POSSIBILE PRESENTARE UNA RINUNCIA --%>
            </c:if>

            <%-- VISUALE DELLE DOMANDE DI RINUNCIA PRESENTATE --%>
            <div id="renunciationSubmittedDisplay" style="display: block;">
                <h2 id="renunciationSubmittedDisplayTitle">Ricorsi presentati:</h2>
                <div id="renunciationSubmittedResultTable">
                    <table id="renunciationSubmittedTable">
                        <thead>
                        <th>Data inserimento</th>
                        <th>Codice Fiscale</th>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Operazioni</th>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- FINE VISUALE DELLE DOMANDE DI RINUNCIA PRESENTATE --%>

        </div>
        <%-- FINE CONTENUTO PRINCIPALE DELLA PAGINA --%>

        <%-- FINESTRA DI INSERIMENTO DI UNA NUOVA DOMANDA DI RINUNCIA --%>
        <div id="renunciationAddWindow" name="renunciationAddWindow" title="Compila una nuova domanda di rinuncia" style="display: inline">
            <form id="renunciationAddWindowForm" name="renunciationAddWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p>
                            <label>Motivo</label>
                            <input type="text" id="renunciationAddWindowMotivo" name="renunciationAddWindowMotivo"  maxlength="50"/>
                            <label class="error" style="display:inline;" id="renunciationAddWindowMotivoError" name="renunciationAddWindowMotivoError"></label>
                        </p>
                        <p>
                            <input type="hidden" id="renunciationAddWindowId" name="renunciationAddWindowId" />
                            <input type="button" id="renunciationAddWindowMotivoSave" onClick="saveNewRenunciation();" value="Sottometti" />
                            <input type="button" id="renunciationAddWindowMotivoUndo" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        <%-- FINE FINESTRA DI INSERIMENTO DI UNA NUOVA DOMANDA DI RINUNCIA --%>

        <%-- FINESTRA DI VISUALE DEI DETTAGLIO DI UNA DOMANDA DI RINUNCIA --%>
        <div id="renunciationViewDetailsWindow" name="renunciationViewDetailsWindow" title="Visualizza domanda di rinuncia" style="display: inline">
            <h3>Dettagli della domanda di rinuncia sottomessa</h3>
            <p id="renunciationViewDetailsText"></p>
            <p>
                <input type="hidden" id="renunciationViewDetailsWindowId" name="renunciationViewDetailsWindowId" />
                <input type="button" id="renunciationViewDetailsWindowPrint" onClick="printRenunciation();" value="Stampa Domanda" />
                <input type="button" id="renunciationViewDetailsWindowUndo" value="Chiudi" />
            </p>
        </div>
        <%-- FINE FINESTRA VISUALE DEI DETTAGLI DI UNA DOMANDA DI RINUNCIA --%>

        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>
        <div id="renunciationConfirmWindow" name="renunciationConfirmWindow" title="Conferma operazione" style="display: inline">
            <div>
                <h3 id="renunciationConfirmWindowText" name="renunciationConfirmWindowText"></h3>
                <p>
                    <input type="button" id="renunciationConfirmWindowConfirmButton" value="Conferma" />
                    <input type="button" id="renunciationConfirmWindowUndoButton" value="Annulla" />
                </p>
            </div>
        </div>
        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>

        <%-- FINESTRA DI AVVISO (ALERT) --%>
        <div id="renunciationAlertWindow" name="renunciationAlertWindow" title="" style="display: inline">
            <div>
                <h3 id="renunciationAlertWindowText" name="renunciationAlertWindowText"></h3>
                <p>
                    <input type="button" id="renunciationAlertWindowOkButton" value="OK" />
                </p>
            </div>
        </div>
        <%-- FINE FINESTRA DI AVVISO (ALERT) --%>

        <%@include file="footer.jsp" %>
    </body>
</html>