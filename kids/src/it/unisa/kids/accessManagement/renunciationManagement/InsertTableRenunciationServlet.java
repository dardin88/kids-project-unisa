package it.unisa.kids.accessManagement.renunciationManagement;

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
 * @author MIKI
 */
public class InsertTableRenunciationServlet extends HttpServlet {

       private IRenunciationManager renunciationManager;

    public void init(ServletConfig config) {
        renunciationManager =(IRenunciationManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RENUNCIATION);
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
        Renunciation[] pageRenunciation=null;
        List<Renunciation> listRenunciation=null;
     try{
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
                   
            String nome= request.getParameter("idGenitore");
            
            
            Renunciation pRenunciation=new Renunciation();
            pRenunciation.setIdBambino(Integer.parseInt(nome));
            
            listRenunciation= renunciationManager.search(pRenunciation);
                      
          
            
            int linksNumber = listRenunciation.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                 if (toShow > 10) {
                    pageRenunciation= new Renunciation[amount];
            System.arraycopy(listRenunciation.toArray(), start, pageRenunciation, 0, amount);
                 }
                 else{         
            pageRenunciation= new Renunciation[toShow];
            System.arraycopy(listRenunciation.toArray(), start, pageRenunciation, 0, toShow);
                 }            
            for (Renunciation clas : pageRenunciation) {
                    
                    JSONArray ja = new JSONArray();
                    
                    ja.put(clas.getIdBambino());
                    String operazioni ="<input class='tableImage' type='image' src='img/trash.png' onclick='DeleteClassBean(\"" + clas.getId()+ "\")'/> <input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/lente.gif' onclick='showAccount(\""+clas.getId()+"\")'/>";
                    ja.put(operazioni);
                    array.put(ja);
                    }
            }
            result.put("sEcho", sEcho);
            result.put("iTotalRecords", listRenunciation.size());
            result.put("iTotalDisplayRecords", listRenunciation.size());
            result.put("aaData", array);
            response.setContentType("application/json");
            response.setHeader("Cache-Control",
                    "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            out.print(result);
     }
     catch(SQLException ex) {
            Logger.getLogger(InsertTableRenunciationServlet.class.getName()).log(Level.SEVERE, null, ex);           
          
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
