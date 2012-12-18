/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.paymentManagement.servlet.InsertPaymentServlet;
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
                CommonMethod.sendMessageRedirect(request, response, "Errore: bambino selezionato non corretto - " + childId, "/canteenParent.jsp");
                return;
            }

            String sickness = request.getParameter("sicknessArea").trim();
            if (sickness.length() > MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo malattie troppo lungo.", "/canteenParent.jsp");
                return;
            }

            String note = request.getParameter("noteArea").trim();
            if (note.length() > MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo note troppo lungo.", "/canteenParent.jsp");
                return;
            }

            accessFacade.modifySickness(childId, sickness);
            accessFacade.modifyAdditionalNotes(childId, note);

            CommonMethod.sendMessageRedirect(request, response, "Dati inseriti con successo.", "/canteenParent.jsp");

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
