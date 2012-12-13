/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MealRequestBean;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import it.unisa.kids.serviceManagement.paymentManagement.servlet.InsertPaymentServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
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
public class ModifySicknessServlet extends HttpServlet {
    
    private static final int MAXLENGTH = 200;
    private IAccessFacade accessFacade;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();
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
            int childId = Integer.parseInt(request.getParameter("childSelectModSick"));
            if (childId <= 0) {
                sendMessageRedirect(request, response, "Errore: bambino selezionato non corretto - " + childId);
                return;
            }

            String sickness = request.getParameter("sicknessArea").trim();
            if (sickness.length() > MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo malattie troppo lungo.");
                return;
            }

            String note = request.getParameter("noteArea").trim();
            if (note.length() > MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo note troppo lungo.");
                return;
            }

            accessFacade.modifySickness(childId, sickness);
            accessFacade.modifyAdditionalNotes(childId, note);

            sendMessageRedirect(request, response, "Dati inseriti con successo.");

        } catch (SQLException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void sendMessageRedirect(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/canteenParent.jsp").forward(request, response);
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