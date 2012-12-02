/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.registrationChildManagement;

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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giuseppe Giovanni Lauri
 */
public class ServletCreateDraftRegistrationChild extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           response.setContentType("text/html;charset=UTF-8");
           JDBCRegistrationChildManager man= JDBCRegistrationChildManager.getInstance();
           String name = request.getParameter(DBNames.ATT_ACCOUNT_NAME);
           String surname = request.getParameter(DBNames.ATT_ACCOUNT_SURNAMEUSER);
           String birthdate = request.getParameter(DBNames.ATT_ACCOUNT_DATEOFBIRTH);
           String birthplace = request.getParameter(DBNames.ATT_ACCOUNT_PLACEOFBIRTH);
           String taxCode = request.getParameter(DBNames.ATT_ACCOUNT_TAXCODE);
           String citizenship = request.getParameter(DBNames.ATT_ACCOUNT_CITIZENSHIP);
           String municipalityResidence = request.getParameter(DBNames.ATT_ACCOUNT_MUNICIPALITYRESIDENCE);
           String provinceResidence = request.getParameter(DBNames.ATT_ACCOUNT_PROVINCERESIDENCE);
           String telephoneNumber = request.getParameter(DBNames.ATT_ACCOUNT_TELEPHONENUMBER);
           String cellular = request.getParameter(DBNames.ATT_ACCOUNT_CELLULARNUMBER);
           String fax = request.getParameter(DBNames.ATT_ACCOUNT_FAX);
           String email = request.getParameter(DBNames.ATT_ACCOUNT_EMAIL);
           String provinceDomicile = request.getParameter(DBNames.ATT_ACCOUNT_PROVINCEDOMICILE);
           String municipalityDomicile = request.getParameter(DBNames.ATT_ACCOUNT_MUNICIPALITYDOMICILIE);
           String capResidence = request.getParameter(DBNames.ATT_ACCOUNT_CAPRESIDENCE);
           String capDomicile = request.getParameter(DBNames.ATT_ACCOUNT_CAPDOMICILIE);
           String qualification = request.getParameter(DBNames.ATT_ACCOUNT_QUALIFICATION);
           String accountType = request.getParameter(DBNames.ATT_ACCOUNT_TYPEACCOUNT);
           String familySituation = request.getParameter(DBNames.ATT_ACCOUNT_FAMILYSITUATION);
           String faculty = request.getParameter(DBNames.ATT_ACCOUNT_FACULTY);
           double income = Double.parseDouble(request.getParameter(DBNames.ATT_ACCOUNT_INCOME));
           String contractExpirationDate = request.getParameter(DBNames.ATT_ACCOUNT_CONTRACTEXPIRATIONDATE);
           String registrationDate = request.getParameter(DBNames.ATT_ACCOUNT_REGISTRATIONDATE);
         
           GregorianCalendar reg= parseGregorianCalendar(registrationDate);
           GregorianCalendar exp= parseGregorianCalendar(contractExpirationDate);
           GregorianCalendar birth= parseGregorianCalendar(birthdate);
           
           RegistrationChild registrationChild= new RegistrationChild();
           registrationChild.setCapDomicile(capDomicile);
           registrationChild.setCapResidence(capResidence);
           registrationChild.setCellularNumber(cellular);
           registrationChild.setCitizenship(citizenship);
           registrationChild.setContractExpirationDate(exp);
           registrationChild.setDataOfBirth(birth);
           registrationChild.setEmail(email);
           registrationChild.setFaculty(faculty);
           registrationChild.setFamilySituation(familySituation);
           registrationChild.setFax(fax);
           registrationChild.setIncome(income);
           registrationChild.setMunicipalityDomicile(municipalityDomicile);
           registrationChild.setMunicipalityResidence(municipalityResidence);
           registrationChild.setNameUser(name);
           registrationChild.setPlaceofBirth(birthplace);
           registrationChild.setProvinceDomicile(provinceDomicile);
           registrationChild.setProvinceResidence(provinceResidence);
           registrationChild.setQualification(qualification);
           registrationChild.setRegistrationDate(reg);
           registrationChild.setSurnameUser(surname);
           registrationChild.setTaxCode(taxCode);
           registrationChild.setTelephoneNumber(telephoneNumber);
           
           man.insert(registrationChild);

           
        } catch (SQLException ex) {
            request.setAttribute("message",
                    "Verfica i campi");
            request.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
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