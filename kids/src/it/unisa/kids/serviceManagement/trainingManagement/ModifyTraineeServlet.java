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
 * @author Marco Moretti
 */
public class ModifyTraineeServlet extends HttpServlet {

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
            if (request.getParameter("NomeInf").equals("") || request.getParameter("IndirizzoInf").equals("") || request.getParameter("MatricolaInf").equals("") || request.getParameter("CittaNascitaInf").equals("") || request.getParameter("CAPInf").equals("") || request.getParameter("CittaResidenzaInf").equals("") || request.getParameter("EmailInf").equals("") || request.getParameter("CognomeInf").equals("") || request.getParameter("DataNascitaInf").equals("")) {
                trainee.setState("Bozza");
            } else {
                trainee.setState("Inserito");
            }
            trainee.setId(Integer.parseInt(request.getParameter("IdInf")));
            trainee.setNameUser(request.getParameter("NomeInf"));
            trainee.setViaResidence(request.getParameter("IndirizzoInf"));
            trainee.setRegister(request.getParameter("MatricolaInf"));
            trainee.setPlaceofBirth(request.getParameter("CittaNascitaInf"));
            trainee.setCapResidence(request.getParameter("CAPInf"));
            trainee.setMunicipalityResidence(request.getParameter("CittaResidenzaInf"));
            trainee.setEmail(request.getParameter("EmailInf"));
            trainee.setSurnameUser(request.getParameter("CognomeInf"));
            trainee.setCellularNumber(request.getParameter("NumeroTelefonicoInf"));
            trainee.setNickName(request.getParameter("nicknameInf"));
            trainee.setPassword(request.getParameter("passwordInf"));
            if (!request.getParameter("DataNascitaInf").equals("")) {
                trainee.setDataOfBirth(parseGregorianCalendar(request.getParameter("DataNascitaInf")));
            }
            trainee.setQualification(request.getParameter("TitoloStudioInf"));

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
