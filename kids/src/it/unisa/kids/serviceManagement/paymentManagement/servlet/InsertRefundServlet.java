/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.paymentManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.paymentManagement.IPaymentManager;
import it.unisa.kids.serviceManagement.paymentManagement.RefundBean;
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
public class InsertRefundServlet extends HttpServlet {

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
            RefundBean refund = new RefundBean();

            int parentId = Integer.parseInt(request.getParameter("hiddenParentIdInsRefund"));
            if (parentId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: genitore selezionato non corretto - " + parentId, "/paymentManagement.jsp");
                return;
            }
            refund.setParentId(parentId);

            String refundDescription = request.getParameter("refundDescription").trim();
            if (refundDescription.length() > DESCRIPTION_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: descrizione rimborso troppo lunga.", "/paymentManagement.jsp");
                return;
            }
            refund.setDescription(refundDescription);

            double amount = Double.parseDouble(request.getParameter("refundAmount").trim());
            if (amount < 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: importo negativo non consentito.", "/paymentManagement.jsp");
                return;
            }
            refund.setAmount(amount);

            paymentManager.insert(refund);

            CommonMethod.sendMessageRedirect(request, response, "Rimborso inserito con successo.", "/paymentManagement.jsp");

        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(InsertRefundServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/paymentManagement.jsp");
            Logger.getLogger(InsertRefundServlet.class.getName()).log(Level.SEVERE, null, e);

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
