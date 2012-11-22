<%-- 
    Document   : linksTable.jsp
    Created on : 13-nov-2012, 14.53.26
    Author     : Dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/linksTable.js"></script>
        <title>Gestione link di tracciabilità - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
            });
        </script>
    </head>
    <div id="addLinkWindow" title="Aggiungi link" style="display: inline">
        <form id="addLinkForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="artefactLabel" for="artefact1">Artefatto 1</label>
                    <select id="artefact1" name="artefact1" class="artefactSelect"><option value=""></option></select>
                </p>
                <p class="formp">
                    <label class="artefactLabel" for="artefact2">Artefatto 2</label>
                    <select id="artefact2" name="artefact2" class="artefactSelect"><option value=""></option></select>
                </p>
                <p class="formp">
                    <input type="submit" class="windowButton" class="submit" id="addLinkButton2" value="Ok"/>
                </p>
            </fieldset>
        </form>
    </div>
    <div id="removeLinkWindow" title="Rimuovi link" style="display: inline">
        <form id="removeLinkForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="artefactLabel">Sei sicuro di voler eliminare questo link?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmRemoveButton" id="confirmRemoveLinkButton" value="Si"/>
                    <input type="button" class="notConfirmRemoveButton" id="notConfirmRemoveLinkButton" value="No"/>
                </p>
            </fieldset>
        </form>
    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <input type="button" id="addLinkButton" value="Aggiungi link di tracciabilità"/>
            <table id="linksTable">
                <thead>
                    <tr>
                        <th>Artefatto 1</th>
                        <th>Artefatto 2</th>
                        <th>Operazioni</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
