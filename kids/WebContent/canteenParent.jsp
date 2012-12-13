<%-- 
    Document   : canteenParent
    Created on : 9-dic-2012, 3.46.11
    Author     : navipar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user == null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType() != 'Genitore'}">
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
        <script type="text/javascript" src="js/canteenParent.js"></script>

        <title>Mensa - Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializeCanteenPage();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="canteenParent.jsp">
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

        <div id="canteenManagement">
            <h1>Mensa</h1>
            <input type="hidden" name="hiddenParentId" id="hiddenParentId" value="${sessionScope.user.getId()}">

            <%-- blocco div delle varie funzioni della mensa --%>
            <div id="generalCanteenSection">
                <div id="canteenTabGroup">
                    <ul>
                        <li><a href="#showDailyMenu"><span class="canteenTab">Visualizza men&ugrave; giornaliero</span></a></li>
                        <li><a href="#showAssociatedMenu"><span class="canteenTab">Visualizza men&ugrave; associati</span></a></li>
                        <li><a href="#modifySickness"><span class="canteenTab">Modifica malattie/Inserisci note</span></a></li>
                        <li><a href="#mealRequest"><span class="canteenTab">Richiedi pasto</span></a></li>
                    </ul>

                    <div id="showDailyMenu">
                        <h2>Men&ugrave; giornaliero</h2>
                        <table>
                            <tr>
                                <td><label for="dailyMenuDate">Data:&nbsp;</label></td>
                                <td><input type="text" name="dailyMenuDate" id="dailyMenuDate" readonly></td>
                            </tr>
                            <tr>
                                <td><label for="dailyMenuFirst">Primo:&nbsp;</label></td>
                                <td><input type="text" name="dailyMenuFirst" id="dailyMenuFirst" readonly></td>
                            </tr>
                            <tr>
                                <td><label for="dailyMenuSecond">Secondo:&nbsp;</label></td>
                                <td><input type="text" name="dailyMenuSecond" id="dailyMenuSecond" readonly></td>
                            </tr>
                            <tr>
                                <td><label for="dailyMenuSideDish">Contorno:&nbsp;</label></td>
                                <td><input type="text" name="dailyMenuSideDish" id="dailyMenuSideDish" readonly></td>
                            </tr>
                            <tr>
                                <td><label for="dailyMenuFruit">Frutta:&nbsp;</label></td>
                                <td><input type="text" name="dailyMenuFruit" id="dailyMenuFruit" readonly></td>
                            </tr>
                        </table>
                    </div>

                    <div id="showAssociatedMenu">
                        <h2>Ricerca men&ugrave; associato</h2>
                        <form style="padding-bottom: 20px" onchange="searchAssociatedMenus();">
                            <fieldset>
                                <label for="menuDate">Data:&nbsp;</label>
                                <input type="text" name="menuDate" id="menuDate">
                                <label for="childSelect">Seleziona bambino:&nbsp;</label>
                                <select name="childSelect" id="childSelect"></select>
                            </fieldset>
                        </form>
                        <table id="showAssociatedMenuTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Primo</th>
                                    <th>Secondo</th>
                                    <th>Contorno</th>
                                    <th>Frutta</th>
                                    <th>Tipo men&ugrave;</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="modifySickness">
                        <h3>Modifica malattie/Inserisci note</h3>
                        <form id="modifySicknessForm" class="cmxform" method="post" action="ModifySickness">
                            <table>
                                <tr>
                                    <td>
                                        <label for="childSelectModSick">Seleziona bambino:&nbsp;</label>
                                        <select name="childSelectModSick" id="childSelectModSick"><option>Seleziona</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="sicknessArea">Malattie:&nbsp;</label></td>
                                    <td><textarea rows="10" cols="50" maxlength="400" name="sicknessArea" id="sicknessArea"></textarea></td>
                                </tr>
                                <tr>
                                    <td><label for="noteArea">Note:&nbsp;</label></td>
                                    <td><textarea rows="10" cols="50" maxlength="400" name="noteArea" id="noteArea"></textarea></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="confirmButton" type="submit" name="modifySicknessButton" id="modifySicknessButton" value="Invia dati">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="mealRequest">
                        <h2>Richiedi pasto</h2>
                        <h3>Selezionare il giorno della richiesta del pasto</h3>
                        <form id="mealRequestForm" class="cmxform" method="post" action="InsertMealRequest">
                            <table>
                                <tr>
                                    <td><label for="requestDate">Data richiesta:&nbsp;</label></td>
                                    <td><input type="text" name="requestDate" id="requestDate"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenParentIdMealReq" id="hiddenParentIdMealReq" value="${sessionScope.user.getId()}">
                                        <input class="confirmButton" type="submit" name="requestMealButton" id="requestMealButton" value="Invia richiesta">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                </div>
            </div>
            <%-- fine blocco div funzioni mensa --%>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
