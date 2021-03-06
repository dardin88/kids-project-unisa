package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddMeetingServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            JDBCMeetingManager am = JDBCMeetingManager.getInstance();
            Meeting meeting = new Meeting();

            meeting.setTitle(request.getParameter("meetingTitolo"));
            meeting.setDescription(request.getParameter("meetingDescrizione"));
            meeting.setDate(request.getParameter("meetingData"));
            meeting.setFirstTime(request.getParameter("meetingOraInizio"));
            meeting.setSecondTime(request.getParameter("meetingOraFine"));
            meeting.setType(request.getParameter("meetingTipo"));
            meeting.setState(request.getParameter("meetingStato"));
            am.insert(meeting);
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
