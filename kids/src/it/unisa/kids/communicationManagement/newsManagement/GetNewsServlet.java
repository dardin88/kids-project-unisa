/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author francesco
 */
public class GetNewsServlet extends HttpServlet {

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
        /*  PrintWriter out = response.getWriter();
         JSONObject result = new JSONObject();
         try {
         JSONArray array = new JSONArray();
         String sStart = request.getParameter("iDisplayStart");
         String sAmount = request.getParameter("sAmount");
         String sEcho = request.getParameter("sEcho");
         INewsManager mn = JDBCNewsManager.getInstance();
         ArrayList<News> listaNews = mn.showNews();
         for (News n : listaNews) {

         JSONArray json = new JSONArray();
         json.put(n.getTitle());
         json.put(n.getDescription());
         json.put(n.getDate().get(Calendar.YEAR) + "/" + (n.getDate().get(Calendar.MONTH) + 1) + "/" + n.getDate().get(Calendar.DAY_OF_MONTH));
         json.put(n.getTime().toString());
         json.put(n.getAttached());
         String operazioni = "<input class='tableImage' type='image' src='img/trash.png' />";
         json.put(operazioni);
         array.put(json);
         }
         result.put("sEcho", sEcho);
         result.put("iTotalRecords", sAmount);
         result.put("iTotalDisplayRecords", sStart);
         result.put("aaData", array);
         response.setContentType("application/json");
         response.setHeader("Cache-Control",
         "private, no-store, no-cache, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         out.println(result);
         } catch (Exception ex) {
         Logger.getLogger(GetNewsServlet.class.getName()).log(
         Level.SEVERE, null, ex);
         } finally {
         out.close();
         }
         */
        PrintWriter out = response.getWriter();
        News[] paginateNewsSet;
        ArrayList<News> listNews;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            INewsManager am = JDBCNewsManager.getInstance();
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
            listNews = am.search(searchTerm);
            int linksNumber = listNews.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateNewsSet = new News[amount];
                    System.arraycopy(listNews.toArray(), start, paginateNewsSet, 0, amount);
                } else {
                    paginateNewsSet = new News[toShow];
                    System.arraycopy(listNews.toArray(), start, paginateNewsSet, 0, toShow);


                }
                for (News a : paginateNewsSet) {
                    JSONArray ja = new JSONArray();
                    ja.put(a.getTitle());
                    ja.put(a.getDescription());
                    int day = a.getDate().get(Calendar.DAY_OF_MONTH);
                    int month = a.getDate().get(Calendar.MONTH);
                    int year = a.getDate().get(Calendar.YEAR);
                    ja.put(year + "-" + month + "-" + day);
                    ja.put(a.getTime().toString());
                    ja.put(a.getType());
                    if(nomeUtente.equals("Segreteria"))
                    {
                     String operazioni = "<input class='tableImage' type='image' src='img/trash.png' />"+"<input class='tableImage' height='20px' type='image' src='img/lente.gif' />";
                     ja.put(operazioni);
                    }
                    else {
                        ja.put("");
                    }
                    array.put(ja);
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
            out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(GetNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
