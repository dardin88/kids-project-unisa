/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Panariello
 */
public class GetSectionEduClassDivsServlet extends HttpServlet {

    private IAccessFacade accessFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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
            List<ClassBean> classList;
            Account userLoggedIn = (Account) request.getSession().getAttribute("user");
            if (userLoggedIn.getAccountType().equals("Genitore")) {
                RegistrationChild searchRegChild = new RegistrationChild();
                searchRegChild.setParentId(userLoggedIn.getId());
                List<RegistrationChild> loggedInUserChildren = accessFacade.search(searchRegChild);
                
                ClassBean searchClass = new ClassBean();
                searchClass.setState("accettata");
                Map<Integer, ClassBean> classMap = new HashMap<Integer, ClassBean>();
                for (RegistrationChild rc : loggedInUserChildren) {
                    searchClass.setIdClasse(rc.getSectionId());
                    List<ClassBean> childClass = accessFacade.search(searchClass);
                    if (!childClass.isEmpty()) {
                        classMap.put(childClass.get(0).getIdClasse(), childClass.get(0));
                    }
                }
                classList = new ArrayList<ClassBean>();
                for (Integer i : classMap.keySet()) {
                    classList.add(classMap.get(i));
                }
            } else {
                classList = accessFacade.getClasses();
            }
            
            for (ClassBean clas : classList) {
                out.println("<div id=\"" + clas.getIdClasse() + "\" class=\"sectionEduClass\">\n"
                        + "     <table id=\"table" + clas.getIdClasse() + "\">\n"
                        + "         <thead>\n"
                        + "             <tr>\n"
                        + "                 <th>Attivit&agrave;</th>\n"
                        + "                 <th>Contenuto</th>\n"
                        + "                 <th>Data inizio</th>\n"
                        + "                 <th>Data fine</th>\n"
                        + "                 <c:if test=\"${sessionScope.user.getAccountType() != 'Genitore'}\">\n"
                        + "                 <th>Operazioni</th>\n"
                        + "                 </c:if>\n"
                        + "             </tr>\n"
                        + "         </thead>\n"
                        + "         <tbody></tbody>\n"
                        + "     </table>\n"
                        + "     <input type=\"button\" value=\"Inserisci Attivit&agrave;\" id=\"insAct" + clas.getIdClasse() + "\">\n"
                        + "     <script type=\"text/javascript\">\n"
                        + "         buildClassTable(" + clas.getIdClasse() + ");\n"
                        + "         buildActButton(" + clas.getIdClasse() + ");\n"
                        + "     </script>\n"
                        + "     <c:if test=\"${sessionScope.user.getAccountType() != 'Responsabile Scientifico'}\">\n"
                        + "     <div style=\"padding-top: 20px;\">\n"
                        + "         <h1>Commenti</h1>\n"
                        + "         <table id=\"comm" + clas.getIdClasse() + "\">\n"
                        + "             <thead>\n"
                        + "                 <tr>\n"
                        + "                     <th>Data</th>\n"
                        + "                     <th>Ora</th>\n"
                        + "                     <th>Contenuto</th>\n"
                        + "                     <th>Autore</th>\n"
                        + "                     <th>Operazioni</th>\n"
                        + "                 </tr>\n"
                        + "             </thead>\n"
                        + "             <tbody></tbody>\n"
                        + "         </table>\n"
                        + "         <input type=\"button\" value=\"Inserisci commento\" id=\"insComm" + clas.getIdClasse() + "\">\n"
                        + "     </div>\n"
                        + "     <script type=\"text/javascript\">\n"
                        + "         buildCommentEduTable(" + clas.getIdClasse() + ");\n"
                        + "         buildCommentButton(" + clas.getIdClasse() + ");\n"
                        + "     </script>\n"
                        + "     </c:if>\n"
                        + "</div>");
            }
        } catch (SQLException e) {
            Logger.getLogger(GetSectionEduClassDivsServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //out.close();
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
