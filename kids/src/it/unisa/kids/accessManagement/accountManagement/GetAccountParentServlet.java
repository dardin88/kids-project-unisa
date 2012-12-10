/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gianmarco
 */
public class GetAccountParentServlet extends HttpServlet {

    private IAccountManager accountManager;

    public void init(ServletConfig config) {
        accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
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

            HttpSession rs = request.getSession();
            int id = (Integer) rs.getAttribute("id");
            System.out.println("Questo è l'id " + id);
            Account account = new Account();
            account.setId(id);
            List<Account> list = accountManager.search(account);
            request.setAttribute("Password", list.get(0).getPassword());
            request.setAttribute("Nickname", list.get(0).getNickName());
            request.setAttribute("Nome", list.get(0).getNameUser());
            request.setAttribute("Cognome", list.get(0).getSurnameUser());
            request.setAttribute("id", list.get(0).getId());
            GregorianCalendar birthDate = list.get(0).getDataOfBirth();
            if (birthDate != null) {
                String giorno = "" + birthDate.get(Calendar.DAY_OF_MONTH);
                if (giorno.length() < 2) {
                    giorno = "0" + giorno;
                }
                String mese = "" + (birthDate.get(Calendar.MONTH) + 1);
                if (mese.length() < 2) {
                    mese = "0" + mese;
                }
                request.setAttribute("DataNascita", birthDate.get(Calendar.YEAR) + "-" + mese + "-" + giorno);
            } else {
                request.setAttribute("DataNascita", null);
            }
            request.setAttribute("app", "0");
            request.setAttribute("ComuneNascita", list.get(0).getPlaceOfBirth());
            request.setAttribute("TitoloStudio", list.get(0).getQualification());
            request.setAttribute("TipoAccount", list.get(0).getAccountType());
            request.setAttribute("TipoGenitore", list.get(0).getTypeParent());
            request.setAttribute("CapDomicilio", list.get(0).getCapDomicile());
            request.setAttribute("CapResidenza", list.get(0).getCapResidence());
            request.setAttribute("Telefono", list.get(0).getTelephoneNumber());
            request.setAttribute("Email", list.get(0).getEmail());
            request.setAttribute("Cellulare", list.get(0).getCellularNumber());
            request.setAttribute("Cittadinanza", list.get(0).getCitizenship());
            request.setAttribute("Facolta", list.get(0).getFaculty());
            request.setAttribute("SituazioneFamiliare", list.get(0).getFamilySituation());
            request.setAttribute("Fax", list.get(0).getFax());
            request.setAttribute("Reddito", list.get(0).getIncome());
            request.setAttribute("ComuneDomicilio", list.get(0).getMunicipalityDomicile());
            request.setAttribute("ComuneResidenza", list.get(0).getMunicipalityResidence());
            request.setAttribute("ProvinciaDomicilio", list.get(0).getProvinceDomicile());
            request.setAttribute("ProvinciaResidenza", list.get(0).getProvinceResidence());
            request.setAttribute("TitoloStudio", list.get(0).getQualification());
            request.setAttribute("CodiceFiscale", list.get(0).getTaxCode());
            request.setAttribute("ViaDomicilio", list.get(0).getViaDomicile());
            request.setAttribute("ViaResidenza", list.get(0).getViaResidence());
            GregorianCalendar dateContract = list.get(0).getContractExpirationDate();
            if (dateContract != null) {
                request.setAttribute("ScadenzaContratto", dateContract.get(Calendar.DAY_OF_MONTH) + "-" + (dateContract.get(Calendar.MONTH) + 1) + "-" + dateContract.get(Calendar.YEAR));
            } else {
                request.setAttribute("ScadenzaContratto", null);
            }

            GregorianCalendar dateRegistration = list.get(0).getRegistrationDate();
            if (dateRegistration != null) {
                request.setAttribute("DataIscrizione", dateRegistration.get(Calendar.DAY_OF_MONTH) + "-" + (dateRegistration.get(Calendar.MONTH) + 1) + "-" + dateRegistration.get(Calendar.YEAR));
            } else {
                request.setAttribute("DataIscrizione", null);
            }




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
