<%-- 
Document   : class
Created on : 3-dic-2012, 13.07.01
Author     : tonino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user.getAccountType()!='Responsabile Asilo' && sessionScope.user.getAccountType()!='Delegato del rettore'}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/class.js"></script>
        <title>Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeClassFields();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div id="linksManagement">
            <div id="classFilter">
                <label>Nome</label>
                <input type="text" id="className"  name="className"/>
                <label>Stato</label>
                <select id="classState" name="classState">
                    <option></option>
                    <option>bozza</option>
                    <option>confermata</option>
                    <option>revisione</option>
                    <option>sottomessa</option>
                </select>
                <input type="button" name="ricerca" id="ricerca" value="Ricerca" onclick="search()"/>
            </div>
            <table id="classTable" style="width:95%;">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Stato</th>
                        <th>Operazione</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <c:if test="${sessionScope.user.getAccountType()=='Responsabile Asilo'}">
                <form name="insertClass" method="post" action="classInsert.jsp" >
                    <input type="submit" id="addClassButton" value="Inserisci Classe" />
                </form>
            </c:if>
        </div>
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
        <%@include file="footer.jsp" %>
    </body>
</html>
