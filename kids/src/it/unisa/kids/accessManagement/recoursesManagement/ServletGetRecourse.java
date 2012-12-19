package it.unisa.kids.accessManagement.recoursesManagement;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Lauri Giuseppe Giovanni
 */
public class ServletGetRecourse extends HttpServlet {
    private IRecoursesManager recourseManager;
    
    public void init(ServletConfig config) {
        recourseManager = (IRecoursesManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RECOURSE);
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
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        boolean isSuccess = true;
        String errorMsg = new String();
        StringBuilder html = new StringBuilder();
        try {
            Recourse tmpRecourse = new Recourse();
            
            // campi necessari per prelevare le informazioni
            String sId = request.getParameter(DBNames.ATT_RENUNCIATION_ID);
            if(sId != null && !sId.equals("")) {
                tmpRecourse.setId(Integer.parseInt(sId));
                
                tmpRecourse = recourseManager.search(tmpRecourse).get(0);
                
                html.append(bold("Dati dell'Iscrizione per cui è stato presentato il ricorso:") + newLine());
                html.append("Data di iscrizione: " + CommonMethod.parseString(tmpRecourse.getDate()) + newLine());
                html.append("Codice fiscale: " + tmpRecourse.getRegistrationChildFiscalCode() + newLine());
                html.append("Cognome: " + tmpRecourse.getRegistrationChildSurname() + newLine());
                html.append("Nome: " + tmpRecourse.getRegistrationChildName() + newLine());
                html.append(newLine() + newLine());
                
                html.append(bold("Dettagli del ricorso:") + newLine());
                html.append("Data di creazione: " + CommonMethod.parseString(tmpRecourse.getDate()) + newLine());
                html.append("Motivazione: " + tmpRecourse.getReason() + newLine());
                html.append("Valutazione: " + tmpRecourse.getValutation() + newLine());
                html.append(newLine());
                
                isSuccess = true;
                json.put("HTML", html.toString());
            } else {
                isSuccess = false;
                errorMsg = "Errore nella passaggio dei parametri";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletGetRecourse.class.getName()).log(Level.SEVERE, null, ex);
            isSuccess = false;
            errorMsg = ex.getMessage();
        }
        System.out.println("Risultato della GetRecourse: " + json.toString());

        json.put("IsSuccess", "" + isSuccess);
        json.put("ErrorMsg", errorMsg);

        out.write(json.toString());
        out.close();
    }

    private String bold(String text) {
        return "<b>" + text + "</b>";
    }
    
    private String newLine() {
        return "<br/>";
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
