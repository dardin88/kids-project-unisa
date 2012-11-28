/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
@WebServlet(name = "ModifyTraineeServlet", urlPatterns = {"/ModifyTrainee"})
public class ModifyTraineeServlet extends HttpServlet {
private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractTrainingManager refinedAbstractTrainingManager = new RefinedAbstractTrainingManager();
        trainingManager = refinedAbstractTrainingManager.getManagerImplementor();
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
            Trainee trainee = new Trainee();

            trainee.setName(request.getParameter(DBNames.ATT_TRAINEE_NAME));
            trainee.setAddress(request.getParameter(DBNames.ATT_TRAINEE_ADDRESS));
            trainee.setRegister(request.getParameter(DBNames.ATT_TRAINEE_REGISTER));
            trainee.setBirthCity(request.getParameter(DBNames.ATT_TRAINEE_BIRTHCITY));
            trainee.setCap(request.getParameter(DBNames.ATT_TRAINEE_CAP));
            trainee.setCityOfResidence(request.getParameter(DBNames.ATT_TRAINEE_CITYOFRESIDENCE));
            trainee.setEmail(request.getParameter(DBNames.ATT_TRAINEE_EMAIL));
            trainee.setSurname(request.getParameter(DBNames.ATT_TRAINEE_SURNAME));
            trainee.setTelephoneNumber(request.getParameter(DBNames.ATT_TRAINEE_TELEPHONENUMBER));
            trainee.setBirthDate(parseGregorianCalendar(request.getParameter(DBNames.ATT_TRAINEE_BIRTHDATE)));
            trainingManager.update(trainee);
            request.setAttribute("message",
                    "Tirocinante modificato con successo");
            request.getServletContext().getRequestDispatcher("/formationSciencePage.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ModifyTraineeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ModifyTraineeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
        }
    }
    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
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
