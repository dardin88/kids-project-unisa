<%-- 
    Document   : classe
    Created on : 23-nov-2012, 14.57.05
    Author     : tonino
--%>

<%@page import="it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild"%>
<%@page import="it.unisa.kids.accessManagement.accountManagement.Account"%>
<%@page import="java.util.*"%>
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
        <script type="text/javascript" src="js/classeInsert.js"></script>
        <title>Registrazione Account - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeRegistrationFields();
            });
        </script>
    </head>
    <%@include file="header.jsp"%>
    <body id="bodyRegistration">

        <form id="registrationForm" class="cmxform"  action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <h1  style="font-weight: bold; font-size: 30pt"id="titleReg" align="center">Form di Creazione Classe</h1><br>
                    <input type="hidden" id="register" name="Matricola" class="registrationField" >
                    <table style="margin-left: 5%; font-weight: bold; font-size: 10pt; float: left">
                        <tr><td> Nome:
                            <td> <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="name" class="registrationField" type="text" name="Nome" size="50%">
                                </p>
                        <tr><td> <p class="formp">
                                    <input style="margin-left: 2%; width: 250px" id="educator" class="registrationField" type="checkbox" name="Stato" size="50%" value="AAA">
                                </p>
                        <tr><td> <p class="formp">
                                    <input id="state" class="registrationField" type="text" name="Stato" size="50%" value="bozza">
                                </p>

                                <%List<Account> listaacc = (ArrayList<Account>) session.getAttribute("ListaDoc");%>   
                                <select id="listaDoc" name="lista1" size="5" multiple="multiple">      
                                    <option value="">- Seleziona Docenti -</option>   
                                    <%
                                        for (int x = 0; x < listaacc.size(); x++) {
                                    %>                                                   
                                    <option value="<%=x%>"><%= listaacc.get(x).getNameUser()%></option>
                                    <%
                                        }
                                    %>

                                    <%-- nella servlet devo prelevarli con:
                                          request.getParameterValues("listaDoc") returns an array of all submitted values.--%>





                    </table>
                </div>
                <input style="width: 300px; margin-left: 7%" type="submit" name="registrationButton" id="registrationButton" value="Salva Bozza" />
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>