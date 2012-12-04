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
        <script type="text/javascript" src="js/accountInformation3.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
        <jsp:include page="/GetAccount" />
         
           <script type="text/javascript">
            $(document).ready(function() {
               
                initializeLinksManager2();
            });
        </script>
    </head>
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">
        
       
        
            <h1  id="title" align="center">Dati Genitore</h1>
            
          
                    <form id="registration"   method="post" action="accountInformation2.jsp"/>
                    <fieldset id="registrationFieldSet">
                    <div  id="artefactsManagement">  
                        
                    <input type="text" style="visibility:hidden" id="id" name="id" value="${id}" readonly="true">
                    
                    <p class="formp">
                        <label> Cap Domicilio : </label> <input id="capDomicile" class="registrationField" type="text" name="CapDomicilio" value="${CapDomicilio}" readonly="true"> 
                    </p>
                    
                    <p class="formp">
                        <label>Titolo di Studio :</label> <input id="qualification" class="registrationField" type="text" name="TitoloStudio" value="${TitoloStudio}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label>Tipo di Account</label> <input id="accountType" class="registrationField" type="text" name="TipoAccount" value="Genitore" value="${TipoAccount}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label>Situazione Familiaria :</label> <input id="familySituation" class="registrationField" type="text" name="SituazioneFamiliare" value="${SituazioneFamiliare}"readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label>Reddito :</label> <input id="income" class="registrationField" type="text" name="Reddito" value="${Reddito}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label>Scadenza Contratto : </label> <input id="contractExpirationDate" class="registrationField" type="text" name="ScadenzaContratto" value="${ScadenzaContratto}"readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label> Facoltà :</label> <input id="faculty" class="registrationField" type="text" name="Facolta" value="${Facolta}">
                    </p>
                    
                    <p class="formp">
                        <label> Data di Immatricolazione : </label> <input id="registrationDate" class="registrationField" type="text" name="DataRegistrazione" value="${DataIscrizione}" readonly="true">
                    </p>
                    
                    <p class="formp">
                        <label> Tipologia Genitore : </label> <input id="typeParent" class="registrationField" type="text" name="TipoGenitore" value="${TipoGenitore}" readonly="true"> 
                    </p>
                    
                      
                                          
                    </div>  
                    </fieldset>
                    <input type="submit" name="Indietro" value="Indietro" onclick="showPartTwoAccount(document.getElementById('id').value)"/>
                        </form>
                    
                  
                  
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
                
        
    
                

       
        <%@include file="footer.jsp" %>

    </body>
</html>
