<%-- 
    Document   : accountSecretary
    Created on : 29-nov-2012, 9.01.50
    Author     : Gianmarco  Del Pozzo
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

        <title>Kids</title>

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
            <table>
                <tr>
                    <td style="width: 110px">
                        <label id="adminSearchLabel"> Nickname: </label>
                    </td>
                    <td style="width: 190px">
                        <input type="text" id="nickname"  name="id" class="adminSearchButton"/>
                    </td>
                    <td style="width: 110px">
                        <label id="adminSearchLabel"> Nome: </label>
                    </td>
                    <td style="width: 190px">
                        <input type="text" id="name"  name="name" class="adminSearchButton"/>
                    </td>
                    <td style="width: 110px">
                        <label id="adminSearchLabel"> Cognome: </label>
                    </td>
                    <td style="width: 190px"> 
                        <input type="text" id="surname" name="surname" class="adminSearchButton"/>
                    </td>
                </tr>
                <tr>
                    <td style="width: 110px">
                        <label id="adminSearchLabel"> Codice Fiscale: </label>
                    </td>
                    <td style="width: 190px">
                        <input type="text" id="taxCode" name="taxCode" class="adminSearchButton"/>
                    </td>
                    <td style="width: 110px">
                        <label id="adminSearchLabel"> Tipo Account: </label>
                    </td>
                    <td style="width: 190px">
                        <select id="type" name="type" class="adminSearchButton" style="width: 180px">
                            <OPTION value="" name="Nothing" selected > Scegli 
                            <OPTION value="Genitore" name="Genitore"> Genitore 
                            <OPTION value="Segreteria" name="DelegatoUfficio"> Segreteria 
                            <OPTION value="Delegato scienze della formazione" name="ScienzeFormazione"> Delegato Scienze della Formazione 
                            <OPTION value="Educatore" name="Educatore"> Educatore 
                            <OPTION value="Coordinatore Psicopedagogico" name="CoordinatorePsicopedagogico"> Coordinatore Psicopedagogico 
                            <OPTION value="Responsabile Scientifico" name="ResponsabileScientifico"> Responsabile Scentifico 
                            <OPTION value="Responsabile Asilo" name="ResponsabileAsilo"> Responsabile Asilo
                            <OPTION value="Delegato del Rettore" name="DelegatoDelRettore"> Delegato del Rettore
                            <OPTION value="Responsabile Mensa" name="ResponsabileMensa"> Responsabile Mensa
                        </select>             
                    </td>
                    <td colspan="2">
                        <input type="button" name="ricarica" id="ricarica" value="Ricerca" onclick="search()"style="width: 100%"/>
                    </td>
                </tr>

            </table> <br><br>

            <form name="insertAccount" method="post" action="accountInsert.jsp" >
                <input type="submit" id="addLinkButton" value="Inserisci Account" />
            </form>

            <table id="accountsTable" style="width:100%;">
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
