/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccountFacade;
import it.unisa.kids.common.facade.IAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marco
 */
public class InsertDailyActivityServlet extends HttpServlet {

    private IActivityManager manager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        manager = (IActivityManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
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
        String note = request.getParameter("nota");
        HttpSession session = request.getSession();

        int idClass = Integer.parseInt(request.getParameter("idClass"));
        String[] attivita = request.getParameterValues("attivita" + idClass);
        System.out.println("Classe"+idClass);
        for (int i = 0; i < attivita.length; i++) {
            try {
                DailyActivitySection dailyActivitySection = new DailyActivitySection();
                dailyActivitySection.setData(new GregorianCalendar());
                dailyActivitySection.setIdActivity(Integer.parseInt(attivita[i]));
                dailyActivitySection.setNotes(note);
                dailyActivitySection.setIdSection(idClass);
                dailyActivitySection.setIdEducator(((Account) session.getAttribute("user")).getId());
                manager.insert(dailyActivitySection);
            } catch (SQLException ex) {
                Logger.getLogger(InsertDailyActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        request.setAttribute("message",
                "Attivita inserite con successo");
        request.getServletContext().getRequestDispatcher("/educatorPage.jsp").forward(request, response);
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
