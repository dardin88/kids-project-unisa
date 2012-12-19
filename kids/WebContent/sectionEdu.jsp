<%-- 
    Document   : sectionEdu
    Created on : 18-dic-2012, 10.09.30
    Author     : navi
--%>

<%@page import="it.unisa.kids.communicationManagement.programEducationalManagement.CommentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <script type="text/javascript" src="js/sectionEdu.js"></script>

        <title>Gestione Programma Educativo Sezione - Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializeSectionEduPage();
            });
        </script>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="sectionEdu.jsp">
                    <fieldset>
                        <p class="formp">
                            <label class="requirementLabel">${requestScope.message}</label>
                        </p>
                        <p class="formp">
                            <input type="submit" class="confirmButton" id="confirmButton" value="OK">
                        </p>
                    </fieldset>
                </form>
            </div>
        </c:if>

        <div id="sectionEduManagement">
            <div id="sectionEduTabs">
                <ul>
                    <jsp:include page="/GetClassTabs"></jsp:include>
                </ul>

                <jsp:include page="/GetSectionEduClassDivs"></jsp:include>

            </div>
            <div style="padding-top: 20px;">
                <input type="button" id="saveDraftBtn" value="Salva come bozza" onclick="saveSectionDraft();">
                <input type="button" id="submitBtn" value="Sottometti" onclick="submitSectionProgram();">
                <input type="button" id="requestModBtn" value="Richiedi modifiche" onclick="requestModSectProg();">
                <input type="button" id="acceptDocRectBtn" value="Accetta documento" onclick="acceptDocumentRect();">
                <input type="button" id="acceptDocScientBtn" value="Accetta documento" onclick="acceptDocumentScient();">
            </div>

            <div id="insertCommentDialog">
                <form id="insertDiffMenuForm" class="cmxform" method="post" action="InsertComment">
                    <table>
                        <tr>
                            <td><label for="commentContent">Commento:&nbsp;</label></td>
                            <td><textarea rows="5" cols="25" name="commentContent" id="commentContent"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" name="commentType" value="<%= CommentBean.CLASS_COMMENT %>">
                                <input type="hidden" name="hiddenAccountId" id="hiddenAccountId" value="${sessionScope.user.getId()}">
                                <input type="hidden" name="hiddenClassId" id="hiddenClassId" value="-1">
                                <input class="confirmButton" type="submit" name="insertComentBtn" id="insertComentBtn" value="Invia">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
