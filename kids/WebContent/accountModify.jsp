<%-- 
    Document   : accountInformation
    Created on : 27-nov-2012, 16.08.07
    Author     : Gianmarco
--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <%--<jsp:include page="/GetAccount" />--%>
         
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
                          
                       
                        <form id="information" method="post" action="ModifyAccount">
                            
                        Matricola*<input id="matricola" type="text" name="Matricola" value="${Matricola}" readonly="true">
                        <input id="id" type="text" name="Id" value="${Id}" style="visibility: hidden">
                        Nome*<input type="text" name="Nome" id="Nome" value="${Nome}" >
                        Cognome*<input type="text" name="Cognome" id="Cognome" value="${Cognome}">
                        Codice Fiscale*<input type="text" name="CodiceFiscale" id="CodiceFiscale" value="${CodiceFiscale}">
                        Data di nascita*<input type="text" name="DataNascita" id="DataNascita" value="${DataNascita}">
                        Comune di nascita*<input type="text" name="CittaNascita" id="ComuneNascita" value="${ComuneNascita}">
                        Comune di residenza*<input type="text" name="CittaResidenza" id="ComuneResidenza" value="${ComuneResidenza}">
                        Provincia di Residenza*<input type="text" name="ProvinciaResidenza" id="capResidenza" value="${ProvinciaResidenza}">
                        Indirizzo di Residenza*<input type="text" name="IndirizzoResidenza" id="IndirizzoResidenza" value="${IndirizzoResindeza}">
                        Cittadinanza</td><td><input type="text" name="Cittadinanza" id="Cittadinanza" value="${Cittadinanza}" >
                        <input type="submit" name="Modifica" value="Modifica" id="modifyButton"></input>
                        </form>
                        <form method="post" action="accountModify2">
                            <input type="submit" name="Avanti" value="Avanti"></input>
                        </form>
                        
                     
 

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
