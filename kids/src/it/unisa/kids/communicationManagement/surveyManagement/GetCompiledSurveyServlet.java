package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author felice
 */
public class GetCompiledSurveyServlet extends HttpServlet {

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
        Survey[] paginateSurveySet;
        List<Survey> listSurvey;
        try {
            ICompiledSurveyManager am = JDBCCompiledSurveyManager.getInstance();
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
            HttpSession s = request.getSession();
            Account account = (Account) s.getAttribute("user");
            listSurvey = am.search(account);
            int linksNumber = listSurvey.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateSurveySet = new Survey[amount];
                    System.arraycopy(listSurvey.toArray(), start, paginateSurveySet, 0, amount);
                } else {
                    paginateSurveySet = new Survey[toShow];
                    System.arraycopy(listSurvey.toArray(), start, paginateSurveySet, 0, toShow);
                }
                for (Survey sur : listSurvey) {
                    JSONArray jarr = new JSONArray();
                    jarr.put(sur.getId());
                    jarr.put("<form id=\'makeSurveyForm\' action=\'surveyForm.jsp\' method=\'GET\'>"+
                            "<input name=\'link\' id=\'link\' type=\'hidden\' value=\'"+sur.getLink()+"\'/>"+
                            "<input name=\'idUtente\' id=\'idUtente\' type=\'hidden\' value=\'"+account.getId()+"\'/>"+
                            "<input name=\'idQuestionario\' id=\'idQuestionario\' type=\'hidden\' value=\'"+sur.getId()+"\'/>"+
                            "<input type=\'submit\' value=\'"+sur.getLink()+"\'/>"+
                            "</form>");
                    array.put(jarr);
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
            out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(GetCompiledSurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private String escapeUrl(String url){
        String escapedUrl = url.replace("/", "//");
        return escapedUrl;
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
