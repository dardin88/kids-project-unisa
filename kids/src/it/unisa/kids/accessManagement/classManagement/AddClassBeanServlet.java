package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
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
 * @author tonino
 */
public class AddClassBeanServlet extends HttpServlet {

    private IRegistrationChildManager regMan;

    public void init(ServletConfig config) {
        regMan = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
        PrintWriter out = response.getWriter();
        try {
            JDBCClassManager clasMan = JDBCClassManager.getInstance();
            ClassBean clas = new ClassBean();
            clas.setIdClasse(0);
            clas.setClassName(request.getParameter(DBNames.ATT_CLASS_NAME));
            clas.setState(request.getParameter(DBNames.ATT_CLASS_STATE));
            clasMan.insert(clas);
            List<ClassBean> searchedClass = clasMan.search(clas);
            String[] childChecked = request.getParameterValues("childRow");
            for (int i = 0; i < childChecked.length; i++) {
                System.out.println(childChecked[i]);
                RegistrationChild tmpRegChild = new RegistrationChild();
                tmpRegChild.setId(Integer.parseInt(childChecked[i]));
                regMan.setSectionRegistrationChild(tmpRegChild, searchedClass.get(0).getIdClasse());
            }
            response.sendRedirect("classe.jsp");
        } catch (SQLException exeption) {
            request.setAttribute("message", "Verfica i campi");
            request.getServletContext().getRequestDispatcher("/classe.jsp").forward(request, response);
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
