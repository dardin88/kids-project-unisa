<%-- 
    Document   : paymentManagement
    Created on : 2-dic-2012, 5.50.41
    Author     : Antonio Panariello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user == null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test="${sessionScope.user.getAccountType() != 'Segreteria'}">
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
        <script type="text/javascript" src="js/payments.js"></script>

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
                <form id="confirmForm" class="cmxform" method="post" action="paymentManagement.jsp">
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

            <%-- blocco div di ricerca del genitore --%>
            <div id="searchParent">
                <form style="padding-bottom: 20px" onkeyup="search();">
                    <fieldset>
                        <table>
                            <tr>
                                <td style="width: 100px">
                                    <label for="parentName" style="font-size: 10pt; font-weight: bold" >Nome:</label>
                                </td>
                                <td style="width: 190px">
                                    <input type="text" name="parentName" id="parentName" style="width: 170px; padding: 1.5%">
                                </td>
                                <td style="width: 100px">
                                    <label for="parentSurname" style="font-size: 10pt; font-weight: bold">Cognome:</label>
                                </td>
                                <td style="width: 190px">
                                    <input type="text" name="parentSurname" id="parentSurname" style="width: 170px; padding: 1.5%">
                                </td>
                                <td style="width: 100px">
                                    <label for="parentFiscalCode" style="font-size: 10pt; font-weight: bold">Codice fiscale:</label>
                                </td>
                                <td style="width: 190px"> 
                                    <input type="text" name="parentFiscalCode" id="parentFiscalCode" style="width: 170px; padding: 1.5%">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>

                <table id="parentsTable">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Codice fiscale</th>
                        </tr>
                    </thead>

                    <tbody>
                    </tbody>
                </table>
            </div>
            <%-- fine blocco div ricerca genitore --%>

            <%-- blocco div delle varie funzioni della gestione pagamenti --%>
            <div id="generalPaymentSection">

                <div id="paymentTabGroup">
                    <span class="selectedParent">Genitore:&nbsp;<span id="selectedParentData"></span></span><br><br>
                    <ul>
                        <li><a href="#showPayments"><span class="paymentsTab">Visualizza pagamenti</span></a></li>
                        <li><a href="#showRefunds"><span class="paymentsTab">Visualizza rimborsi</span></a></li>
                        <li><a href="#insertPayment"><span class="paymentsTab">Inserisci pagamento</span></a></li>
                        <li><a href="#insertRefund"><span class="paymentsTab">Inserisci rimborso</span></a></li>
                        <li><a href="#validatePayment"><span class="paymentsTab">Convalida pagamenti</span></a></li>
                    </ul>

                    <div id="insertPayment">
                        <form id="insertPaymentForm" class="cmxform" method="post" action="InsertPayment">
                            <table>
                                <tr>
                                    <td><label for="paymentDescription">Descrizione pagamento:&nbsp;</label></td>
                                    <td><input type="text" name="paymentDescription" id="paymentDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="amount">Importo:&nbsp;</label></td>
                                    <td><input type="text" name="amount" id="amount"></td>
                                </tr>
                                <tr>
                                    <td><label for="payee">Beneficiario:&nbsp;</label></td>
                                    <td><input type="text" name="payee" id="payee" value="Unisa - Kids" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="discount">Percentuale di sconto:&nbsp;</label></td>
                                    <td><input type="text" name="discount" id="discount" value="0"></td>
                                </tr>
                                <tr>
                                    <td><label for="discountDescription">Descrizione sconto:&nbsp;</label></td>
                                    <td><input type="text" name="discountDescription" id="discountDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="expDate">Data di scadenza:&nbsp;</label></td>
                                    <td><input type="text" name="expDate" id="expDate"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenParentIdInsPayment" id="hiddenParentIdInsPayment" value="-1">
                                        <input class="confirmButton" type="submit" name="insertPaymentButton" id="insertPaymentButton" value="Inserisci pagamento">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="insertRefund">
                        <form id="insertRefundForm" class="cmxform" method="post" action="InsertRefund">
                            <table>
                                <tr>
                                    <td><label for="refundDescription">Descrizione rimborso:&nbsp;</label></td>
                                    <td><input type="text" name="refundDescription" id="refundDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="refundAmount">Importo:&nbsp;</label></td>
                                    <td><input type="text" name="refundAmount" id="refundAmount"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenParentIdInsRefund" id="hiddenParentIdInsRefund" value="-1">
                                        <input class="confirmButton" type="submit" name="insertRefundButton" id="insertRefundButton" value="Inserisci rimborso">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <div id="showPayments">
                        <table id="showPaymentsTable" style="width:100%">
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

                    <%-- div della Dialog per la modifica dei pagamenti --%>
                    <div id="modifyPaymentsDialog" title="Modifica pagamento">
                        <form id="modifyPaymentForm" class="cmxform" method="post" action="UpdatePayment">
                            <table>
                                <tr>
                                    <td><label for="modifyPaymentDescription">Descrizione pagamento:&nbsp;</label></td>
                                    <td><input type="text" name="modifyPaymentDescription" id="modifyPaymentDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyExpDate">Data di scadenza:&nbsp;</label></td>
                                    <td><input type="text" name="modifyExpDate" id="modifyExpDate"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyAmount">Importo:&nbsp;</label></td>
                                    <td><input type="text" name="modifyAmount" id="modifyAmount"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyDiscount">Percentuale di sconto:&nbsp;</label></td>
                                    <td><input type="text" name="modifyDiscount" id="modifyDiscount"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyDiscountDescription">Descrizione sconto:&nbsp;</label></td>
                                    <td><input type="text" name="modifyDiscountDescription" id="modifyDiscountDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyOriginAccount">Conto d'origine:&nbsp;</label></td>
                                    <td><input type="text" name="modifyOriginAccount" id="modifyOriginAccount" disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyPayee">Beneficiario:&nbsp;</label></td>
                                    <td><input type="text" name="modifyPayee" id="modifyPayee" value="Unisa - Kids" readonly></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyReceiptCode">Codice ricevuta:&nbsp;</label></td>
                                    <td><input type="text" name="modifyReceiptCode" id="modifyReceiptCode" disabled></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenModPaymentId" id="hiddenModPaymentId" value="-1">
                                        <input class="confirmButton" type="submit" name="modifyPaymentButton" id="modifyPaymentButton" value="Conferma modifica pagamento">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <%-- fine div della Dialog per la modifica dei pagamenti --%>

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

                    <%-- div della Dialog per la modifica dei rimborsi --%>
                    <div id="modifyRefundsDialog" title="Modifica rimborso">
                        <form id="modifyRefundsForm" class="cmxform" method="post" action="UpdatePayment">
                            <table>
                                <tr>
                                    <td><label for="modifyRefundDescription">Descrizione rimborso:&nbsp;</label></td>
                                    <td><input type="text" name="modifyRefundDescription" id="modifyRefundDescription"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyRefundAmount">Importo:&nbsp;</label></td>
                                    <td><input type="text" name="modifyRefundAmount" id="modifyRefundAmount"></td>
                                </tr>
                                <tr>
                                    <td><label for="modifyRefundStatus">Effettuato:&nbsp;</label></td>
                                    <td><input type="checkbox" name="modifyRefundStatus" id="modifyRefundStatus" value="performedTrue"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenModRefundId" id="hiddenModRefundId" value="-1">
                                        <input class="confirmButton" type="submit" name="modifyRefundButton" id="modifyRefundButton" value="Conferma modifica rimborso">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <%-- fine div della Dialog per la modifica dei rimborsi --%>

                    <div id="validatePayment">
                        <table id="showPaymentsConvTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Data di scadenza</th>
                                    <th>Descrizione pagamento</th>
                                    <th>Importo</th>
                                    <th>Sconto</th>
                                    <th>Descrizione sconto</th>
                                    <th>Importo dovuto</th>
                                    <th>Beneficiario</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <%-- div della Dialog per la convalida dei pagamenti --%>
                    <div id="validatePaymentsDialog" title="Convalida pagamento">
                        <form id="validatePaymentForm" class="cmxform" method="post" action="UpdatePayment">
                            <table>
                                <tr>
                                    <td><label for="validatePaymentDescription">Descrizione pagamento:&nbsp;</label></td>
                                    <td><input type="text" name="validatePaymentDescription" id="validatePaymentDescription" disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="validateAmount">Importo:&nbsp;</label></td>
                                    <td><input type="text" name="validateAmount" id="validateAmount" disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="validatePayee">Beneficiario:&nbsp;</label></td>
                                    <td><input type="text" name="validatePayee" id="validatePayee" value="Unisa - Kids" readonly disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="validateDiscount">Percentuale di sconto:&nbsp;</label></td>
                                    <td><input type="text" name="validateDiscount" id="validateDiscount" disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="validateDiscountDescription">Descrizione sconto:&nbsp;</label></td>
                                    <td><input type="text" name="validateDiscountDescription" id="validateDiscountDescription" disabled></td>

                                </tr>
                                <tr>
                                    <td><label for="validateExpDate">Data di scadenza:&nbsp;</label></td>
                                    <td><input type="text" name="validateExpDate" id="validateExpDate" disabled></td>
                                </tr>
                                <tr>
                                    <td><label for="validateOriginAccount">Conto d'origine:&nbsp;</label></td>
                                    <td><input type="text" name="validateOriginAccount" id="validateOriginAccount"></td>
                                </tr>
                                <tr>
                                    <td><label for="validateConfirmCode">Codice ricevuta pagamento:&nbsp;</label></td>
                                    <td><input type="text" name="validateConfirmCode" id="validateConfirmCode"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="hiddenValPaymentId" id="hiddenValPaymentId" value="-1">
                                        <input class="confirmButton" type="submit" name="validatePaymentButton" id="validatePaymentButton" value="Conferma convalida pagamento">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <%-- fine div della Dialog per la convalida dei pagamenti --%>
                </div>
                <button id="goToParentsSearchBtn" style="margin-top: 10px;">Indietro</button>
            </div>
            <%-- fine blocco div funzioni gestione pagamenti --%>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
