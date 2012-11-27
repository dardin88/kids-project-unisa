<%-- 
    Document   : communication
    Created on : 24-nov-2012, 20.00.18
    Author     : Elena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/registrationManager.js"></script>
        <title>Comunicazione Bambini - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                initializeCommunicationManager();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="newCommunicationWindow" title="Inserisci Comunicazione" style="display: inline">
        <form id="addCommunicationForm" class="cmxform" method="get" action="">
            <fieldset>
                <div id="artefactsManagement">
                    <p class="formp">
                    <legend> <h3> Tipo: </h3></legend> <br>
                    <ul>
                        <input type="radio" id="typeCommunication" name="typeCommunication" checked> Comunicazione salute
                        <input type="radio" id="typeCommunication" name="typeCommunication"> Comunicazione bisogno
                    </ul> <br>
                    </p>
                    <p class="formp">
                        <label ><h3> idEducatore: </h3></label> <br>
                        <textarea id="idEducatorCommunication" name="idEducatorCommunication" class="textarea" rows="5" cols=120%>
                        </textarea> <br> 
                    </p>
                    <p class="formp">
                        <label ><h3> idBambino: </h3></label> <br>
                        <textarea id="idChildCommunication" name="idChildCommunication" class="textarea" rows="5" cols=120%>
                        </textarea> <br> 
                    </p>
                    <p class="formp">
                        <label ><h3> Descrizione: </h3></label> <br>
                         <textarea id="descriptionCommunication" name="descriptionCommunication" class="textarea" rows="5" cols=120%>
                        </textarea> <br>
                    </p>
                    <p class="formp">
                        <label><h3 style="float: left"> Data: </h3></label>
                        <input type="text" id="dateCommunication" name="dateCommunication">
                    </p>
                    <p class="formp">
                        <label ><h3> Risolvi: </h3></label> <br>
                        <input type="radio" id="solvedCommunication" name="solvedCommunication"> Risolvi
                    </p>
                    <p>
                        <input type="button" id="addCommunicationButton" value="Inserisci Comunicazione" />
                        <input type="button" id="notAddCommunicationButton" value="Annulla" />
                    </p>
                </div>
            </fieldset>
        </form>
    </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
