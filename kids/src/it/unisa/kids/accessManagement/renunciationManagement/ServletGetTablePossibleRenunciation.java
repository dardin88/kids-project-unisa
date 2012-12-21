package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lauri Giuseppe Giovanni
 */
public class ServletGetTablePossibleRenunciation extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;
    private IRenunciationManager renunciationManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRenunciationManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRenunciationManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
        renunciationManager = (IRenunciationManager) refinedAbstractRenunciationManager.getManagerImplementor(DBNames.TABLE_RENUNCIATION);
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
        
        RegistrationChild[] pageRenunciation = null;
        List<RegistrationChild> listPossibleRenunciation;
        // parametro di ricerca della tabella
        String searchTerm = request.getParameter("sSearch");
        
        try {
            JSONArray array = new JSONArray();
            JSONObject result = new JSONObject();
            
            // L'output è in base alla tipologia dell'account
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
            
            RegistrationChild tmp = new RegistrationChild();
            tmp.setParentId(account.getId());
            
            // Le fasi in cui è possibile presentare domanda di rinuncia sono le seguenti
            tmp.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_ACCEPTED);
            listPossibleRenunciation = registrationChildManager.search(tmp, searchTerm);
            
            tmp.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_COMPLETED);
            listPossibleRenunciation.addAll(registrationChildManager.search(tmp, searchTerm));
            
            tmp.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE_VALIDATED);
            listPossibleRenunciation.addAll(registrationChildManager.search(tmp, searchTerm));
            
            List<Renunciation> listToNotDisplay = renunciationManager.getListFromParent(account.getId(), null);
            
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

            int linksNumber = listPossibleRenunciation.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    pageRenunciation = new RegistrationChild[amount];
                    System.arraycopy(listPossibleRenunciation.toArray(), start, pageRenunciation, 0, amount);
                } else {
                    pageRenunciation = new RegistrationChild[toShow];
                    System.arraycopy(listPossibleRenunciation.toArray(), start, pageRenunciation, 0, toShow);
                }
                for(RegistrationChild possibileRinuncia : pageRenunciation) {
                    JSONArray ja = new JSONArray();
                    
                    // si escludono dalla possibilità le iscrizioni che ne hanno già presentata una
                    int i = 0;
                    int size = listToNotDisplay.size();
                    boolean ithas = false;
                    while(i < size && !ithas) {
                        if(possibileRinuncia.getId() == listToNotDisplay.get(i).getRegistrationChildId()) {
                            ithas = true;
                            // ogni iscrizione ne può avere solo una di rinuncia, quindi se è stata trovata
                            // può essere rimossa dalla lista
                            listToNotDisplay.remove(i);
                        } else {
                            i++;
                        }
                    }
                    if(i == size) {
                        ja.put(possibileRinuncia.getFiscalCode());
                        ja.put(possibileRinuncia.getSurname());
                        ja.put(possibileRinuncia.getName());
                        ja.put(possibileRinuncia.getRegistrationPhase());

                        String operazioni = "<input class='tableImage' type='image' style=\"width:20px;height:20px\" " +
                                            "title=\"Presenta domanda di rinuncia\" alt=\"Presenta domanda di rinuncia\" " +
                                            "src='img/accept.png' onclick='openInsertRenunciationWindow(\"" + possibileRinuncia.getId() + "\")'/>";

                        ja.put(operazioni);
                        array.put(ja);
                    }
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listPossibleRenunciation.size());
            result.put("iTotalDisplayRecords", listPossibleRenunciation.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetTableRenunciation.class.getName()).log(Level.SEVERE, null, ex);
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
