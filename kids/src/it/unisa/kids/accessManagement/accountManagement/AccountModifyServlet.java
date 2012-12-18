/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

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
 * @author Gianmarco Del Pozzo
 */
public class AccountModifyServlet extends HttpServlet {

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
    private IAccountManager man;

    public void init(ServletConfig config) {
        man = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Account account2 = new Account();
        List<Account> list;
        try {
            response.setContentType("text/html;charset=UTF-8");

            int id = Integer.parseInt(request.getParameter("id"));
            account2.setId(id);
            list = man.search(account2);
            String name = request.getParameter("nomeAccount");
            String surname = request.getParameter("cognomeAccount");
            String birthdate = request.getParameter("dataNascitaAccount");
            String birthplace = request.getParameter("comuneNascitaAccount");
            String taxCode = request.getParameter("codiceFiscaleAccount");
            String citizenship = request.getParameter("cittadinanzaAccount");
            String municipalityResidence = request.getParameter("comuneResidenzaAccount");
            String provinceResidence = request.getParameter("provinciaResidenzaAccount");
            String addressResidence = request.getParameter("indirizzoResidenzaAccount");
            String fax = request.getParameter("fax");
            String email = request.getParameter("email");
            String telephoneNumber = request.getParameter("telefono");
            String cellular = request.getParameter("cellulare");
            String capResidence = request.getParameter("capResidenza");
            String provinceDomicile = request.getParameter("provinciaDomicilio");
            String municipalityDomicile = request.getParameter("comuneDomicilio");
            String addressDomicile = request.getParameter("indirizzoDomicilio");
            String capDomicile = request.getParameter("capDomicilio");
            String familySituation = request.getParameter("situazioneFamiliaria");
            String faculty = request.getParameter("facolta");
            String qualification = request.getParameter("titoloStudio");
            String typeAccount = request.getParameter("tipoAccount");
            double income = Double.parseDouble(request.getParameter("reddito"));
            String expDate = request.getParameter("scadenzaContratto");
            String regDate = request.getParameter("dataRegistrazione");
            String typeParent = request.getParameter("tipoGenitore");

            GregorianCalendar exp;
            GregorianCalendar reg;
            GregorianCalendar birth = parseGregorianCalendar(birthdate);

            if (expDate != "") {
                exp = parseGregorianCalendar(expDate);
            } else {
                exp = parseGregorianCalendar("0000-00-00");
            }
            if (regDate != "") {
                reg = parseGregorianCalendar(regDate);
            } else {
                reg = parseGregorianCalendar("0000-00-00");
            }

            Account account = new Account();
            account = list.get(0);
            account.setId(id);

            account.setNameUser(name);
            account.setSurnameUser(surname);
            account.setDataOfBirth(birth);
            account.setPlaceofBirth(birthplace);
            account.setTaxCode(taxCode);
            account.setCitizenship(citizenship);
            account.setMunicipalityResidence(municipalityResidence);
            account.setProvinceResidence(provinceResidence);
            account.setViaResidence(addressResidence);
            account.setFax(fax);
            account.setEmail(email);
            account.setTelephoneNumber(telephoneNumber);
            account.setCellularNumber(cellular);
            account.setCapResidence(capResidence);
            account.setProvinceDomicile(provinceDomicile);
            account.setMunicipalityDomicile(municipalityDomicile);
            account.setViaDomicile(addressDomicile);
            account.setCapDomicile(capDomicile);
            account.setFamilySituation(familySituation);
            account.setFaculty(faculty);
            account.setQualification(qualification);
            account.setAccountType(typeAccount);
            account.setIncome(income);
            account.setContractExpirationDate(exp);
            account.setRegistrationDate(reg);
            account.setTypeParent(typeParent);

            account = man.update(account);


        } catch (SQLException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, "SQL-Error: " + ex.getMessage(), ex);
        }
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
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
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
