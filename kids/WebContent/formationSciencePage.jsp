<%-- 
    Document   : officeDelegatePage
    Created on : 16-nov-2012, 14.16.11
    Author     : Marco Moretti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType()!='Delegato scienze della formazione'}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/formationSciencePage.js"></script>

        <link rel="stylesheet" type="text/css" href="css/template.css" >
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                messageDialog();

            });
        </script>
    </head>
    <c:if test="${requestScope.message!=null}">
        <div id="confirm" title="Message" style="display: inline">
            <form id="confirmForm" class="cmxform" method="post" action="formationSciencePage.jsp">
                <fieldset>
                    <p class="formp">
                        <label class="requirementLabel">${requestScope.message}</label>
                    </p>
                    <p class="formp">
                        <input type="submit" class="confirmButton" id="confirmButton" value="ok"/>

                    </p>
                </fieldset>
            </form>
        </div>
    </c:if>
    <body>
        <%@include file="header.jsp" %>
        <div id="description" style="padding-top:20px;padding-left:20px;">
            <p style="font-size: 30px;">Questa &egrave la pagina del Ufficio Scienze Della Formazione</p>
            <p style="font-size: 30px;">L'ufficio scienze della formazione pu&ograve:</p>
            <p style="font-size: 30px;">1)Inserire , modificare e eliminare i Tirocinanti</p>
            <p style="font-size: 30px;">2)contattare i tirocinanti</p>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
