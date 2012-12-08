package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class InsertCompiledSurveyServlet  extends HttpServlet {
    
    private PrintWriter out;
    
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
         
          HttpSession s = request.getSession();
          Account account =  (Account) s.getAttribute("user");
                
            try{
                response.setContentType("text/html;charset=UTF-8");
                ISurveyManager am = JDBCSurveyManager.getInstance();
                Survey sur = new Survey();
                int sId = Integer.parseInt(request.getParameter("idQuestionario"));
                int pId = Integer.parseInt(request.getParameter("idGenitore"));
                /* e se.. (perchè in effetti l'id del genitore si ricava dalla visualizzazione del questionario)
                 int parent = account.getId();
                 sur.setParentId(parent);
                 */
                boolean sCompiled = Boolean.parseBoolean(request.getParameter("Compilato"));
             //   String sLink = request.getParameter("Link"); 
                
                sur.setId(sId);
                sur.setParent(pId);
                sur.setCompiled(sCompiled);
         //       sur.setLink(sLink);
                
               
                int officer= account.getId();
                sur.setOfficer(officer);
                
                am.insert(sur); 
                
                
            }catch (Exception ex) {
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
