<%-- 
    Document   : secretarySurveyManagement
    Created on : 7-dic-2012, 17.58.34 
    Author     : Felice D'Avino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.user.getAccountType()!='Segreteria'}">
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
        <link rel="stylesheet" type="text/css" href="css/TableTools.css">

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/TableTools.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/surveyShowTable.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                initializeLinksManager();
                activePage();
            });
        </script>
    </head>
    <div class="removeSurvey" id="removeSurveyWindow" title="Rimuovi Questionario" >
        <h3 style="text-align:center; margin-top: 10px; margin-bottom: 10px">Vuoi rimuovere definitivamente questo questionario?</h3>
        <input type="button" class="buttonRemove" id="removeSurveyButton" value="Ok" />
        <input type="button" class="buttonRemove" id="notRemoveSurveyButton" value="Annulla" />
    </div>

    <body>
        <%@include file="header.jsp" %>
        <div id="surveyManagement">
            <div id="surveyTabGroup">
                <ul>
                    <li><a href="#webapp">Crea e Gestisci Questionari</a></li>
                    <li><a href="#questionariTable">Visualizza Questionari Proposti</a></li>
                </ul>
                <div id="webapp">
                    <iframe src= "https://www.jotformeu.com"></iframe>
                    <div>
                        <p>
                            Affinch&egrave i genitori possano compilare il questionario, &egrave necessario fare click sulla scheda relativa a 
                            <i>"Setup & Embed"</i> e successivamente fare click sulla icona rappresentante <i>"Embed Forms"</i>, copiare così l'indirizzo all'interno della casella sottostante.
                        </p>
                        <form align="center"  class ="cmxform" action= "#" method= "POST">
                            <input id="intext" type= "text"></input>
                            <button  id = "inSub" type="submit" name= "sub" onclick="addSurvey()" >Invia</button>
                        </form>
                        <p>
                            Pu&ograve essere necessario attuare politiche per le quali un genitore non può rispondere più d'una volta al questionario.
                            Ci&ograve &egrave possibile: basta cliccare sulla icona dell'ingranaggio <i>"Preferences"</i> e scegliere l'opzione <i>"Form limits"</i>, da qu&igrave scegliere <i>"Controllo Strict</i>
                        </p>
                    </div>
                </div>
                <div id="questionariTable">
                    <table id="linkTable">
                        <thead>
                            <tr>
                                <th>ID Questionario</th>
                                <th>Link</th>   
                                <th>Operazioni</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
