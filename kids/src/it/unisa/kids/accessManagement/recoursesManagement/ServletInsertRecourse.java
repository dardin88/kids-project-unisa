package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.JDBCRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletInsertRecourse extends HttpServlet {
    private IRecoursesManager recourseManager;

    public void init(ServletConfig config) {
        recourseManager = (IRecoursesManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RECOURSE);
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
        JSONObject json = new JSONObject();
        boolean isSuccess = false;
        String errorMsg = new String();
        
        try {
            // Prelevo i dati necessari
            GregorianCalendar creationDate = new GregorianCalendar();
            creationDate.setTime(new Date(System.currentTimeMillis()));

            String motivo = request.getParameter(DBNames.ATT_RECOURSE_REASON);
            String sRegistrationChildId = request.getParameter(DBNames.ATT_RECOURSE_REGISTRATIONCHILDID);
            
            if(motivo != null && !motivo.equals("") && sRegistrationChildId != null && !sRegistrationChildId.equals("")) {
                // Creo il ricorso
                Recourse newRecourse = new Recourse();
                newRecourse.setDate(creationDate);
                newRecourse.setReason(motivo);
                newRecourse.setValutation(DBNames.ATT_RECOURSE_VALUTATION_TOEVALUATE);
                newRecourse.setRegistrationChildId(Integer.parseInt(sRegistrationChildId));
                
                // La inserisco nel db
                isSuccess = recourseManager.insert(newRecourse);
                // imposto la registrationchild come in "ricorso"
                RegistrationChild tmpRegistrationChild = new RegistrationChild();
                tmpRegistrationChild.setId(newRecourse.getRegistrationChildId());
                JDBCRegistrationChildManager.getInstance().recourseRegistrationChild(tmpRegistrationChild);
            } else {
                errorMsg = "Errore nel passaggio dei parametri";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletInsertRecourse.class.getName()).log(Level.SEVERE, "SQL-Error: " + ex.getMessage(), ex);
            errorMsg = ex.getMessage();
        }
        json.put("IsSuccess", isSuccess);
        json.put("ErrorMsg", errorMsg);

        out.write(json.toString());
        out.close();
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
