<%-- 
    Document   : newsGenitorePage
    Created on : 26-nov-2012, 18.25.29
    Author     : francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/template.css" >       
        <script type="text/javascript" src="js/functions.js"></script>       
        <title>Gestione News - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
            });
        </script>
        <title>Visualizza News</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="linksManagement">
            <h1 style="font-size: 35px;text-align: center;"> Benvenuto nella vostra sezione,nel menù a sinistra avete le vostre scelte! </h1>
            
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
