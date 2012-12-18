package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.Mail;
import it.unisa.kids.common.MailManager;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
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

/**
 *
 * @author tonino
 */
public class SendMailRequestClassModifyServlet extends HttpServlet {

    private IAccountManager accMan;

    @Override
    public void init(ServletConfig config) {
        accMan = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
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
        try {
            MailManager mailManager = new MailManager();
            Mail mail = new Mail();
            mail.setBody(request.getParameter("messaggio"));
            mail.setSubject("Richiesta modifica classe \"" + request.getParameter("nomeClasse") + "\"");
            Account pAccount = new Account();
            pAccount.setAccountType("Responsabile Asilo");
            List<Account> searchedAccount = accMan.search(pAccount);
            ArrayList<String> email=new ArrayList<>();
            for (Account e : searchedAccount) {
                email.add(e.getEmail());
            }
            mail.setTo(email);
            mailManager.sendMail(mail);
            response.sendRedirect("class.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(SendMailRequestClassModifyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
