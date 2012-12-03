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
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
        <%-- <jsp:include page="/GetAccount" /> --%>
         
           <script type="text/javascript">
            $(document).ready(function() {
                //initializeLinksManager();
                initializeLinksManager2();
            });
        </script>
    </head>
    <body>
        
       <%@include file="header.jsp" %>
        <div  style="padding-top: 20px;padding-left: 240px;">
            <h1  id="title" align="center">Dati Genitore</h1>
            <form method="post" action="accountInformation2.jsp">
            <table>     
                        <input type="text" id="id" name="id" value="${id}" style="visibility: hidden">
                        Matricola*<input id="matricola" type="text" name="Matricola" value="${Matricola}" readonly="true">
                        Nome*<input type="text" name="Nome" id="Nome" value="${Nome}"readonly="true" >
                        Cognome*<input type="text" name="Cognome" id="Cognome" value="${Cognome}"readonly="true">
                        Codice Fiscale*<input type="text" name="CodiceFiscale" id="CodiceFiscale" value="${CodiceFiscale}"readonly="true">
                        Data di nascita*<input type="text" name="DataNascita" id="DataNascita" value="${DataNascita}"readonly="true">
                        Comune di nascita*<input type="text" name="CittaNascita" id="ComuneNascita" value="${ComuneNascita}"readonly="true">
                        Comune di residenza*<input type="text" name="CittaResidenza" id="ComuneResidenza" value="${ComuneResidenza}"readonly="true">
                        Provincia di Residenza*<input type="text" name="ProvinciaResidenza" id="capResidenza" value="${ProvinciaResidenza}"readonly="true">
                        Indirizzo di Residenza*<input type="text" name="IndirizzoResidenza" id="IndirizzoResidenza" value="${ViaResidenza}"readonly="true">
                        Cittadinanza</td><td><input type="text" name="Cittadinanza" id="Cittadinanza" value="${Cittadinanza}"readonly="true" >
                        
            </table>  
                        <input type="submit" name="Avanti1" value="Avanti" id="avantiButton"></input>
                        
                        
                        </form>
                    <form id="information2" method="post" action="accountModify.jsp" />
                    <input type="button" name="Modifica" value="Modifica" id="modifyButton" onclick="modifyAccount()" ></input>
                        </form>
                        <input type="button" name="Elimina" value="Elimina" id="eliminaButton" onclick="removeAccount()"></input>
                    
                  
                  
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
                <div id="removeAccount" title="Rimozione">
                      <P> <h3> Attenzione! </h3> <br> vuoi davvero eliminare questo Account?<BR>
                      N.B. una volta confermato la scelta non potrai più tornare indietro!</P>
                </div>
                
        
    
                

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
