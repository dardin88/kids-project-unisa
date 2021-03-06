<%-- 
    Document   : healthCommunicationTable
    Created on : Nov 29, 2012, 1:22:36 PM
    Author     : Elena Sollai
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
        <script type="text/javascript" src="js/healthCommunicationTable.js"></script>
        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>

        <title>Kids</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                $("#artefactDate").datepicker({dateFormat:'yy-mm-dd' });
                buildShowTable();
            });
        </script>
    </head>
    <div id="addLinkWindow" title="Inserisci Comunicazione Salute" style="display: inline">
        <form id="addLinkForm" class="cmxform" method="post" action="">
            <fieldset>
                <table>
                    <tr>
                        <td style="width: 150px">
                            <label class="artefactLabel" for="artefactSurname">Nome Bambino *</label>
                        </td>
                        <td style="width: 150px">
                            <input id="artefactName" type="text" name="name" ></input>                                         
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 150px">
                            <label class="artefactLabel" for="artefactSurname">Cognome Bambino *</label>
                        </td>
                        <td style="width: 150px">
                            <input id="artefactSurname" type="text" name="surname" ></input>                                         
                        </td>
                    </tr>
                    <tr style="width: 150px">
                        <td style="width: 150px">
                            <label class="artefactLabel" for="artefactDescription">Descrizione *</label>
                        </td>
                        <td style="width: 150px">  
                            <textarea id="artefactDescription"rows="5" cols="25" name="description"></textarea>
                        </td>
                    </tr>
                    <tr>        
                        <td style="width: 150px">
                            <label class="artefactLabel" for="artefactData">Data *</label>
                        </td>
                        <td style="width: 150px">
                            <input id="artefactDate"  type="text" name="date"></input>
                        </td>
                    </tr>
                </table>
                <p style="text-align: center">
                    <input type="submit" class="windowButton" id="addLinkButton3" value="Ok"/>                 
                </p>
            </fieldset>
        </form>
    </div> 
    <div id="showCommunicationWindow" title="Visualizza Comunicazione" style="display: inline">  
        <fieldset>
            <table style="width:100%;">
                <tr>
                    <td colspan="4">       
                        <label class="artefactLabel2">Id Comunicazione:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelId"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">       
                        <label class="artefactLabel2">Tipo Comunicazione:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelType"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">       
                        <label class="artefactLabel2">Id Educatore:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelIdEducator"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">                    
                        <label class="artefactLabel2">Id Bambino:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelIdChild"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">       
                        <label class="artefactLabel2">Nome Bambino:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelName"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">       
                        <label class="artefactLabel2">Cognome Bambino:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelSurname"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">                    
                        <label class="artefactLabel2">Descrizione:</label>
                    </td>
                    <td colspan="4">
                        <label class="artefactLabel" id="labelDescription"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">                    
                        <label class="artefactLabel2">Data:</label>
                    </td>
                    <td colspan="4">                    
                        <label class="artefactLabel" id="labelDate"></label> 
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <c:if test="${sessionScope.user.getAccountType()=='Educatore'}" >
                <input type="button" id="addLinkButton" value="Inserisci Comunicazione Salute" />               
            </c:if>
            <table id="linkTable" style="width:95%;">
                <thead>
                    <tr>
                        <th>Id Bambino</th>
                        <th>Nome Bambino</th>
                        <th>Cognome Bambino</th>
                        <th>Descrizione</th>
                        <th>Data</th>
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
