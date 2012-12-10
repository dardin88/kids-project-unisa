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
        <title>Classification Management</title>
    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initializeRegistrationFields();
            createTableRegistrationChild();
<c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
            $("#classificationModifyWindowSave").button();
            $("#classificationModifyWindowSave").click(function() {
                classificationButtonActionSaveModify();
            });
            $("#classificationModifyWindowUndo").button();
            $("#classificationModifyWindowUndo").click(function() {
                changeWindowVisibility("classificationModifyWindow", false);
            });
            $("#classificationButtonOpenWindowCreateNew").button();
            $("#classificationButtonOpenWindowCreateNew").click(function() {
                classificationButtonOpenWindowCreateNew();
            });
            $("#classificationButtonActionCreateNew").button();
            $("#classificationButtonActionCreateNew").click(function() {
                classificationButtonActionCreateNew();
            });
            $("#classificationButtonActionSetProvvisoria").button();
            $("#classificationButtonActionSetProvvisoria").click(function() {
                classificationButtonActionSetProvvisoria();
            });
            $("#classificationButtonActionSetDefinitiva").button();
            $("#classificationButtonActionSetDefinitiva").click(function() {
                classificationButtonActionSetDefinitiva();
            });
            $("#classificationButtonOpenWindowAddCriterion").button();
            $("#classificationButtonOpenWindowAddCriterion").click(function() {
                classificationButtonOpenWindowAddCriterion();
            });
            $("#classificationButtonActionSaveCriterion").button();
            $("#classificationButtonActionSaveCriterion").click(function() {
                classificationButtonActionSaveCriterion();
            });
            $("#classificationButtonOpenWindowEditCriteria").button();
            $("#classificationButtonOpenWindowEditCriteria").click(function() {
                classificationButtonOpenWindowEditCriteria();
            });
            $("#classificationButtonActionSaveModifyCriteria").button();
            $("#classificationButtonActionSaveModifyCriteria").click(function() {
                classificationButtonActionSaveModify();
            });
            $("#classificationButtonActionUpdateResult").button();
            $("#classificationButtonActionUpdateResult").click(function() {
                classificationButtonActionUpdateResult();
            });
            
</c:if>
        });
        
    </script>
    
    <body>
        <%@include file="header.jsp" %>
        <div id="classificationSubmit">
            <input type="hidden" id="classificationSelectedId" />
            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
            <input type="submit" id="classificationButtonOpenWindowCreateNew" value="Crea una nuova graduatoria" />
            <input type="submit" id="classificationButtonOpenWindowCreateNew" value="Crea una nuova graduatoria" />
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
        <div id="classificationDisplay">
            
                        <div id="classificationFormWindowInfo" name="classificationFormWindowInfo" style="display: inline">
                            <p class="formp"> 
                                <legend><h3>Data Creazione:</h3></legend>
                                <input id="classificationFormWindowData" name="classificationFormWindowData" readonly="readonly" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Nome:</h3></legend>
                                <input id="classificationFormWindowNome" name="classificationFormWindowNome" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Status:</h3></legend>
                                <input id="classificationFormWindowStatus" name="classificationFormWindowStatus" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                            <p class="formp"> 
                                <legend><h3>Fase della domanda di iscrizione:</h3></legend>
                                <input class="details" id="registrationChildFormWindowFaseIscrizione" name="registrationChildFormWindowFaseIscrizione" type="text" size="40%" style="margin-right: 2%"> 
                            </p>
                        </div>
                        <div id="classificationFormWindowResultTable">
                            <table id="classificationResultTable">
                                <thead>
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

        
        <div id="classificationModifyWindow" name="classificationModifyWindow" title="Conferma operazione" style="display: inline">
            <form id="classificationModifyForm" name="classificationModifyForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <p>
                            <input type="hidden" id="classificationModifyWindowId" name="classificationModifyWindowId" />
                        </p>
                        <p>
                            <label>Data:</label>
                            <input type="text" readonly="readonly" id="classificationModifyWindowData" name="classificationModifyWindowData" />
                        </p>
                        <p>
                            <label>Status:</label>
                            <input type="text" readonly="readonly" id="classificationModifyWindowStatus" name="classificationModifyWindowStatus" />
                        </p>
                        <p>
                            <label>Nome</label>
                            <input type="text" id="classificationModifyWindowNome" name="classificationModifyWindowNome" />
                        </p>
                        <p>
                            <input type="submit" id="classificationModifyWindowSave" value="Salva" />
                            <input type="button" id="classificationModifyWindowUndo" value="Annulla" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>

        
        <div id="classificationAlertWindow" name="classificationAlertWindow" title="" style="display: inline">
            <form id="classificationAlertWindowForm" name="classificationAlertWindowForm" class="cmxform" method="post" action="">
                <fieldset>
                    <div>
                        <h3 id="classificationAlertWindowTitle" name="classificationAlertWindowTitle"></h3>
                        <p>
                            <input type="button" id="classificationAlertWindowOkButton" value="OK" />
                        </p>
                    </div>
                </fieldset>
            </form>
        </div>

    <%@include file="footer.jsp" %>
    </body>
</html>