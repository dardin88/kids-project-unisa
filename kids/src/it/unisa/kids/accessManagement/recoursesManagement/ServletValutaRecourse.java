package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.accessManagement.renunciationManagement.Renunciation;
import it.unisa.kids.accessManagement.renunciationManagement.ServletConfirmRenunciation;
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
public class ServletValutaRecourse extends HttpServlet {
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
            Recourse tmpRecourse = new Recourse();
            
            String sId = request.getParameter(DBNames.ATT_RENUNCIATION_ID);
            String sRegChildId = request.getParameter(DBNames.ATT_RENUNCIATION_REGISTRATIONCHILDID);
            String sValutazione = request.getParameter(DBNames.ATT_RECOURSE_VALUTATION);
            // campi necessari per prelevare le informazioni
            if(sId != null && !sId.equals("") && sRegChildId != null && !sRegChildId.equals("") &&
                    (sValutazione.equals(DBNames.ATT_RECOURSE_VALUTATION_TOEVALUATE) || sValutazione.equals(DBNames.ATT_RECOURSE_VALUTATION_ACCEPTED) ||
                    sValutazione.equals(DBNames.ATT_RECOURSE_VALUTATION_REFUSED))) {
                int id = Integer.parseInt(sId);
                int regChildId = Integer.parseInt(sRegChildId);
                tmpRecourse.setId(id);
                tmpRecourse.setValutation(sValutazione);
                
                isSuccess = recourseManager.update(tmpRecourse);
                
                // notifica al RegistrationChildManager
                RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
                IRegistrationChildManager registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
                
                RegistrationChild tmpRegistrationChild = new RegistrationChild();
                tmpRegistrationChild.setId(regChildId);
                isSuccess &= registrationChildManager.confirmReceiptRegistrationChild(tmpRegistrationChild);
                
                // se il ricorso viene accettato, il risultato nella graduatoria dovrebbe venir accettato
                // DA IMPLEMENTARE
                
                
                
            } else {
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(ServletConfirmRenunciation.class.getName()).log(Level.SEVERE, null, ex);
            errorMsg = ex.getMessage();
        }
        
        json.put("IsSuccess", "" + isSuccess);
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
