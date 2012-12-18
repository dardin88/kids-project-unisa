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
import java.io.IOException;
import java.sql.SQLException;
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
public class InsertPaymentServlet extends HttpServlet {

    private static final int DESCRIPTION_MAXLENGTH = 200;
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
            PaymentBean payment = new PaymentBean();

            int parentId = Integer.parseInt(request.getParameter("hiddenParentIdInsPayment"));
            if (parentId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: genitore selezionato non corretto - " + parentId, "/paymentManagement.jsp");
                return;
            }
            payment.setParentId(parentId);

            String paymentDescription = request.getParameter("paymentDescription").trim();
            if (paymentDescription.length() > DESCRIPTION_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione pagamento troppo lunga.", "/paymentManagement.jsp");
                return;
            }
            payment.setPaymentDescription(paymentDescription);

            double amount = Double.parseDouble(request.getParameter("amount").trim());
            if (amount < 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: importo negativo non consentito.", "/paymentManagement.jsp");
                return;
            }
            payment.setAmount(amount);

            payment.setPayee(request.getParameter("payee").trim());

            double discount = Double.parseDouble(request.getParameter("discount").trim());
            if (discount < 0 || discount > 100) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: valore sconto non consentito.", "/paymentManagement.jsp");
                return;
            }
            payment.setDiscount(discount);

            String discountDescription = request.getParameter("discountDescription").trim();
            if (discountDescription.length() > DESCRIPTION_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione sconto troppo lunga.", "/paymentManagement.jsp");
                return;
            }
            payment.setDiscountDescription(discountDescription);

            String expDate = request.getParameter("expDate");
            if (expDate == null) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: data di scadenza non specificata.", "/paymentManagement.jsp");
                return;
            }
            payment.setExpDate(CommonMethod.parseGregorianCalendar(expDate));

            payment.setPaid(false);

            paymentManager.insert(payment);

            CommonMethod.sendMessageRedirect(request, response, "Pagamento inserito con successo.", "/paymentManagement.jsp");

        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        }
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
