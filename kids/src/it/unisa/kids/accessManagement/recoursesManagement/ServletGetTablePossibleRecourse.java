package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.classificationManagement.Classification;
import it.unisa.kids.accessManagement.classificationManagement.IClassificationManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletGetTablePossibleRecourse extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;
    private IClassificationManager classificationManager;
    
    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRenunciationManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRenunciationManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
        classificationManager = (IClassificationManager) refinedAbstractRenunciationManager.getManagerImplementor(DBNames.TABLE_CLASSIFICATION);
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
        
        RegistrationChild[] pageRecourse = null;
        List<RegistrationChild> listPossibleRecourse;
        // parametro di ricerca della tabella
        String searchTerm = request.getParameter("sSearch");
        
        try {
            
            JSONArray array = new JSONArray();
            JSONObject result = new JSONObject();
            
            // Il genitore può presentare ricorsi solo se ci sono graduatorie provvisorie
            Classification tmpClassification = new Classification();
            tmpClassification.setStatus(DBNames.ATT_CLASSIFICATION_STATUS_PROVVISORIA);
            if(classificationManager.search(tmpClassification).size() > 0) {
                // L'output è in base alla tipologia dell'account
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("user");

                RegistrationChild tmp = new RegistrationChild();
                tmp.setParentId(account.getId());

                // Le domande di iscrizione per le quali è possibile presentare un ricorso sono quelle "ricevute"
                tmp.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_RECEIPT);
                listPossibleRecourse = registrationChildManager.search(tmp, searchTerm);
            
            } else {
                listPossibleRecourse = new ArrayList<>();
            }
            
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

            int linksNumber = listPossibleRecourse.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    pageRecourse = new RegistrationChild[amount];
                    System.arraycopy(listPossibleRecourse.toArray(), start, pageRecourse, 0, amount);
                } else {
                    pageRecourse = new RegistrationChild[toShow];
                    System.arraycopy(listPossibleRecourse.toArray(), start, pageRecourse, 0, toShow);
                }
                for(RegistrationChild possibileRicorso : pageRecourse) {
                    JSONArray ja = new JSONArray();
                    
                    ja.put(possibileRicorso.getFiscalCode());
                    ja.put(possibileRicorso.getSurname());
                    ja.put(possibileRicorso.getName());
                    
                    String operazioni = "<input class='tableImage' type='image' style=\"width:20px;height:20px\" " +
                                        "title=\"Presenta ricorso\" alt=\"Presenta ricorso\" " +
                                        "src='img/accept.png' onclick='openInsertRecourseWindow(\"" + possibileRicorso.getId() + "\")'/>";

                    ja.put(operazioni);
                    array.put(ja);
                    
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listPossibleRecourse.size());
            result.put("iTotalDisplayRecords", listPossibleRecourse.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetTablePossibleRecourse.class.getName()).log(Level.SEVERE, null, ex);
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
