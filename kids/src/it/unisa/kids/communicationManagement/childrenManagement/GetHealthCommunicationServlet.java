/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Elena
 */
public class GetHealthCommunicationServlet extends HttpServlet {
    
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
        PrintWriter out = response.getWriter();
        Communication[] paginateCommunicationSet;
        ArrayList<Communication> listCommunication;
     try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            ICommunicationManager am = JDBCCommunicationManager.getInstance();
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
            HttpSession s = request.getSession();
            Account account =  (Account) s.getAttribute("user");
            String nomeUtente=account.getAccountType();
            String searchTerm = "";
            searchTerm = request.getParameter("sSearch");            
            listCommunication = am.searchCommunication(searchTerm);
            int linksNumber = listCommunication.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateCommunicationSet = new Communication[amount];
                    System.arraycopy(listCommunication.toArray(), start, paginateCommunicationSet, 0, amount);
                } else {
                    paginateCommunicationSet = new Communication[toShow];
                    System.arraycopy(listCommunication.toArray(), start, paginateCommunicationSet, 0, toShow);
	          }
                for (Communication a : paginateCommunicationSet) {
                    if (a.getType().equalsIgnoreCase("Salute")){
                    JSONArray ja = new JSONArray();
                    ja.put(a.getIdChild());
                    ja.put(am.getName(a.getIdChild()));
                    ja.put(am.getSurname(a.getIdChild()));
                    ja.put(a.getDescription());
                    ja.put(a.getDate());
                    String operazioni="<div style=\"text-align:center;\" ><input id=\"idShowCommunication\" class='tableImage' height='20px' type='image' src='img/lente.gif' onclick=\"showCommunication("+a.getId()+",'"+a.getType()+"','"+a.getIdEducator()+"','"+a.getIdChild()+"','"+am.getName(a.getIdChild())+"','"+am.getSurname(a.getIdChild())+"','"+a.getDescription()+"','"+a.getDate()+"')\" /></div>";
                    ja.put(operazioni);
                    array.put(ja);
                    }
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", linksNumber);
            result.put("iTotalDisplayRecords", linksNumber);
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control","private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(GetHealthCommunicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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