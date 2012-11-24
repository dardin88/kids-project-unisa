/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.childrenManagement;

import com.sun.istack.logging.Logger;
import com.sun.xml.ws.config.metro.parser.jsr109.String;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger.Level;

/**
 *
 * @author Elena
 */
public class ModifyCommunicationServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
           JDBCCommunicationManager man= JDBCCommunicationManager.getInstance();
           
            int aType = request.getParameter("typeCommunication");
            int aIdEducator = request.getParameter("idEducatorCommunication");
            int aIdChild = request.getParameter("idChildCommunication");
            String aDescription = request.getParameter("descriptionCommunication");
            GregorianCalendar aDate = request.getParameter("dateCommunication");
            boolean aSolved = request.getParameter("solvedCommunication");
           
           Communication a= new Communication();
            a.setType(aType); 
            a.setIdEducator(aIdEducator);
            a.setIdChild(aIdChild);
            a.setDescription(aDescription);
            a.setDate(aDate);
            a.setSolved(aSolved);
           
           man.modifyCommunication(a);
           
        } catch (SQLException ex) {
            Logger.getLogger(ModifyCommunicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {            
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(it.unisa.kids.accessManagement.accountManagement.ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(it.unisa.kids.accessManagement.accountManagement.ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
