<%-- 
    Document   : classe
    Created on : 3-dic-2012, 13.07.01
    Author     : tonino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/classe.js"></script>
        <title>Classe - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeClassFields();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <body id="bodyRegistration">
        
        <h1  id="titleReg" align="center">Classi</h1>
        
        <div id="linksManagement">
                <h1 style="font-size: 35px;text-align: center;"> Classi </h1>
                
                <input type="text" id="state"  name="state"  />
                <input type="text" id="name"  name="name"/>
                <input type="button" name="ricarica" id="ricarica" value="ricerca" onclick="search()"/>
                
                <table id="accountsTable" style="width:95%;">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Stato</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <form name="insertAccount" method="post" action="classInsert.jsp" >
                     <input type="submit" id="addLinkButton" value="Inserisci Classe" />
                </form>
                
                
            </div>
      <div id="removeAccountWindow" title="Rimuovi Classe" style="display: inline">
        <form id="removeAccountForm" class="cmxform" method="post" action="">
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
