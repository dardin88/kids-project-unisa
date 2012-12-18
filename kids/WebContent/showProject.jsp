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
    <c:redirect url="index.jsp" />
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
                buildShowTable();
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
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <c:if test="${sessionScope.user.getAccountType()=='Coordinatore Psicopedagogico'}" >
                <div style="text-align: center">
                    <input type="button" style="margin-bottom: 5px;height: 40px" onclick="uploadFile()" id="submitProjectAnnualButton" value="Carica Progetto Annuale" />               
                    <input type="button" style="margin-bottom: 5px;height: 40px" onclick="submit(${requestScope.Id})" id="confirmProjectAnnualButton" value="Sottometti" />
                </div>
            </c:if>
            <table width="95%" border="0" style="font-size: 18px">
                <tr><th>Path</th><th>Stato</th></tr>
                <tr style="text-align: center">  <td><label id="mostraPath"></label></td>
                    <td><label id="mostraStato"></label></td></tr>
            </table>
            <br />
            <h1 style="font-size: 24px">Possibili Commenti</h1>                
            <%@include file="commentEdu.jsp" %>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
