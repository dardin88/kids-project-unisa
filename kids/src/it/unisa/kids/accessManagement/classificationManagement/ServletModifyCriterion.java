package it.unisa.kids.accessManagement.classificationManagement;

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
public class ServletModifyCriterion extends HttpServlet {
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
        boolean isSuccess = false;
        
        try {
            String sId = request.getParameter(DBNames.ATT_CLASSIFICATION_ID);
            String sDescription = request.getParameter(DBNames.ATT_CRITERIA_DESCRIPTION);
            String sComparator = request.getParameter(DBNames.ATT_CRITERIA_COMPARATOR);
            String sCondition = request.getParameter(DBNames.ATT_CRITERIA_CONDITION);
            String sWight = request.getParameter(DBNames.ATT_CRITERIA_WEIGHT);
            String sActive = request.getParameter(DBNames.ATT_CRITERIA_ACTIVE);
            
            // campi necessari per prelevare le informazioni
            if(sId != null && !sId.equals("")) {
                Criterion tmpCriterion = new Criterion();
                tmpCriterion.setId(Integer.parseInt(sId));
                
                if(sDescription != null && !sDescription.equals("")) {
                    tmpCriterion.setDescription(sDescription);
                }
                if(sComparator != null && !sComparator.equals("")) {
                    tmpCriterion.setComparator(sComparator);
                }
                if(sCondition != null && !sCondition.equals("")) {
                    tmpCriterion.setCondition(sCondition);
                }
                if(sWight != null && !sWight.equals("")) {
                    tmpCriterion.setWeight(Double.parseDouble(sWight));
                }
                if(sActive != null && !sActive.equals("")) {
                    tmpCriterion.setActive(Boolean.parseBoolean(sActive));
                }
                isSuccess = classificationManager.updateCriterion(tmpCriterion);
            } else {
                errorMsg = "Errore nella passaggio dei parametri";
            }
        } catch(SQLException ex) {
            Logger.getLogger(ServletModifyCriterion.class.getName()).log(Level.SEVERE, null, ex);
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
