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
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author utente
 */
public class GetPaymentsTableServlet extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out = response.getWriter();
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
            int start = 0;
            int amount = 10;
            String sStart = request.getParameter("iDisplayStart");
            String sAmount = request.getParameter("sAmount");
            String sEcho = request.getParameter("sEcho");
            if (sStart != null) {
                start = Integer.parseInt(sStart);
                if (start < 0) {
                    start = 0;
                }
            }
            if (sAmount != null) {
                amount = Integer.parseInt(sAmount);
                if (amount < 10) {
                    amount = 10;
                }
            }

            PaymentBean searchPayment = checkSearchParameters(request);
            searchPayment.setId(-1);                // setto a -1 cosi la search non considera l'id
            searchPayment.setAmount(-1);            // idem
            searchPayment.setDiscount(-1);          // idem
            searchPayment.setChargeUsable(false);   // idem
            searchPayment.setPaidUsable(false);     // idem
            List<PaymentBean> paymentsList = paymentManager.search(searchPayment);

            PaymentBean[] paginatePaymentSet;
            int linksNumber = paymentsList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginatePaymentSet = new PaymentBean[amount];
                    System.arraycopy(paymentsList.toArray(), start, paginatePaymentSet, 0, amount);
                } else {
                    paginatePaymentSet = new PaymentBean[toShow];
                    System.arraycopy(paymentsList.toArray(), start, paginatePaymentSet, 0, toShow);
                }
                for (PaymentBean payment : paginatePaymentSet) {
                    JSONObject jObj = new JSONObject();
                    
                    double amountPayment = payment.getAmount();
                    double discountPayment = payment.getDiscount();
                    double amountDuePayment = amountPayment - (amountPayment * discountPayment / 100);
                    
                    jObj.put("0", unparseGregorianCalendar(payment.getExpDate()));
                    checkAddToJSON(jObj, "1", payment.getPaymentDescription());
                    jObj.put("2", amountPayment);
                    jObj.put("3", discountPayment);
                    checkAddToJSON(jObj, "4", payment.getDiscountDescription());
                    jObj.put("5", amountDuePayment);
                    jObj.put("6", payment.isCharge() ? "Si" : "No");
                    jObj.put("7", payment.isPaid() ? "Si" : "No");
                    
                    jObj.put("DT_RowId", "" + payment.getId());
                    
                    array.put(jObj);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", linksNumber);
            result.put("iTotalDisplayRecords", linksNumber);
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
            Logger.getLogger(GetPaymentsTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetPaymentsTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private PaymentBean checkSearchParameters(HttpServletRequest pRequest) {
        PaymentBean payment = new PaymentBean();
        
        String parentIdParameter = pRequest.getParameter("parentId");
        int parentId = 0;
        try {
            parentId = Integer.parseInt(parentIdParameter);
        } catch (NumberFormatException e) {
            Logger.getLogger(GetPaymentsTableServlet.class.getName()).log(Level.SEVERE, "Cannot parse this parentId: " + parentIdParameter, e);
        }
        
        payment.setParentId(parentId);
        return payment;
    }
    
    private String unparseGregorianCalendar(GregorianCalendar pDate) {
        if (pDate != null) {
            return pDate.get(GregorianCalendar.YEAR) + "-"
                    + (pDate.get(GregorianCalendar.MONTH) + 1) + "-"
                    + pDate.get(GregorianCalendar.DAY_OF_MONTH);
        } else {
            return null;
        }
    }
    
    private void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, "");
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
