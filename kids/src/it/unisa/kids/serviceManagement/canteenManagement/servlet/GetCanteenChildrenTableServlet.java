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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
public class GetCanteenChildrenTableServlet extends HttpServlet {

    private IAccessFacade accessFacade;
    private ICanteenManager canteenManager;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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
            Map<Integer, List<Integer>> classChildMap = (Map<Integer, List<Integer>>) getServletContext().getAttribute("classChildMap");
            for (Integer i : classChildMap.keySet()) {
                Logger.getLogger("prova").log(Level.SEVERE, "rc = " + classChildMap.get(i));
            }
            List<Integer> childIdsSelectedList = classChildMap.get(Integer.parseInt(request.getParameter("classId")));
            Integer[] paginateChildIdSet;

            if (childIdsSelectedList == null) {
                result.put("sEcho", sEcho);
                result.put("iTotalRecords", 0);
                result.put("iTotalDisplayRecords", 0);
                result.put("aaData", array);
                response.setContentType("application/json");
                response.setHeader("Cache-Control",
                        "private, no-store, no-cache, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                out.print(result);
                return;
            }

            int linksNumber = childIdsSelectedList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateChildIdSet = new Integer[amount];
                    System.arraycopy(childIdsSelectedList.toArray(), start, paginateChildIdSet, 0, amount);
                } else {
                    paginateChildIdSet = new Integer[toShow];
                    System.arraycopy(childIdsSelectedList.toArray(), start, paginateChildIdSet, 0, toShow);
                }
                for (int childId : paginateChildIdSet) {
                    JSONObject jObj = new JSONObject();

                    // ottengo i dati del bambino
                    RegistrationChild searchRegChild = new RegistrationChild();
                    searchRegChild.setId(childId);
                    RegistrationChild regChild = accessFacade.search(searchRegChild).get(0);

                    // ottengo i dati del padre del bambino
                    Account searchAccount = new Account();
                    searchAccount.setId(regChild.getParentId());
                    Account parent = accessFacade.search(searchAccount).get(0);

                    // controllo se l'associazione bambino --- menu esiste già per il giorno attuale
                    boolean childHasDiffMenu = false;
                    MenuBean searchMenu = new MenuBean();
                    searchMenu.setChildInscriptionId(regChild.getId());
                    searchMenu.setDate(new GregorianCalendar());
                    searchMenu.setType(MenuBean.DIFF_MENU);
                    List<MenuBean> childDiffMenuList = canteenManager.search(searchMenu);
                    if (!childDiffMenuList.isEmpty()) {
                        childHasDiffMenu = true;
                    }

                    CommonMethod.checkAddToJSON(jObj, "0", regChild.getName() + " " + regChild.getSurname());
                    CommonMethod.checkAddToJSON(jObj, "1", parent.getNameUser() + " " + parent.getSurnameUser());

                    String acceptImage = "<img class=\"tableImage\" style=\"width:20px;height:20px\" title=\"Si\" alt=\"Si\" src=\"img/accept.png\" />";
                    String negateImage = "<img class=\"tableImage\" style=\"width:20px; height:20px;\" title=\"No\" alt=\"No\" src=\"img/negate.png\" />";
                    jObj.put("2", childHasDiffMenu ? acceptImage : negateImage);

                    jObj.put("DT_RowId", "" + regChild.getId());
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
            Logger.getLogger(GetCanteenChildrenTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetCanteenChildrenTableServlet.class.getName()).log(Level.SEVERE, null, ex);
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
