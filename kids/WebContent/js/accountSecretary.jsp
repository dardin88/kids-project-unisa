<%-- 
    Document   : accountSecretary
    Created on : 29-nov-2012, 9.01.50
    Author     : Gianmarco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/account.js"></script>
        <title>Gestione News "Visualizza News"- Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                buildAccountTable();
            });
        </script>
    </head>
<body>
        <%@include file="headerNews.jsp" %>
            <div id="linksManagement">
                <h1 style="font-size: 35px;text-align: center;"> Account </h1>
                
                <input type="text" id="nickname" name="id"  />
                <input type="text" id="name"  name="name"/>
                <input type="text" id="surname" name="surname" />
                <input type="text" id="taxCode" name="taxCode" />
                <input type="text" id="type" name="type"/>
                <input type="button" name="ricarica" id="ricarica" onclick="search()"/>
                
                <table id="accountsTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Matricola</th>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Codice Fiscale</th>
                            <th>Tipo Account</th>
                            <th>Operazione</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <form name="insertAccount" method="post" action="account.jsp" >
                     <input type="submit" id="addLinkButton" value="Inserisci Account" />
                </form>
                
                
            </div>
      
        <%@include file="footer.jsp" %>
    </body>
</html>