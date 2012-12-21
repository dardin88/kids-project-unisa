package it.unisa.kids.accessManagement.classificationManagement;

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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletGetTableCriteria extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        
        Criterion[] paginateCriterionRequestSet;
        List<Criterion> listCriterion;
        try {
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
            
            listCriterion = classificationManager.searchCriterion(new Criterion());
            
            // PRECONFIGURAZIONE DELLA TABELLA
            int start = 0;
            int amount = 10;
            String sStart = request.getParameter("iDisplayStart");
            String sAmount = request.getParameter("sAmount");
            String sEcho = request.getParameter("sEcho");
            if (sStart != null) {
                start = Integer.parseInt(sStart);
                if (start < 0) {
                    start = 0;
                }
            }
            if (sAmount != null) {
                amount = Integer.parseInt(sAmount);
                if (amount < 10) {
                    amount = 10;
                }
            }
            int linksNumber = listCriterion.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateCriterionRequestSet = new Criterion[amount];
                    System.arraycopy(listCriterion.toArray(), start, paginateCriterionRequestSet, 0, amount);
                } else {
                    paginateCriterionRequestSet = new Criterion[toShow];
                    System.arraycopy(listCriterion.toArray(), start, paginateCriterionRequestSet, 0, toShow);
                }
                
                // CREAZIONE DELL'OUTPUT DELLA TABELLA - POPOLAMENTO
                for(Criterion criterionElement : paginateCriterionRequestSet) {
                    JSONArray ja = new JSONArray();
                    ja.put(criterionElement.getDescription());
                    ja.put(criterionElement.getDbField());
                    ja.put(criterionElement.getComparator());
                    ja.put(criterionElement.getCondition());
                    ja.put(criterionElement.getWeight());
                    String htmlActive = "<input type=\"checkbox\" onChange=\"changeCriterionActive(" + criterionElement.getId() + ", " + !criterionElement.getActive() + ");\"";
                    if(criterionElement.getActive()) {
                            htmlActive += " checked=\"checked\"";
                        }
                        htmlActive += " />";
                    ja.put(htmlActive);
                    
                    String operazioni = "<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Elimina\" alt=\"Elimina\" src='img/trash.png' onclick='deleteCriteriaWindow(" + criterionElement.getId() + ")'/>";
                    ja.put(operazioni);
                    array.put(ja);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", linksNumber);
            result.put("iTotalDisplayRecords", linksNumber);
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
        } catch(SQLException ex) {
            Logger.getLogger(ServletGetTableClassification.class.getName()).log(Level.SEVERE, null, ex);
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
