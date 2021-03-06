/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.JDBCAccountManager;
import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.classManagement.IClassManager;
import it.unisa.kids.accessManagement.classManagement.JDBCClassManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.ClassMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antonio Panariello
 */
public class GetClassTabsServlet extends HttpServlet {

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
        HttpSession s = request.getSession();
        Account user = (Account) s.getAttribute("user");
        IClassManager cm = JDBCClassManager.getInstance();
        try {
            if (!"Genitore".equals(user.getAccountType())) {
                List<ClassBean> classList = accessFacade.getClasses();
                for (ClassBean clas : classList) {
                    out.print("<li><a href=\"#" + clas.getIdClasse() + "\">" + clas.getClassName() + "</a></li>");
                }
            }else{
                RegistrationChild child = new RegistrationChild();
                child.setParentId(user.getId());
                List<RegistrationChild> children = accessFacade.search(child);
                int sectionId = children.get(0).getSectionId();
                ClassBean classToSearch = new ClassBean();
                classToSearch.setIdClasse(sectionId);
                ClassBean clas = cm.search(classToSearch).get(0);
                out.println("<li><a href=\"#" + clas.getIdClasse() + "\">" + clas.getClassName() + "</a></li>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetClassTabsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
