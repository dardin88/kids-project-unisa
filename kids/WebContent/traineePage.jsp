<%-- 
    Document   : traineePage
    Created on : 23-nov-2012, 10.56.39
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Tirocinante'}">
    <c:redirect url="index.jsp" />
</c:if>
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
        <title>Pagina Tirocinante</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="description" style="padding-top:20px;padding-left:20px;">
            <p style="font-size: 30px;">Questa &egrave la pagina del Tirocinante</p>
            <p style="font-size: 30px;">Il Tirocinante pu&ograve:</p>
            <p style="font-size: 30px;">1)visualizzare il proprio registro delle attivit&agrave</p>
            <p style="font-size: 30px;">2)rispondere alle convocazioni</p>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
