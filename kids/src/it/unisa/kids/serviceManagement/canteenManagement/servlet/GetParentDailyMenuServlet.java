/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
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
import org.json.JSONObject;

/**
 *
 * @author navi
 */
public class GetParentDailyMenuServlet extends HttpServlet {

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

            List<MenuBean> menuList = canteenManager.getLastMenu(1, MenuBean.DAILY_MENU, true);
            if (menuList.isEmpty()) {
                return;
            }

            MenuBean menu = menuList.get(0);

            CommonMethod.checkAddToJSON(result, "date", CommonMethod.parseString(menu.getDate()));
            CommonMethod.checkAddToJSON(result, "first", menu.getFirst());
            CommonMethod.checkAddToJSON(result, "second", menu.getSecond());
            CommonMethod.checkAddToJSON(result, "sideDish", menu.getSideDish());
            CommonMethod.checkAddToJSON(result, "fruit", menu.getFruit());

            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
            Logger.getLogger(GetParentDailyMenuServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetParentDailyMenuServlet.class.getName()).log(Level.SEVERE, null, ex);
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
