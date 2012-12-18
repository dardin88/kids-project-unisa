/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.paymentManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.paymentManagement.IPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import it.unisa.kids.serviceManagement.paymentManagement.RefundBean;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author utente
 */
public class UpdatePaymentServlet extends HttpServlet {

    private static final int DESCRIPTION_MAXLENGTH = 200;
    private static final int ORIGACCOUNT_MAXLENGTH = 30;
    private static final int RECEIPTCODE_MAXLENGTH = 50;
    private static final String PERFORMED_TRUE = "performedTrue";
    private IPaymentManager paymentManager;

    public void init(ServletConfig config) {
        this.paymentManager = (IPaymentManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PAYMENT);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getParameter("hiddenModPaymentId") != null) {
                modifyPayment(request, response);
            } else if (request.getParameter("hiddenValPaymentId") != null) {
                validatePayment(request, response);
            } else if (request.getParameter("hiddenModRefundId") != null) {
                modifyRefund(request, response);
            }

        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (ParseException e) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: data non corretta.", "/paymentManagement.jsp");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void modifyPayment(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, SQLException, IOException, ServletException, ParseException {
        PaymentBean payment = new PaymentBean();
        int paymentId = Integer.parseInt(request.getParameter("hiddenModPaymentId"));
        if (paymentId <= 0) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: pagamento selezionato non corretto - " + paymentId, "/paymentManagement.jsp");
            return;
        }
        payment.setId(paymentId);

        String paymentDescription = request.getParameter("modifyPaymentDescription").trim();
        if (paymentDescription.length() > DESCRIPTION_MAXLENGTH) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione pagamento troppo lunga.", "/paymentManagement.jsp");
            return;
        }
        payment.setPaymentDescription(paymentDescription);

        double amount = Double.parseDouble(request.getParameter("modifyAmount").trim());
        if (amount < 0) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: importo negativo non consentito.", "/paymentManagement.jsp");
            return;
        }
        payment.setAmount(amount);

        String originAccount = request.getParameter("modifyOriginAccount");
        if (originAccount != null) {
            if (originAccount.trim().length() > ORIGACCOUNT_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: conto origine errato", "/paymentManagement.jsp");
                return;
            }
            payment.setOriginAccount(originAccount.trim());
        }

        payment.setPayee(request.getParameter("modifyPayee").trim());

        String receiptCode = request.getParameter("modifyReceiptCode");
        if (receiptCode != null) {
            if (receiptCode.trim().equals("") || receiptCode.trim().length() > RECEIPTCODE_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: codice di ricevuta pagamento inserito non correttamente", "/paymentManagement.jsp");
                return;
            } else {
                payment.setReceiptCode(receiptCode.trim());
            }
        }

        double discount = Double.parseDouble(request.getParameter("modifyDiscount").trim());
        if (discount < 0 || discount > 100) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: valore sconto non consentito.", "/paymentManagement.jsp");
            return;
        }
        payment.setDiscount(discount);

        String discountDescription = request.getParameter("modifyDiscountDescription").trim();
        if (discountDescription.length() > DESCRIPTION_MAXLENGTH) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione sconto troppo lunga.", "/paymentManagement.jsp");
            return;
        }
        payment.setDiscountDescription(discountDescription);

        String expDate = request.getParameter("modifyExpDate");
        if (expDate == null) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: data di scadenza non specificata.", "/paymentManagement.jsp");
            return;
        }
        payment.setExpDate(CommonMethod.parseGregorianCalendar(expDate));

        payment.setPaid(false);
        paymentManager.update(payment);
        CommonMethod.sendMessageRedirect(request, response, "Pagamento modificato con successo.", "/paymentManagement.jsp");
    }

    private void validatePayment(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, SQLException, IOException, ServletException {
        PaymentBean payment = new PaymentBean();
        int paymentId = Integer.parseInt(request.getParameter("hiddenValPaymentId"));
        if (paymentId <= 0) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: pagamento selezionato non corretto - " + paymentId, "/paymentManagement.jsp");
            return;
        }
        payment.setId(paymentId);

        String originAccount = request.getParameter("validateOriginAccount").trim();
        if (originAccount.length() > ORIGACCOUNT_MAXLENGTH) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: conto origine errato", "/paymentManagement.jsp");
            return;
        }
        payment.setOriginAccount(originAccount);

        String receiptCode = request.getParameter("validateConfirmCode").trim();
        if (receiptCode != null && (receiptCode.trim().equals("") || receiptCode.trim().length() > RECEIPTCODE_MAXLENGTH)) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: codice di ricevuta pagamento inserito non correttamente", "/paymentManagement.jsp");
            return;
        } else {
            payment.setReceiptCode(request.getParameter("validateConfirmCode").trim());
        }

        payment.setPaid(true);
        payment.setPaidUsable(true);

        // setto opportunamente gli attributi da non usare nell'update
        payment.setAmount(-1);
        payment.setDiscount(-1);
        payment.setParentId(-1);

        paymentManager.update(payment);
        CommonMethod.sendMessageRedirect(request, response, "Pagamento convalidato con successo.", "/paymentManagement.jsp");
    }

    private void modifyRefund(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, SQLException, IOException, ServletException {
        RefundBean refund = new RefundBean();
        int refundId = Integer.parseInt(request.getParameter("hiddenModRefundId"));
        if (refundId <= 0) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: rimborso selezionato non corretto - " + refundId, "/paymentManagement.jsp");
            return;
        }
        refund.setId(refundId);

        String refundDescription = request.getParameter("modifyRefundDescription").trim();
        if (refundDescription.length() > DESCRIPTION_MAXLENGTH) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione rimborso troppo lunga.", "/paymentManagement.jsp");
            return;
        }
        refund.setDescription(refundDescription);

        double amount = Double.parseDouble(request.getParameter("modifyRefundAmount").trim());
        if (amount < 0) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: importo negativo non consentito.", "/paymentManagement.jsp");
            return;
        }
        refund.setAmount(amount);

        String refundStatus = request.getParameter("modifyRefundStatus");
        Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.INFO, "refundStatus: " + refundStatus);
        refund.setPerformedUsable(true);
        if (refundStatus != null && refundStatus.equals(PERFORMED_TRUE)) {
            refund.setPerformed(true);
        } else {
            refund.setPerformed(false);
        }

        Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.INFO, "refund: " + refund.toString());
        refund.setParentId(-1);
        paymentManager.update(refund);
        CommonMethod.sendMessageRedirect(request, response, "Rimborso modificato con successo.", "/paymentManagement.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
