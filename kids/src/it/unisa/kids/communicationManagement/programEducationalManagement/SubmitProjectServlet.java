/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.Mail;
import it.unisa.kids.common.MailManager;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccountFacade;
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
 * @author dario
 */
public class SubmitProjectServlet extends HttpServlet {

    private IProgramEducational programEducationalManager;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        RefinedAbstractManager refinedAbstractProgramEducationalManager = RefinedAbstractManager.getInstance();
        programEducationalManager = (IProgramEducational) refinedAbstractProgramEducationalManager.getManagerImplementor(DBNames.TABLE_ANNUAL_PROJ);
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
            int id = programEducationalManager.show().get(0).getId();
            String tipoAttore = request.getParameter("tipoAttore");
            AnnualProject project = new AnnualProject();
            project.setId(id);
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
            if (request.getParameter("submitCoord") != null) {
                project.setState("submitCoord");
            } else if (request.getParameter("acceptCoord") != null) {
                project.setState("acceptCoord");
            } else if (request.getParameter("acceptResp") != null) {
                project.setState("acceptResp");
            } else if (request.getParameter("acceptDeleg") != null) {
                project.setState("acceptDeleg");
            } else if (request.getParameter("requestModify") != null) {
                project.setState("requestModify");
            }
            if (tipoAttore.equalsIgnoreCase("Coordinatore Psicopedagogico")) {
                for (Account n : respAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                for (Account n : delAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                m.setBody("Programma Sottomesso da Coordinatore Psicopedagogico");
            } else if (tipoAttore.equalsIgnoreCase("Responsabile Scientifico")) {
                for (Account n : coordinatoreAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                for (Account n : delAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                m.setBody("Programma Sottomesso da Responsabile Scientifico");
            } else if (tipoAttore.equalsIgnoreCase("Delegato del Rettore")) {
                for (Account n : respAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                for (Account n : coordinatoreAccount) {
                    listaDestinatari.add(n.getEmail());
                }
                m.setBody("Programma Convalidato dal Delegato del rettore");
            }
            programEducationalManager.updateProgramEducational(project);
            m.setSubject("Sottomissione Progetto Annuale");
            m.setTo(listaDestinatari);
            MailManager mm = new MailManager();
            mm.sendMail(m);
            response.sendRedirect("/kids/showProject.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(SubmitProjectServlet.class.getName()).log(Level.SEVERE, null, ex);
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
