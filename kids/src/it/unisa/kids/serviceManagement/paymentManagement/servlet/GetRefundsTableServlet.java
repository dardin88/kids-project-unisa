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
import java.io.PrintWriter;
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
public class GetRefundsTableServlet extends HttpServlet {

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

            RefundBean searchRefund = checkSearchParameters(request);
            searchRefund.setId(-1);
            searchRefund.setAmount(-1);
            searchRefund.setPerformedUsable(false);
            List<RefundBean> refundsList = paymentManager.search(searchRefund);
            RefundBean[] paginateRefundSet;


            int linksNumber = refundsList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateRefundSet = new RefundBean[amount];
                    System.arraycopy(refundsList.toArray(), start, paginateRefundSet, 0, amount);
                } else {
                    paginateRefundSet = new RefundBean[toShow];
                    System.arraycopy(refundsList.toArray(), start, paginateRefundSet, 0, toShow);
                }
                for (RefundBean refund : paginateRefundSet) {
                    JSONObject jObj = new JSONObject();

                    CommonMethod.checkAddToJSON(jObj, "0", refund.getDescription());
                    jObj.put("1", refund.getAmount());

                    String acceptImage = "<img class=\"tableImage\" style=\"width:20px;height:20px\" title=\"Si\" alt=\"Si\" src=\"img/accept.png\" />";
                    String negateImage = "<img class=\"tableImage\" style=\"width:20px; height:20px;\" title=\"No\" alt=\"No\" src=\"img/negate.png\" />";
                    jObj.put("2", refund.isPerformed() ? acceptImage : negateImage);

                    jObj.put("DT_RowId", "" + refund.getId());

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
            Logger.getLogger(GetRefundsTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetRefundsTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private RefundBean checkSearchParameters(HttpServletRequest pRequest) {
        RefundBean refund = new RefundBean();

        String parentIdParameter = pRequest.getParameter("parentId");
        int parentId = 0;
        try {
            parentId = Integer.parseInt(parentIdParameter);
        } catch (NumberFormatException e) {
            Logger.getLogger(GetRefundsTableServlet.class.getName()).log(Level.SEVERE, "Cannot parse this parentId: " + parentIdParameter, e);
        }

        refund.setParentId(parentId);
        return refund;
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
