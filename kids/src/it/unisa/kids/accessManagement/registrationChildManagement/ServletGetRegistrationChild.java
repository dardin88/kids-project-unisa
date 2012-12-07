/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 * Servlet used to get the value of a registrationchild
 * 
 * @author Giuseppe Giovanni Lauri
 */
public class ServletGetRegistrationChild extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
            RegistrationChild tmpChild = new RegistrationChild();
            
            // campi necessari per prelevare le informazioni
            if(!request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID).equals("")) {
                int id = Integer.parseInt(request.getParameter(DBNames.ATT_REGISTRATIONCHILD_ID));
                tmpChild.setId(id);
                System.out.println("Sono nella servlet con l'id: " + id);
                // ricerco
                List<RegistrationChild> listResult = registrationChildManager.search(tmpChild);
                if(listResult.size() > 0) {
                    
                    // prelevo il risultato (dovrebbe essere unico
                    tmpChild = listResult.get(0);

                    // costruisco l'output
                    
                    json.put(DBNames.ATT_REGISTRATIONCHILD_ID, tmpChild.getId());
                    
                    json.put(DBNames.ATT_REGISTRATIONCHILD_SURNAME, tmpChild.getSurname());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_NAME, tmpChild.getName());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE, CommonMethod.parseString(tmpChild.getBirthDate()));
                    json.put(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE, tmpChild.getBirthPlace());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE, tmpChild.getFiscalCode());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP, tmpChild.getCitizenship());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_USERRANGE, tmpChild.getUserRange());
                    
                    json.put(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE, CommonMethod.parseString(tmpChild.getRegistrationDate()));
                    json.put(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE, tmpChild.getRegistrationPhase());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID, tmpChild.getParentId());
                    
                    json.put(DBNames.ATT_REGISTRATIONCHILD_SICKNESS, tmpChild.getSickness());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_VACCINATIONS, tmpChild.getVaccinations());
                    json.put(DBNames.ATT_REGISTRATIONCHILD_PRIVACYSTATEMENT, tmpChild.getPrivacyStatement());
                    
                    json.put(DBNames.ATT_REGISTRATIONCHILD_SECTIONID, tmpChild.getSectionId());
                    
                    System.out.println(json.toString());
                    out.write(json.toString());
                } else {
                    System.out.println("Errore nella prelevazione dell'ID: " + id);
                }
            } else {
                System.out.println("Errore nella passaggio dei parametri");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetRegistrationChild.class.getName()).log(
                    Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGetRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGetRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        }
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
