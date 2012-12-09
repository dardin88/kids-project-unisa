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
        <script type="text/javascript" src="js/canteen.js"></script>

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
            <h1>Mensa</h1>
            <input type="hidden" name="hiddenParentId" id="hiddenParentId" value="${sessionScope.user.getId()}">
            <%-- blocco div delle varie funzioni della mensa --%>
            <div id="generalCanteenSection">
                <div id="canteenTabGroup">
                    <ul>
                        <li><a href="#showDailyMenu"><span class="paymentsTab">Visualizza men&ugrave; giornaliero</span></a></li>
                        <li><a href="#showAssociatedMenu"><span class="paymentsTab">Visualizza men&ugrave; associati</span></a></li>
                        <li><a href="#modifySickness"><span class="paymentsTab">Modifica malattie/Inserisci note</span></a></li>
                        <li><a href="#mealRequest"><span class="paymentsTab">Richiedi pasto</span></a></li>
                    </ul>

                    <div id="showDailyMenu">
                        <table id="showDailyMenuTable" style="width:100%">
                            <thead>
                                <tr>
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

                    <div id="showAssociatedMenu">
                        <h3>Selezionare un elemento della lista per visualizzare i dettagli sul pasto</h3>
                        <table id="showAssociatedMenuTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Tipo men&ugrave;</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div id="showAssociatedMenuDialog">
                            <table>
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

                    <div id="modifySickness">
                        <h3>Modifica malattie/Inserisci note</h3>
                        <table>
                            <tr>
                                <td><label for="sicknessArea">Malattie:&nbsp;</label></td>
                                <td><textarea rows="5" cols="20" maxlength="400" name="sicknessArea" id="sicknessArea"></textarea></td>
                            </tr>
                            <tr>
                                <td><label for="sicknessArea">Note:&nbsp;</label></td>
                                <td><textarea rows="5" cols="20" maxlength="400" name="sicknessArea" id="sicknessArea"></textarea></td>
                            </tr>
                        </table>
                    </div>
                    
                    <div id="mealRequest">
                        <h3>Modifica malattie/Inserisci note</h3>
                        <table>
                            <tr>
                                <td><label for="sicknessArea">Malattie:&nbsp;</label></td>
                                <td><textarea rows="5" cols="20" maxlength="400" name="sicknessArea" id="sicknessArea"></textarea></td>
                            </tr>
                            <tr>
                                <td><label for="sicknessArea">Note:&nbsp;</label></td>
                                <td><textarea rows="5" cols="20" maxlength="400" name="sicknessArea" id="sicknessArea"></textarea></td>
                            </tr>
                        </table>
                    </div>

                </div>
            </div>
            <%-- fine blocco div funzioni mensa --%>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
