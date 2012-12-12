/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class GetDailyMenuTableServlet extends HttpServlet {
    
    private ICanteenManager canteenManager;

    public void init(ServletConfig config) {
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
            
            List<MenuBean> menuList;
            String dailyMenuDateStr = request.getParameter("dailyMenuDate");
            if (dailyMenuDateStr != null && !dailyMenuDateStr.trim().equals("")) {
                try {
                    MenuBean searchMenu = new MenuBean();
                    searchMenu.setDate(parseGregorianCalendar(dailyMenuDateStr));
                    searchMenu.setType(MenuBean.DAILY_MENU);
                    menuList = canteenManager.search(searchMenu);
                } catch (ParseException e) {
                    sendMessageRedirect(request, response, "Errore: data inserita non valida");
                    return;
                }
            } else if (request.getParameter("onlyLastDailyMenu") != null) {
                menuList = canteenManager.getLastMenu(10, MenuBean.DAILY_MENU);
            } else {
                sendMessageRedirect(request, response, "Errore: impossibile recuperare la lista dei men&ugrave;");
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
                    if (menu.getChildInscriptionId() > 0) {
                        continue;
                    }
                    
                    JSONObject jObj = new JSONObject();
                    
                    checkAddToJSON(jObj, "0", unparseGregorianCalendar(menu.getDate()));
                    checkAddToJSON(jObj, "1", menu.getFirst());
                    checkAddToJSON(jObj, "2", menu.getSecond());
                    checkAddToJSON(jObj, "3", menu.getSideDish());
                    checkAddToJSON(jObj, "4", menu.getFruit());

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
            Logger.getLogger(GetDailyMenuTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetDailyMenuTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private void sendMessageRedirect(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/canteenManagement.jsp").forward(request, response);
    }

    private void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, "");
        }
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }
    
    private String unparseGregorianCalendar(GregorianCalendar pDate) {
        if (pDate != null) {
            return pDate.get(GregorianCalendar.YEAR) + "-"
                    + (pDate.get(GregorianCalendar.MONTH) + 1) + "-"
                    + pDate.get(GregorianCalendar.DAY_OF_MONTH);
        } else {
            return null;
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
