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
        Account account=new Account();
           try {
           
           JDBCAccountManager man= JDBCAccountManager.getInstance();
          HttpSession rs= request.getSession();
           String i= (String) rs.getAttribute("Id");
           int id= Integer.parseInt(i);
           man.search(account);
          
        
           
           String provinceDomicile = request.getParameter(DBNames.ATT_ACCOUNT_PROVINCEDOMICILE);
          
          String capResidence = request.getParameter(DBNames.ATT_ACCOUNT_CAPRESIDENCE);
          String familySituation= request.getParameter(DBNames.ATT_ACCOUNT_FAMILYSITUATION);
          String addressDomicile= request.getParameter(DBNames.ATT_ACCOUNT_ADDRESSDOMICILE);
          String qualification= request.getParameter(DBNames.ATT_ACCOUNT_QUALIFICATION);
          String typeAccount= request.getParameter(DBNames.ATT_ACCOUNT_TYPEACCOUNT);
          double income= Double.parseDouble(request.getParameter(DBNames.ATT_ACCOUNT_INCOME));
          String expDate=request.getParameter(DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE);
          String regDate= request.getParameter(DBNames.ATT_ACCOUNT_REGISTRATIONDATE);
          String typeParent= request.getParameter(DBNames.ATT_ACCOUNT_TYPEPARENT);
          
           GregorianCalendar exp= parseGregorianCalendar(expDate);
           GregorianCalendar reg= parseGregorianCalendar(regDate);
          
           account.setId(account.getId());
           account.setRegister(account.getRegister());
           account.setAccountType(typeAccount);
           account.setCapDomicile(account.getCapDomicile());
           account.setCapResidence(capResidence);
           account.setCellularNumber(account.getCellularNumber());
           account.setCitizenship(account.getCitizenship());
           account.setContractExpirationDate(exp);
           account.setDataOfBirth(account.getDataOfBirth());
           account.setEmail(account.getEmail());
           account.setFaculty(account.getFaculty());
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
           account.setCapDomicile(account.getCapDomicile());   
           account.setMunicipalityDomicile(account.getMunicipalityDomicile());     
           account.setProvinceDomicile(provinceDomicile);
           account.setProvinceResidence(account.getProvinceResidence());
           account.setViaResidence(account.getViaResidence());
           account.setViaDomicile(addressDomicile);
           account.setState(account.getState());
           account.setTypeParent(typeParent);
           
 
           man.update(account);
           
           request.getServletContext().getRequestDispatcher("/accountInformation.jsp").forward(request, response);
           
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
