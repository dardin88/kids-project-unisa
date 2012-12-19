/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.CommonMethod;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Antonio Panariello
 */
public class GetSectionActivitiesTableServlet extends HttpServlet {

    private IActivityManager manager;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        manager = (IActivityManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
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

            List<Activity> activityList = null;
            int classId = Integer.parseInt(request.getParameter("classId"));
            Activity searchActivity = new Activity();
            searchActivity.setIdClass(classId);
            activityList = manager.search(searchActivity);

            Activity[] paginateActivitySet;
            int linksNumber = activityList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateActivitySet = new Activity[amount];
                    System.arraycopy(activityList.toArray(), start, paginateActivitySet, 0, amount);
                } else {
                    paginateActivitySet = new Activity[toShow];
                    System.arraycopy(activityList.toArray(), start, paginateActivitySet, 0, toShow);
                }

                for (Activity act : paginateActivitySet) {
                    JSONObject jObj = new JSONObject();

                    String operazioni = "<input class='tableImage' type='image' height='20px' src='img/change.png' onclick=\"updateActivity(" + act.getId() + ");\" />"
                            + "<input class='tableImage' type='image' src='img/trash.png' onclick=\"removeActivity(" + act.getId() + ");\" />";

                    CommonMethod.checkAddToJSON(jObj, "0", act.getName());
                    CommonMethod.checkAddToJSON(jObj, "1", act.getDescription());
                    CommonMethod.checkAddToJSON(jObj, "2", CommonMethod.parseString(act.getStartDate()));
                    CommonMethod.checkAddToJSON(jObj, "3", CommonMethod.parseString(act.getEndDate()));
                    CommonMethod.checkAddToJSON(jObj, "4", operazioni);

                    jObj.put("DT_RowId", "" + act.getId());
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
            Logger.getLogger(GetSectionActivitiesTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (SQLException e) {
            Logger.getLogger(GetSectionActivitiesTableServlet.class.getName()).log(Level.INFO, null, e);
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
