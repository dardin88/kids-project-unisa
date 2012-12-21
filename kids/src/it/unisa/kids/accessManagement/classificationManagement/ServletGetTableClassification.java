package it.unisa.kids.accessManagement.classificationManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.CommonMethod;
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
public class ServletGetTableClassification extends HttpServlet {
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
        
        Classification[] paginateClassificationRequestSet;
        List<Classification> listClassification;
        // parametro di ricerca della tabella
        String searchTerm = request.getParameter("sSearch");
        
        try {
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
            // L'output è in base alla tipologia dell'account
            Account account = (Account) request.getSession().getAttribute("user");
            
            switch (account.getAccountType()) {
                case "Genitore":
                    // Il genitore può vedere solo le classifiche provvisorie e definitive
                    Classification classification = new Classification();
                    classification.setStatus(DBNames.ATT_CLASSIFICATION_STATUS_PROVVISORIA);
                    
                    listClassification = classificationManager.search(classification, searchTerm);

                    classification.setStatus(DBNames.ATT_CLASSIFICATION_STATUS_DEFINITIVA);
                    listClassification.addAll(classificationManager.search(classification, searchTerm));
                    break;
                case "Segreteria":
                    // La segreteria potrà vederle tutte
                    listClassification = classificationManager.search(new Classification(), searchTerm);
                    
                    break;
                default:
                    listClassification = new ArrayList<Classification>();
                    break;
            }
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
            int linksNumber = listClassification.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateClassificationRequestSet = new Classification[amount];
                    System.arraycopy(listClassification.toArray(), start, paginateClassificationRequestSet, 0, amount);
                } else {
                    paginateClassificationRequestSet = new Classification[toShow];
                    System.arraycopy(listClassification.toArray(), start, paginateClassificationRequestSet, 0, toShow);
                }
                
                // CREAZIONE DELL'OUTPUT DELLA TABELLA - POPOLAMENTO
                for (Classification classificationElement : paginateClassificationRequestSet) {
                    JSONArray ja = new JSONArray();
                    ja.put(CommonMethod.parseString(classificationElement.getDate()));
                    ja.put(classificationElement.getName());
                    ja.put(classificationElement.getStatus());
                    
                    StringBuffer operazioni = new StringBuffer();
                    // Sia genitore che segreteria possono vederne i dettagli, la visualizza dettagli deve esser possibile su tutte le domanda
                    operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Visualizza Dettagli\" alt=\"Dettagli\" src='img/lente.gif' onclick='viewDetailsClassification(\""+classificationElement.getId()+"\")'/>");
                    
                    if(account.getAccountType().equals("Segreteria")) {
                        // solo il genitore può eliminarla o modificarla
                        if(classificationElement.getStatus().equals(DBNames.ATT_CLASSIFICATION_STATUS_BOZZA)) {
                            operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Modifica\" alt=\"Modifica\" src='img/edit.gif' onclick='openWindowModifyClassification(\""+classificationElement.getId()+"\")'/>");
                        }
                        if(classificationElement.getStatus().equals(DBNames.ATT_CLASSIFICATION_STATUS_BOZZA)) {
                            operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Elimina\" alt=\"Elimina\" src='img/trash.png' onclick='openWindowDeleteClassification(\"" + classificationElement.getId() + "\")'/>");
                        }
                        if(classificationElement.getStatus().equals(DBNames.ATT_CLASSIFICATION_STATUS_BOZZA)) {
                            operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Rendi Provvisoria\" alt=\"Rendi Provvisoria\" src='img/accept.png' onclick='openWindowToProvvisoriaClassification(\"" + classificationElement.getId() + "\")'/>");
                        }
                        if(classificationElement.getStatus().equals(DBNames.ATT_CLASSIFICATION_STATUS_PROVVISORIA)) {
                            operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Rendi Definitiva\" alt=\"Rendi Definitiva\" src='img/accept.png' onclick='openWindowToDefinitivaClassification(\"" + classificationElement.getId() + "\")'/>");
                        }
                    }
                    ja.put(operazioni.toString());
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
