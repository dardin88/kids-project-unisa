/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
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
 * @author tonino
 */
public class GetTableChildServlet extends HttpServlet {

    private IRegistrationChildManager childManager;

    public void init(ServletConfig config) {
        childManager = (IRegistrationChildManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        RegistrationChild[] pageRegistrationChild = null;
        List<RegistrationChild> listRegistrationChild = null;

        try {
            JSONArray array = new JSONArray();
            JSONObject result = new JSONObject();
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

            listRegistrationChild = childManager.search(new RegistrationChild());

            int linksNumber = listRegistrationChild.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    pageRegistrationChild = new RegistrationChild[amount];
                    System.arraycopy(listRegistrationChild.toArray(), start, pageRegistrationChild, 0, amount);
                } else {
                    pageRegistrationChild = new RegistrationChild[toShow];
                    System.arraycopy(listRegistrationChild.toArray(), start, pageRegistrationChild, 0, toShow);
                }
                for (RegistrationChild childreg : pageRegistrationChild) {
                    JSONArray ja = new JSONArray();

                    ja.put(childreg.getName());
                    ja.put(childreg.getSurname());
                    String operazioni = "<input type='checkbox' name='childRow' value='" + childreg.getId() + "' >";
                    
                    Logger.getLogger(GetTableChildServlet.class.getName()).log(Level.SEVERE, "operazioni = " + operazioni);

                    ja.put(operazioni);
                    array.put(ja);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listRegistrationChild.size());
            result.put("iTotalDisplayRecords", listRegistrationChild.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);

        } catch (SQLException ex) {
            Logger.getLogger(GetTableClassServlet.class.getName()).log(Level.SEVERE, null, ex);
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
