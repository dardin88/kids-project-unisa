/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.childrenManagement;

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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Elena
 */
public class GetDetailCommunicationServlet extends HttpServlet {
    
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
        JSONArray array = new JSONArray();
        try {
            ICommunicationManager am = JDBCCommunicationManager.getInstance();
            String searchTerm = "id";
            searchTerm = request.getParameter("sSearch");  
            ArrayList<Communication> listCommunication = am.searchCommunication(searchTerm);
            for (Communication a : listCommunication) {
                JSONObject json = new JSONObject();
                json.put("type",a.getType());
                json.put("idEducator",a.getIdEducator());
                json.put("idChild",a.getIdChild());
                json.put("description",a.getDescription());
                //int day = a.getDate().get(Calendar.DAY_OF_MONTH);
                //int month = a.getDate().get(Calendar.MONTH);
                //int year = a.getDate().get(Calendar.YEAR);
                //json.put("date",year + "-" + month + "-" + day);
                json.put("date",a.getDate());
                json.put("solved",a.getSolved());
                array.put(json);
            }
            out.println(array.toString());
        }
        catch (Exception ex) {
            Logger.getLogger(GetCommunicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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