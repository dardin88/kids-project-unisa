package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 * Servlet used to create a new draft of registrationchild
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class ServletEditPhaseRegistrationChild extends HttpServlet {
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
            throws ServletException, IOException, SQLException, ParseException {
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        boolean isSuccess = false;
        String errorMsg = new String();
        
        try {
            // Prelevo i dati necessari
            String id = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID);
            String registrationPhase = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE);
            
            RegistrationChild tmpChild = new RegistrationChild();
            tmpChild.setId(Integer.parseInt(id));
            switch(registrationPhase) {
            case    DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_SUBMITTED :
                isSuccess = registrationChildManager.submitRegistrationChild(tmpChild);
                break;
            case    DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_RECEIPT :
                isSuccess = registrationChildManager.confirmReceiptRegistrationChild(tmpChild);
                break;
            case    DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_DELETED :
                // se la domanda di iscrizione è una bozza, la si elimina definitivamente,
                // altrimenti la si cambia in "eliminata" per conservarne i dati
                tmpChild = registrationChildManager.search(tmpChild).get(0);
                if(tmpChild.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_DRAFT)) {
                    isSuccess = registrationChildManager.delete(tmpChild);
                } else {
                    isSuccess = registrationChildManager.removeRegistrationChild(tmpChild);
                }
                break;
            default :
                errorMsg = "Parametro 'FaseDellIscrizione' errato!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletEditPhaseRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletEditPhaseRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletEditPhaseRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletEditPhaseRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletEditPhaseRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
