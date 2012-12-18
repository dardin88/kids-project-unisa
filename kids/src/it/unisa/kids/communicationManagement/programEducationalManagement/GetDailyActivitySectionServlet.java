/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.timeServiceManagement.ITimeServiceManager;
import it.unisa.kids.serviceManagement.timeServiceManagement.TimeServiceRequest;
import it.unisa.kids.serviceManagement.trainingManagement.GetTraineesServletTable;
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
 * @author marco
 */
public class GetDailyActivitySectionServlet extends HttpServlet {

    private IActivityManager manager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        manager = (IActivityManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            DailyActivitySection[] paginateDailyActivitySectionSet;
            List<DailyActivitySection> listDailyActivitySections;
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
                int idSection = Integer.parseInt(request.getParameter("idSection"));
                DailyActivitySection dailyActivitySection = new DailyActivitySection();
            listDailyActivitySections = manager.search(dailyActivitySection);


                int linksNumber = listDailyActivitySections.size();
                if (linksNumber < amount) {
                    amount = linksNumber;
                }
                if (linksNumber != 0) {
                    int toShow = linksNumber - start;
                    if (toShow > 10) {
                        paginateDailyActivitySectionSet = new DailyActivitySection[amount];
                        System.arraycopy(listDailyActivitySections.toArray(), start, paginateDailyActivitySectionSet, 0, amount);
                    } else {
                        paginateDailyActivitySectionSet = new DailyActivitySection[toShow];
                        System.arraycopy(listDailyActivitySections.toArray(), start, paginateDailyActivitySectionSet, 0, toShow);
                    }
                    for (DailyActivitySection das : paginateDailyActivitySectionSet) {
                        JSONArray ja = new JSONArray();
                        ja.put("Genitore");
                        String type = ();
                        ja.put(type);
                        ja.put(timeService.getDayRequested());
                        String operazioni = "Conferma?<input type=\"checkbox\" id=\"" + timeService.getId() + "\" onclick=\"updateTimeServiceRequest('" + timeService.getId() + "',this)\"";
                        if (timeService.getConfirmed() == 1) {
                            operazioni += "checked>";
                        } else {
                            operazioni += ">";
                        }
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
            

        } catch (SQLException ex) {
            Logger.getLogger(GetDailyActivitySectionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
