<%-- 
    Document   : account
    Created on : 23-nov-2012, 14.57.05
    Author     : tonino ft. gianma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
<c:if test="${sessionScope.id >=0}">
    <c:if test="${sessionScope.user.getAccountType()=='Genitore'}"> 
        <c:redirect url="newsGenitorePage.jsp" />
    </c:if>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <title>Registrazione Account - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <form id="registrationForm" class="cmxform"  action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Form di Registrazione</h1><br>
                    <input type="hidden" id="register" name="Matricola" class="registrationField" >
                    <table style="margin-left: 5%; font-weight: bold; font-size: 10pt; float: left">
                        <tr><td> Nome:
                            <td> <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="accountName" class="registrationField" type="text" name="Nome" size="50%">
                                </p> 
                        <tr><td> Cognome: <td>       
                                <p class="formp">
                                    <input  style="margin-left: 2%; width: 250px" id="accountSurname" class="registrationField"type="text" name="Cognome">                                </p>
                        <tr><td>  Data di nascita:<td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="dateOfBirth" class="registrationField" type="text" name="DataNascita">
                                </p>
                        <tr><td> Comune di nascita: <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="placeOfBirth" class="registrationField" type="text" name="ComuneNascita">
                                </p>
                        <tr><td> CodiceFiscale: <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="taxCode" class="registrationField" type="text" name="CodiceFiscale">
                                </p>
                        <tr><td> Cittadinanza: <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="citizenship" class="registrationField" type="text" name="Cittadinanza">
                                </p>
                        <tr><td> Comune di residenza: <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="municipalityResidence" class="registrationField" type="text" name="ComuneResidenza">
                                </p>
                        <tr><td> Provincia Residenza: <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="provinceResidence" class="registrationField" type="text" name="ProvinciaResidenza">
                                </p>
                        <tr><td> Indirizzo Residenza:  <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="viaResidence" class="registrationField" type="text" name="IndirizzoResidenza">
                                </p>
                    </table>
                </div>
                <input style="width: 300px; margin-left: 7%" type="submit" name="registrationButton" id="registrationButton" value="Avanti" />
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>