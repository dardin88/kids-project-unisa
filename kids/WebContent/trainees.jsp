<%-- 
    Document   : trainee
    Created on : 17-nov-2012, 11.47.59
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">              
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/trainees.js"></script>
        <title>Tirocinanti </title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                buildTraineeTable();
                initializeLinksManager();
            });
        </script>
    </head>
    <div id="removeTraineeWindow" title="Rimuovi Tirocinante" style="display: inline">
        <form id="removeTraineeForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="requirementLabel">Sei sicuro di voler eliminare questo Tirocinante?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmRemoveButton" id="confirmRemoveLinkButton" value="Si"/>
                    <input type="button" class="notConfirmRemoveButton" id="notConfirmRemoveLinkButton" value="No"/>
                </p>
            </fieldset>
        </form>
    </div>
    <div id="insertTraineeWindow" title="Inserisci Tirocinante" style="display: inline;">
        <form id="" class="cmxform" method="post" action="">
            <table>
                <tr><td>Matricola*</td><td><input id="Matricola" type="text" name="Matricola" ></td></tr>
                <tr><td> Nome*</td><td><input type="text" name="Nome" id="Nome"></td></tr>
                <tr><td>Cognome*</td><td><input type="text" name="Cognome" id="Cognome"></td></tr>
                <tr><td>Data di nascita*</td><td><input type="date" name="DataNascita" id="DataNascita"></td></tr>
                <tr><td>Città di nascita*</td><td><input type="text" name="CittaNascita" id="CittaNascita"></td></tr>
                <tr><td>Città di residenza*</td><td><input type="text" name="CittaResidenza" id="CittaResidenza"></td></tr>
                <tr><td>Indirizzo*</td><td><input type="text" name="Indirizzo" id="Indirizzo"></td></tr>
                <tr><td>CAP*</td><td><input type="text" name="CAP" id="CAP"></td></tr>
                <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonico" id="NumeroTelfonico"></td></tr>
                <tr><td>Email*</td><td><input type="text" name="Email" id="Email"></td></tr>
                <tr><td>Titolo Studio</td><td><input type="text" name="TitoloStudio" id=""></td><tr>
            </table>
            <input id="saveButton" type="submit" name="submit" value="Salva" style="float:right;"onClick="controlla()" >

        </form>
    </div>
    <body>
        <%@include file="header.jsp" %>
        <div id="traineesManagement">
            <form id="insertForm" method="" action="">
                <input type="button" id="insertButton" name="Inserisci" value="Inserisci nuovo tirocinante" onClick="openInsertTraineeDialog()" >
            </form> 
            <form id="searchTrainee" method="post" name="nomeForm" style="padding-top: 15px;">
                Nome:<input type="text" name="Nome" id="Nome" onkeyup="search()">
                Cognome:<input type="text" name="Cognome" id="Cognome" onkeyup="search()">
            </form>
            <table id="traineesTable">
                <thead>
                    <tr>
                        <th>Matricola</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Stato</th>
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
