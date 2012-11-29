<%-- 
    Document   : healthCommunicationTable
    Created on : Nov 29, 2012, 1:23:31 PM
    Author     : Elena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        <script type="text/javascript" src="js/healthCommunicationTable.js"></script>
        <title>Gestione Bambini "Visualizza Cominucazioni Salute"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                buildShowTable();
            });
        </script>
    </head>
    <div id="addLinkWindow" title="Inserisci Comunicazione" style="display: inline">
         <form id="addLinkForm" class="cmxform" method="post" action="">
             <fieldset>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactType">Tipo *</label>
                     <input id="artefactType"style=" display:block;" type="text" name="Type"></input>
                 </p>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactIdEducator">Educatore *</label>
                     <input id="artefactIdEducator" style="display: block" type="text" name="idEducator" ></input>                     
                 </p>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactIdChild">Bambino *</label>
                     <input id="artefactIdChild" style="display:block;" type="text" name="idChild"></input>
                 </p>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactDescription">Descrizione *</label>
                     <input id="artefactDescription" style="display:block;" type="date" name="description"></input>
                 </p>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactDate">Data *</label>
                     <input id="artefactDate" style="display:block;" type="date" name="date"></input>
                 </p>
                 <input type="submit" class="windowButton" id="addLinkButton3" value="Ok"/>                 
             </fieldset>
         </form>
    </div>
  <body>
        <%@include file="header.jsp" %>
            <div id="linksManagement">
                <h1 style="font-size: 35px;text-align: center;"> Lista Comunicazioni Salute </h1>
                <c:if test="${sessionScope.user.getAccountType()=='Educatore'}" >
                    <%--     <form name="insertNews" method="post" action="newsInsertNews.jsp" > --%>
                        <input type="button" id="addLinkButton" value="Inserisci Comunicazione Salute" />               
                </c:if>
                <table id="linkTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>IdEducatore</th>
                            <th>IdBambino</th>
                            <th>Descrizione</th>
                            <th>Data</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
      
        <%@include file="footer.jsp" %>
    </body>
</html>
