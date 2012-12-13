package it.unisa.kids.accessManagement.classManagement;

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
public class GetTableClassServlet extends HttpServlet {

    private IClassManager classManager;

    public void init(ServletConfig config) {
        classManager = (IClassManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_CLASS);
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
        ClassBean[] pageClassBean = null;
        List<ClassBean> listClassBean = null;
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

            String nome = request.getParameter("name");
            String stato = request.getParameter("state");

            ClassBean pClassBean = new ClassBean();
            pClassBean.setState(stato);
            pClassBean.setClassName(nome);

            if (!pClassBean.getClassName().equals("") || !pClassBean.getState().equals("")) {
                listClassBean = classManager.search(pClassBean);
            } else {
                listClassBean = classManager.getAll();
            }

            int linksNumber = listClassBean.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    pageClassBean = new ClassBean[amount];
                    System.arraycopy(listClassBean.toArray(), start, pageClassBean, 0, amount);
                } else {
                    pageClassBean = new ClassBean[toShow];
                    System.arraycopy(listClassBean.toArray(), start, pageClassBean, 0, toShow);
                }
                for (ClassBean clas : pageClassBean) {

                    JSONArray ja = new JSONArray();

                    ja.put(clas.getClassName());
                    ja.put(clas.getState());
                    String operazioni = " <input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/lente.gif' onclick='showClass(\"" + clas.getIdClasse() + "\")'/><input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/edit.gif' onclick='modifyClass(\"" + clas.getIdClasse() + "\")'/><input class='tableImage' type='image' src='img/trash.png' onclick='removeClass(\"" + clas.getIdClasse() + "\")'/>";
                    ja.put(operazioni);
                    array.put(ja);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listClassBean.size());
            result.put("iTotalDisplayRecords", listClassBean.size());
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
