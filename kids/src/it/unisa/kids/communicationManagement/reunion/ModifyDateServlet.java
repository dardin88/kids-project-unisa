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
            int minuti = Integer.parseInt(request.getParameter("modifyMinuteDay"));
            int firstOra;
            int firstMinuti;
            int secondOra;
            int secondMinuti;

            for (Reunion r : list) {
                if (r.getId() == id) {
                    if (giorni != 0) {
                        String day = r.getDate();
                        String[] result = day.split("-");
                        int res = Integer.parseInt(result[2]);
                        res = res + giorni;
                        String newDay = Integer.toString(res);
                        meeting.setDate(result[0] + "-" + result[1] + "-" + newDay);
                    }
                    if (minuti != 0) {
                        String firstMin = r.getFirstTime();
                        String secondMin = r.getSecondTime();
                        String[] first = firstMin.split(":");
                        firstOra = Integer.parseInt(first[0]);
                        firstMinuti = Integer.parseInt(first[1]);
                        String[] second = firstMin.split(":");
                        secondOra = Integer.parseInt(second[0]);
                        secondMinuti = Integer.parseInt(second[1]);
                        int Ora = Math.abs(minuti) / 60;
                        int Min = Math.abs(minuti) % 60;

                        if (minuti < 0) {
                            firstOra -= Ora;
                            firstMinuti -= Min;
                            secondOra -= Ora;
                            secondMinuti -= Min;
                        } else {
                            firstOra += Ora;
                            firstMinuti += Min;
                            secondOra += Ora;
                            secondMinuti += Min;
                        }
                        meeting.setFirstTime(firstOra + ":" + firstMinuti + ":00");
                        meeting.setSecondTime(secondOra + ":" + secondMinuti + ":00");
                        System.out.println(firstOra + ":" + firstMinuti + ":00");
                    }
                    meeting.setId(r.getId());
                    meeting.setTitle(r.getTitle());
                    meeting.setDescription(r.getDescription());
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
