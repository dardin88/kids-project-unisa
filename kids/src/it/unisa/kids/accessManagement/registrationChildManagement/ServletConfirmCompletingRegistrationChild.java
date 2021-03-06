package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class ServletConfirmCompletingRegistrationChild extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
            int id = Integer.parseInt(request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID));
            String malattie = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ISSICKNESSSET);
            String vaccinazioni = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ISVACCINATIONSSET);
            String privacy = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ISPRIVACYSTATEMENTSET);

            // Creo la domanda di iscrizione bambino
            RegistrationChild registrationChild = new RegistrationChild();
            registrationChild.setId(id);
            
            registrationChild = registrationChildManager.search(registrationChild).get(0);
            registrationChild.setIsSicknessSet(malattie);
            registrationChild.setIsVaccinationsSet(vaccinazioni);
            registrationChild.setIsPrivacyStatementSet(privacy);
            registrationChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_VALIDATED);
            
            // La inserisco nel db
            isSuccess = registrationChildManager.update(registrationChild);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, "SQL-Error: " + ex.getMessage(), ex);
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
