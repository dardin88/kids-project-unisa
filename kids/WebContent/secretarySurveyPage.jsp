<%-- 
    Document   : secretarySurveyManagement
    Created on : 7-dic-2012, 17.58.34 
    Author     : felice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
    <c:if test="${sessionScope.user==null}">
        <c:redirect url="index.jsp" />
    </c:if>
--%>

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

        <title>Gestione Questionari Valutazione - Kids</title>
        
        <script type = "text/javascript">
	function showD() {
		if(document.getElementById("intext").value.length == 0) {
			alert("Non è stato inserito nulla. Prego di riprovare"); 
			return false;
		}
		else { 
			alert("Grazie! Il questionario è stato aggiunto alla lista e sarà  visibile dai genitori."); 
			return true;
		}
	}
        </script>
    
        <script>
            $(function() {
                 $( "#surveyTabGroup" ).tabs();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
<%--
<c:if test="${sessionScope.user.getAccountType()=='Segreteria'}" >
            <input type="button" id="addLinkButton" value="Gestisci Questionari" />               
        </c:if>
--%>
     

        <div id="surveyManagement">
            <h1>Gestione Questionari Valutazione</h1>

   
            <%-- blocco div delle varie funzioni della gestione questionari --%>
            <div id="generalSurveySection">
                <h2>Operazioni</h2>
               
                <div id="surveyTabGroup">
                    <ul>
                        <li><a href="#webapp"><span class="manSurvTab">Crea e Gestisci Questionari</span></a></li>
                       <!-- <li><a href=""><span class="showSurvTab">Visualizza Tabella Questionari-Genitore</span></a></li>   -->
                      
                    </ul>
                    
                    <div id = "webapp">
                        <iframe src= "https://www.jotformeu.com"></iframe>
                    
                     <div>
                        <p>JotForm &egrave un ottimo servizio di creazione e gestione di questionari compilabili online. 
                           Purtroppo per&ograve, per condividere un questionario, affinch&egrave i genitori possano compilarlo, &egrave necessario fare click sulla scheda relativa a 
                           <i>"Setup & Embed"</i> e successivamente fare click sulla icona rappresentante <i>"Embed Forms"</i>, copiare così l'indirizzo all'interno della casella sottostante.</p>
            
                        <form align="center"  class ="cmxform" action= "#" method= "POST">
		                <input id="intext" type= "text"></input>
		                <button  id = "inSub" type="submit" name= "sub" onclick="showD()" >Invia</button>
                         </form>
        
                         <p>Pu&ograve essere necessario attuare politiche per le quali un genitore non può rispondere più d'una volta al questionario.Ci&ograve &egrave possibile: basti cliccare
                            sulla icona dell'ingranaggio <i>"Preferences"</i> e scegliere l'opzione <i>"Form limits"</i>, da qu&igrave scegliere <i>"Controllo Strict</i></p>
                   </div>
                </div>    
                </div>
             </div>
          </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
