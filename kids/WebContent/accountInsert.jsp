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
                $("#dateOfBirth").datepicker({dateFormat:'yy-mm-dd'});
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <form id="registrationForm" class="cmxform"  action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Form di Registrazione</h1><br> <br>
                    <input type="hidden" id="register" name="Matricola" class="registrationField" >

                    <div >
                        <span id="accountLabel">  Nome: </span>
                        <input id="accountName" class="accountInput" type="text" name="Nome" size="50%">
                    </div>

                    <div>
                        <label id="accountLabel"> Cognome: </label>
                        <input id="accountSurname" class="accountInput"type="text" name="Cognome">                    
                    </div>

                    <div>
                        <label id="accountLabel"> Data di nascita: </label>
                        <input id="dateOfBirth" class="accountInput" type="text" name="DataNascita">
                    </div>

                    <div>
                        <label id="accountLabel"> Comune di nascita: </label>
                        <input id="placeOfBirth" class="accountInput" type="text" name="ComuneNascita">
                    </div>

                    <div>
                        <label id="accountLabel"> CodiceFiscale: </label>
                        <input  id="taxCode" class="accountInput" type="text" name="CodiceFiscale">
                    </div>

                    <div>
                        <label id="accountLabel"> Cittadinanza: </label>
                        <input  id="citizenship" class="accountInput" type="text" name="Cittadinanza">
                    </div>

                    <div>
                        <label id="accountLabel"> Comune di residenza: </label>
                        <input  id="municipalityResidence" class="accountInput" type="text" name="ComuneResidenza">
                    </div>

                    <div>
                        <label id="accountLabel"> Provincia Residenza: </label>
                        <input  id="provinceResidence" class="accountInput" type="text" name="ProvinciaResidenza">
                    </div>

                    <div>
                        <label id="accountLabel"> Indirizzo Residenza: </label>
                        <input  id="viaResidence" class="accountInput" type="text" name="IndirizzoResidenza">
                    </div>
                </div>
                <input style="width: 300px; margin-left: 7%" type="submit" name="registrationButton" id="registrationButton" value="Avanti" />
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>