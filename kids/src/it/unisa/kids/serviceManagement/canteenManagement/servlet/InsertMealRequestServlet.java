/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MealRequestBean;
import it.unisa.kids.serviceManagement.paymentManagement.servlet.InsertPaymentServlet;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author navi
 */
public class InsertMealRequestServlet extends HttpServlet {

    private ICanteenManager canteenManager;

    public void init(ServletConfig config) {
        this.canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MENU);
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
        response.setContentType("text/html;charset=UTF-8");

        try {
            MealRequestBean mealReq = new MealRequestBean();

            int parentId = Integer.parseInt(request.getParameter("hiddenParentIdMealReq"));
            if (parentId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: genitore selezionato non corretto - " + parentId, "/canteenParent.jsp");
                return;
            }
            mealReq.setParentId(parentId);

            String reqDateStr = request.getParameter("requestDate");
            GregorianCalendar reqDate;
            try {
                reqDate = CommonMethod.parseGregorianCalendar(reqDateStr);
            } catch (Exception e) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: data inserita non corretta", "/canteenParent.jsp");
                return;
            }
            mealReq.setDate(reqDate);

            canteenManager.insert(mealReq);

            CommonMethod.sendMessageRedirect(request, response, "Richiesta pasto inviata con successo.", "/canteenParent.jsp");

        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/canteenParent.jsp");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/canteenParent.jsp");
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
