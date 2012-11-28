<%-- 
    Document   : showTable
    Created on : 23-nov-2012, 17.38.35
    Author     : francesco
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
        <script type="text/javascript" src="js/newsShowTable.js"></script>
        <title>Gestione News "Visualizza News"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
                buildShowTable();
            });
        </script>
    </head>
    <%-- <div id="addLinkWindow" title="Inserisci News" style="display: inline">
         <form id="addLinkForm" class="cmxform" method="post" action="">
             <fieldset>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactTitolo">Titolo</label>
                     <input id="artefactTitolo" type="text" name="nomeNews" ></input>
                 </p>
                 <p class="formp">
                     <label class="artefactLabel" for="artefactDescrizione">Descrizione</label>
                     <input id="artefactDescrizione" type="text" name="descrizioneNews"></input>
                 </p>
                 <p class="formp">
                      <label class="artefactLabel" for="artefactTipo">Tipo</label>
                  <select id="artefactTipo" name="artefactTipo" class="artefactSelect">
                      <option value="">Evento</option>
                      <option vale="">Notizia</option>
                  </select>
                 </p>
                  <p class="formp">
                     <label class="artefactLabel" for="artefactData">Data</label>
                     <input id="artefactData" type="date" name="dataNews"></input>
                 </p>
                  <p class="formp">
                     <label class="artefactLabel" for="artefactOra">Ora</label>
                     <input id="artefactOra" type="time" name="oraNews"></input>
                 </p>
                  <p class="formp">
                     <label class="artefactLabel" for="artefactAllegato">Allegato(Può essere anche vuoto)</label>
                     <input id="artefactAllegato" type="text" name="allegatoNews"></input>
                 </p>
                 
                 
                 
                 <input type="submit" class="windowButton" class="submit" id="addLinkButton2" value="Ok"/>
                 
             </fieldset>
         </form>
</div> --%>
    <body>
        <%@include file="headerNews.jsp" %>
            <div id="linksManagement">
                <h1 style="font-size: 35px;text-align: center;"> Lista News </h1>
                <c:if test="${sessionScope.user.getAccountType()=='Delegato Ufficio'}" >
                    <form name="insertNews" method="post" action="newsInsertNews.jsp" >
                        <input type="submit" id="addLinkButton" value="Inserisci News" />
                    </form>
                </c:if>
                <table id="linkTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Titolo</th>
                            <th>Descrizione</th>
                            <th>Data</th>
                            <th>Ora</th>
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