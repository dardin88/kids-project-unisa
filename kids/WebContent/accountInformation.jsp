<%-- 
    Document   : accountInformation
    Created on : 27-nov-2012, 16.08.07
    Author     : Gianmarco

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Genitore'}">
        <c:redirect url="index.jsp" />
</c:if>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/traineeInformation.css" >
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
        <title> Kids </title>
        <jsp:include page="/GetAccount" /> 

        <script type="text/javascript">
            $(document).ready(function() {
                //initializeLinksManager();
                initializeLinksManager2();
            });
        </script>
    </head>
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">




        <form id="registration" class="cmxform"  method="post" action="accountInformation2.jsp">
            <fieldset id="registrationFieldSet">
                <div  id="artefactsManagement" >
                    <h1  style="text-align: center; font-size: 30pt; margin-bottom: 5%" >Dati Genitore</h1>  
                    <input type="text" id="id" name="id" value="${id}" >
                    <table style="margin-left: 5%; font-weight: bold; font-size: 10pt; float: left">
                        <tr><td> Matricola* 
                            <td> <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="matricola" type="text" name="Matricola" value="${Matricola}" readonly="true">
                                </p> 
                        <tr><td> Nome* <td>
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="Nome" id="Nome" value="${Nome}" readonly="true" >
                                </p>
                        <tr><td> Cognome* <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="Cognome" id="Cognome" value="${Cognome}"readonly="true">
                                </p>
                        <tr><td> Codice Fiscale* <td>       
                                <p class="formp">
                                    <input  style="margin-left: 2%; width: 250px" type="text" name="CodiceFiscale" id="CodiceFiscale" value="${CodiceFiscale}" readonly="true">
                                </p>
                        <tr><td>  Data di nascita*<td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="DataNascita" id="DataNascita" value="${DataNascita}" readonly="true">
                                </p>
                        <tr><td> Comune di nascita* <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="CittaNascita" id="ComuneNascita" value="${ComuneNascita}" readonly="true">
                                </p>
                        <tr><td> Comune di residenza* <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="CittaResidenza" id="ComuneResidenza" value="${ComuneResidenza}" readonly="true">
                                </p>
                        <tr><td> Provincia di Residenza*<td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="ProvinciaResidenza" id="capResidenza" value="${ProvinciaResidenza}"readonly="true">
                                </p>
                        <tr><td> Indirizzo di Residenza* <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="IndirizzoResidenza" id="IndirizzoResidenza" value="${ViaResidenza}" readonly="true">
                                </p>
                        <tr><td> Cittadinanza <td>       
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="Cittadinanza" id="Cittadinanza" value="${Cittadinanza}"readonly="true" >
                                </p>
                    </table>
                   <%-- <p>
                        <img id="logoAccount" src="img/logo.png" style="float: left"> <br>
                        <label style="font-weight: bold; font-size: 30pt"> Kids Project </label>
                    </p>--%>
                </div>
            </fieldset>
            <input style="float: left; width: 150px; margin-right: 2%; margin-left: 2%" type="submit" name="Avanti1" value="Avanti" id="avantiButton" onclick="showPartTwoAccount(document.getElementById('id').value)"></input>


        </form>
        <form id="information2" method="post" action="" >
            <input style="float: left; width: 150px; margin-right: 2%" type="button" name="Modifica" value="Modifica" id="modifyButton" onclick="modifyAccount(document.getElementById('id').value)" > </input>
    </form>
                <input style="width: 150px" type="button" name="Elimina" value="Elimina" id="eliminaButton" onclick="removeAccountParent(document.getElementById('id').value)"></input>



    <%--   <c:if test="${sessionScope.user!=null}">
           <c:if test="${sessionScope.user.getTypeAccount()=='Genitore'}"> 
                   <form method="post" action="index.jsp">
                       <input type="submit" name="Indietro" value="Indietro" id="genericButton"></input>
                   </form>
           </c:if>
           <c:if test="${sessionScope.user.getTypeAccount()=='Admin'}">        
                   <form method="post" action="tableAccount.jsp">
                       <input type="submit" name="Indietro" value="Indietro" id="genericButton"></input>
                   </form>
            </c:if>        
        </c:if>--%>
    <%--rimozione account --%>
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





</div>
<%@include file="footer.jsp" %>

</body>
</html>
