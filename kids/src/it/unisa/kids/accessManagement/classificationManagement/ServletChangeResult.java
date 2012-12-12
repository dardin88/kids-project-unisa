/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.classificationManagement;

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
import org.json.JSONObject;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletChangeResult extends HttpServlet {
    private IClassificationManager classificationManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        classificationManager = (IClassificationManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_CLASSIFICATION);
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
        String errorMsg = new String();
        boolean isSuccess;
        
        try {
            
            String sClassId = request.getParameter("ClassificationId");
            String sRegChildId = request.getParameter("RegistrationChildId");
            String sNewResult = request.getParameter("Result");
            // campi necessari per prelevare le informazioni
            if(sClassId != null && !sClassId.equals("") && sRegChildId != null && !sRegChildId.equals("") && sNewResult != null && !sNewResult.equals("")) {
                int classificationId = Integer.parseInt(sClassId);
                int registrationChildId = Integer.parseInt(sRegChildId);
                boolean newResult = Boolean.parseBoolean(sNewResult);
                
                Result toUpdate = new Result();
                toUpdate.setClassificationId(classificationId);
                toUpdate.setRegistrationChildId(registrationChildId);
                toUpdate.setResult(newResult);
                
                isSuccess = classificationManager.updateResult(toUpdate);
                
            } else {
                isSuccess = false;
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(ServletChangeResult.class.getName()).log(Level.SEVERE, null, ex);
            isSuccess = false;
            errorMsg = ex.getMessage();
        }
        
        json.put("IsSuccess", "" + isSuccess);
        json.put("ErrorMsg", errorMsg);
        
        System.out.println("Risultato della ChangeResult: " + json.toString());

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
