<%-- 
    Document   : newsDelegatoPage
    Created on : 26-nov-2012, 22.28.57
    Author     : francesco
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
        <script type="text/javascript" src="js/newsShowTable.js"></script>
        <title>Gestione News - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeLinksManager();
            });
        </script>
    </head>
    <body>
        <%@include file="headerNews.jsp" %>
        <div id="linksManagement">
            <h1 style="font-size: 35px;text-align: center;"> Benvenuto nella vostra sezione,nel menù a sinistra avete le vostre scelte! </h1>
          
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
