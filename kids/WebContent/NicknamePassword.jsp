<%-- 
    Document   : NicknamePassword
    Created on : 9-dic-2012, 11.07.46
    Author     : Gianmarco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/modifyAccountRegistration.js"></script>
          <script type="text/javascript" src="js/account.js"></script>
        <title>Nickname e Password - Kids Project</title>
        <jsp:include page="/NicknamePassword" /> 
        <script type="text/javascript">
            $(document).ready(function(){
    $('#NickPassAccountWindow').dialog("open");
    var string=document.getElementById('Nickname').value;
    var string2=document.getElementById('Password').value; 
    $('#box').html("Nickname : "+string+"    Password : "+string2);
    $("#confirmRemoveLinkButton").button();
        $("#confirmRemoveLinkButton").click(function(){
           location.href="./index.jsp";
        });
        

              }

        </script>
    </head>
    <body>
        <input type="text" id="Nickname" value="${Nickname}">
        <input type="text" id="Password" value="${Password}">
        
        <div id="NickPassAccountWindow" title="Rimuovi Account" style="display: inline">
            <form id="NickPassAccountForm" class="cmxform" method="post" action="index.jsp">
                <fieldset>
                    <p class="formp">
                        <span id="box" class="requirementLabel"> </span>
                    </p>
                    <p class="formp">
                        <input type="submit" class="confirmRemoveButton" id="confirmRemoveLinkButton" value="OK"/>
                   
                    </p>
                </fieldset>
            </form>
        </div>
    </body>
    
    
</html>
