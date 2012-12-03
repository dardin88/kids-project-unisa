<%-- 
    Document   : accountInformation
    Created on : 27-nov-2012, 16.08.07
    Author     : Gianmarco
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getTypeAccount()!='Genitore'}">
        <c:redirect url="index.jsp" />
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/traineeInformation.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
         <jsp:include page="/GetAccount" />
         
           <script type="text/javascript">
            $(document).ready(function() {
               
                initializeLinksManager();
            });
        </script>
    </head>
    <body>
        
       <%@include file="header.jsp" %>
        <div  style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Dati Genitore</h1>
                          
                        
                   
                        <form id="information" method="post" action="ModifyAccount2">
         
                    Cap Domicilio : <input id="capDomicilie" class="registrationField" type="text" name="CapDomicilio"value="${CapDomicilio}">
                    Titolo di Studio : <input id="qualification" class="registrationField" type="text" name="TitoloStudio"value="${TitoloDiStudio}">
                    Tipo di Account <input id="accountType" class="registrationField" type="text" name="TipoAccoun" value="Genitore"value="${TipoAccount}">
                    Situazione Familiaria : <input id="familySituation" class="registrationField" type="text" name="SituazioneFamiliare" value="${SituazioneFamiliare}">
                    Reddito : <input id="income" class="registrationField" type="text" name="income"value="${Reddito}">
                    Scadenza Contratto : <input id="contractExpirationDate" class="registrationField" type="text" name="ScadenzaContratto" value="${ScadenzaContratto}">
                    Facoltà : <input id="faculty" class="registrationField" type="text" name="Facolta"value="${Facolta}">
                    Data di Immatricolazione : <input id="registrationDate" class="registrationField" type="text" name="DataRegistrazione"value="${DataRegistrazione}" >
                    Tipologia Genitore : <input id="typeParent" class="registrationField" type="text" name="TipoGenitore" value="${TipoGenitore}" > 
                    
                      
                      <input type="submit" name="Avanti2" value="Modifica" id="modifyButton"></input>
                        </form>
                        <form id="back" action="accountModify2" method="post">
                        <input type="submit" name="Indietro" value="Indietro" id="backButton"> </input>
                        </form>
                     
               
 

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>

