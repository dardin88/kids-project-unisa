<%-- 
    Document   : accountSecretary
    Created on : 29-nov-2012, 9.01.50
    Author     : Gianmarco
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
        <script type="text/javascript" src="js/account.js"></script>
        <title>Gestione News "Visualizza News"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager2();
                buildAccountTable();
            });
        </script>
    </head>
    <%@include file="header.jsp" %>
<body>
        
            <div id="linksManagement">
                <h1 style="font-size: 35px;text-align: center;"> Account </h1>
                
                 Nickname:<input type="text" id="nickname"  name="id"  />
                Nome:<input type="text" id="name"  name="name"/>
                Cognome:<input type="text" id="surname" name="surname" />
                Codice Fiscale:<input type="text" id="taxCode" name="taxCode" />
                Tipo Account<select id="type" name="type">
                                <OPTION value="Nothing" name="Nothing" > Scegli 
                                <OPTION value="Genitore" name="Genitore" selected> Genitore 
                                <OPTION value="Delegato Ufficio" name="DelegatoUfficio"> Segreteria 
                                <OPTION value="Delegato scienze della formazione" name="ScienzeFormazione"> Delegato Scienze della Formazione 
                                <OPTION value="Educatore" name="Educatore"> Educatore 
                                <OPTION value="Coordinatore Psicopedagogico" name="CoordinatorePsicopedagogico"> Coordinatore Psicopedagogico 
                                <OPTION value="Responsabile Scientifico" name="ResponsabileScientifico"> Responsabile Scentifico 
                                <OPTION value="Responsabile Asilo" name="ResponsabileAsilo"> Responsabile Asilo
                            </select>
                <input type="button" name="ricarica" id="ricarica" value="ricerca" onclick="search()"/>
                
                <table id="accountsTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>NickName</th>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Codice Fiscale</th>
                            <th>Tipo Account</th>
                            <th>Operazione </th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <form name="insertAccount" method="post" action="accountInsert.jsp" >
                     <input type="submit" id="addLinkButton" value="Inserisci Account" />
                </form>
                
                
            </div>
      <div id="removeAccountWindow" title="Rimuovi Account" style="display: inline">
        <form id="removeAccountForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="requirementLabel">Sei sicuro di voler eliminare questo Account?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmRemoveButton" id="confirmRemoveLinkButton" value="Si"/>
                    <input type="button" class="notConfirmRemoveButton" id="notConfirmRemoveLinkButton" value="No"/>
                </p>
            </fieldset>
        </form>
    </div>
                
        <%@include file="footer.jsp" %>
    </body>
</html>
