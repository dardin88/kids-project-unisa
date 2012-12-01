/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;

import java.io.IOException;
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
public class InsertTraineeServlet extends HttpServlet {

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
        try {
            Account trainee = new Account();
            if (request.getParameter("Nome").equals("") || request.getParameter("Indirizzo").equals("") || request.getParameter("Matricola").equals("") || request.getParameter("CittaNascita").equals("") || request.getParameter("CAP").equals("") || request.getParameter("CittaResidenza").equals("") || request.getParameter("Email").equals("") || request.getParameter("Cognome").equals("") || request.getParameter("DataNascita").equals("")) {
                trainee.setState("Bozza");
            } else {
                trainee.setState("Inserito");
            }
            trainee.setAccountType("8");
            trainee.setNameUser(request.getParameter("Nome"));
            trainee.setViaResidence(request.getParameter("Indirizzo"));
            trainee.setRegister(request.getParameter("Matricola"));
            trainee.setPlaceofBirth(request.getParameter("CittaNascita"));
            trainee.setCapResidence(request.getParameter("CAP"));
            trainee.setMunicipalityResidence(request.getParameter("CittaResidenza"));
            trainee.setEmail(request.getParameter("Email"));
            trainee.setSurnameUser(request.getParameter("Cognome"));
            trainee.setCellularNumber(request.getParameter("NumeroTelefonico"));
            if (!request.getParameter("DataNascita").equals("")) {
                trainee.setDataOfBirth(parseGregorianCalendar(request.getParameter("DataNascita")));

            }
            trainee.setQualification(request.getParameter("TitoloStudio"));
            trainingManager.insert(trainee);
            request.setAttribute("message",
                    "Tirocinante inserito con successo");
            request.getServletContext().getRequestDispatcher("/formationSciencePage.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("message",
                    "Tirocinante non inserito");
            request.getServletContext().getRequestDispatcher("/trainees.jsp").forward(request, response);

        } catch (ParseException e) {
            Logger.getLogger(InsertTraineeServlet.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
