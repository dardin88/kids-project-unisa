package it.unisa.kids.accessManagement.classManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
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
public class GetTableEducatorServlet extends HttpServlet {

    private IAccountManager educatorManager;

    @Override
    public void init(ServletConfig config) {
        educatorManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
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

            Account[] pageAccount;
            List<Account> listAccount = null;
            List<Account> otherListAccount = null;

            JSONArray array = new JSONArray();
            JSONObject result = new JSONObject();
            int start = 0;
            int amount = 10;
            int classId = 0;
            if (!request.getParameter("classId").equals("")) {
                classId = Integer.parseInt(request.getParameter("classId"));
            }
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

            if (request.getSession().getAttribute("cercamiNeiSogni").equals("modify")) {
                //metto un account vuoto perchè quando si richiede la modifica deve visualizzare tutti gli educatori
                Account tmp = new Account();
                ClassBean clas = new ClassBean();
                clas.setIdClasse(classId);
                listAccount = educatorManager.searchEducatorByClass(clas);
                tmp.setAccountType("Educatore");
                otherListAccount = educatorManager.search(tmp);
                for (Account edu : otherListAccount) {
                    if (!listAccount.contains(edu)) {
                        listAccount.add(edu);
                    }
                }
            } else if (request.getSession().getAttribute("cercamiNeiSogni").equals("insert")) {
                Account tmp = new Account();
                tmp.setAccountType("Educatore");
                listAccount = educatorManager.search(tmp);
            } else if (request.getSession().getAttribute("cercamiNeiSogni").equals("information")) {
                //metto un account vuoto perchè quando si richiede la modifica deve visualizzare tutti gli educatori
                ClassBean clas = new ClassBean();
                clas.setIdClasse(Integer.parseInt(request.getParameter("classId")));
                listAccount = educatorManager.searchEducatorByClass(clas);
            }

            int linksNumber = listAccount.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    pageAccount = new Account[amount];
                    System.arraycopy(listAccount.toArray(), start, pageAccount, 0, amount);
                } else {
                    pageAccount = new Account[toShow];
                    System.arraycopy(listAccount.toArray(), start, pageAccount, 0, toShow);
                }
                for (Account educator : pageAccount) {
                    JSONArray ja = new JSONArray();

                    ja.put(educator.getNameUser());
                    ja.put(educator.getSurnameUser());
                    String operazioni;
                    if (request.getSession().getAttribute("cercamiNeiSogni").equals("modify")) {
                        ClassBean tmpClassBean = new ClassBean();
                        tmpClassBean.setIdClasse(classId);
                        List<Account> checkedEducator = educatorManager.searchEducatorByClass(tmpClassBean);
                        operazioni = "<input type='checkbox' id='educatorRow' name='educatorRow' value='" + educator.getId() + "' >";
                        for (Account c2 : checkedEducator) {
                            if (educator.getId() == c2.getId()) {
                                operazioni = "<input type='checkbox' id='educatorRow' name='educatorRow' value='" + educator.getId() + "' checked=\'true\'>";
                            }
                        }
                    } else {
                        operazioni = "<input type='checkbox' id='educatorRow' name='educatorRow' value='" + educator.getId() + "' >";
                    }
                    ja.put(operazioni);
                    array.put(ja);
                }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listAccount.size());
            result.put("iTotalDisplayRecords", listAccount.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
        } catch (SQLException ex) {
            Logger.getLogger(GetTableEducatorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
