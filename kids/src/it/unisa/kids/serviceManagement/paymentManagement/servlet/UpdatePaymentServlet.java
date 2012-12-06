/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.paymentManagement.servlet;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.paymentManagement.IPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.PaymentBean;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private static final String CHARGE_TRUE = "chargeTrue";
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
            }

        } catch (SQLException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (ParseException e) {
            sendMessageRedirect(request, response, "Errore: data non corretta.");
            Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void modifyPayment(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, SQLException, IOException, ServletException, ParseException {
        PaymentBean payment = new PaymentBean();
        int paymentId = Integer.parseInt(request.getParameter("hiddenModPaymentId"));
        if (paymentId <= 0) {
            sendMessageRedirect(request, response, "Errore: pagamento selezionato non corretto - " + paymentId);
            return;
        }
        payment.setId(paymentId);

        String paymentDescription = request.getParameter("modifyPaymentDescription").trim();
        if (paymentDescription.length() > DESCRIPTION_MAXLENGTH) {
            sendMessageRedirect(request, response, "Errore: descrizione pagamento troppo lunga.");
            return;
        }
        payment.setPaymentDescription(paymentDescription);

        double amount = Double.parseDouble(request.getParameter("modifyAmount").trim());
        if (amount < 0) {
            sendMessageRedirect(request, response, "Errore: importo negativo non consentito.");
            return;
        }
        payment.setAmount(amount);

        payment.setPayee(request.getParameter("modifyPayee").trim());

        double discount = Double.parseDouble(request.getParameter("modifyDiscount").trim());
        if (discount < 0 || discount > 100) {
            sendMessageRedirect(request, response, "Errore: valore sconto non consentito.");
            return;
        }
        payment.setDiscount(discount);

        String discountDescription = request.getParameter("modifyDiscountDescription").trim();
        if (discountDescription.length() > DESCRIPTION_MAXLENGTH) {
            sendMessageRedirect(request, response, "Errore: descrizione sconto troppo lunga.");
            return;
        }
        payment.setDiscountDescription(discountDescription);

        String charge = request.getParameter("modifyCharge");
        payment.setChargeUsable(true);
        if (charge != null && charge.equals(CHARGE_TRUE)) {
            payment.setCharge(true);
        } else {
            payment.setCharge(false);
        }

        String expDate = request.getParameter("modifyExpDate");
        if (expDate == null) {
            sendMessageRedirect(request, response, "Errore: data di scadenza non specificata.");
            return;
        }
        payment.setExpDate(parseGregorianCalendar(expDate));

        payment.setPaid(false);
        paymentManager.update(payment);
        sendMessageRedirect(request, response, "Pagamento modificato con successo.");
    }

    private void validatePayment(HttpServletRequest request, HttpServletResponse response)
            throws NumberFormatException, SQLException, IOException, ServletException, ParseException {
        PaymentBean payment = new PaymentBean();
        int paymentId = Integer.parseInt(request.getParameter("hiddenValPaymentId"));
        if (paymentId <= 0) {
            sendMessageRedirect(request, response, "Errore: pagamento selezionato non corretto - " + paymentId);
            return;
        }
        payment.setId(paymentId);

        String originAccount = request.getParameter("validateOriginAccount").trim();
        if (originAccount.length() > ORIGACCOUNT_MAXLENGTH) {
            sendMessageRedirect(request, response, "Errore: conto origine errato");
            return;
        }
        payment.setOriginAccount(originAccount);

        String receiptCode = request.getParameter("validateConfirmCode").trim();
        if (receiptCode != null && !receiptCode.trim().equals("")) {
            //payment.setReceiptCode(request.getParameter("validateConfirmCode"));       // bisogna aggiungere l'attributo nel db
        } else {
            sendMessageRedirect(request, response, "Errore: nessun codice di ricevuta pagamento inserito");
            return;
        }
        payment.setPaid(true);
        payment.setPaidUsable(true);

        // setto opportunamente gli attributi da non usare nell'update
        payment.setAmount(-1);
        payment.setDiscount(-1);
        payment.setChargeUsable(false);
        payment.setParentId(-1);

        paymentManager.update(payment);
        sendMessageRedirect(request, response, "Pagamento convalidato con successo.");
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }

    private void sendMessageRedirect(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/paymentManagement.jsp").forward(request, response);
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
