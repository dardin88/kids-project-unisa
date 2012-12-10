/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.meeting;

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
            int firstOra = 0;
            int firstMinuti = 0;
            int secondOra = 0;
            int secondMinuti = 0;
            int Ora = 0;
            int Min = 0;

            for (Reunion r : list) {
                if (r.getId() == id) {
                    if (giorni != 0) {
                        String day = r.getDate();
                        String[] result = day.split("-");
                        int res = Integer.parseInt(result[2]);
                        res = res + giorni;
                        String newDay = Integer.toString(res);
                        meeting.setDate(result[0] + "-" + result[1] + "-" + newDay);
                    } else {
                        meeting.setDate(r.getDate());
                    }
                    if (minuti != 0) {
                        String firstMin = r.getFirstTime();
                        String secondMin = r.getSecondTime();
                        String[] first = firstMin.split(":");
                        firstOra = Integer.parseInt(first[0]);
                        firstMinuti = Integer.parseInt(first[1]);
                        String[] second = secondMin.split(":");
                        secondOra = Integer.parseInt(second[0]);
                        secondMinuti = Integer.parseInt(second[1]);
                        Ora = (Math.abs(minuti)) / 60;
                        Min = (Math.abs(minuti)) % 60;
                        System.out.println("Minuti : " + minuti + " OAbs: " + Ora + " MAbs: " + Min);
                        System.out.println("FMin: " + firstMinuti + " SMin: " + secondMinuti + "  ORA: " + Ora + " MIN: " + Min);
                        if (minuti < 0) {
                            if (Math.abs(minuti) == 30) {
                                if (firstMinuti == 0) {
                                    firstMinuti = 60;
                                    firstOra--;
                                }
                                if (secondMinuti == 0) {
                                    secondMinuti = 60;
                                    secondOra--;
                                }


                                if (firstMinuti == 15) {
                                    firstMinuti = 75;
                                    firstOra--;
                                }
                                if (secondMinuti == 15) {
                                    secondMinuti = 75;
                                    secondOra--;
                                }
                            }


                            firstOra -= Ora;
                            firstMinuti -= Min;
                            secondOra -= Ora;
                            secondMinuti -= Min;
                            meeting.setFirstTime(firstOra + ":" + firstMinuti + ":00");
                            meeting.setSecondTime(secondOra + ":" + secondMinuti + ":00");
                        } else {
                            firstOra += Ora;
                            firstMinuti += Min;
                            secondOra += Ora;
                            secondMinuti += Min;
                            if (firstMinuti == 60) {
                                firstMinuti = 0;
                                firstOra++;
                            }
                            if (secondMinuti == 60) {
                                secondMinuti = 0;
                                secondOra++;
                            }
                            if (firstMinuti == 75) {
                                firstMinuti = 45;
                                firstOra++;
                            }
                            if (secondMinuti == 75) {
                                secondMinuti = 45;
                                secondOra++;
                            }


                            meeting.setFirstTime(firstOra + ":" + firstMinuti + ":00");
                            meeting.setSecondTime(secondOra + ":" + secondMinuti + ":00");
                        }

                        System.out.println(firstOra + ":" + firstMinuti + ":00");
                    } else {
                        meeting.setFirstTime(r.getFirstTime());
                        meeting.setSecondTime(r.getSecondTime());
                    }

                    meeting.setId(r.getId());
                    meeting.setTitle(r.getTitle());
                    meeting.setDescription(r.getDescription());
                    meeting.setType(r.getType());
                    am.update(meeting);
                    break;
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
