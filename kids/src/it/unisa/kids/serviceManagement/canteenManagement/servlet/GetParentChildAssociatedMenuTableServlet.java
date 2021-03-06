/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
 * @author navi
 */
public class GetParentChildAssociatedMenuTableServlet extends HttpServlet {

    private IAccessFacade accessFacade;
    private ICanteenManager canteenManager;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
        this.canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MENU);
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

            int childId = 0;
            try {
                childId = Integer.parseInt(request.getParameter("childId"));
            } catch (NumberFormatException e) {
                doExit(result, sEcho, array, response, out);
                return;
            }

            List<MenuBean> menuList;
            String menuDateStr = request.getParameter("menuDate");
            if (menuDateStr != null && !menuDateStr.trim().equals("")) {
                try {
                    MenuBean searchMenu = new MenuBean();
                    searchMenu.setDate(CommonMethod.parseGregorianCalendar(menuDateStr));
                    searchMenu.setChildInscriptionId(childId);
                    menuList = canteenManager.search(searchMenu);
                } catch (Exception e) {
                    doExit(result, sEcho, array, response, out);
                    return;
                }
            } else {
                doExit(result, sEcho, array, response, out);
                return;
            }

            MenuBean[] paginateMenuSet;
            int linksNumber = menuList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateMenuSet = new MenuBean[amount];
                    System.arraycopy(menuList.toArray(), start, paginateMenuSet, 0, amount);
                } else {
                    paginateMenuSet = new MenuBean[toShow];
                    System.arraycopy(menuList.toArray(), start, paginateMenuSet, 0, toShow);
                }

                for (MenuBean menu : paginateMenuSet) {
                    JSONObject jObj = new JSONObject();

                    CommonMethod.checkAddToJSON(jObj, "0", CommonMethod.parseString(menu.getDate()));
                    CommonMethod.checkAddToJSON(jObj, "1", menu.getFirst());
                    CommonMethod.checkAddToJSON(jObj, "2", menu.getSecond());
                    CommonMethod.checkAddToJSON(jObj, "3", menu.getSideDish());
                    CommonMethod.checkAddToJSON(jObj, "4", menu.getFruit());
                    CommonMethod.checkAddToJSON(jObj, "5", menu.getType());

                    //jObj.put("DT_RowId", "" + menu.getId());
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
            Logger.getLogger(GetParentChildAssociatedMenuTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetParentChildAssociatedMenuTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private void doExit(JSONObject result, String sEcho, JSONArray array, HttpServletResponse response, PrintWriter out) {
        result.put("sEcho", sEcho);
        result.put("iTotalRecords", 0);
        result.put("iTotalDisplayRecords", 0);
        result.put("aaData", array);
        response.setContentType("application/json");
        response.setHeader("Cache-Control",
                "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        out.print(result);
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
