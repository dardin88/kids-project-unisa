<%-- 
    Document   : showTable
    Created on : 23-nov-2012, 17.38.35
    Author     : francesco di lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
        <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Segreteria'}">
    <c:if test="${sessionScope.user.getAccountType()!='Genitore'}">
         <c:redirect url="index.jsp" />
    </c:if>
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

        <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/newsShowTable.js"></script>
        <title>Gestione News "Visualizza News"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                $("#artefactData").datepicker({dateFormat:'yy-mm-dd' });
                $("#artefactData2").datepicker({dateFormat:'yy-mm-dd'});    
                //$("#artefactOra").timepicker();
    
                buildShowTable();
                
            });
        </script>
    </head>
    <div id="addLinkWindow" title="Inserisci News" style="display: inline">
        <form id="addLinkForm" class="cmxform" method="post" action="" enctype="multipart/form-data" >
            <fieldset>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactTitolo">Titolo *</label>
                    <input id="artefactTitolo" type="text" name="nomeNews" ></input>                     
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactDescrizione">Descrizione *</label>
                    <input id="artefactDescrizione"  type="text" name="descrizioneNews"></input>
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactTipo">Tipo *</label>
                    <select id="artefactTipo" name="selectNews" onblur="verifyOra()" class="artefactSelect">
                        <option value="0">Scegli tipo News</option>
                        <option value="1">Evento</option>
                        <option value="2">Notizia</option>
                        <option value="3">Apertura Mensa</option>
                        <option value="4">Chiusura Mensa</option>
                    </select>
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactData">Data *</label>
                    <input id="artefactData"  type="text" name="dataNews"></input>
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactOra">Ora</label>
                    <input id="artefactOra" onkeyup="hiddenMessage()" type="text" name="oraNews"></input>
                    <span id="errOra" style="visibility: hidden;color:red;font-weight: bold"> Ora Obbligatoria per questo tipo di news </span> 
                </p>
                <p style="text-align: left;" class="formp">
                    <label class="artefactLabel" for="artefactAllegato">Allegato(Può essere anche vuoto)</label>
                    <input type="file"  id="addLinkButton2"  value="Scegli il file" name="scegliFile"></input>

                </p>                      
                <input type="submit" class="windowButton" id="addLinkButton3" value="Ok"/>                 
            </fieldset>
        </form>
    </div> 
    <div id="removeNewsWindow" title="Rimuovi News" style="display: inline">
        <p class="formp">    
        <h3> Vuoi rimuovere definitivamente questa news?</h3>
        <input type="button" class="windowButton" id="removeNewsButton" value="Ok" />
        <input type="button" class="windowButton" id="notRemoveNewsButton" value="Annulla" />
    </p>
</div>
<div id="updateNewsWindow" title="Modifica News" style="display: inline">
    <form id="updateNewsForm" name="updateNewsForm" class="cmxform" method="post" action="">
        <fieldset>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactTitolo">Titolo *</label>
                <input id="artefactTitolo2"  type="text" name="nomeNews" ></input>                     
            </p>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactDescrizione">Descrizione *</label>
                <input id="artefactDescrizione2"  type="text" name="descrizioneNews"></input>
            </p>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactTipo">Tipo *</label>
                <select id="artefactTipo2" onblur="verifyOra()" name="selectNews" class="artefactSelect">
                    <option value="0">Scegli tipo News</option>
                    <option value="1">Evento</option>
                    <option value="2">Notizia</option>
                    <option value="3">Apertura Mensa</option>
                    <option value="4">Chiusura Mensa</option>
                </select>
            </p>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactData">Data *</label>
                <input id="artefactData2"  type="text" name="dataNews"></input>
            </p>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactOra">Ora</label>
                <input id="artefactOra2" onblur="hiddenMessage()" type="time" name="oraNews"></input>
                <span id="errOra" style="visibility: hidden;color:red;font-weight: bold;margin-left: 1%;"> Ora Obbligatoria per questo tipo di news </span> 
            </p>
            <p style="text-align: left;" class="formp">
                <label class="artefactLabel" for="artefactAllegato">Allegato non pu&ograve; essere modificato</label>
            </p>                       
            <input type="submit" class="windowButton" id="confirmUpdateNews" value="Ok"/>                 
        </fieldset>
    </form>
</div>
<body>
    <%@include file="header.jsp" %>
    <div id="linksManagement">
        <h1 style="font-size: 35px;text-align: center;"> Lista News </h1>
        <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}" >
            <input type="button" id="addLinkButton" value="Inserisci News" />               
        </c:if>
        <table id="linkTable" style="width:95%;">
            <thead>
                <tr>
                    <th>Titolo</th>
                    <th>Descrizione</th>
                    <th>Data</th>
                    <th>Ora</th>
                    <th>Tipo</th>
                    <th>Allegato</th>
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
