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
        <script type="text/javascript" src="js/accountInformation2.js"></script>
        <title>Registrazione Account - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>

    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">


        <form id="registrationForm" class="cmxform" action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Form di Registrazione</h1><br> <br>
                    <input type="hidden" name="page" id="page" value="1">
                    <div>
                        <span id="accountLabel">  Indirizzo Residenza: </span>
                        <input id="viaResidence" class="accountInput" type="text" name="ViaResidenza">
                    </div>

                    <div>
                        <label id="accountLabel"> C.A.P. Residenza: </label>
                        <input id="capResidence" class="accountInput" type="text" name="CapResidenza">
                    </div>

                    <div>
                        <span id="accountLabel">  Numero di telefono: </span>
                        <input id="telephoneNumber" class="accountInput" type="text" name="Telefono">
                    </div>

                    <div>
                        <label id="accountLabel">Numero di cellulare: </label>
                        <input id="cellularNumber" class="accountInput" type="text" name="Cellulare">
                    </div>

                    <div>
                        <span id="accountLabel">  Indirizzo Fax: </span>
                        <input id="fax" class="accountInput" type="text" name="Fax">
                    </div>

                    <div>
                        <label id="accountLabel">Indirizzo email:</label>
                        <input id="email" class="accountInput" type="text" name="Email">
                    </div>

                    <div>
                        <span id="accountLabel">  Comune di Domicilio: </span>
                        <input id="municipalityDomicile" class="accountInput" type="text" name="ComuneDomicilio">
                    </div>

                    <div>
                        <label id="accountLabel">Provincia di Domicilio:</label>
                        <input id="provinceDomicile" class="accountInput" type="text" name="ProvinciaDomicilio">
                    </div>

                    <div>
                        <span id="accountLabel">  Indirizzo Domicilio: </span>
                        <input id="viaDomicile" class="accountInput" type="text" name="ViaDomicilio">
                    </div>
                </div>
                <%--<input type="submit" id="registrationButton" value="Avanti" />--%>

                <input style="width: 300px; margin-left: 7%" type="submit" id="registrationButton" value="Avanti" />
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>