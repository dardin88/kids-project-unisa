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
        PrintWriter out = response.getWriter();
        News[] paginateNewsSet;
        ArrayList<News> listNews;
        try {
            response.setContentType("text/html;charset=UTF-8");
            // Thread.sleep(5000);
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
            Account account = (Account) s.getAttribute("user");
            String nomeUtente = account.getAccountType();
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
                    if (!a.getType().equalsIgnoreCase("OrarioDiServizio")) {
                        ja.put(a.getTitle());
                        String d = (a.getDate().get(Calendar.YEAR) + "-" + (a.getDate().get(Calendar.MONTH) + 1) + "-" + a.getDate().get(Calendar.DAY_OF_MONTH));
                        ja.put(d);
                        ja.put(a.getTime().toString());
                        ja.put(a.getType());
                        String time = (a.getTime().toString().substring(0, 5));
                        ja.put("<a style=\"color:black;background:none;\" href=\"DownloadFile?nameFile=" + a.getAttached() + "\">" + a.getAttached() + "</a>");
                        String operazioni = "<div style=\"text-align:center;\" ><input id=\"idShowNews\" class='tableImage' height='20px' type='image' src='img/lente.gif' onclick=\"showNews('" + a.getTitle() + "','" + a.getDescription() + "','" + a.getType() + "','" + d + "','" + time + "','" + a.getAttached() + "')\" />";
                        if (nomeUtente.equals("Segreteria")) {
                            operazioni += "<input id=\"idUpdateNews\" class='tableImage' height='20px' type='image' src='img/change.png' onclick=\"updateNews(" + a.getId() + ",'" + a.getTitle() + "','" + a.getDescription() + "','" + a.getType() + "','" + d + "','" + time + "','" + a.getAttached() + "')\" />" + "<input id=\"removeNews\" onclick=\"removeNews(" + a.getId() + ",'" + a.getAttached() + "')\" class='tableImage' type='image' src='img/trash.png' /></div>";
                            ja.put(operazioni);
                        } else {
                            operazioni += "</div>";
                            ja.put(operazioni);
                        }
                        array.put(ja);
                    }
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
