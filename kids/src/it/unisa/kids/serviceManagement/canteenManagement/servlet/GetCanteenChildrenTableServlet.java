/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
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

            ServletContext getCanteenTableContext = getServletContext().getContext("/GetCanteenClassTable");
            Map<Integer, List<Integer>> classChildMap = (Map<Integer, List<Integer>>) getCanteenTableContext.getAttribute("classChildMap");
            List<Integer> childIdsSelectedList = classChildMap.get(Integer.parseInt(request.getParameter("classId")));
            int[] paginateChildIdSet;


            int linksNumber = childIdsSelectedList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateChildIdSet = new int[amount];
                    System.arraycopy(childIdsSelectedList.toArray(), start, paginateChildIdSet, 0, amount);
                } else {
                    paginateChildIdSet = new int[toShow];
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

                    checkAddToJSON(jObj, "0", regChild.getName() + " " + regChild.getSurname());
                    checkAddToJSON(jObj, "1", parent.getNameUser() + " " + parent.getSurnameUser());
                    
                    String acceptImage = "<img class=\"tableImage\" style=\"width:20px;height:20px\" title=\"Si\" alt=\"Si\" src=\"img/accept.png\" />";
                    String negateImage = "<img class=\"tableImage\" style=\"width:20px; height:20px;\" title=\"No\" alt=\"No\" src=\"img/negate.png\" />";
                    jObj.put("2", needsDiffMenu(regChild) ? acceptImage : negateImage);

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

    private void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, "");
        }
    }

    private boolean needsDiffMenu(RegistrationChild rc) {
        if (rc.getSickness() != null && !rc.getSickness().trim().equals("")) {
            if (rc.getAdditionalNotes() != null && !rc.getAdditionalNotes().trim().equals("")) {
                return true;
            }
        }
        return false;
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