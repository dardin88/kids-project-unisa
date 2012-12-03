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
        <%-- <jsp:include page="/GetAccount" /> --%>
         
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
              
                    <form id="information" method="post" action="accountInformation2.jsp"/>
            <table>                       
                       
                    Cap Domicilio : <input id="capDomicilie" class="registrationField" type="text" name="CapDomicilio"value="${CapDomicilio}"readonly="true">
                    Titolo di Studio : <input id="qualification" class="registrationField" type="text" name="TitoloStudio" value="${TitoloDiStudio}"readonly="true">
                    Tipo di Account <input id="accountType" class="registrationField" type="text" name="TipoAccount" value="Genitore"value="${TipoAccount}"readonly="true">
                    Situazione Familiaria : <input id="familySituation" class="registrationField" type="text" name="SituazioneFamiliare "value="${SituazioneFamiliare}"readonly="true">
                    Reddito : <input id="income" class="registrationField" type="text" name="Reddito" value="${Reddito}"readonly="true">
                    Scadenza Contratto : <input id="contractExpirationDate" class="registrationField" type="text" name="ScadenzaContratto"value="${ScadenzaContratto}"readonly="true">
                    Facoltà <input id="faculty" class="registrationField" type="text" name="Facolta"value="${Facolta}">
                    Data di Immatricolazione : <input id="registrationDate" class="registrationField" type="text" name="DataRegistrazione"value="${dataRegistrazione}" readonly="true">
                    Tipologia Genitore : <input id="typeParent" class="registrationField" type="text" name="TipoGenitore" value="${TipoGenitore}" readonly="true"> 
                    Matricola : <input type="text" id="register" name="Matricola"  class="registrationField" />
                    
                      
            </table>                                
                       
                        <input type="submit" name="Indietro" value="Indietro"/>
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
                
        
    
                

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
