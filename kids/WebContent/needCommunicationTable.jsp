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
                $("#artefactDate").datepicker({dateFormat:'yy-mm-dd' });
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
                        <label class="artefactLabel" for="artefactType">Tipo *</label>
                    </td>
                    <td>
                        <select id="artefactType" name="typeCommunication" class="artefactSelect">
                            <option value="0">Scegli tipo Comunicazione</option>
                            <option value="1">Bisogni</option>
                        </select>
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="artefactIdEducator">Id Educatore *</label>
                    </td>
                    <td>
                        <input id="artefactIdEducator" type="text" name="idEducator" ></input>                                         
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="artefactIdChild">Id Bambino *</label>
                    </td>
                    <td>
                        <input id="artefactIdChild" type="text" name="idChild" ></input>                                         
                    </td>
                    </p>
                    </tr>
                    <tr>
                    <p style="text-align: left;" class="formp">
                    <td>
                        <label class="artefactLabel" for="artefactDescription">Descrizione *</label>
                    </td>
                    <td>  
                        <textarea id="artefactDescription"rows="5" cols="25" name="description"></textarea>
                    </td>
                    </p>
                    </tr>
                    
                    <tr>        
                    <p style="text-align: left;" class="formp">
                    <td>

                        <label class="artefactLabel" for="artefactData">Data *</label>
                    </td>
                    <td>
                        <input id="artefactDate"  type="text" name="date"></input>
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
    
<div id="updateCommunicationWindow" title="Visualizza Communication" style="display: inline">
    <form id="updateCommunicationForm"  name="updateCommunicationForm" class="cmxform" method="post" action="">
        <fieldset>
            <table style="width:100%;">
                <tr>
                <p style="text-align: left;" class="formp">
                <td>
                    <label class="artefactLabel" for="artefactSolved">Risolvi *</label>
                </td>
                <td>
                    <select id="artefactSolved" disabled onblur="verifyOra()" name="Risolvi" class="artefactSelect">
                        <option value="0">Risolvi Comunicazione</option>
                        <option value="1">No</option>
                        <option value="2">Si</option>
                    </select>
                </td>
                </p>
                </tr>
                    <td>
                       <c:if test="${sessionScope.user.getAccountType()=='Educatore'}">
                           <input type="button" class="windowButton2" id="updateCommunication" value="Risolvi" onclick="enableButtonUpdate()" />
                           <input type="submit" style="visibility: hidden;" class="windowButton2" id="confirmUpdateCommunication"  value="Salva"/>                 
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
        <h1 style="font-size: 35px;text-align: center;"> Lista Communication </h1>
        <c:if test="${sessionScope.user.getAccountType()=='Educatore'}" >
            <input type="button" id="addLinkButton" value="Inserisci Communication" />               
        </c:if>
        <table id="linkTable" style="width:95%;">
            <thead>
                <tr>
                    <th>Tipo</th>
                    <th>Id Educatore</th>
                    <th>Id Bambino</th>
                    <th>Descrizione</th>
                    <th>Data</th>
                    <th>Risolto</th>
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
