<%-- 
    Document   : paymentParent
    Created on : 8-dic-2012, 1.21.59
    Author     : Antonio Panariello
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

        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <script type="text/javascript" src="js/paymentsParent.js"></script>

        <title>Kids</title>

        <script type="text/javascript">
            $(document).ready(function() {
                activePage();
                messageDialog();
                initializePaymentsPage();
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <c:if test="${requestScope.message != null}">
            <div id="confirm" title="Message" style="display: inline">
                <form id="confirmForm" class="cmxform" method="post" action="paymentParent.jsp">
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

        <div id="paymentsManagement">
            <input type="hidden" name="hiddenParentId" id="hiddenParentId" value="${sessionScope.user.getId()}">
            <%-- blocco div delle varie funzioni dei pagamenti --%>
            <div id="generalPaymentSection">
                <div id="paymentTabGroup">
                    <ul>
                        <li><a href="#showPayments"><span class="paymentsTab">Visualizza pagamenti</span></a></li>
                        <li><a href="#showRefunds"><span class="paymentsTab">Visualizza rimborsi</span></a></li>
                    </ul>

                    <div id="showPayments">
                        <table id="showPaymentsTable" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>Data di scadenza</th>
                                    <th>Descrizione pagamento</th>
                                    <th>Importo</th>
                                    <th>Sconto</th>
                                    <th>Descrizione sconto</th>
                                    <th>Importo dovuto</th>
                                    <th>Conto d'origine</th>
                                    <th>Beneficiario</th>
                                    <th>Codice ricevuta</th>
                                    <th>Effettuato</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div id="showRefunds">
                        <table id="showRefundsTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Descrizione</th>
                                    <th>Importo</th>
                                    <th>Effettuato</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <%-- fine blocco div funzioni pagamenti --%>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
