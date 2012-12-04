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


        <h1  id="titleReg" align="center">Form di Registrazione</h1>

        <form id="registrationForm" class="cmxform"  action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <input type="text" id="register" name="Matricola" class="registrationField" style="visibility: hidden">
                    <p class="formp">
                        <label> <h3>Nome: </h3> </label>
                        <input id="accountName" class="registrationField" type="text" name="Nome" size="50%">
                    </p>
                    <p class="formp">
                        <label> <h3>Cognome:  </h3> </label>
                        <input id="accountSurname" class="registrationField"type="text" name="Cognome">
                    </p>
                    <p class="formp">
                        <label> <h3>Data di nascita:  </h3> </label>
                        <input id="dateOfBirth" class="registrationField" type="text" name="DataNascita">
                    </p>
                    <p class="formp">
                        <label> <h3>Comune di nascita:  </h3> </label>
                        <input id="placeOfBirth" class="registrationField" type="text" name="ComuneNascita">
                    </p>
                    <p class="formp">
                        <label> <h3>Codice Fiscale:  </h3> </label>
                        <input id="taxCode" class="registrationField" type="text" name="CodiceFiscale">
                    </p>
                    <p class="formp">
                        <label> <h3>Cittadinanza:  </h3> </label>
                        <input id="citizenship" class="registrationField" type="text" name="Cittadinanza">
                    </p>
                    <p class="formp">
                        <label> <h3>Comune di residenza:  </h3> </label>
                        <input id="municipalityResidence" class="registrationField" type="text" name="ComuneResidenza">
                    </p>
                    <p class="formp">
                        <label> <h3>Provincia Residenza:  </h3> </label>
                        <input id="provinceResidence" class="registrationField" type="text" name="ProvinciaResidenza">
                    </p>
                    <p class="formp">
                        <label> <h3>Indirizzo Residenza: </h3> </label>
                        <input id="viaResidence" class="registrationField" type="text" name="IndirizzoResidenza">
                    </p>
                    <input type="submit" name="registrationButton" id="registrationButton" value="Avanti" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>