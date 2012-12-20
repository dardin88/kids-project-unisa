/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Pasquale
 */
public class LoadCalendarServlet extends HttpServlet {

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
    private IMeetingManager am;

    public void init(ServletConfig config) {
        am = (IMeetingManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REUNION);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            ArrayList<Meeting> list = am.getMeetingList();
            JSONArray jsonArray = new JSONArray();

            Account acc = (Account) request.getSession().getAttribute("user");
            String account = acc.getAccountType();

            for (Meeting r : list) {
                if (account.equals("Admin") || account.equals("Segreteria") || account.equals("Delegato del rettore") || account.equals("Responsabile Asilo")) {
                    JSONObject json = new JSONObject();
                    json.put("id", r.getId());
                    json.put("title", r.getTitle());
                    json.put("description", r.getDescription());

                    String[] data = r.getDate().split("-");
                    String[] first = r.getFirstTime().split(":");
                    json.put("start", data[0] + "-" + data[1] + "-" + data[2] + "T" + first[0] + ":" + first[1] + ":00+01:00");
                    String[] end = r.getDate().split("-");
                    String[] second = r.getSecondTime().split(":");
                    json.put("end", end[0] + "-" + end[1] + "-" + end[2] + "T" + second[0] + ":" + second[1] + ":00+01:00");

                    jsonArray.put(json);
                } else {
                    if (r.getState().equals("Confermato")) {
                        JSONObject json = new JSONObject();
                        json.put("id", r.getId());
                        json.put("title", r.getTitle());
                        json.put("description", r.getDescription());

                        String[] data = r.getDate().split("-");
                        String[] first = r.getFirstTime().split(":");
                        json.put("start", data[0] + "-" + data[1] + "-" + data[2] + "T" + first[0] + ":" + first[1] + ":00+01:00");
                        String[] end = r.getDate().split("-");
                        String[] second = r.getSecondTime().split(":");
                        json.put("end", end[0] + "-" + end[1] + "-" + end[2] + "T" + second[0] + ":" + second[1] + ":00+01:00");

                        jsonArray.put(json);
                    }
                }
            }
            out.write(jsonArray.toString());
        } catch (SQLException ex) {
            Logger.getLogger(LoadCalendarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadCalendarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
}
