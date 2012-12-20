<%-- 
    Document   : register.jsp
    Created on : Dec 18, 2012, 10:04:46 AM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType() != 'Responsabile Scientifico'
              && sessionScope.user.getAccountType() != 'Delegato del rettore'
              && sessionScope.user.getAccountType() != 'Coordinatore Psicopedagogico'
              && sessionScope.user.getAccountType() != 'Educatore'
              && sessionScope.user.getAccountType() != 'Genitore'}">
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
        <script type="text/javascript" src="js/register.js"></script>
        <title>Registro</title>
        <script text="text/javascript">
            $(document).ready(function() {
                initializeLinksManager();
                activePage();
            });
        </script>
    </head>
    <div id="informationDailyActivityWindow" title="Informazioni Attivita" style="display: inline;">
        <table>

            <tr><td>Nome</td><td><input id="Nome" type="text" name="Nome" value=""  disabled="true"></td></tr>
            <tr><td>Data</td><td><input type="text" name="Data" id="Data" value="" disabled="true" ></td></tr>
            <tr><td>Educatore</td><td><input type="text" name="Educatore" id="Educatore" value=""disabled="true" ></td></tr>
            <tr><td>Note</td><td><textarea name="Note" id="Note" value=""disabled="true" cols="20" rows="5" style="resize: none" ></textarea></td></tr>



        </table>


    </div>
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
