<%-- 
    Document   : showProject
    Created on : 8-dic-2012, 13.29.24
    Author     : Francesco Di Lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Coordinatore Psicopedagogico'}">
    <c:if test="${sessionScope.user.getAccountType()!='Responsabile Scientifico'}">
        <c:if test="${sessionScope.user.getAccountType()!='Delegato del rettore'}">
            <c:redirect url="index.jsp" />
        </c:if>
    </c:if>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/showProject.js"></script>        
        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                showComments();
                
            });
        </script>
        <title>Kids</title>
    </head>
    <div id="uploadFileWindow" title="Sottometti Progetto" style="display: inline">
        <form id="uploadFileForm" class="cmxform" method="post" action="" enctype="multipart/form-data" >
            <label class="artefactLabel" for="file">File:</label>
            <input type="file"  id="scegliFile"  value="Scegli il file" name="scegliFile"></input>
            <input type="submit" class="windowButton" id="sottometti" value="Salva Bozza"/>                 
        </form>
    </div>
    <div id="insertCommentoWindow" title="Inserisci Commento" style="display: inline">
        <form id="insertCommentoForm" class="cmxform" method="post" action="">
            <fieldset>
                <table width="80%">
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="artefactDescrizione">Contenuto *</label>
                    </td>
                    <td>  
                        <textarea id="contenutoCommento"rows="5" cols="25" name="contenutoCommento"></textarea>
                    </td>
                    </p>
                    </tr>
                </table>
                <input type="submit" class="windowButton" id="submitCommento" value="Ok"/> 
                <input type="hidden" id="idAutore" value=${sessionScope.user.getId()} />
                <input type="hidden" id="idProgetto" />
            </fieldset>  
        </form>
    </div>
    <div class="removeNews" id="removeCommentoWindow" title="Rimuovi News" >
        <p class="formp">    
        <h3> Vuoi rimuovere definitivamente questo commento?</h3>
        <input type="button" class="buttonRemove" id="removeCommentoButton" value="Ok" />
        <input type="button" class="buttonRemove" id="notRemoveCommentoButton" value="Annulla" />
    </p>
</div>
<body>
    <%@include file="header.jsp" %>
    <div id="linksManagement">
        <form id="modifyProjectForm" class="cmxform"  action="SubmitProject" method="post">
            <input type="hidden" value="${sessionScope.user.getAccountType()}" name="tipoAttore" id="tipoAttore" />
            <c:if test="${sessionScope.user.getAccountType()=='Coordinatore Psicopedagogico'}" >
                <div style="text-align: center">
                    <input type="button" style="margin-bottom: 5px;height: 40px" onclick="uploadFile()" name="draftCoord" id="draftCoord" value="Carica Progetto Annuale" />               
                    <input type="submit" style="margin-bottom: 5px;height: 40px" id="submitCoord" name="submitCoord" value="Sottometti al Responsabile Scientifico" />
                </div>
            </c:if>
            <c:if test="${sessionScope.user.getAccountType()=='Responsabile Scientifico'}" >
                <div style="text-align: center">
                    <input type="submit" style="margin-bottom: 5px;height: 40px"  value="Sottometti al Delegato del Rettore" name="acceptResp" id="acceptResp" />
                    <input type="submit" style="margin-bottom: 5px;height: 40px"  value="Richiedi Modifiche" name="requestModify" id="requestModify" />
                </div>
            </c:if>
            <c:if test="${sessionScope.user.getAccountType()=='Delegato del rettore'}" >
                <div style="text-align: center">
                    <input type="submit" style="margin-bottom: 5px;height: 40px"  value="Accettazione definitiva" name="acceptDeleg" id="acceptDeleg" />
                    <input type="submit" style="margin-bottom: 5px;height: 40px"  value="Richiedi Modifiche" name="requestModify" id="requestModify" />
                </div>
            </c:if>
        </form>
        <div style="padding-top: 22px;font-size: 18px;font-weight: bold">
            Path: <span id="mostraPath"></span>
        </div>
        <div style="font-size: 18px;font-weight: bold;padding-bottom: 30px;">
            Stato: <span id="mostraStato"></span>
        </div>
        <h1 style="font-size: 24px">Possibili Commenti</h1>                
        <c:if test="${sessionScope.user.getAccountType()=='Responsabile Scientifico'}" >
            <input type="button" style="margin-bottom: 5px;height: 30px" id="insertCommento" onclick="inserisciCommento()"value="Inserisci Commento" />
        </c:if>
        <c:if test="${sessionScope.user.getAccountType()=='Delegato del rettore'}" >
            <input type="button" style="margin-bottom: 5px;height: 30px" id="insertCommento" onclick="inserisciCommento()" value="Inserisci Commento" />
        </c:if>
        <table id="commentEduTable">
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Ora</th>
                    <th>Contenuto</th>
                    <th>Autore</th>
                    <th>Operazioni</th>
                </tr>
            </thead>
        </table>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
