/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.communicationManagement.newsManagement.News;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
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

public class UpdateTimeServiceServlet extends HttpServlet {
private ITimeServiceManager timeServiceManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        timeServiceManager = (ITimeServiceManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_TIMESERVICE);
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
            HttpSession session=request.getSession();
            String timeService=request.getParameter("orarioDiServizio");
            News news=new News();
            news.setDelegate(((Account)session.getAttribute("user")).getId());
            news.setId(Integer.parseInt(request.getParameter("idNews")));
            news.setDescription(timeService);
            news.setType("OrarioDiServizio");
            news.setDate(new GregorianCalendar());
            news.setTime(new Time(0,0,0));
            timeServiceManager.update(news);
           
            request.getServletContext().getRequestDispatcher("/timeService.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTimeServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
