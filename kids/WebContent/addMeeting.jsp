<%-- 
    Document   : insertMeeting.jsp
    Created on : 22-nov-2012
    Author     : Pasquale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
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
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/addMeeting.js"></script>
        <title>Inserisci Riunione - Kids Project</title>
        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                initializeMeetingManager();
            });
        </script>
    </head>
    <div id="addMeetingWindow" title="Inserisci Riunione" style="display: inline">
            <fieldset>
                <p class="formp">
                    <label class="artefactLabel" for="artefact">Vuoi inserire la runione?</label>
                </p>
                <p class="formp">
                    <input type="button" class="confirmAddButton" id="addMeetingButtonSi" value="Si"/>
                    <input type="button" class="notConfirmAddButton" id="addMeetingButtonNo" value="No"/>
                </p>
            </fieldset>
    </div>

    <body>
        <%@include file="header.jsp" %>
        <form id="addMeetingForm" class="cmxform" method="post" action="">
        <div id="artefactsManagement">
            <fieldset class="artefactLabel">
                <label ><h3> Titolo: </h3></label> <br>
                <input id="titleMeeting" name="titleMeeting" type="text" size=50%> <br> <br>
            </fieldset>
            <fieldset class="artefactLabel">
                <label ><h3> Descrizione: </h3></label> <br>
                <textarea id="descriptionMeeting" name="descriptionMeeting" class="textarea" rows="5" cols=120%> 
                </textarea> <br> 
            </fieldset>
            <fieldset class="artefactLabel"> 
                <table> 
                    <tr>
                        <td ><h3> Data: </h3>
                        <td><h3> Ora: </h3>
                    <tr>
                        <td> <input type="text" id="dataMeeting" name="dataMeeting"> <label > (formato gg/mm/aaaa) </label> 
                        <td> <input type="text" id="timeMeeting" name="timeMeeting">
                </table>
            </fieldset>
            <fieldset class="artefactLabel"> 
                <legend> <h3> Tipologia: </h3></legend> <br>
                <ul>
                  <input type="radio" id="typeMeeting" name="typeMeeting" checked> Riunione tra rappresentanti;   <br>
                  <input type="radio" id="typeMeeting" name="typeMeeting"> Riunione bimestrale; <br>
                  <input type="radio" id="typeMeeting" name="typeMeeting"> Riunione Scuola-Famiglia; <br>
                </ul> <br>
            </fieldset>
            <fieldset>
                <input type="button" id="addMeetingButton" value="Inserisci Riunione" />
            </fieldset>
              
            
        </div>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
