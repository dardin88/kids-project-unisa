<%-- 
    Document   : trainee
    Created on : 17-nov-2012, 11.47.59
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Delegato scienze della formazione'}">
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
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
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
                messageDialog();
            });
        </script>
    </head>
    <c:if test="${requestScope.message!=null}">
        <div id="confirm" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="trainees.jsp">
                <fieldset>
                    <p class="formp">
                        <label class="requirementLabel">${requestScope.message}</label>
                    </p>
                    <p class="formp">
                        <input type="submit" class="confirmButton" id="confirmButton" value="ok"/>

                    </p>
                </fieldset>
            </form>
        </div>
    </c:if>
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
        <form id="information" class="cmxform" method="post" action="InsertTrainee">
            <table>
                <tr><td>Matricola*</td><td><input id="Matricola" type="text" name="Matricola" ></td></tr>
                <tr><td> Nome*</td><td><input type="text" name="Nome" id="Nome"></td></tr>
                <tr><td>Cognome*</td><td><input type="text" name="Cognome" id="Cognome"></td></tr>
                <tr><td>Data di nascita*</td><td><input type="date" name="DataNascita" id="DataNascita"></td></tr>
                <tr><td>Città di nascita*</td><td><input type="text" name="CittaNascita" id="CittaNascita"></td></tr>
                <tr><td>Città di residenza*</td><td><input type="text" name="CittaResidenza" id="CittaResidenza"></td></tr>
                <tr><td>Indirizzo*</td><td><input type="text" name="Indirizzo" id="Indirizzo"></td></tr>
                <tr><td>CAP*</td><td><input type="text" name="CAP" id="CAP"></td></tr>
                <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonico" id="NumeroTelefonico"></td></tr>
                <tr><td>Email*</td><td><input type="text" name="Email" id="Email"></td></tr>
                <tr><td>Titolo Studio</td><td><input type="text" name="TitoloStudio" id=""></td><tr>
            </table>
            <input id="saveButton" type="submit" name="submit" value="Salva" style="float:right;" >

        </form>
    </div>
    <div id="informationTraineeWindow" title="Informazioni Tirocinante" style="display: inline;">
        <form id="informationTrainee" class="cmxform" method="post" action="ModifyTrainee">
            <table>
                <tr><td>Matricola*</td><td><input id="RegisterInf" type="text" name="MatricolaInf" value=""  disabled="true"></td></tr>
                <tr><td> Nome*</td><td><input type="text" name="NomeInf" id="NameInf" value="" disabled="true" ></td></tr>
                <tr><td>Cognome*</td><td><input type="text" name="CognomeInf" id="SurnameInf" value=""disabled="true" ></td></tr>
                <tr><td>Data di nascita*</td><td><input type="date" name="DataNascitaInf" id="BirthDateInf" value=""disabled="true" ></td></tr>
                <tr><td>Città di nascita*</td><td><input type="text" name="CittaNascitaInf" id="CityOfBirthInf" value="" disabled="true" ></td></tr>
                <tr><td>Città di residenza*</td><td><input type="text" name="CittaResidenzaInf" id="CityOfResidenceInf" value="" disabled="true" ></td></tr>
                <tr><td>Indirizzo*</td><td><input type="text" name="IndirizzoInf" id="AddressInf" value="" disabled="true" ></td></tr>
                <tr><td>CAP*</td><td><input type="text" name="CAPInf" id="CAPInf" value="" disabled="true" ></td></tr>
                <tr><td>Numero di telefono</td><td><input type="text" name="NumeroTelefonicoInf" id="TelephoneNumberInf" value="" disabled="true" ></td></tr>
                <tr><td>Email*</td><td><input type="text" name="EmailInf" id="EmailInf" value="" disabled="true" ></td></tr>
                <tr><td>Titolo Studio</td><td><input type="text" name="TitoloStudioInf" id="QualificationInf" value="" disabled="true"></td><tr>


            </table>
                <input id="modifyButton" type="button" name="submit" value="Modifica" style="float:right;" onclick="modify()" >
                <input id="saveChanges" type="submit" name="submit" value="salva" style="float:right;visibility: hidden" </input>
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
