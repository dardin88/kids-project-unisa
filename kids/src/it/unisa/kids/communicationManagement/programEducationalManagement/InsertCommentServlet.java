/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.Mail;
import it.unisa.kids.common.MailManager;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccountFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author Antonio Panariello
 */
public class InsertCommentServlet extends HttpServlet {

    private IActivityManager activityManager;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.activityManager = (IActivityManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
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

            CommentBean insertComment = createInsertComment(request, response);
            if (insertComment == null) {
                return;
            }

            activityManager.insert(insertComment);

        } catch (SQLException e) {
            Logger.getLogger(InsertCommentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            Logger.getLogger(InsertCommentServlet.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private CommentBean createInsertComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String commentType = request.getParameter("commentType");
        CommentBean insertComment = new CommentBean();

        if (commentType.equals(CommentBean.ANNUAL_COMMENT)) {
            insertComment.setClassId(0);       // setto a 0 per inserire commento annuale
            GregorianCalendar g = new GregorianCalendar();
            insertComment.setDate(g);
            insertComment.setAuthorId(Integer.parseInt(request.getParameter("idAutore")));
            insertComment.setTime(new java.sql.Time(g.getTime().getTime()));
            insertComment.setAnnualId(Integer.parseInt(request.getParameter("idProgetto")));
            insertComment.setContent(request.getParameter("contenutoCommento"));
            Mail m = new Mail();
            Account resp = new Account();
            Account delegatoRettore = new Account();
            Account coordinatore = new Account();
            List<Account> respAccount = new ArrayList<>();
            List<Account> delAccount = new ArrayList<>();
            List<Account> coordinatoreAccount = new ArrayList<>();
            ArrayList<String> listaDestinatari = new ArrayList<>();
            resp.setAccountType("Responsabile Scientifico");
            delegatoRettore.setAccountType("Delegato del Rettore");
            coordinatore.setAccountType("Coordinatore Psicopedagogico");
            AccountFacade facade = new AccountFacade();
            respAccount = facade.search(resp);
            delAccount = facade.search(delegatoRettore);
            coordinatoreAccount = facade.search(coordinatore);
            String tipoAttore = request.getParameter("tipoAttore");            
            if (tipoAttore.equalsIgnoreCase("Responsabile Scientifico")) {
                for (Account n : coordinatoreAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                for (Account n : delAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                m.setBody("Commento inserito da Responsabile Scientifico");
            } else if (tipoAttore.equalsIgnoreCase("Delegato del Rettore")) {
                for (Account n : respAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                for (Account n : coordinatoreAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                m.setBody("Commento inserito dal Delegato del rettore");
            }
            m.setSubject("Commento Progetto Annuale");
            m.setTo(listaDestinatari);
            MailManager mm = new MailManager();
            mm.sendMail(m);
        } else {
            insertComment.setAnnualId(0);      // setto a 0 per inserire commento per classi
            int classId = -1;
            try {
                classId = Integer.parseInt(request.getParameter("hiddenClassId"));
            } catch (NumberFormatException e) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: classe non corretta", "/sectionEdu.jsp");
                return null;
            }
            if (classId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: classe non corretta", "/sectionEdu.jsp");
                return null;
            }

            int authorId = -1;
            try {
                authorId = Integer.parseInt(request.getParameter("hiddenAccountId"));
            } catch (NumberFormatException e) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: account non valido", "/sectionEdu.jsp");
                return null;
            }

            String commentContent = request.getParameter("commentContent");

            GregorianCalendar g = new GregorianCalendar();
            insertComment.setDate(g);
            insertComment.setTime(new java.sql.Time(g.get(Calendar.HOUR), g.get(Calendar.MINUTE), g.get(Calendar.SECOND)));
            insertComment.setClassId(classId);
            insertComment.setAuthorId(authorId);
            insertComment.setContent(commentContent.trim());

        }

        return insertComment;
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
