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
        <script type="text/javascript" src="js/accountModify2.js"></script>
        <title>Registrazione Account - Kids Project</title>
        
        <jsp:include page="/GetAccount" /> 
        
        <script type="text/javascript">
                     
            $(document).ready(function() {
                initializeRegistrationFields();
            });
            
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">


        <h1  id="titleReg" align="center">Form di Registrazione</h1>

        <form id="registrationForm" class="cmxform" action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <p class="formp">
                        
                        <input id="id" class="registrationField" type="text" name="pag"  value="${id}">
                       
                       
                        <label> <h3> Indirizzo Residenza: </h3> </label> 
                        <input id="viaResidence" class="registrationField" type="text" name="ViaResidenza"  value="${ViaResidenza}">
                    </p>
                    <p class="formp">

                        <label><h3> C.A.P. Residenza : </h3> </label>
                        <input id="capResidence" class="registrationField" type="text" name="CapResidenza" value="${CapResidenza}">
                    </p>
                    <p class="formp">

                        <label><h3>Numero di telefono: </h3></label>
                        <input id="telephoneNumber" class="registrationField" type="text" name="Telefono" value="${Telefono}">
                    </p>
                    <p class="formp">

                        <label><h3>Numero di cellulare:</h3></label>
                        <input id="cellularNumber" class="registrationField" type="text" name="Cellulare" value="${Cellulare}">
                    </p>
                    <p class="formp">

                        <label><h3> Indirizzo Fax: </h3></label> 
                        <input id="fax" class="registrationField" type="text" name="Fax" value="${Fax}">
                    </p>
                    <p class="formp">

                        <label><h3> Indirizzo email:</h3> </label>
                        <input id="email" class="registrationField" type="text" name="Email" value="${Email}">
                    </p>
                    <p class="formp">

                        <label><h3>Comune di Domicilio:</h3></label>
                        <input id="municipalityDomicile" class="registrationField" type="text" name="ComuneDomicilio" value="${ComuneDomicilio}">
                    </p>
                    <p class="formp">

                        <label><h3> Provincia di Domicilio: </h3></label>
                        <input id="provinceDomicile" class="registrationField" type="text" name="ProvinciaDomicilio" value="${ProvinciaResidenza}">
                    </p>
                    <p class="formp">

                        <label><h3> Indirizzo Domicilio: </h3></label>
                        <input id="viaDomicile" class="registrationField" type="text" name="ViaDomicilio" value="${ViaDomicilio}" >
                    </p>

                    <input type="submit" name="registrationButton" id="registrationButton" value="Avanti" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>