<%-- 
    Document   : classe
    Created on : 27-nov-2012, 16.08.07
    Author     : tonino

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/traineeInformation.css" >
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/accountInformation.js"></script>
        <script type="text/javascript" src="js/classe.js"></script>
        <title> Kids </title>

        <jsp:include page="/GetClass"/> 

        <script type="text/javascript">
            $(document).ready(function() {
                //initializeLinksManager();
                initializeLinksManager2();
            });
        </script>
    </head>
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">




        <form id="registration" class="cmxform"  method="post" action="classe.jsp">
            <fieldset id="registrationFieldSet">
                <div  id="artefactsManagement" >
                    <h1  style="text-align: center; font-size: 30pt; margin-bottom: 5%" >Dati Classe</h1>  
                    <input type="hidden" id="id" name="id" value="${id}" >
                    <table style="margin-left: 5%; font-weight: bold; font-size: 10pt; float: left">
                        <tr><td> Nome 
                            <td> <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="Nome" type="text" name="Nome" value="${Nome}" readonly="true">
                                </p> 
                        <tr><td> Stato <td>
                                <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" type="text" name="Stato" id="Stato" value="${Stato}" readonly="true" >
                                </p>
                    </table>
                </div>
            </fieldset>
            <input style="float: left; width: 150px; margin-right: 2%; margin-left: 2%" type="submit" name="Avanti1" value="Avanti" id="avantiButton" onclick="showPartTwoAccount(document.getElementById('id').value)"></input>


        </form>
        <form id="information2" method="post" action="" >
            <input style="float: left; width: 150px; margin-right: 2%" type="button" name="Modifica" value="Modifica" id="modifyButton" onclick="modifyAccount(document.getElementById('id').value)" > </input>
        </form>
        <input style="width: 150px; margin-right: 2%" type="button" name="Elimina" value="Elimina" id="eliminaButton" onclick="removeAccountParent(document.getElementById('id').value)"></input>

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
        <%@include file="footer.jsp" %>

    </body>
</html>
