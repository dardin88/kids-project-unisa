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
              <form id="information" method="post" action="accountInformation3.jsp">
            <table>      
                        
                    Indirizzo Residenza: <input id="viaResidence" class="registrationField" type="text" name="ViaResidenza" value="${ViaResidenza}" readonly="true">
                    C.A.P. Residenza : <input id="capResidence" class="registrationField" type="text" name="CapResidenza" value="${CapResidenza}" readonly="true">
                    Numero di telefono: <input id="telephoneNumber" class="registrationField" type="text" name="Telefono" value="${Telefono}" readonly="true">
                    Numero di cellulare: <input id="cellularNumber" class="registrationField" type="text" name="Cellulare" value="${Cellulare}" readonly="true">
                    Fax: <input id="fax" class="registrationField" type="text" name="Fax" value="${fax}" readonly="true">
                    Indirizzo email: <input id="email" class="registrationField" type="text" name="Email" value="${email}" readonly="true"> 
                    Comune di Domicilio:<input id="municipalityDomicilie" class="registrationField" type="text" name="ComuneDomicilio" value="${ComuneDomicilio}" readonly="true">
                    Provincia di Domicilio:<input id="provinceDomicilie" class="registrationField" type="text" name="ProvinciaDomicilio" value="${ComuneResidenza}" readonly="true">
                    Indirizzo Domicilio:<input id="viaDomicile" class="registrationField" type="text" name="ViaDomicilio" value="${ViaDomicilio}" readonly="true">
                   
            </table>  
                    <input type="submit" name="Avanti2" value="Avanti" id="modifyButton"></input>                     
                        </form>
                        <form id="information" method="post" action="accountInformation.jsp">
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
