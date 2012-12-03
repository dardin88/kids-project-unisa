/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.accountManagement;

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

/**
 *
 * @author Gianmarco Del Pozzo
 */
public class AddAccountServlet extends HttpServlet {

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

            JDBCAccountManager man = JDBCAccountManager.getInstance();
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

            GregorianCalendar birth = parseGregorianCalendar(birthdate);

            Account account = new Account();

            account.setRegister(matricola);
            account.setNameUser(name);
            account.setSurnameUser(surname);
            account.setDataOfBirth(birth);
            account.setPlaceofBirth(birthplace);
            account.setTaxCode(taxCode);
            account.setCitizenship(citizenship);
            account.setMunicipalityResidence(municipalityResidence);
            account.setProvinceResidence(provinceResidence);
            account.setViaResidence(addressResidence);

            account = man.insert(account);
            
            request.getSession().setAttribute("id", account.getId());

            // serve per capire da quale pagina provengo
            request.getServletContext().getRequestDispatcher("/trainees.jsp").forward(request, response);
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.INFO, "redirect to accountInsert2 eseguito???");
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
