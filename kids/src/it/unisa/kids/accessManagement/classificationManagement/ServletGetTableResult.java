package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ServletGetTableResult extends HttpServlet {
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
        
        Result[] paginateClassificationRequestSet;
        List<Result> listResult;
        // parametro di ricerca della tabella
        String searchTerm = request.getParameter("sSearch");
        
        try {
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
            
            String requestClassificationId = request.getParameter(DBNames.ATT_CLASSIFICATION_ID);
            String requestClassificationStatus = request.getParameter(DBNames.ATT_CLASSIFICATION_STATUS);
            
            if(requestClassificationId != null) {
                int classificationId = Integer.parseInt(requestClassificationId);
                Result toSearch = new Result();
                toSearch.setClassificationId(classificationId);
                listResult = classificationManager.searchResult(toSearch, searchTerm);
            } else {
                listResult = new ArrayList<Result>();
            }
            // I genitori visualizzano solo il risultato, la segreteria può modificarla
            Account account = (Account) request.getSession().getAttribute("user");

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
            int linksNumber = listResult.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateClassificationRequestSet = new Result[amount];
                    System.arraycopy(listResult.toArray(), start, paginateClassificationRequestSet, 0, amount);
                } else {
                    paginateClassificationRequestSet = new Result[toShow];
                    System.arraycopy(listResult.toArray(), start, paginateClassificationRequestSet, 0, toShow);
                }
                
                
                // CREAZIONE DELL'OUTPUT DELLA TABELLA - POPOLAMENTO
                int posizione = 1;
                double previousScore = 0;
                for (Result resultElement : paginateClassificationRequestSet) {
                    JSONArray ja = new JSONArray();
                    if(resultElement.getScore() < previousScore) { // la lista è ordinata in modo decrescente del punteggio
                        posizione++;
                    }
                    ja.put(posizione);
                    previousScore = resultElement.getScore();
                    
                    ja.put(resultElement.getRegistrationChildFiscalCode());
                    ja.put(resultElement.getRegistrationChildSurname());
                    ja.put(resultElement.getRegistrationChildName());
                    ja.put(resultElement.getScore());
                    
                    
                    if(account.getAccountType().equals("Segreteria") && !requestClassificationStatus.equals(DBNames.ATT_CLASSIFICATION_STATUS_DEFINITIVA)) {
                        String htmlResult = "<input type=\"checkbox\" onChange=\"aggiornaRisultatoResult(" + 
                                resultElement.getClassificationId() + ", " + resultElement.getRegistrationChildId() + ", " + resultElement.getResult() + ");\" ";
                        if(resultElement.getResult()) {
                            htmlResult += "checked=\"checked\"";
                        }
                        htmlResult += " />";
                        ja.put(htmlResult);
                    } else {
                        if(resultElement.getResult()) {
                            ja.put("Ammessa");
                        } else {
                            ja.put("Non ammessa");
                        }
                    }
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
            Logger.getLogger(ServletGetTableResult.class.getName()).log(Level.SEVERE, null, ex);
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
