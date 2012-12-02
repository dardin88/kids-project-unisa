<%-- 
    Document   : paymentManagement
    Created on : 2-dic-2012, 5.50.41
    Author     : navi
--%>

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

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/payments.js"></script>

        <title>Gestione pagamenti - Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                messageDialog();
                activeTabs();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="paymentManagement.jsp">
                    <fieldset>
                        <p class="formp">
                            <label for="confirmButton" class="requirementLabel">${requestScope.message}</label>
                        </p>
                        <p class="formp">
                            <input type="submit" class="confirmButton" id="confirmButton" value="ok"/>
                        </p>
                    </fieldset>
                </form>
            </div>
        </c:if>
        <form>
            <input type="button" onclick="test();" value="Lock/Unlock">
        </form>
        
        <div id="paymentsManagement">
            <div id="paymentTabGroup">
                <ul>
                    <li><a href="#showPayments"><span class="paymentsTab">Visualizza pagamenti</span></a></li>
                    <li><a href="#showRefunds"><span class="paymentsTab">Visualizza rimborsi</span></a></li>
                    <li><a href="#addCharge"><span class="paymentsTab">Addebita pagamento</span></a></li>
                    <li><a href="#addDiscount"><span class="paymentsTab">Applica sconto</span></a></li>
                </ul>

                <div id="showPayments">
                    <table id="parentsTable">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Cognome</th>
                                <th>Codice fiscale</th>
                            </tr>
                        </thead>
                    </table>

                    <table id="paymentsTable">
                        <thead>
                            <tr>
                                <th>Data di scadenza</th>
                                <th>Descrizione</th>
                                <th>Importo</th>
                                <th>Sconto</th>
                                <th>Descrizione sconto</th>
                                <th>Importo dovuto</th>
                            </tr>
                        </thead>
                    </table>
                </div>

                <div id="showRefunds">
                    <table id="parentsTable2">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Cognome</th>
                                <th>Codice fiscale</th>
                            </tr>
                        </thead>
                    </table>

                    <table id="paymentsTable">
                        <thead>
                            <tr>
                                <th>Data di scadenza</th>
                                <th>Descrizione</th>
                                <th>Importo</th>
                                <th>Sconto</th>
                                <th>Descrizione sconto</th>
                                <th>Importo dovuto</th>
                            </tr>
                        </thead>
                    </table>
                </div>

                <div id="addCharge">

                </div>

                <div id="addDiscount">

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
