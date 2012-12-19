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
        <script type="text/javascript" src="js/recourseManager.js"></script>
        <script type="text/javascript" src="js/recourseTables.js"></script>
        
        <title>Kids</title>
    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initRecoursePage();
            
<c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            createTablePossibleRecourse();
</c:if>
            createTableSubmittedRecourse();
        });
        
    </script>
    
    <body>
        <%@include file="header.jsp" %>
        
        <%-- CONTENUTO PRINCIPALE DELLA PAGINA --%>
        <div id="recourseContentPage">
            
            <%-- INFORMAZIONI GENERALI DELLA PAGINA UTILIZZATE NEI FILE JS --%>
            <div id="recourseGeneralInfo" style="display: none;">
                <input type="hidden" id="user" value="${sessionScope.user.getAccountType()}" />
            </div>
            <%-- INFORMAZIONI GENERALI DELLA PAGINA UTILIZZATE NEI FILE JS --%>
        
<c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            <%-- VISUALE DELLE POSSIBILI ISCRIZIONI PER CUI E' POSSIBILE PRESENTARE UN RICORSO --%>
            <div id="recoursePossibleDisplay" style="display: block;margin-bottom:30px;">
                <h2 id="recoursePossibleDisplayTitle">Iscrizioni per le quali è possibile presentare un ricorso</h2>
                <div id="recoursePossibleResultTable">
                    <table id="recoursePossibleTable">
                        <thead>
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
            <%-- FINE VISUALE DELLE POSSIBILI ISCRIZIONI PER CUI E' POSSIBILE PRESENTARE UNA RICORSO --%>
</c:if>
        
            <%-- VISUALE DELLE DOMANDE DI RINUNCIA PRESENTATE --%>
            <div id="recourseSubmittedDisplay" style="display: block;">
                <h2 id="recourseSubmittedDisplayTitle">Domande di rinuncia presentate:</h2>
                <div id="recourseSubmittedResultTable">
                    <table id="recourseSubmittedTable">
                        <thead>
                            <th>Data inserimento</th>
                            <th>Codice Fiscale</th>
                            <th>Cognome</th>
                            <th>Nome</th>
                            <th>Valutazione</th>
                            <th>Operazioni</th>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- FINE VISUALE DEi RICORSI PRESENTATI --%>
        
        </div>
        <%-- FINE CONTENUTO PRINCIPALE DELLA PAGINA --%>
        
        <%-- FINESTRA DI INSERIMENTO DI UN RICORSO --%>
        <div id="recourseAddWindow" name="recourseAddWindow" title="Compila un RICORSO" style="display: inline">
            <form id="recourseAddWindowForm" name="recourseAddWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p>
                            <label>Motivo</label>
                            <input type="text" id="recourseAddWindowMotivo" name="recourseAddWindowMotivo"  maxlength="50"/>
                            <label class="error" style="display:inline;" id="recourseAddWindowMotivoError" name="recourseAddWindowMotivoError"></label>
                        </p>
                        <p>
                            <input type="hidden" id="recourseAddWindowId" name="recourseAddWindowId" />
                            <input type="button" id="recourseAddWindowMotivoSave" onClick="saveNewRecourse();" value="Sottometti" />
                            <input type="button" id="recourseAddWindowMotivoUndo" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>
        <%-- FINE FINESTRA DI INSERIMENTO DI UN RICORSO --%>
        
        <%-- FINESTRA DI VISUALE DEI DETTAGLIO DI UN RICORSO --%>
        <div id="recourseViewDetailsWindow" name="recourseViewDetailsWindow" title="Visualizza ricorso" style="display: inline">
            <h3>Dettagli del ricorso</h3>
            <p id="recourseViewDetailsText"></p>
            <p>
                <input type="hidden" id="recourseViewDetailsWindowId" name="recourseViewDetailsWindowId" />
                <input type="button" id="recourseViewDetailsWindowUndo" value="Chiudi" />
            </p>
        </div>
        <%-- FINE FINESTRA VISUALE DEI DETTAGLI DI UNA DOMANDA DI RINUNCIA --%>
        
        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>
        <div id="recourseConfirmWindow" name="recourseConfirmWindow" title="Conferma operazione" style="display: inline">
            <div>
                <h3 id="recourseConfirmWindowText" name="recourseConfirmWindowText"></h3>
                <p>
                    <input type="button" id="recourseConfirmWindowConfirmButton" value="Conferma" />
                    <input type="button" id="recourseConfirmWindowUndoButton" value="Annulla" />
                </p>
            </div>
        </div>
        <%-- FINESTRA DI RICHIESTA DI CONFERMA --%>

        <%-- FINESTRA DI AVVISO (ALERT) --%>
        <div id="recourseAlertWindow" name="recourseAlertWindow" title="" style="display: inline">
            <div>
                <h3 id="recourseAlertWindowText" name="recourseAlertWindowText"></h3>
                <p>
                    <input type="button" id="recourseAlertWindowOkButton" value="OK" />
                </p>
            </div>
        </div>
        <%-- FINE FINESTRA DI AVVISO (ALERT) --%>
        
    <%@include file="footer.jsp" %>
    </body>
</html>