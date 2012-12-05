/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.paymentManagement.servlet;

import it.unisa.kids.serviceManagement.trainingManagement.*;
import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author utente
 */
public class GetParentsTableServlet extends HttpServlet {

    private IAccessFacade accessFacade;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
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
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out = response.getWriter();
            JSONObject result = new JSONObject();
            JSONArray array = new JSONArray();
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

            Account searchAccount = checkSearchParameters(request);
            List<Account> parentsList = accessFacade.search(searchAccount);
            Account[] paginateParentSet;


            int linksNumber = parentsList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateParentSet = new Account[amount];
                    System.arraycopy(parentsList.toArray(), start, paginateParentSet, 0, amount);
                } else {
                    paginateParentSet = new Account[toShow];
                    System.arraycopy(parentsList.toArray(), start, paginateParentSet, 0, toShow);
                }
                for (Account parent : paginateParentSet) {
                    JSONObject jObj = new JSONObject();
                    
                    checkAddToJSON(jObj, "0", parent.getNameUser());
                    checkAddToJSON(jObj, "1", parent.getSurnameUser());
                    checkAddToJSON(jObj, "2", parent.getTaxCode());
                    
                    jObj.put("DT_RowId", "" + parent.getId());
                    array.put(jObj);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", linksNumber);
            result.put("iTotalDisplayRecords", linksNumber);
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
            Logger.getLogger(GetParentsTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetParentsTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private Account checkSearchParameters(HttpServletRequest pRequest) {
        Account acc = new Account();
        acc.setAccountType("Genitore");

        if (pRequest.getParameter("parentName") != null && !pRequest.getParameter("parentName").equals("")) {
            acc.setNameUser(pRequest.getParameter("parentName"));
        }

        if (pRequest.getParameter("parentSurname") != null && !pRequest.getParameter("parentSurname").equals("")) {
            acc.setSurnameUser(pRequest.getParameter("parentSurname"));
        }

        if (pRequest.getParameter("parentFiscalCode") != null && !pRequest.getParameter("parentFiscalCode").equals("")) {
            acc.setTaxCode(pRequest.getParameter("parentFiscalCode"));
        }

        return acc;
    }
    
    private void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, JSONObject.NULL);
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
