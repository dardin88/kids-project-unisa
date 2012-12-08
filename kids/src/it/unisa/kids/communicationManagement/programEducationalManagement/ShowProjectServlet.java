/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.communicationManagement.newsManagement.GetNewsServlet;
import it.unisa.kids.communicationManagement.newsManagement.INewsManager;
import it.unisa.kids.communicationManagement.newsManagement.JDBCNewsManager;
import it.unisa.kids.communicationManagement.newsManagement.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class ShowProjectServlet extends HttpServlet {

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
        AnnualProject[] paginateAnnualProjectSet;
        ArrayList<AnnualProject> listAnnualProject;

        response.setContentType("text/html;charset=UTF-8");
        // Thread.sleep(5000);
        out = response.getWriter();
        IProgramEducational am = JDBCProgramEducational.getInstance();
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
        String word=request.getParameter("sSearch");
        if(!word.equals(""))
        {   
            int searchTerm = Integer.parseInt(word);
            AnnualProjectSection a = null;
            try {
                a = am.getProgramEducational(searchTerm);
                System.out.print(a.getName());
            } catch (SQLException ex) {
                Logger.getLogger(ShowProjectServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

//            int linksNumber = listAnnualProject.size();
//            if (linksNumber < amount) {
//                amount = linksNumber;
//            }
//            if (linksNumber != 0) {
//                int toShow = linksNumber - start;
//                if (toShow > 10) {
//                    paginateAnnualProjectSet = new AnnualProject[amount];
//                    System.arraycopy(listAnnualProject.toArray(), start, paginateAnnualProjectSet, 0, amount);
//                } else {
//                    paginateAnnualProjectSet = new AnnualProject[toShow];
//                    System.arraycopy(listAnnualProject.toArray(), start, paginateAnnualProjectSet, 0, toShow);
//
//
//                }
//                for (AnnualProject a : paginateAnnualProjectSet) {
            JSONArray ja = new JSONArray();
            ja.put(a.getName());
            ja.put(a.getTopic());
            ja.put(a.getApplicationYear());
            ja.put(a.getScadenza());
            String operazioni = "<div style=\"text-align:center;\" ><input id=\"idUpdateNews\" class='tableImage' height='20px' type='image' src='img/change.png' )\" />";
//                    if(nomeUtente.equals("Segreteria"))
//                    {
//                     operazioni += "<input id=\"removeNews\" onclick=\"removeNews("+a.getId() +",'"+a.getAttached()+"')\" class='tableImage' type='image' src='img/trash.png' /></div>";
//                     ja.put(operazioni);
//                    }
//                    else {
            operazioni += "</div>";
            ja.put(operazioni);

            array.put(ja);
            //            }

        }

        result.put("sEcho", sEcho);
        // result.put("iTotalRecords", linksNumber);
        // result.put("iTotalDisplayRecords", linksNumber);
        result.put("aaData", array);
        response.setContentType("application/json");
        response.setHeader("Cache-Control",
                "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        out.println(result);

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