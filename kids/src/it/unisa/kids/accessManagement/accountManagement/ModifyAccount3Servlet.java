package it.unisa.kids.accessManagement.accountManagement;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.JDBCAccountManager;
import it.unisa.kids.accessManagement.accountManagement.ModifyAccountServlet;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Gianmarco
 */
public class ModifyAccount3Servlet extends HttpServlet {

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
        Account account2=new Account();
          List<Account> list;
           try {
           System.out.println("Sono Qui");
           JDBCAccountManager man= JDBCAccountManager.getInstance();
         HttpSession rs= request.getSession();
         int id= (Integer) rs.getAttribute("id");
         System.out.println("Questo è l'id"+id);

          account2.setId(id);
          list=man.search(account2);
                    
          String capDomicile = request.getParameter("capDomicilio");
          String familySituation= request.getParameter("situazioneFamiliaria");
          String faculty= request.getParameter("facolta");
          String qualification= request.getParameter("titoloStudio");
          String typeAccount= request.getParameter("tipoAccount");
          double income= Double.parseDouble(request.getParameter("reddito"));
          String expDate=request.getParameter("scadenzaContratto");
          String regDate= request.getParameter("dataRegistrazione");
          String typeParent= request.getParameter("tipoGenitore");
          
           GregorianCalendar exp= parseGregorianCalendar(expDate);
           GregorianCalendar reg= parseGregorianCalendar(regDate);
           
          
           
          Account account=list.get(0);
           System.out.println("Questo è il nome dell'account 2:"+account.getNameUser());
           account.setId(account.getId());
           account.setRegister(account.getRegister());
           account.setAccountType(typeAccount);
           account.setCapDomicile(capDomicile);
           account.setCapResidence(account.getCapResidence());
           account.setCellularNumber(account.getCellularNumber());
           account.setCitizenship(account.getCitizenship());
           account.setContractExpirationDate(exp);
           account.setDataOfBirth(account.getDataOfBirth());
           account.setEmail(account.getEmail());
           account.setFaculty(faculty);
           account.setFamilySituation(familySituation);
           account.setFax(account.getFax());
           account.setIncome(income);
           account.setMunicipalityDomicile(account.getMunicipalityDomicile());
           account.setMunicipalityResidence(account.getMunicipalityResidence());
           account.setNameUser(account.getNameUser());
           account.setPlaceofBirth(account.getPlaceOfBirth());
           account.setProvinceDomicile(account.getProvinceDomicile());
           account.setProvinceResidence(account.getProvinceResidence());
           account.setQualification(qualification);
           account.setRegistrationDate(reg);
           account.setSurnameUser(account.getSurnameUser());
           account.setTaxCode(account.getTaxCode());
           account.setTelephoneNumber(account.getTelephoneNumber());  
           account.setMunicipalityDomicile(account.getMunicipalityDomicile());     
           account.setProvinceResidence(account.getProvinceResidence());
           account.setViaResidence(account.getViaResidence());
           account.setViaDomicile(account.getViaDomicile());
           account.setState(account.getState());
           account.setTypeParent(typeParent);
           
 
           man.update(account);
                      
        }  catch (SQLException ex) {
            Logger.getLogger(ModifyAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (ParseException ex) {
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
            Logger.getLogger(ModifyAccount3Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModifyAccount3Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
