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
public class RemoveTraineeActivityServlet extends HttpServlet {

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
        
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            System.out.println("Id"+id);
            TraineeActivity traineeActivity=new TraineeActivity();
            traineeActivity.setId(id);
            trainingManager.delete(traineeActivity);

            request.setAttribute("message",
                    "Attivit&agrave eliminata con successo");
            request.getServletContext().getRequestDispatcher("/secretaryPage.jsp").forward(request, response);
            
        } catch(SQLException e){
            e.printStackTrace();
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