<%-- 
    Document   : canteenManagement
    Created on : 9-dic-2012, 3.42.09
    Author     : navipar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user == null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType() != 'Responsabile Mensa'}">
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
        <script type="text/javascript" src="js/canteen.js"></script>

        <title>Gestione mensa - Kids</title>

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
                <form id="confirmForm" class="cmxform" method="post" action="canteenManagement.jsp">
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
            <h1>Gestione mensa</h1>
            <%-- blocco div delle varie funzioni della gestione mensa --%>
            <div id="generalCanteenSection">
                <h2>Operazioni</h2>
                <div id="canteenTabGroup">
                    <ul>
                        <li><a href="#diffMenus"><span class="canteenTab">Men&ugrave; differenziati</span></a></li>
                        <li><a href="#showAssociatedMenus"><span class="canteenTab">Visualizza men&ugrave; associati</span></a></li>
                        <li><a href="#showDailyMenus"><span class="canteenTab">Visualizza i men&ugrave; giornalieri</span></a></li>
                        <li><a href="#modifyDailyMenu"><span class="canteenTab">Modifica men&ugrave; giornaliero</span></a></li>
                        <li><a href="#showMealRequests"><span class="canteenTab">Visualizza richieste pasti</span></a></li>
                    </ul>

                    <div id="diffMenus">
                        <div id="classSelection">
                            <h3>Selezionare la classe interessata</h3>
                            <table id="showClassTable" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>Classe</th>
                                        <th>Numero pasti richiesti</th>
                                        <th>Presenza condizioni particolari</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div id="childSelection">
                            <span class="selectedClass">Classe:&nbsp;<span id="selectedClassData"></span></span>
                            <h3>Selezionare un bambino per inserire il men&ugrave; differenziato esaminando le condizioni particolari</h3>
                            <table id="childSelectionTable" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>Bambino</th>
                                        <th>Genitore</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div id="insertDiffMenuDialog">
                            <form id="insertDiffMenuForm" class="cmxform" method="post" action="InsertCanteenMenu">
                                <table>
                                    <tr>
                                        <td><label for="firstDiff">Primo:&nbsp;</label></td>
                                        <td><input type="text" name="firstDiff" id="firstDiff"></td>
                                    </tr>
                                    <tr>
                                        <td><label for="secondDiff">Secondo:&nbsp;</label></td>
                                        <td><input type="text" name="secondDiff" id="secondDiff"></td>
                                    </tr>
                                    <tr>
                                        <td><label for="sideDishDiff">Contorno:&nbsp;</label></td>
                                        <td><input type="text" name="sideDishDiff" id="sideDishDiff"></td>
                                    </tr>
                                    <tr>
                                        <td><label for="fruitDiff">Frutta:&nbsp;</label></td>
                                        <td><input type="text" name="fruitDiff" id="fruitDiff"></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="hidden" name="hiddenChildIdInsDiff" id="hiddenChildIdInsDiff" value="-1">
                                            <input class="confirmButton" type="submit" name="insertDiffMenuButton" id="insertDiffMenuButton" value="Inserisci men&ugrave; differenziato">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>

                    <div id="showAssociatedMenus">
                        <h2>Ricerca men&ugrave; associato</h2>
                        <form style="padding-bottom: 20px" onkeyup="searchAssociatedMenus();">
                            <fieldset>
                                <label for="menuDate">Data:&nbsp;</label>
                                <input type="text" name="menuDate" id="menuDate">
                                <label for="onlyLastAssMenu">Mostra solo gli ultimi men&ugrave; inseriti:&nbsp;</label>
                                <input type="checkbox" name="onlyLastAssMenu" id="onlyLastAssMenu" value="">
                            </fieldset>
                        </form>
                        <h3>Selezionare un menu per visualizzare i dettagli sui pasti</h3>
                        <table id="showAssociatedMenusTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Bambino</th>
                                    <th>Genitore</th>
                                    <th>Tipo men&ugrave;</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div id="showAssociatedMenusDialog">
                            <table>
                                <tr>
                                    <td><label for="associatedDate">Data:&nbsp;</label></td>
                                    <td><input type="text" name="associatedDate" id="associatedDate" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="associatedFirst">Primo:&nbsp;</label></td>
                                    <td><input type="text" name="associatedFirst" id="associatedFirst" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="associatedSecond">Secondo:&nbsp;</label></td>
                                    <td><input type="text" name="associatedSecond" id="associatedSecond" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="associatedSideDish">Contorno:&nbsp;</label></td>
                                    <td><input type="text" name="associatedSideDish" id="associatedSideDish" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="associatedFruit">Frutta:&nbsp;</label></td>
                                    <td><input type="text" name="associatedFruit" id="associatedFruit" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="associatedMenuType">Tipo men&ugrave;:&nbsp;</label></td>
                                    <td><input type="text" name="associatedMenuType" id="associatedMenuType" readonly></td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div id="showDailyMenus">
                        <h2>Ricerca men&ugrave; giornaliero</h2>
                        <form style="padding-bottom: 20px" onkeyup="searchDailyMenus();">
                            <fieldset>
                                <label for="dailyMenuDate">Data:&nbsp;</label>
                                <input type="text" name="dailyMenuDate" id="dailyMenuDate">
                                <label for="onlyLastDailyMenu">Mostra solo gli ultimi men&ugrave; inseriti:&nbsp;</label>
                                <input type="checkbox" name="onlyLastDailyMenu" id="onlyLastDailyMenu" value="">
                            </fieldset>
                        </form>
                        <h3>Lista dei men&ugrave; giornalieri</h3>
                        <table id="showDailyMenusTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Primo</th>
                                    <th>Secondo</th>
                                    <th>Contorno</th>
                                    <th>Frutta</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="modifyDailyMenu">
                        <h2>Modifica men&ugrave; giornaliero</h2>
                        <form id="modifyDailyMenuForm" class="cmxform" method="post" action="InsertDailyMenu">
                            <table>
                                <tr>
                                    <td><label for="firstEditDaily">Primo:&nbsp;</label></td>
                                    <td><input type="text" name="firstEditDaily" id="firstEditDaily"></td>
                                </tr>
                                <tr>
                                    <td><label for="secondEditDaily">Secondo:&nbsp;</label></td>
                                    <td><input type="text" name="secondEditDaily" id="secondEditDaily"></td>
                                </tr>
                                <tr>
                                    <td><label for="sideDishEditDaily">Contorno:&nbsp;</label></td>
                                    <td><input type="text" name="sideDishEditDaily" id="sideDishEditDaily"></td>
                                </tr>
                                <tr>
                                    <td><label for="fruitEditDaily">Frutta:&nbsp;</label></td>
                                    <td><input type="text" name="fruitEditDaily" id="fruitEditDaily"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="confirmButton" type="submit" name="modifyDailyMenuButton" id="modifyDailyMenuButton" value="Modifica men&ugrave; giornaliero">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="showMealRequests">
                        <h2>Lista dei men&ugrave; giornalieri</h2>
                        <table id="showMealRequestsTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Genitore</th>
                                    <th>Soddisfatta</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <%-- fine blocco div funzioni gestione mensa --%>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
