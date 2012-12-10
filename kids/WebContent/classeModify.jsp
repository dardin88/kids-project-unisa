<%-- 
    Document   : classeModify
    Created on : 10-dic-2012, 9.18.03
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
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
        <link rel="stylesheet" type="text/css" href="css/template.css">
        <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/classemodify.js"></script>
        <title>Registrazione Account - Kids Project</title>

        <jsp:include page="/GetClass" /> 

        <script type="text/javascript">
                     
            $(document).ready(function() {
                initializeRegistrationFields();
            });
            
        </script>
    </head>

    <%@include file="header.jsp"%>

    <body id="bodyRegistration">



        <h1  id="titleReg" align="center">Modifica Classe</h1>

        <form id="registrationForm2" class="cmxform"  action="" method="post">
            <fieldset id="registrationFieldSet">
                <div id="artefactsManagement">
                    <input type="text" id="id" name="id" class="registrationField"  value="${id}">
                    <p class="formp">
                        <label> <h3>Nome: </h3> </label>
                        <input id="Nome" class="registrationField" type="text" name="Nome" size="50%" value="${Nome}">
                    </p>
                    <p class="formp">
                        <label> <h3>Stato:  </h3> </label>
                        <input id="Stato" class="registrationField"type="text" name="Stato" value="${Stato}">
                    </p>

                    <%--tabella--%>

                    <table id="accountsTable" style="width:95%;">
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

                <%-- fine tabella--%>

                <input type="submit" name="registrationButton" id="registrationButton" value="Modifica" />
                </div>
            </fieldset>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>