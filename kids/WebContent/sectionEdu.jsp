<%-- 
    Document   : sectionEdu
    Created on : 18-dic-2012, 10.09.30
    Author     : navi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <link rel="stylesheet" type="text/css" href="css/TableTools.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/TableTools.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/sectionEdu.js"></script>

        <title>Gestione Programma Educativo Sezione - Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializeSectionEduPage();
            });
        </script>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <div id="sectionEduManagement">
            <div id="sectionEduTabs">
                <ul>
                    <jsp:include page="/GetClassTabs"></jsp:include>
                </ul>

                <jsp:include page="/GetSectionEduClassDivs"></jsp:include>

            </div>
            <div style="padding-top: 20px;">
                <h1>Commenti</h1>
                <%@include file="commentEdu.jsp" %>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
