<%-- 
    Document   : surveyForm
    Created on : Dec 10, 2012, 3:59:47 PM
    Author     : Felice D'Avino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <header>
        <link rel="stylesheet" type="text/css" href="css/template.css" >

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/compiledSurveyShowTable.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                addCompiledSurvey();
            });
        </script>
    </header>
    <body>
        <input id ="userid" type="hidden" value="${param.idUtente}">
        <input id ="idQuestionario" type="hidden" value="${param.idQuestionario}">
        <div id="webapp">
            <iframe id="questionarioIFrame" src="${param.link}">
        </div>
    </body>
</html>
