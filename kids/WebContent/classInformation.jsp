<%-- 
    Document   : classe
    Created on : 27-nov-2012, 16.08.07
    Author     : tonino

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css" />
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>

        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/class.js"></script>
        <title> Kids </title>
        <jsp:include page="/GetClass"/> 
        <script type="text/javascript">
            $(document).ready(function() {
                initializeChildrenInformationFields();
            });
        </script>
    </head>
    <div id="removeClassWindow" title="Rimuovi Classe" style="display: inline">
        <form id="removeClassForm" class="cmxform" method="post" action="">
            <fieldset>
                <p class="formp">
                    <label class="requirementLabel">Sei sicuro di voler eliminare questa classe?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmRemoveButton" id="confirmRemoveLinkButton" value="Si"/>
                    <input type="button" class="notConfirmRemoveButton" id="notConfirmRemoveLinkButton" value="No"/>
                </p>
            </fieldset>
        </form>
    </div>
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">
        <%
            String cercamiNeiSogni = "information";
            session.setAttribute("cercamiNeiSogni", cercamiNeiSogni);
        %>
        <input type="hidden" id="classId" name="classId" value="${id}" >
        <div  id="artefactsManagement" >
            <h1  style="text-align: center; font-size: 30pt; margin-bottom: 5%" >Dati Classe</h1>  
            <div class="classInformationDiv">
                <label class="classInformationTitle">Nome classe: </label>
                <label class="classInformationLabel">${Nome}</label>
            </div>
            <div class="classInformationDiv">
                <label class="classInformationTitle">Stato classe: </label>
                <label class="classInformationLabel">${Stato}</label>
            </div>
            <div id="childrenTableId">
                <table id="childrenTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Nome bambino</th>
                            <th>Cognome bambino</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div id="educatorTableId">
                <table id="educatorTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Nome educatore</th>
                            <th>Cognome educatore</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <input class="classButton" type="button" value="Indietro" id="backClassButton" onclick="window.location.replace('class.jsp');"/>
        <%@include file="footer.jsp" %>

    </body>
</html>
