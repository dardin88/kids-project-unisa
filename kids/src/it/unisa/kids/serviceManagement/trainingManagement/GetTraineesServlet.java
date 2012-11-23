/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
@WebServlet(name = "GetTraineesServlet", urlPatterns = {"/GetTrainees"})
public class GetTraineesServlet extends HttpServlet {
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
            String register=request.getParameter("matricola");
            Trainee trainee=new Trainee();
            trainee.setRegister(register);
            ArrayList<Trainee> list=trainingManager.search(trainee);
            request.setAttribute("nome", list.get(0).getName());
            request.setAttribute("cognome", list.get(0).getSurname());
            request.setAttribute("matricola", list.get(0).getRegister());
            GregorianCalendar birthDate=list.get(0).getBirthDate();
            request.setAttribute("dataNascita", birthDate.get(Calendar.DAY_OF_MONTH)+"/"+(birthDate.get(Calendar.MONTH)+1)+"/"+birthDate.get(Calendar.YEAR));
            request.setAttribute("cittaNascita", list.get(0).getBirthCity());
            request.setAttribute("cittaResidenza", list.get(0).getCityOfResidence());
            request.setAttribute("indirizzo", list.get(0).getAddress());
            request.setAttribute("CAP", list.get(0).getCap());
            request.setAttribute("numeroTelefonico", list.get(0).getTelephoneNumber());
            request.setAttribute("email", list.get(0).getEmail());

        } catch (SQLException ex) {
            Logger.getLogger(GetTraineesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
