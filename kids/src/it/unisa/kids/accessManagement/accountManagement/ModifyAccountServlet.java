/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.common.DBNames;
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
 * @author Gianmarco
 */
public class ModifyAccountServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
           JDBCAccountManager man= JDBCAccountManager.getInstance();
           List<Account> list;
           Account account2= new Account();
           
           int id= Integer.parseInt(request.getParameter("id"));
         
           System.out.println("QUESTO E' L'ID : "+id);
           request.getSession().setAttribute("id", id);
           account2.setId(id);
           list=man.search(account2);
           
           
            String matricola = request.getParameter("matricolaAccount");
            String name = request.getParameter("nomeAccount");
            String surname = request.getParameter("cognomeAccount");
            String birthdate = request.getParameter("dataNascitaAccount");
            String birthplace = request.getParameter("comuneNascitaAccount");
            String taxCode = request.getParameter("codiceFiscaleAccount");
            String citizenship = request.getParameter("cittadinanzaAccount");
            String municipalityResidence = request.getParameter("comuneResidenzaAccount");
            String provinceResidence = request.getParameter("provinciaResidenzaAccount");
            String addressResidence = request.getParameter("indirizzoResidenzaAccount");
      
           GregorianCalendar birth= parseGregorianCalendar(birthdate);
           
           Account account= list.get(0);
           
           account.setId(account.getId());
           account.setRegister(matricola);
           account.setAccountType(account.getAccountType());
           account.setCapDomicile(account.getCapDomicile());
           account.setCapResidence(account.getCapResidence());
           account.setCellularNumber(account.getCellularNumber());
           account.setCitizenship(citizenship);
           account.setContractExpirationDate(account.getContractExpirationDate());
           account.setDataOfBirth(birth);
           account.setEmail(account.getEmail());
           account.setFaculty(account.getFaculty());
           account.setFamilySituation(account.getFamilySituation());
           account.setFax(account.getFax());
           account.setIncome(account.getIncome());
           account.setMunicipalityDomicile(account.getMunicipalityDomicile());
           account.setMunicipalityResidence(municipalityResidence);
           account.setNameUser(name);
           account.setPlaceofBirth(birthplace);
           account.setProvinceDomicile(account.getProvinceDomicile());
           account.setProvinceResidence(provinceResidence);
           account.setQualification(account.getQualification());
           account.setRegistrationDate(account.getRegistrationDate());
           account.setSurnameUser(surname);
           account.setTaxCode(taxCode);
           account.setTelephoneNumber(account.getTelephoneNumber());
           account.setCapDomicile(account.getCapDomicile());   
           account.setMunicipalityDomicile(account.getMunicipalityDomicile());     
           account.setProvinceDomicile(account.getProvinceDomicile());
           account.setProvinceResidence(account.getProvinceResidence());
           account.setViaResidence(addressResidence);
           account.setViaDomicile(account.getViaDomicile());
           account.setState(account.getState());
           account.setTypeParent(account.getTypeParent());
                             
           man.update(account);
           
        } catch (SQLException ex) {
            Logger.getLogger(ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {            
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
        } catch (ParseException ex) {
            Logger.getLogger(ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
