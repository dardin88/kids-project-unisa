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
 * @author marco
 */
public class GetClassDivRegisterServlet extends HttpServlet {

    private IAccessFacade accessFacade;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<ClassBean> classList = accessFacade.getClasses();
            for (ClassBean clas : classList) {
                out.println("<div id=\"" + clas.getIdClasse() + "\" style=\"width:97%\">");
                out.println("<input type=\"button\" id=\"insertActivityButton"+clas.getIdClasse()+"\" value=\"Inserisci Attivit&agrave\" onclick=\"openInsertActivity('"+clas.getIdClasse()+"')\">");
                out.println("<table id=\"table" + clas.getIdClasse() + "\">\n"
                        + "                <thead>\n"
                        + "                    <tr>\n"
                        +"                         <th>Giorno</th>\n  "
                        + "                        <th>Nome Attvit&agrave</th>\n"
                        +"                         <th>Educatore</th>\n"
                        + "                        <th>Operazioni</th>\n"
                        + "\n"
                        + "                    </tr>\n"
                        + "                </thead>\n"
                        + "                <tbody>\n"
                        + "                </tbody>\n"
                        + "            </table>");
                out.println("</div>");
                out.println("<script text=\"text/javascipt\">buildTable(\"" + clas.getIdClasse() + "\");buildInsertButton(\""+clas.getIdClasse()+"\");</script>");

            }
            

        } catch (SQLException ex) {
            Logger.getLogger(GetClassTabsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           // out.close();
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