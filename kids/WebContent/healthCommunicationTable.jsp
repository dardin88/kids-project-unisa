<%-- 
    Document   : healthCommunicationTable
    Created on : Nov 29, 2012, 1:22:36 PM
    Author     : Elena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />    
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Educatore'} && ${sessionScope.user.getAccountType()!='Genitore'}">
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
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">
        
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/healthCommunicationTable.js"></script>
        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        
        <title>Gestione Bambini "Visualizza Cominucazioni Salute"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                $("#artefactData").datepicker({dateFormat:'yy-mm-dd' });
                buildHealthCommunicationTable();
            });
        </script>
    </head>
    <div id="addCommunicationWindow" title="Inserisci Comunicazione" style="display: inline">
         <form id="addCommunicationForm" class="cmxform" method="post" action="" enctype="multipart/form-data" >
             <fieldset>
                 <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactType">Tipo *</label>
                      <select id="artefactType"style=" display:block;" name="Type" class="artefactSelect">
                        <option value="0">Scegli tipo Comunicazione</option>
                        <option value="1">Salute</option>
                    </select>
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactIdEducator">Educatore *</label>
                    <input id="artefactIdEducator" style="display: block" type="text" name="idEducator" ></input>                     
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactIdChild">Bambino *</label>
                    <input id="artefactIdChild" style="display:block;" type="text" name="idChild"></input>
                </p>
                <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactData">Data *</label>
                     <input id="artefactData"  type="text" name="date"></input>
                 </p>
                 <input type="submit" class="windowButton" id="addLinkButton3" value="Ok"/>                 
             </fieldset>
         </form>
    </div>
    <div class="removeCommunication" id="removeCommunicationWindow" title="Rimuovi Comunicazione" >
        <p class="formp">    
        <h3> Vuoi rimuovere definitivamente questa comunicazione?</h3>
        <input type="button" class="buttonRemove" id="removeCommunicationButton" value="Ok" />
        <input type="button" class="buttonRemove" id="notRemoveCommunicationButton" value="Annulla" />
    </p>
</div>
    <div id="updateCommunicationWindow" title="Modifica Comunicazione" style="display: inline">
        <form id="updateCommunicationForm" name="updateCommunicationForm" class="cmxform" method="post" action="">
            <fieldset>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactType">Tipo *</label>
                    <select id="artefactType"style=" display:block;" name="Type" class="artefactSelect">
                        <option value="0">Scegli tipo Comunicazione</option>
                        <option value="1">Salute</option>
                      </select>
                 </p>
                 <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactIdEducator">Educatore *</label>
                     <input id="artefactIdEducator" style="display: block" type="text" name="idEducator" ></input>                     
                 </p>
                 <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactIdChild">Bambino *</label>
                     <input id="artefactIdChild" style="display:block;" type="text" name="idChild"></input>
                 </p>
                 <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactDescription">Descrizione *</label>
                     <input id="artefactDescription" style="display:block;" type="date" name="description"></input>
                 </p>
                 <p style="text-align: left;" class="formp">
                     <label class="artefactLabel" for="artefactData">Data *</label>
                     <input id="artefactData"  type="text" name="date"></input>
                 </p>
                  <input type="submit" class="windowButton" id="confirmUpdateCommunication" value="Ok"/>                 
        </fieldset>
    </form>
</div>  
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <h1 style="font-size: 35px;text-align: center;"> Lista Comunicazioni Salute </h1>
            <c:if test="${sessionScope.user.getAccountType()=='Educatore'}" >
                <input type="button" id="addCommunicationButton" value="Inserisci Comunicazione Salute" />               
            </c:if>
            <table id="CommunicationTable" style="width:95%;">
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
