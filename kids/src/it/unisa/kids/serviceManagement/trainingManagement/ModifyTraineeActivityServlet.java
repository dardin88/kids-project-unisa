/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author utente
 */
public class ModifyTraineeActivityServlet extends HttpServlet {
 private static Logger logger = Logger.getLogger("global");
    private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTrainingManager = RefinedAbstractManager.getInstance();
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
             TraineeActivity traineeActivity=new TraineeActivity();
             Account account=new Account();
             account.setRegister(request.getParameter("MatricolaTirocinante"));
             List<Account> list=trainingManager.search(account);
             if(list==null){
                                  throw new SQLException();

             }
             else
                traineeActivity.setTrainee(list.get(0).getId());
            traineeActivity.setId(Integer.parseInt(request.getParameter("id")));
            traineeActivity.setName(request.getParameter("Attivita"));
            traineeActivity.setDescription(request.getParameter("Descrizione"));
            traineeActivity.setDate(parseGregorianCalendar(request.getParameter("Data")));
            traineeActivity.setStart(parseTime(request.getParameter("OraInizio")));
            traineeActivity.setEnd(parseTime(request.getParameter("OraFine")));
            traineeActivity.setOpinion(request.getParameter("Giudizio"));
            
            trainingManager.update(traineeActivity);
            request.setAttribute("message",
                    "Richiesta modificata con successo");
            request.getServletContext().getRequestDispatcher("/managerTraineeActivity.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ModifyTraineeActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            request.setAttribute("message",
                    "Tirocinante non trovato");
            request.getServletContext().getRequestDispatcher("/managerTraineeActivity.jsp").forward(request, response);
        } finally {            
            out.close();
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
