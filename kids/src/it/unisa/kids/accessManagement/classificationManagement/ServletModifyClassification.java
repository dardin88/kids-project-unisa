/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
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
public class ServletModifyClassification extends HttpServlet {
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
        boolean isSuccess;
        String errorMsg = new String();
        
        try {
            Classification tmpClassification = new Classification();
            // campi necessari per prelevare le informazioni
            if(!request.getParameter(DBNames.ATT_CLASSIFICATION_ID).equals("")) {
                int classificationId = Integer.parseInt(request.getParameter(DBNames.ATT_CLASSIFICATION_ID));
                tmpClassification.setId(classificationId);
                
                if(request.getParameter(DBNames.ATT_CLASSIFICATION_DATA) != null) {
                    GregorianCalendar data = CommonMethod.parseGregorianCalendar(request.getParameter(DBNames.ATT_CLASSIFICATION_DATA));
                    tmpClassification.setDate(data);
                }
                String nome = request.getParameter(DBNames.ATT_CLASSIFICATION_NAME);
                if(nome != null) {
                    tmpClassification.setName(nome);
                }
                String stato = request.getParameter(DBNames.ATT_CLASSIFICATION_STATUS);
                if(stato != null) {
                    tmpClassification.setStatus(stato);
                    if(stato.equals(DBNames.ATT_CLASSIFICATION_STATUS_DEFINITIVA)) {
                        // Si rendono Accettate le domande di iscrizione che hanno esito positivo (fase di notify (Observe design patter) al RegistrationChildManager
                        // Prendo l'elenco dei risultati
                        Result resultToSearch = new Result();
                        resultToSearch.setClassificationId(classificationId);
                        List<Result> risultati = classificationManager.searchResult(resultToSearch);
                        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
                        IRegistrationChildManager registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
                        RegistrationChild tmpChild = new RegistrationChild();
                        for(Result tmpResult : risultati) {
                            if(tmpResult.getResult()) { // Se la domanda di iscrizione è stata accettata
                                tmpChild.setId(tmpResult.getRegistrationChildId());
                                // la si imposta come 'accettata'
                                registrationChildManager.acceptRegistrationChild(tmpChild);
                                classificationManager.deleteAllResultFromDifferentClassification(tmpResult.getRegistrationChildId(), classificationId);
                            }
                        }
                    }
                }
                isSuccess = classificationManager.update(tmpClassification);
            } else {
                isSuccess = false;
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(ServletModifyClassification.class.getName()).log(Level.SEVERE, null, ex);
            isSuccess = false;
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
