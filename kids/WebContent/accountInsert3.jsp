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
        <script type="text/javascript" src="js/accountInformation3.js"></script>
        <title>Registrazione Account - Kids Project</title>
         <jsp:include page="/NicknamePassword" />
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
                $("#registrationDate").datepicker({dateFormat:'yy-mm-dd'});
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <form id="registrationForm" class="cmxform" action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">

                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Form di Registrazione</h1><br> <br>

                    <div>
                        <span id="accountLabel">  Cap Domicilio: </span>
                        <input id="capDomicile" class="accountInput" type="text" name="CapDomicilio">
                    </div>

                    <div>
                        <label id="accountLabel"> Titolo di Studio: </label>
                        <input id="qualification" class="accountInput" type="text" name="TitoloStudio">                  
                    </div>

                    <div>
                        <span id="accountLabel">  Tipo di Account: </span>
                        <input id="typeAccount" class="accountInput" type="text" name="typeAccount" value="TipoAccount">
                    </div>

                    <div>
                        <label id="accountLabel"> Situazione Familiaria: </label>
                        <input id="familySituation" class="accountInput" type="text" name="SituazioneFamiliaria">
                    </div>

                    <div>
                        <span id="accountLabel">  Reddito: </span>
                        <input id="income" class="accountInput" type="text" name="Reddito">
                    </div>

                    <div>
                        <label id="accountLabel"> Scadenza Contratto: </label>
                        <input id="contractExpirationDate" class="accountInput" type="text" name="ScadenzaContratto">
                    </div>

                    <div>
                        <span id="accountLabel">  Facoltà: </span>
                        <input id="faculty" class="accountInput" type="text" name="Facolta">
                    </div>

                    <div>
                        <label id="accountLabel"> Data di Immatricolazione: </label>
                        <input id="registrationDate" class="accountInput" type="text" name="DataRegistrazione">
                    </div>

                    <div>
                        <label id="accountLabel"> Tipologia Genitore: </label>
                        <input id="typeParent" class="accountInput" type="text" name="TipoAccount" /> 
                    </div>
                    
                    <input id="password" class="registrationField" type="text" name="Password" value="${Password}" style="visibility: hidden"> 
                    <input id="nickname" class="registrationField" type="text" name="Nickname" value="${Nickname}" style="visibility: hidden"> 
                </div>
                <%--<input type="submit" id="registrationButton" value="Completa Registrazione" />--%>
                
                <input style="width: 300px; margin-left: 7%" type="submit" id="registrationButton" value="Completa Registrazione" />

            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
