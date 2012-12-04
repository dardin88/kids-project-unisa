/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author utente
 */
@WebServlet(name = "InsertTraineeActivityServlet", urlPatterns = {"/InsertTraineeActivity"})
public class InsertTraineeActivityServlet extends HttpServlet {

    private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTrainingManager =  RefinedAbstractManager.getInstance();
        trainingManager = (ITrainingManager) refinedAbstractTrainingManager.getManagerImplementor(DBNames.TABLE_TRAINEE);
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
            TraineeActivity traineeActivity = new TraineeActivity();
            System.out.println(request.getParameter("Tirocinante"));
            traineeActivity.setName(request.getParameter("Nome"));
            traineeActivity.setDate(parseGregorianCalendar(request.getParameter("Data")));
            traineeActivity.setDescription(request.getParameter("Descrizione"));
            traineeActivity.setTrainee(Integer.parseInt(request.getParameter("Tirocinante")));
            traineeActivity.setStart(parseTime(request.getParameter("OraInizio")));
            traineeActivity.setEnd(parseTime(request.getParameter("OraFine")));
            traineeActivity.setDelegate(Integer.parseInt(request.getParameter("AccountDelegato")));
            trainingManager.insert(traineeActivity);
            request.setAttribute("message",
                    "Attivit&agrave inserito con successo");
            request.getServletContext().getRequestDispatcher("/secretaryPage.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("message",
                    "Verfica i campi");
            request.getServletContext().getRequestDispatcher("/insertTraineeActivity.jsp").forward(request, response);
            Logger.getLogger(InsertTraineeActivityServlet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException e) {
            request.setAttribute("message",
                    "Verfica i campi");
            request.getServletContext().getRequestDispatcher("/insertTraineeActivity.jsp").forward(request, response);
            Logger.getLogger(InsertTraineeActivityServlet.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }
    private Time parseTime(String pTime) throws ParseException{
        DateFormat df = new SimpleDateFormat("hh:mm");
        Date parsed=df.parse(pTime);
        Time time=new Time(parsed.getTime());
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