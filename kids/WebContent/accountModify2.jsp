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
                       Indirizzo Residenza: <input id="viaResidence" class="registrationField" type="text" name="ViaResidenza" value="${ViaResidenza}" >
                    C.A.P. Residenza : <input id="capResidence" class="registrationField" type="text" name="CapResidenza" value="${CapResidenza}" >
                    Numero di telefono: <input id="telephoneNumber" class="registrationField" type="text" name="Telefono" value="${Telefono}">
                    Numero di cellulare: <input id="cellularNumber" class="registrationField" type="text" name="Cellulare" value="${Cellulare}" >
                    Fax: <input id="fax" class="registrationField" type="text" name="Fax" value="${Fax}" >
                    Indirizzo email: <input id="email" class="registrationField" type="text" name="Email" value="${Email}"> 
                    Comune di Domicilio:<input id="municipalityDomicilie" class="registrationField" type="text" name="ComuneDomicilio" value="${ComuneDomicilio}" >
                    Provincia di Domicilio:<input id="provinceDomicilie" class="registrationField" type="text" name="ProvinciaDomicilio" value="${ComuneResidenza}" >
                    Indirizzo Domicilio:<input id="viaDomicile" class="registrationField" type="text" name="ViaDomicilio" value="${ViaDomicilio}" >
                        <input type="submit" name="Avanti2" value="Modifica" id="modifyButton"></input>
                        </form>
                        <form id="avanti" action="accountModify3" method="post">
                        <input type="submit" name="Avanti" value="Avanti" id="avantiButton"> </input>
                        </form>
                        <form id="back" action="accountModify" method="post">
                        <input type="submit" name="Indietro" value="Indietro" id="backButton"> </input>
                        </form>
                     
               
 

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
