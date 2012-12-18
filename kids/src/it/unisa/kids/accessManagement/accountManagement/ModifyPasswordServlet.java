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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Gianmarco
 */
public class ModifyPasswordServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject jObj = new JSONObject();
        PrintWriter out = response.getWriter();
        Account account2 = new Account();
        List<Account> list;
        try {

            int id = Integer.parseInt(request.getParameter("id"));
            account2.setId(id);
            list = man.search(account2);

            String old = request.getParameter("old");
            String newPass = request.getParameter("newPass");
            String newPass2 = request.getParameter("newPass2");

            Account account = list.get(0);

            if ((!newPass.equals(newPass2)) || (!old.equals(account.getPassword()))) {
                jObj.put("message", "NO");

            } else {
                jObj.put("message", "OK");

                account.setPassword(newPass);
            }

            account.setId(account.getId());
            account.setRegister(account.getRegister());
            account.setAccountType(account.getAccountType());
            account.setCapDomicile(account.getCapDomicile());
            account.setCapResidence(account.getCapResidence());
            account.setCellularNumber(account.getCellularNumber());
            account.setCitizenship(account.getCitizenship());
            account.setContractExpirationDate(account.getContractExpirationDate());
            account.setDataOfBirth(account.getDataOfBirth());
            account.setEmail(account.getEmail());
            account.setFaculty(account.getFaculty());
            account.setFamilySituation(account.getFamilySituation());
            account.setFax(account.getFax());
            account.setIncome(account.getIncome());
            account.setMunicipalityDomicile(account.getMunicipalityDomicile());
            account.setMunicipalityResidence(account.getMunicipalityResidence());
            account.setNameUser(account.getNameUser());
            account.setPlaceofBirth(account.getPlaceOfBirth());
            account.setProvinceDomicile(account.getProvinceDomicile());
            account.setProvinceResidence(account.getProvinceResidence());
            account.setQualification(account.getQualification());
            account.setRegistrationDate(account.getRegistrationDate());
            account.setSurnameUser(account.getSurnameUser());
            account.setTaxCode(account.getTaxCode());
            account.setTelephoneNumber(account.getTelephoneNumber());
            account.setCapDomicile(account.getCapDomicile());
            account.setMunicipalityDomicile(account.getMunicipalityDomicile());
            account.setProvinceDomicile(account.getProvinceDomicile());
            account.setProvinceResidence(account.getProvinceResidence());
            account.setViaResidence(account.getViaResidence());
            account.setViaDomicile(account.getViaDomicile());
            account.setState(account.getState());
            account.setTypeParent(account.getTypeParent());

            man.update(account);
            out.print(jObj);

        } catch (SQLException ex) {
            Logger.getLogger(ModifyPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void sendMessageRedirect(HttpServletRequest request, HttpServletResponse response, String msg, int id)
            throws ServletException, IOException {
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/accountInformation.jsp?id=" + id).forward(request, response);
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
