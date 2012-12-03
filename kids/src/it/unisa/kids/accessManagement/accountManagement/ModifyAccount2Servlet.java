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
public class ModifyAccount2Servlet extends HttpServlet {

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
        Account account=new Account();
           try {
           
           JDBCAccountManager man= JDBCAccountManager.getInstance();
           HttpSession rs= request.getSession();
           String i= (String) rs.getAttribute("Id");
           int id= Integer.parseInt(i);
           
           account.setId(id);
           man.search(account);
           
           String provinceResidence = request.getParameter(DBNames.ATT_ACCOUNT_PROVINCERESIDENCE);
           
           String fax = request.getParameter(DBNames.ATT_ACCOUNT_FAX);
           String email = request.getParameter(DBNames.ATT_ACCOUNT_EMAIL);
           String telephoneNumber = request.getParameter(DBNames.ATT_ACCOUNT_TELEPHONENUMBER);
           String cellular = request.getParameter(DBNames.ATT_ACCOUNT_CELLULARNUMBER);
           String capResidence = request.getParameter(DBNames.ATT_ACCOUNT_CAPRESIDENCE);
           String addressResidence = request.getParameter(DBNames.ATT_ACCOUNT_ADDRESSRESIDENCE);
           String provinceDomicile = request.getParameter(DBNames.ATT_ACCOUNT_PROVINCEDOMICILE);
           String municipalityDomicile = request.getParameter(DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE);
           String addressDomicile= request.getParameter(DBNames.ATT_ACCOUNT_ADDRESSDOMICILE);

           
           String capDomicile = request.getParameter(DBNames.ATT_ACCOUNT_CAPDOMICILIE);
                  
           account.setId(account.getId());
           account.setState(account.getState());
           account.setRegister(account.getRegister());
           account.setAccountType(account.getAccountType());
           account.setCapDomicile(account.getCapDomicile());
           account.setCapResidence(capResidence);
           account.setCellularNumber(cellular);
           account.setCitizenship(account.getCitizenship());
           account.setContractExpirationDate(account.getContractExpirationDate());
           account.setDataOfBirth(account.getDataOfBirth());
           account.setEmail(email);
           account.setFaculty(account.getFaculty());
           account.setFamilySituation(account.getFamilySituation());
           account.setFax(fax);
           account.setIncome(account.getIncome());
           account.setMunicipalityDomicile(municipalityDomicile);
           account.setMunicipalityResidence(account.getMunicipalityResidence());
           account.setNameUser(account.getNameUser());
           account.setPlaceofBirth(account.getPlaceOfBirth());
           account.setProvinceDomicile(provinceDomicile);
           account.setProvinceResidence(account.getProvinceResidence());
           account.setQualification(account.getQualification());
           account.setRegistrationDate(account.getRegistrationDate());
           account.setSurnameUser(account.getSurnameUser());
           account.setTaxCode(account.getTaxCode());
           account.setTelephoneNumber(telephoneNumber);
           account.setCapDomicile(capDomicile);   
           account.setMunicipalityDomicile(municipalityDomicile);     
           account.setProvinceDomicile(provinceDomicile);
           account.setProvinceResidence(addressDomicile);
           account.setViaResidence(addressResidence);
           account.setState(account.getState());
           account.setTypeParent(account.getTypeParent());
 
           man.update(account);
           String app=(String)request.getSession().getAttribute("pagina");
          if(app.equals("0")){
           request.getServletContext().getRequestDispatcher("/accountModify3.jsp").forward(request, response);
          }
          else{
              request.getServletContext().getRequestDispatcher("/accountInsert3.jsp").forward(request, response);
          }
        } catch (SQLException ex) {
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
