/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.accessManagement.registrationChildManagement;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
@WebServlet(name = "ServletGetTableRegistartionChild", urlPatterns = {"/GetTableRegistrationChild"})
public class ServletGetTableRegistrationChild extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
        
        System.out.println("sono nella servlet di gettable");
        
        RegistrationChild[] paginateChildRequestSet;
        List<RegistrationChild> listChildRequest;
        try {
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
           /*if (request.getParameter(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE) != null) {
                RegistrationChild child = new RegistrationChild();
                child.setRegistrationPhase(request.getParameter(DBNames.ATT_REGISTRATIONCHILD_REGISTRATIONPHASE));
                
                listChildRequest = registrationChildManager.search(child);
            } else {*/
                RegistrationChild child = new RegistrationChild();
                listChildRequest = registrationChildManager.search(child);
            //}

            int linksNumber = listChildRequest.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateChildRequestSet = new RegistrationChild[amount];
                    System.arraycopy(listChildRequest.toArray(), start, paginateChildRequestSet, 0, amount);
                } else {
                    paginateChildRequestSet = new RegistrationChild[toShow];
                    System.arraycopy(listChildRequest.toArray(), start, paginateChildRequestSet, 0, toShow);
                }
                for (RegistrationChild regChildRequest : paginateChildRequestSet) {
                    JSONArray ja = new JSONArray();
                    ja.put(regChildRequest.getSurname());
                    ja.put(regChildRequest.getName());
                    ja.put(regChildRequest.getRegistrationPhase());
                    String operazioni = "<input class='tableImage' type='image' src='img/trash.png' onclick='removeRegistrationChild(\"" + regChildRequest.getId() + "\")'/>"
                            + "<input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/edit.png' onclick='editRegistrationChild(\""+regChildRequest.getId()+"\")'/>"
                            + "<input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/zoo.png' onclick='viewDetailsRegistrationChild(\""+regChildRequest.getId()+"\")'/>";
                    ja.put(operazioni);
                    array.put(ja);
                                System.out.println(array.length());

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
        } catch(SQLException ex) {
            Logger.getLogger(ServletGetTableRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
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
