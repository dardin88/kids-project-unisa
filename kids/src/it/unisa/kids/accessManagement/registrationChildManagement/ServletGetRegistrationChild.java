/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * Servlet used to create a new draft of registrationchild
 * 
 * @author Giuseppe Giovanni Lauri
 */
public class ServletGetRegistrationChild extends HttpServlet {

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
        JSONArray jsonArray = new JSONArray();
        try {
            JDBCRegistrationChildManager registrationChildManager = JDBCRegistrationChildManager.getInstance();

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
            
            RegistrationChild tmpChild = new RegistrationChild();
            List<RegistrationChild> listResult = new ArrayList<RegistrationChild>();
            if(account.getAccountType().equals("Segreteria")) {
                tmpChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_SUBMITTED);
                listResult.addAll(registrationChildManager.search(tmpChild));
                tmpChild.setRegistrationPhase(DBNames.ATT_REGISTRATIONCHILD_ENUM_REGISTRATIONPHASE_COMPLETED);
                listResult.addAll(registrationChildManager.search(tmpChild));
            } else if(account.getAccountType().equals("Genitore")) {
                tmpChild.setParentId(account.getId());
                listResult.addAll(registrationChildManager.search(tmpChild));
            }
            for(RegistrationChild tmp : listResult) {
                JSONObject json = new JSONObject();
                json.put(DBNames.ATT_REGISTRATIONCHILD_ID, tmp.getId());
                json.put(DBNames.ATT_REGISTRATIONCHILD_SURNAME, tmp.getSurname());
                json.put(DBNames.ATT_REGISTRATIONCHILD_NAME, tmp.getName());
                json.put(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE, tmp.getBirthDate());
                json.put(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE, tmp.getBirthPlace());
                json.put(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE, tmp.getFiscalCode());
                json.put(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP, tmp.getCitizenship());
                json.put(DBNames.ATT_REGISTRATIONCHILD_SICKNESS, tmp.getSickness());
                json.put(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONDATE, tmp.getRegistrationDate());
                json.put(DBNames.ATT_REGISTRATIONCHILD_USERRANGE, tmp.getUserRange());
                json.put(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE, tmp.getRegistrationPhase());
                json.put(DBNames.ATT_REGISTRATIONCHILD_PARENTACCOUNTID, tmp.getParentId());
                jsonArray.put(json);
            }
            out.println(jsonArray.toString());
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
