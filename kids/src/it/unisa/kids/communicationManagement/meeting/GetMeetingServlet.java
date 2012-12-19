/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pasquale
 */
public class GetMeetingServlet extends HttpServlet {

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
    private IMeetingManager am;

    public void init(ServletConfig config) {
        am = (IMeetingManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REUNION);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            ArrayList<Meeting> list = am.getMeetingList();

            String var = request.getParameter("Id");

            Account acc = (Account) request.getSession().getAttribute("user");
            String account = acc.getAccountType();

            int k = Integer.parseInt(var);

            for (Meeting r : list) {
                if (r.getId() == k) {
                    if (account.equals("Admin") || account.equals("Segreteria") || account.equals("Delegato del rettore") || account.equals("Responsabile Asilo")) {
                        out.println(r.getId() + "," + r.getTitle() + "," + r.getDescription() + "," + r.getDate() + "," + r.getFirstTime() + "," + r.getSecondTime() + "," + r.getType() + "," + r.getState());
                    } else {
                        out.println(r.getId() + "," + r.getTitle() + "," + r.getDescription() + "," + r.getDate() + "," + r.getFirstTime() + "," + r.getSecondTime() + "," + r.getType());
                    }
                }
            }

            // request.setAttribute("#sceltaMeeting", "non lo so");
            // request.getServletContext().getRequestDispatcher("/meetingCalendar.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(LoadCalendarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetMeetingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
