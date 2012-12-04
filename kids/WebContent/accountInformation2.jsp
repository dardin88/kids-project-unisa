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
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/traineeInformation.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation2.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
        <jsp:include page="/GetAccount" />
         
           <script type="text/javascript">
            $(document).ready(function() {
               
                initializeLinksManager2();
            });
        </script>
        <%@include file="header.jsp" %>
    </head>
    
    <body id="bodyRegistration">
        
       
     
            <h1  id="title" align="center">Dati Genitore</h1>
              <form id="registration"  method="post" action="accountInformation3.jsp">
                     <fieldset id="registrationFieldSet">
                     <div  id="artefactsManagement">
                
                    <input type="text" style="visibility:hidden" id="id" name="id" value="${id}" readonly="true">
                    <p class="formp">
                        <label><h3>Indirizzo Residenza:</h3></label> <input id="viaResidence" class="registrationField" type="text" name="ViaResidenza" value="${ViaResidenza}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3>C.A.P. Residenza :</h3></label> <input id="capResidence" class="registrationField" type="text" name="CapResidenza" value="${CapResidenza}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Numero di telefono:</h3></label> <input id="telephoneNumber" class="registrationField" type="text" name="Telefono" value="${Telefono}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Numero di cellulare:</h3></label> <input id="cellularNumber" class="registrationField" type="text" name="Cellulare" value="${Cellulare}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Fax:</h3></label> <input id="fax" class="registrationField" type="text" name="Fax" value="${Fax}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3>Indirizzo email: </h3></label> <input id="email" class="registrationField" type="text" name="Email" value="${Email}" readonly="true"> 
                    </p>
                    
                    <p class="formp">
                        <label> <h3> Comune di Domicilio : </h3></label> <input id="municipalityDomicilie" class="registrationField" type="text" name="ComuneDomicilio" value="${ComuneDomicilio}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label> <h3>Provincia di Domicilio:</h3> </label> <input id="provinceDomicilie" class="registrationField" type="text" name="ProvinciaDomicilio" value="${ProvinciaResidenza}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label><h3> Indirizzo Domicilio:</h3> </label> <input id="viaDomicile" class="registrationField" type="text" name="ViaDomicilio" value="${ViaDomicilio}" readonly="true">
                    </p>
            
                    <input type="submit" name="Avanti2" value="Avanti" id="modifyButton" onclick="showAccount(document.getElementById('id').value)"></input>    
                     </div>
                    </fieldset>
                    </form>
                    
                        
                    <input type="button" name="Indietro" value="Indietro" onclick="back(document.getElementById('id').value)"/></input>
                        
                    
                  
                  
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
                <div id="removeAccount" title="Titolo della finestra">
                      <P> <h3> Attenzione! </h3> <br> vuoi davvero eliminare questo Account?<BR>
                      N.B. una volta confermato la scelta non potrai più tornare indietro!</P>
                </div>
                
        
    
                

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
