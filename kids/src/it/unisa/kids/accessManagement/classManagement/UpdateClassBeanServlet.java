package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.Educator;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
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
 * @author tonino
 */
public class UpdateClassBeanServlet extends HttpServlet {

    private IClassManager clasMan;
    private IRegistrationChildManager regMan;
    private IAccountManager accMan;

    @Override
    public void init(ServletConfig config) {
        clasMan = (IClassManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_CLASS);
        regMan = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
        accMan = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            boolean flag = false; //questo flag è necessario perchè senza quando cliccavo su accetta o richiedi modifica mi svuotava la classe
            ClassBean clas = new ClassBean();
            clas.setIdClasse(Integer.parseInt(request.getParameter("classId")));
            clas.setClassName(request.getParameter("className"));
            if (request.getParameter("draftClassButton") != null) {
                clas.setState("bozza");
            } else if (request.getParameter("submitClassButton") != null) {
                clas.setState("sottomessa");
            } else if (request.getParameter("isRequestModify") != null) {
                flag = true;
                clas.setState("revisione");
            } else if (request.getParameter("acceptedClassButton") != null) {
                clas.setState("accettata");
                flag = true;
            }
            clasMan.update(clas);
            if (flag == false) {
                ClassBean oldClas = new ClassBean();
                oldClas.setIdClasse(Integer.parseInt(request.getParameter("classId")));
                oldClas = clasMan.search(oldClas).get(0);
                List<RegistrationChild> oldChildren = oldClas.getBambini();
                String[] childrenChecked = request.getParameterValues("childRow");
                for (RegistrationChild c1 : oldChildren) {
                    regMan.setSectionRegistrationChild(c1, 0);
                }
                if (childrenChecked != null) {
                    for (int i = 0; i < childrenChecked.length; i++) {
                        RegistrationChild tmpRegChild = new RegistrationChild();
                        tmpRegChild.setId(Integer.parseInt(childrenChecked[i]));
                        regMan.setSectionRegistrationChild(tmpRegChild, clas.getIdClasse());
                    }
                }
                List<Account> oldEducators = accMan.searchEducatorByClass(clas);
                String[] educatorChecked = request.getParameterValues("educatorRow");
                for (Account a1 : oldEducators) {
                    accMan.unassignEducatorToClass(a1, clas);
                }
                if (educatorChecked != null) {
                    for (int i = 0; i < educatorChecked.length; i++) {
                        Account educator = new Educator();
                        educator.setId(Integer.parseInt(educatorChecked[i]));
                        accMan.assignEducatorToClass(educator, clas);
                    }
                }
            }
            response.sendRedirect("class.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClassBeanServlet.class.getName()).log(Level.SEVERE, null, ex);
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
