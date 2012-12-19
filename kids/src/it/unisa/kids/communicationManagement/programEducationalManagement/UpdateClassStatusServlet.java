/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Antonio Panariello
 */
public class UpdateClassStatusServlet extends HttpServlet {
    
    private IAccessFacade accessFacade;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accessFacade = new AccessFacade();
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jObj = new JSONObject();
        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            String classStatus = request.getParameter("classStatus");
            
            ClassBean searchClass = new ClassBean();
            searchClass.setIdClasse(classId);
            ClassBean classToUpdate = accessFacade.search(searchClass).get(0);
            if (classStatus.equals("Bozza")) {
                classToUpdate.setStatoProgetto("Bozza");
            } else if (classStatus.equals("Sottomessa")) {
                classToUpdate.setStatoProgetto("Sottomessa");
            } else if (classStatus.equals("RichiestaMod")) {
                classToUpdate.setStatoProgetto("RichiestaMod");
            } else if (classStatus.equals("AccettaRett")) {
                classToUpdate.setStatoProgetto("AccettaRett");
            } else if (classStatus.equals("AccettaScient")) {
                classToUpdate.setStatoProgetto("AccettaScient");
            }
            
            accessFacade.update(classToUpdate);
            
            jObj.put("message", "Richiesta effettuata");
            out.print(jObj);
        } catch (SQLException e) {
            jObj.put("message", "Errore durante la richiesta");
        } finally {
            out.close();
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
