<%-- 
    Document   : renunciation
    Created on : 09-dec-2012, 21.00.23
    Author     : Lauri Giuseppe Giovanni
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Genitore'} && ${sessionScope.user.getAccountType()!='Segreteria'}">
        <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/renunciationManager.js"></script>
        <script type="text/javascript" src="js/renunciationTables.js"></script>
        
        <title>Classification Management</title>
    </head>
    <script type="text/javascript">
        $(document).ready(function() {
            activePage();
            initRenunciationPage();
            
            createTableClassification();
        });
        
    </script>
    
    <body>
        <%@include file="header.jsp" %>
         <br>
        <div id="renunciationManagement">
            <div id="renunciationContent">
                <h1 style="font-size: 35px;text-align: center;">Gestione Domande d'Iscrizione</h1>

                <%--
                <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
                    <div>
                        <input type="button" id="newRegistrationChildButton" value="Inserisci Domanda di Iscrizione"/>
                    </div>
                </c:if>
                --%>


                <table id="showRenunciationTable">
                    <thead>
                        <tr>
                            <th>Cognome</th>
                            <th>Nome</th>
                            <th>Operazioni</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>    

            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>

</html>