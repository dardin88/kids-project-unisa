<%-- 
    Document   : needCommunicationTable
    Created on : Nov 29, 2012, 1:22:36 PM
    Author     : Elena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        <link rel="stylesheet" type="text/css" href="css/jquery.ui.timepicker.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/needCommunicationTable.js"></script>
        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>

        <title>Gestione Bambini "Visualizza Cominucazioni Bisogni"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                $("#artefactData").datepicker({dateFormat:'yy-mm-dd' });
                buildShowTable();
            });
        </script>
    </head>
    <div id="addLinkWindow" title="Inserisci Comunicazione Bisogni" style="display: inline">
        <form id="addLinkForm" class="cmxform" method="post" action="" enctype="multipart/form-data" >
            <fieldset>
                <table>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="type">Tipo *</label>
                    </td>
                    <td>
                        <select id="artefactType" name="type" class="artefactSelect">
                            <option value="0">Scegli tipo Comunicazione</option>
                            <option value="1">Bisogni</option>
                        </select>
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="idEducator">Id Educatore *</label>
                    </td>
                    <td>
                        <input id="artefactIdEducator" type="text" name="idEducator" ></input>                                         
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="idChild">Id Bambino *</label>
                    </td>
                    <td>
                        <input id="artefactIdChild" type="text" name="idChild" ></input>                                         
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="Description">Descrizione *</label>
                    </td>
                    <td>  
                        <textarea id="artefactDescription"rows="5" cols="25" name="Description"></textarea>
                    </td>
                    </p>
                    </tr>
                    <tr>        
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="artefactData">Data *</label>
                    </td>
                    <td>
                        <input id="artefactData"  type="text" name="date"></input>
                    </td>
                    </p>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" class="windowButton" id="addLinkButton3" value="Ok"/>                 
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
    <div id="solveCommunicationWindow" title="Risolvi Comunicazione" style="display: inline">
    <form id="solveCommunicationForm"  name="solveCommunicationForm" class="cmxform" method="post" action="">
        <fieldset>
            <table style="width:100%;">
                <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="type">Risolvi *</label>
                    </td>
                    <td>
                        <select id="artefactSolved" name="type" class="artefactSelect">
                            <option value="0">No</option>
                            <option value="1">Si</option>
                        </select>
                    </td>
                    </p>
                    </tr>
                <tr>
                    <td>
                       <c:if test="${sessionScope.user.getAccountType()=='Educatore'}">
                           <input type="button" class="windowButton2" id="solveCommunication" value="Risolvi" onclick="enableButtonUpdate()" />
                           <input type="submit" style="visibility: hidden;" class="windowButton2" id="confirmSolveCommunication"  value="Salva"/>                 
                       </c:if>
                    </td>
                </tr>
            </table>                 
        </fieldset>
    </form>
</div>
<body>
    <%@include file="header.jsp" %>
    <div id="linksManagement">
        <h1 style="font-size: 35px;text-align: center;"> Lista Comunicazioni Bisogno </h1>
        <c:if test="${sessionScope.user.getAccountType()=='Educatore'}" >
            <input type="button" id="addLinkButton" value="Inserisci Comunicazione Bisogno" />               
        </c:if>
        <table id="linkTable" style="width:95%;">
            <thead>
                <tr>
                    <th>IdComunicazione</th>
                    <th>IdEducatore</th>
                    <th>IdBambino</th>
                    <th>Descrizione</th>
                    <th>Data</th>
                    <th>Risolto</th>
                    <th>Operazione</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
