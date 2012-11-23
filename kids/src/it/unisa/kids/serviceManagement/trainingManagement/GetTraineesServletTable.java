/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.storage.connectionPool.DBConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
public class GetTraineesServletTable extends HttpServlet {

    private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractTrainingManager refinedAbstractTrainingManager = new RefinedAbstractTrainingManager();
        trainingManager = refinedAbstractTrainingManager.getManagerImplementor();
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
        Trainee[] paginateTraineeSet;
        ArrayList<Trainee> listTrainee;
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
            if (!request.getParameter("Nome").equals("") && request.getParameter("Cognome").equals("")) {
                Trainee t = new Trainee();
                t.setName(request.getParameter("Nome"));
                listTrainee = trainingManager.search(t);
            } else if (request.getParameter("Nome").equals("") && !request.getParameter("Cognome").equals("")) {
                Trainee t = new Trainee();
                t.setSurname(request.getParameter("Cognome"));
                listTrainee = trainingManager.search(t);
            } else if (!request.getParameter("Nome").equals("") && !request.getParameter("Cognome").equals("")) {
                Trainee t = new Trainee();
                t.setSurname(request.getParameter("Cognome"));
                t.setName(request.getParameter("Nome"));

                listTrainee = trainingManager.search(t);
            } else {
                listTrainee = trainingManager.search();

            }

            int linksNumber = listTrainee.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateTraineeSet = new Trainee[amount];
                    System.arraycopy(listTrainee.toArray(), start, paginateTraineeSet, 0, amount);
                } else {
                    paginateTraineeSet = new Trainee[toShow];
                    System.arraycopy(listTrainee.toArray(), start, paginateTraineeSet, 0, toShow);
                }
                for (Trainee trainee : paginateTraineeSet) {
                    
                    JSONArray ja = new JSONArray();
                    ja.put(trainee.getRegister());
                    ja.put(trainee.getName());
                    ja.put(trainee.getSurname());
                    String operazioni = "<input class='tableImage' type='image' src='img/trash.png' onclick='removeTrainee(\"" + trainee.getRegister() + "\")'/><input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/zoo.png' onclick='loadInformationTraineePage(\""+trainee.getRegister()+"\")'/>";
                    ja.put(operazioni);
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
            out.print(result);
        } catch (SQLException ex) {
            Logger.getLogger(GetTraineesServletTable.class.getName()).log(Level.SEVERE, null, ex);
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
