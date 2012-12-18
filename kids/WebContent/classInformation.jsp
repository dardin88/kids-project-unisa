<%-- 
    Document   : classe
    Created on : 27-nov-2012, 16.08.07
    Author     : tonino
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user.getAccountType()!='Responsabile Asilo' && sessionScope.user.getAccountType()!='Delegato del rettore'}">
    <c:redirect url="index.jsp" />
</c:if>
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
                initializeClassInformationFields();
            });
        </script>
    </head>
    <div id="requestModifyClassWindow" title="Richiedi modifica classe" style="display: inline">
        <form id="requestModifyForm" class="cmxform" method="post" action="">
            <table>
                <tr>
                <p style="text-align: left;" class="formp">
                <td style="text-align: center">
                    <label class="artefactLabel" for="artefactMessaggio">Messaggio </label>
                </td>
                </tr>
                <tr>
                    <td>
                        <textarea id="artefactMessaggio"rows="5" cols="50" name="artefactMessaggio"></textarea>
                    </td>
                    </p>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" class="windowButton" id="requestModifyClassButton2" value="Invia richiesta" onclick="sendMail()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div> 
    <%@include file="header.jsp" %>
    <body id="bodyRegistration">
        <%
            String cercamiNeiSogni = "information";
            session.setAttribute("cercamiNeiSogni", cercamiNeiSogni);
        %>
        <div  id="artefactsManagement" >
            <input type="hidden" id="classId" name="classId" value="${id}" >
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
        <form id="modifyClassForm" class="cmxform"  action="UpdateClassBean" method="post">
            <input class="classButton" type="button" value="Indietro" id="backClassButton" onclick="window.location.replace('class.jsp');"/>
            <c:if test="${sessionScope.user.getAccountType()=='Delegato del rettore'}">
                <c:if test="${Stato == 'sottomessa'}">
                    <input type="hidden" id="classId" name="classId" value="${id}" >
                    <input type="hidden" id="className"  name="className" value="${Nome}"/>
                    <input type="button" id="requestModifyClassButton" name="requestModifyClassButton" class="classButton" value="Richiedi modifica" onclick="requestClassModify()" />
                    <input type="submit" id="acceptedClassButton" name="acceptedClassButton" class="classButton" value="Accetta classe" />
                </c:if>
            </c:if>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
