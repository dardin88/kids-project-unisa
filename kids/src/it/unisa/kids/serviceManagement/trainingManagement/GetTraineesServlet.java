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
import java.util.ArrayList;
import java.util.Calendar;
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
public class GetTraineesServlet extends HttpServlet {

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
        PrintWriter o = null;
        try {
            o = response.getWriter();
            int id = Integer.parseInt(request.getParameter("traineeId"));
            Account trainee = new Account();
            trainee.setId(id);
            List<Account> list = trainingManager.search(trainee);
            /*request.setAttribute("nome", list.get(0).getNameUser());
             request.setAttribute("cognome", list.get(0).getSurnameUser());
             request.setAttribute("matricola", list.get(0).getRegister());
            
             request.setAttribute("dataNascita", birthDate.get(Calendar.DAY_OF_MONTH)+"/"+(birthDate.get(Calendar.MONTH)+1)+"/"+birthDate.get(Calendar.YEAR));
             request.setAttribute("cittaNascita", list.get(0).getPlaceOfBirth());
             request.setAttribute("cittaResidenza", list.get(0).getMunicipalityResidence());
             request.setAttribute("indirizzo", list.get(0).getViaResidence());
             request.setAttribute("CAP", list.get(0).getCapResidence());
             request.setAttribute("numeroTelefonico", list.get(0).getCellularNumber());
             request.setAttribute("email", list.get(0).getEmail());
             request.getServletContext().getRequestDispatcher("/trainees.jsp?prova=ciao").forward(request, response);
             */

            for (Account a : list) {
                GregorianCalendar birthDate = a.getDataOfBirth();
                System.out.println("Data:"+birthDate.get(Calendar.YEAR));
                if (birthDate !=null) {
                    String date=birthDate.get(Calendar.YEAR)+"-"+ (birthDate.get(Calendar.MONTH) + 1) +"-"+birthDate.get(Calendar.DAY_OF_MONTH)  ;
                    System.out.println(date);
                    o.println(a.getRegister() + "," + a.getNameUser() + "," + a.getSurnameUser() + "," +date + "," + a.getPlaceOfBirth() + "," + a.getMunicipalityResidence() + "," + a.getViaResidence() + "," + a.getCapResidence() + "," + a.getCellularNumber() + "," + a.getEmail() + "," + a.getQualification());
                } else {
                    o.println(a.getRegister() + "," + a.getNameUser() + "," + a.getSurnameUser() + "," +""+ a.getPlaceOfBirth() + "," + a.getMunicipalityResidence() + "," + a.getViaResidence() + "," + a.getCapResidence() + "," + a.getCellularNumber() + "," + a.getEmail() + "," + a.getQualification());
                }

            }

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
