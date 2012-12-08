package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author felice
 */
public class UpdateSurveyServlet extends HttpServlet{
    
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
        try{
       
            PrintWriter out = response.getWriter();
            String oldLink = request.getParameter("oldLink");
            ISurveyManager am = JDBCSurveyManager.getInstance();
            Survey sur = new Survey();
            int sId = Integer.parseInt(request.getParameter("Id"));
            String sLink = request.getParameter("Link");
            
            sur.setId(sId);
            sur.setLink(sLink);
            
            HttpSession s = request.getSession();
            Account account = (Account) s.getAttribute("user");
            int idOfficer = account.getId();
            sur.setOfficer(idOfficer);
           
            am.update(sur);
            
             
        } catch(SQLException ex){
            Logger.getLogger(InsertSurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
