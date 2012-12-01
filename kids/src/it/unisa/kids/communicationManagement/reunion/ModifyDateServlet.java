/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.reunion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pasquale
 */
public class ModifyDateServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            JDBCReunionManager am = JDBCReunionManager.getInstance();
            ArrayList<Reunion> list = am.getMeetingList();
            Reunion meeting = new Reunion();

            int id = Integer.parseInt(request.getParameter("modifyDateId"));
            int giorni = Integer.parseInt(request.getParameter("modifyDateDay"));

            for (Reunion r : list) {
                if (r.getId() == id) {
                    String day = r.getDate();
                    String[] result = day.split("-");
                    int res = Integer.parseInt(result[2]);
                    res = res + giorni;
                    String newDay = Integer.toString(res);
                    meeting.setId(r.getId());
                    meeting.setTitle(r.getTitle());
                    meeting.setDescription(r.getDescription());
                    meeting.setDate(result[0] + "-" + result[1] + "-" + newDay);
                    meeting.setFirstTime(r.getFirstTime());
                    meeting.setSecondTime(r.getSecondTime());
                    meeting.setType(r.getType());
                    am.update(meeting);
                }
            }
            request.getServletContext().getRequestDispatcher("/meetingCalendar.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddMeetingServlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
