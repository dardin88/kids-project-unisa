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
                sendMessageRedirect(request, response, "Errore: genitore selezionato non corretto - " + parentId);
                return;
            }
            payment.setParentId(parentId);
            
            String paymentDescription = request.getParameter("paymentDescription").trim();
            if (paymentDescription.length() > DESCRIPTION_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: descrizione pagamento troppo lunga.");
                return;
            }
            payment.setPaymentDescription(paymentDescription);
            
            double amount = Double.parseDouble(request.getParameter("amount").trim());
            if (amount < 0) {
                sendMessageRedirect(request, response, "Errore: importo negativo non consentito.");
                return;
            }
            payment.setAmount(amount);
            
            payment.setPayee(request.getParameter("payee").trim());
            
            double discount = Double.parseDouble(request.getParameter("discount").trim());
            if (discount < 0 || discount > 100) {
                sendMessageRedirect(request, response, "Errore: valore sconto non consentito.");
                return;
            }
            payment.setDiscount(discount);
            
            String discountDescription = request.getParameter("discountDescription").trim();
            if (discountDescription.length() > DESCRIPTION_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: descrizione sconto troppo lunga.");
                return;
            }
            payment.setDiscountDescription(discountDescription);
            
            String expDate = request.getParameter("expDate");
            if (expDate == null) {
                sendMessageRedirect(request, response, "Errore: data di scadenza non specificata.");
                return;
            }
            payment.setExpDate(parseGregorianCalendar(expDate));
            
            payment.setPaid(false);
            
            paymentManager.insert(payment);
            
            sendMessageRedirect(request, response, "Pagamento inserito con successo.");

        } catch (SQLException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);
        
        } catch (NumberFormatException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);
        
        } catch (ParseException e) {
            sendMessageRedirect(request, response, "Errore: data non corretta.");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);
        } 
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
