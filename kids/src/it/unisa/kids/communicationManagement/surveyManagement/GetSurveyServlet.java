package it.unisa.kids.communicationManagement.surveyManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class GetSurveyServlet extends HttpServlet{
    
    
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
        try{
            ISurveyManager am = JDBCSurveyManager.getInstance();
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
            Account account =  (Account) s.getAttribute("user");
            String nomeUtente=account.getAccountType();                       
            listSurvey = am.search();
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

              for(Survey sur : listSurvey) {
                  JSONArray jarr = new JSONArray();
                  jarr.put(sur.getId());
                  jarr.put(sur.getLink());
                  
                  String operazioni="<div style=\"text-align:center;\" ><input id=\"idUpdateSurvey\" class='tableImage' height='20px' type='image' src='img/lente.gif' onclick=\"updateSurvey("+sur.getId()+",'"+sur.getLink()+"')\" />";
                    if( nomeUtente.equals("Genitore")){
                        operazioni += "<input id=\"idUpdateSurvey\" class='tableImage' height='20px' type='image' src='img/change.png' onclick=\"updateSurvey("+sur.getId()+",'"+sur.getLink()+"')\" />"+"<input id=\"removeSurvey\" onclick=\"removeSurvey("+sur.getId() +",'"+sur.getLink()+"')\" class='tableImage' type='image' src='img/trash.png' /></div>";
                        jarr.put(operazioni);
                    }
                    else {
                        operazioni+="</div>";
                        jarr.put(operazioni);
                    }
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
        
        }catch (Exception ex) {
            Logger.getLogger(GetSurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
