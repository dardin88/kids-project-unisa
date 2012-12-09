package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
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
public class InsertTableChildServlet extends HttpServlet {

    private IRenunciationManager registrationRenunciationManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRenunciationManager = RefinedAbstractManager.getInstance();
        registrationRenunciationManager = (IRenunciationManager) refinedAbstractRenunciationManager.getManagerImplementor(DBNames.TABLE_RENUNCIATION);
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

        Renunciation[] paginateRenunciationRequestSet;
        List<Renunciation> listRenunciationRequest;
        try {
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
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

            // L'output è in base alla tipologia dell'account


            Account account = (Account) request.getSession().getAttribute("user");
            Renunciation renunciation = new Renunciation();

            //prende l'id di chi ha fatto l'accesso
            HttpSession session = request.getSession();
            Account user = (Account) session.getAttribute("user");
            int idGenitore = user.getId();


            //Crea la lista dei figli di idGenitore
            RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
            IRegistrationChildManager registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);

            RegistrationChild tmpChild = new RegistrationChild();
            tmpChild.setParentId(idGenitore);

            List<RegistrationChild> listaFigli = registrationChildManager.search(tmpChild);
            registrationChildManager.search(null);



            /*    if (account.getAccountType().equals("Genitore")) {
             // Il genitore può vedere solo le proprie domande di iscrizione



             renunciation.setParentId(account.getId());
             } else if (account.getAccountType().equals("Segreteria")) {
             // La segreteria potrà vedere solo le richieste sottomesse dai genitori, che dovrà andare a confermare
             renunciation.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED);
             }
             */


            listRenunciationRequest = registrationRenunciationManager.search(renunciation);
            //}

            int linksNumber = listRenunciationRequest.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateRenunciationRequestSet = new Renunciation[amount];
                    System.arraycopy(listRenunciationRequest.toArray(), start, paginateRenunciationRequestSet, 0, amount);
                } else {
                    paginateRenunciationRequestSet = new Renunciation[toShow];
                    System.arraycopy(listRenunciationRequest.toArray(), start, paginateRenunciationRequestSet, 0, toShow);
                }

                for (RegistrationChild regRenunciationRequest : listaFigli) {
                    JSONArray ja = new JSONArray();
                    ja.put(regRenunciationRequest.getName());
                    ja.put(regRenunciationRequest.getSurname());

                    /*
                     StringBuffer operazioni = new StringBuffer();
                     // Sia genitore che segreteria possono vederne i dettagli, la visualizza dettagli deve esser possibile su tutte le domanda
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Visualizza Dettagli\" alt=\"Dettagli\" src='img/lente.gif' onclick='openViewDetailsRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");

                     if (account.getAccountType().equals("Genitore")) {
                     // solo il genitore può eliminarla o modificarla
                     if (regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DRAFT)) {
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Modifica\" alt=\"Modifica\" src='img/edit.gif' onclick='openModifyRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");
                     }
                     if (!regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_ACCEPTED) && !regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DELETED)) {
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Elimina\" alt=\"Elimina\" src='img/trash.png' onclick='openDeleteRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");
                     }
                     if (regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_ACCEPTED)) {
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" title=\"Completa\" alt=\"Completa\" src='img/tocomplete.png' onclick='openCompleteRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");
                     }
                     }
                     if (account.getAccountType().equals("Segreteria")) {
                     // solo la segreteria può confermare
                     if (regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED)) {
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" alt=\"Conferma\" alt=\"Conferma ricezione\" src='img/accept.png' onclick='confirmReceivingRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");
                     }
                     // e confermare il completamento
                     if (regRenunciationRequest.getRegistrationPhase().equals(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_COMPLETED)) {
                     operazioni.append("<input class='tableImage' type='image' style=\"width:20px;height:20px\" alt=\"Conferma\" alt=\"Conferma completamento\" src='img/accept.png' onclick='openComfirmCompletingRegistrationChildWindow(\"" + regRenunciationRequest.getId() + "\")'/>");
                     }
                     }
                     ja.put(operazioni.toString());
                     array.put(ja);
                     */
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
        } catch (SQLException ex) {
            Logger.getLogger(InsertTableChildServlet.class.getName()).log(Level.SEVERE, null, ex);
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
