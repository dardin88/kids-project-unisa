package it.unisa.kids.accessManagement.renunciationManagement;

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
public class ServletDeleteRenunciation extends HttpServlet {
    private IRenunciationManager classificationManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        classificationManager = (IRenunciationManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_RENUNCIATION);
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
        boolean isSuccess = true;
        String errorMsg = new String();
        
        try {
            Renunciation tmpRenunciation = new Renunciation();
            
            String sId = request.getParameter(DBNames.ATT_RENUNCIATION_ID);
            // campi necessari per prelevare le informazioni
            if(sId != null && !sId.equals("")) {
                int id = Integer.parseInt(sId);
                
                tmpRenunciation.setId(id);
                
                isSuccess = classificationManager.delete(tmpRenunciation);
                
            } else {
                isSuccess = false;
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(ServletDeleteRenunciation.class.getName()).log(Level.SEVERE, null, ex);
            isSuccess = false;
            errorMsg = ex.getMessage();
        }
        
        json.put("IsSuccess", "" + isSuccess);
        json.put("ErrorMsg", errorMsg);
        
        System.out.println("Risultato della DeleteRenunciation: " + json.toString());

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