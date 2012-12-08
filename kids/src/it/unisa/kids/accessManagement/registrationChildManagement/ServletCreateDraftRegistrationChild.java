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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * Servlet used to create a new draft of registrationchild
 * 
 * @author Giuseppe Giovanni Lauri
 */
public class ServletCreateDraftRegistrationChild extends HttpServlet {
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
        boolean isSuccess = true;
        String errorMsg = new String();
        
        try {
            // Prelevo i dati necessari
            String surname = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_SURNAME);
            String name = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_NAME);

            String birthDate = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE);
            GregorianCalendar birth;
            if(!birthDate.equals("")) {
                birth = CommonMethod.parseGregorianCalendar(birthDate);
            } else {
                birth = null;
            }
            String birthPlace = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE);
            String fiscalCode = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE);
            String citizenship = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP);
            String userRange = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_USERRANGE);
            
            // L'id del padre viene preso dalla sessione
            HttpSession session = request.getSession();
            Account parentAccount = (Account) session.getAttribute("user");

            // La data di creazione della bozza va presa dal server
            GregorianCalendar registrationDate = new GregorianCalendar();
            registrationDate.setTime(new Date(System.currentTimeMillis()));

            //* TEST DELLA RICHIESTA ALLA SERVLET
            System.out.println("Sono nella CreateServlet ed il cognome è: " + request.getParameter(DBNames.ATT_REGISTRATIONCHILD_SURNAME));
            //*/
            
            // Creo la domanda di iscrizione bambino
            RegistrationChild registrationChild = new RegistrationChild();
            registrationChild.setSurname(surname);
            registrationChild.setName(name);
            registrationChild.setBirthDate(birth);
            registrationChild.setBirthPlace(birthPlace);
            registrationChild.setFiscalCode(fiscalCode);
            registrationChild.setCitizenship(citizenship);
            registrationChild.setRegistrationDate(registrationDate);
            registrationChild.setUserRange(userRange);
            registrationChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_DRAFT);
            registrationChild.setParentId(parentAccount.getId());

            // La inserisco nel db
            isSuccess = registrationChildManager.insert(registrationChild);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, "SQL-Error: " + ex.getMessage(), ex);
            isSuccess = false;
            errorMsg = ex.getMessage();
        } finally {
            System.out.println("Risultato: " + isSuccess);
            
            if(isSuccess) {
                json.put("IsSuccess", "true");
            } else {
                json.put("IsSuccess", "false");
            }
            json.put("ErrorMsg", errorMsg);
            
            out.write(json.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletCreateDraftRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
