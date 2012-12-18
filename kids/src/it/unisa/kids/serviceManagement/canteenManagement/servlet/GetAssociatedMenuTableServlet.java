/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import java.io.IOException;
import java.io.PrintWriter;
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
public class GetAssociatedMenuTableServlet extends HttpServlet {

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

            List<MenuBean> menuList = null;
            String menuDateStr = request.getParameter("menuDate");
            String onlyLast = request.getParameter("onlyLastAssMenu");
            if (menuDateStr != null && !menuDateStr.trim().equals("")) {
                try {
                    MenuBean searchMenu = new MenuBean();
                    searchMenu.setDate(CommonMethod.parseGregorianCalendar(menuDateStr));
                    searchMenu.setChildInscriptionId(-1);
                    menuList = canteenManager.search(searchMenu);
                } catch (Exception e) {
                    return;
                }
            } else if (onlyLast != null && !onlyLast.trim().equals("")) {
                menuList = canteenManager.getLastMenu(10, null, false);
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
                    if (menu.getChildInscriptionId() <= 0) {
                        continue;
                    }

                    JSONObject jObj = new JSONObject();

                    // ottengo i dati del bambino
                    RegistrationChild searchRegChild = new RegistrationChild();
                    searchRegChild.setId(menu.getChildInscriptionId());
                    RegistrationChild regChild = accessFacade.search(searchRegChild).get(0);

                    // ottengo i dati del padre del bambino
                    Account searchAccount = new Account();
                    searchAccount.setId(regChild.getParentId());
                    Account parent = accessFacade.search(searchAccount).get(0);

                    CommonMethod.checkAddToJSON(jObj, "0", CommonMethod.parseString(menu.getDate()));
                    CommonMethod.checkAddToJSON(jObj, "1", regChild.getName() + " " + regChild.getSurname());
                    CommonMethod.checkAddToJSON(jObj, "2", parent.getNameUser() + " " + parent.getSurnameUser());
                    CommonMethod.checkAddToJSON(jObj, "3", menu.getType());

                    jObj.put("DT_RowId", "" + menu.getId());
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
            Logger.getLogger(GetAssociatedMenuTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetAssociatedMenuTableServlet.class.getName()).log(Level.SEVERE, null, ex);
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
