<%-- 
    Document   : register.jsp
    Created on : Dec 18, 2012, 10:04:46 AM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">              
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/register.js"></script>
        <title>Registro</title>
        <script text="text/javascript">
            $(document).ready(function() {
                initializeLinksManager();

            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div id="registerManagement">    

            <div id="registerTab">   
                <ul>
                    <jsp:include page="/GetClassTabs"></jsp:include>
                    </ul>
                    
                <jsp:include page="/GetClassDivRegister"></jsp:include>

                </div>
            </div>

        <%@include file="footer.jsp" %>


    </body>
</html>
