package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
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
public class ServletGetRenunciation extends HttpServlet {
    private IRenunciationManager renunciationManager;
    private IAccountManager accountManager;
    private IRegistrationChildManager registrationChildManager;
    
    public void init(ServletConfig config) {
        renunciationManager = (IRenunciationManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RENUNCIATION);
        accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
        registrationChildManager = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
        StringBuilder html = new StringBuilder();
        try {
            Renunciation tmpRenunciation = new Renunciation();
            RegistrationChild tmpRegistrationChild = new RegistrationChild();
            Account tmpAccount = new Account();
            
            // campi necessari per prelevare le informazioni
            String sId = request.getParameter(DBNames.ATT_RENUNCIATION_ID);
            if(sId != null && !sId.equals("")) {
                tmpRenunciation.setId(Integer.parseInt(sId));
                tmpRenunciation = renunciationManager.search(tmpRenunciation).get(0);
                
                tmpRegistrationChild.setId(tmpRenunciation.getRegistrationChildId());
                tmpRegistrationChild = registrationChildManager.search(tmpRegistrationChild).get(0);
                
                tmpAccount.setId(tmpRegistrationChild.getParentId());
                tmpAccount = accountManager.search(tmpAccount).get(0);
                
                html.append(bold("Dati Genitore:") + newLine());
                html.append("Codice fiscale: " + tmpAccount.getTaxCode() + newLine());
                html.append("Cognome: " + tmpAccount.getSurnameUser() + newLine());
                html.append("Nome: " + tmpAccount.getNameUser() + newLine());
                html.append("Data di nascita: " + CommonMethod.parseString(tmpAccount.getDataOfBirth()) + newLine());
                html.append("Luogo di nascita: " + tmpAccount.getPlaceOfBirth() + newLine());
                html.append(newLine() + newLine());
                
                html.append(bold("Dati di Iscrizione del Figlio:") + newLine());
                html.append("Data di iscrizione: " + CommonMethod.parseString(tmpRegistrationChild.getRegistrationDate()) + newLine());
                html.append("Codice fiscale: " + tmpRegistrationChild.getFiscalCode() + newLine());
                html.append("Cognome: " + tmpRegistrationChild.getSurname() + newLine());
                html.append("Nome: " + tmpRegistrationChild.getName() + newLine());
                html.append("Data di nascita: " + CommonMethod.parseString(tmpRegistrationChild.getBirthDate()) + newLine());
                html.append("Luogo di nascita: " + tmpRegistrationChild.getBirthPlace() + newLine());
                html.append(newLine() + newLine());
                
                html.append(bold("Dati della Domanda di Rinuncia:") + newLine());
                html.append("Data di creazione: " + CommonMethod.parseString(tmpRenunciation.getDate()) + newLine());
                html.append("Motivazione: " + tmpRenunciation.getReason() + newLine());
                html.append(newLine());
                if(tmpRenunciation.getIsConfirmed()) {
                    html.append(bold("La domanda di rinuncia è stata ricevuta e confermata!"));
                }
                
                isSuccess = true;
                json.put("HTML", html.toString());
            } else {
                isSuccess = false;
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetRenunciation.class.getName()).log(Level.SEVERE, null, ex);
            isSuccess = false;
            errorMsg = ex.getMessage();
        }
        System.out.println("Risultato della GetRenunciation: " + json.toString());

        json.put("IsSuccess", "" + isSuccess);
        json.put("ErrorMsg", errorMsg);

        out.write(json.toString());
        out.close();
    }

    private String bold(String text) {
        return "<b>" + text + "</b>";
    }
    
    private String newLine() {
        return "<br/>";
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
