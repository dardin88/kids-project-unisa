<%-- 
    Document   : account
    Created on : 23-nov-2012, 14.57.05
    Author     : Gianmarco
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
                    
                      <input id="id" class="registrationField" type="text" name="pag"  value="${id}">
                      
                    <p class="formp">
                        <label><h3>Cap Domicilio :</h3>  </label>
                        <input id="capDomicile" class="registrationField" type="text" name="CapDomicilio"  value="${CapDomicilio}">
                    </p>
                    
                    <p class="formp">
                        <label><h3> Titolo di Studio :</h3> </label>  
                        <input id="qualification" class="registrationField" type="text" name="TitoloStudio" value="${TitoloStudio}">                   
                    </p>
                    
                    <p class="formp">
                        <label><h3>Tipo di Account :</h3> </label>
                        <input id="typeAccount" class="registrationField" type="text" name="typeAccount"  value="${TipoAccount}">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Situazione Familiaria : </h3></label>
                        <input id="familySituation" class="registrationField" type="text" name="SituazioneFamiliaria"  value="${SituazioneFamiliare}">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Reddito :</h3> </label>  
                        <input id="income" class="registrationField" type="text" name="Reddito" value="${Reddito}">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Scadenza Contratto :</h3></label> 
                        <input id="contractExpirationDate" class="registrationField" type="text" name="ScadenzaContratto" value="${ScadenzaContratto}">
                    </p>
                    
                    <p class="formp">
                        <label><h3> Facoltà:</h3> </label> 
                        <input id="faculty" class="registrationField" type="text" name="Facolta" value="${Facolta}">
                    </p>
                    
                    <p class="formp">
                        <label><h3> Data di Immatricolazione :</h3> </label> 
                        <input id="registrationDate" class="registrationField" type="text" name="DataRegistrazione"  value="${DataIscrizione}">
                    </p>
                    
                    <p class="formp">
                        <label><h3> Tipologia Genitore :</h3></label>
                        <input id="typeParent" class="registrationField" type="text" name="TipoAccount" value="${TipoGenitore}" /> 
                    </p>
                    
                    <input type="submit" id="registrationButton" value="Completa Registrazione" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
