/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.MailManager;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observer;
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
 * @author utente
 */
public class InsertConvocationServlet extends HttpServlet {

    private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTrainingManager = RefinedAbstractManager.getInstance();
        trainingManager =  (ITrainingManager) refinedAbstractTrainingManager.getManagerImplementor(DBNames.TABLE_TRAINEE);
        
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
            String activity = request.getParameter("Attivita");
            String date = request.getParameter("Data");
            String startTime = request.getParameter("OraInizio");
            String endTime = request.getParameter("OraFine");
            HttpSession session = request.getSession();
            Account delegate = (Account) session.getAttribute("user");
            int delegateId = delegate.getId();
            String[] trainees = request.getParameterValues("trainees");
            if (trainees == null) {
                request.setAttribute("message",
                        "Non hai selezionato nessun tirocinante");
                request.getServletContext().getRequestDispatcher("/formationScienceNotifications.jsp").forward(request, response);
                return;
            }
            for (int i = 0; i < trainees.length; i++) {
                TraineeConvocation convocation = new TraineeConvocation();
                convocation.setActivityName(activity);
                convocation.setDelegateId(delegateId);
                convocation.setTraineeId(Integer.parseInt(trainees[i]));
                convocation.setDate(parseGregorianCalendar(date));
                convocation.setStartTime(parseTime(startTime));
                convocation.setEndTime(parseTime(endTime));
                trainingManager.insert(convocation);

            }
            request.setAttribute("message",
                    "Convocazioni inviate con successo");
            request.getServletContext().getRequestDispatcher("/formationSciencePage.jsp").forward(request, response);




        } catch (SQLException ex) {
            Logger.getLogger(InsertConvocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(InsertConvocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }

    private Time parseTime(String pTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("hh:mm");
        Date parsed = df.parse(pTime);
        Time time = new Time(parsed.getTime());
        return time;
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
