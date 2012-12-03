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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gianmarco
 */
public class getTableAccountSecretaryServlet extends HttpServlet {

    private IAccountManager accountManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractAccountManager =null;//da vedere
        accountManager = (IAccountManager) refinedAbstractAccountManager.getManagerImplementor(DBNames.TABLE_ACCOUNT);
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
        Account[] pageAccount=null;
        List<Account> listAccount=null;
        try {
            JSONArray array = new JSONArray();
            JSONObject result = new JSONObject();
            int start = 0;
            int amount = 10;
            String sStart = request.getParameter("iDisplayStart");
            String sAmount = request.getParameter("sAmount");
            String sEcho = request.getParameter("sEcho");
            if (sStart != null) {
                start = Integer.parseInt(sStart);
                if (start < 0) {
                    start = 0;
                }
            }
            if (sAmount != null) {
                amount = Integer.parseInt(sAmount);
                if (amount < 10) {
                    amount = 10;
                }
            }
                   
            String nome= request.getParameter("name");
            String cognome= request.getParameter("surname");
            String codFisc= request.getParameter("taxCode");
            String nomeAcc= request.getParameter("nickName");
            String tipo= request.getParameter("type");
            
            Account account=new Account();
            account.setNameUser(nome);
            account.setSurnameUser(cognome);
            account.setTaxCode(codFisc);
            account.setAccountType(tipo);
            account.setNickName(nomeAcc);
            
            listAccount= accountManager.search(account);
            
            int linksNumber = listAccount.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                 if (toShow > 10) {
                    pageAccount= new Account[amount];
            System.arraycopy(listAccount.toArray(), start, pageAccount, 0, amount);
                 }
                 else{         
            pageAccount= new Account[toShow];
            System.arraycopy(listAccount.toArray(), start, pageAccount, 0, toShow);
                 }            
            for (Account acc : pageAccount) {
                    
                    JSONArray ja = new JSONArray();
                    
                    ja.put(acc.getNickName());
                    ja.put(acc.getNameUser());
                    ja.put(acc.getSurnameUser());
                    ja.put(acc.getTaxCode());
                    ja.put(acc.getAccountType());
                    String operazioni = "<input type='button' value='Visualizza' onclick='showAccount(\"" + acc.getId() + "\")'/>";
                    ja.put(operazioni);
                    array.put(ja);
                    }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listAccount.size());
            result.put("iTotalDisplayRecords", listAccount.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
            }
            
            catch (SQLException ex) {
            Logger.getLogger(getTableAccountSecretaryServlet.class.getName()).log(Level.SEVERE, null, ex);           
          
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
