/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.serviceManagement.trainingManagement.GetTraineesServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

/**
 *
 * @author Gianmarco
 */
public class GetAccountServlet extends HttpServlet {
    
      private IAccountManager accountManager;

    public void init(ServletConfig config) {
        RefinedAbstractAccountManager refinedAbstractAccountManager = new RefinedAbstractAccountManager();
        accountManager = refinedAbstractAccountManager.getManagerImplementor();
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            Account account=new Account();
            account.setId(id);
            List<Account> list=accountManager.search(account);
            request.setAttribute("nome", list.get(0).getNameUser());
            request.setAttribute("cognome", list.get(0).getSurnameUser());
            request.setAttribute("id", list.get(0).getId());
            GregorianCalendar birthDate=list.get(0).getDataOfBirth();
            request.setAttribute("dataNascita", birthDate.get(Calendar.DAY_OF_MONTH)+"/"+(birthDate.get(Calendar.MONTH)+1)+"/"+birthDate.get(Calendar.YEAR));
            request.setAttribute("comuneNascita", list.get(0).getPlaceOfBirth());
            request.setAttribute("tipoAccount", list.get(0).getAccountType());
            request.setAttribute("capDomicilio", list.get(0).getCapDomicile());
            request.setAttribute("capResidenza", list.get(0).getCapResidence());
            request.setAttribute("telefono", list.get(0).getTelephoneNumber());
            request.setAttribute("email", list.get(0).getEmail());
            request.setAttribute("cellulare", list.get(0).getCellularNumber());
            request.setAttribute("cittadinanza", list.get(0).getCitizenship());
            request.setAttribute("scadenzaContratto", list.get(0).getContractExpirationDate());
            request.setAttribute("facoltà", list.get(0).getFaculty());
            request.setAttribute("situazioneFamiliare", list.get(0).getFamilySituation());
            request.setAttribute("fax", list.get(0).getFax());
            request.setAttribute("reddito", list.get(0).getIncome());
            request.setAttribute("comuneDomicilio", list.get(0).getMunicipalityDomicile());
            request.setAttribute("comuneResidenza", list.get(0).getMunicipalityResidence());
            request.setAttribute("provinciaDomicilio", list.get(0).getProvinceDomicile());
            request.setAttribute("provinciaResidenza", list.get(0).getProvinceResidence());
            request.setAttribute("titoloDiStudio", list.get(0).getQualification());
            request.setAttribute("numeroCivicoDomicilio", list.get(0).getStreetNumberDomicile());
            request.setAttribute("numeroCivicoResidenza", list.get(0).getStreetNumberResidence());
            request.setAttribute("codFisc", list.get(0).getTaxCode());
            request.setAttribute("viaDomicilio", list.get(0).getViaDomicile());
            request.setAttribute("viaResidenza", list.get(0).getViaResidence());
            GregorianCalendar dateContract=list.get(0).getContractExpirationDate();
            request.setAttribute("scadenzaContratto", dateContract.get(Calendar.DAY_OF_MONTH)+"/"+(dateContract.get(Calendar.MONTH)+1)+"/"+dateContract.get(Calendar.YEAR));
            GregorianCalendar dateRegistration=list.get(0).getRegistrationDate();
            request.setAttribute("dataIscrizione", dateRegistration.get(Calendar.DAY_OF_MONTH)+"/"+(dateRegistration.get(Calendar.MONTH)+1)+"/"+dateRegistration.get(Calendar.YEAR));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GetTraineesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
