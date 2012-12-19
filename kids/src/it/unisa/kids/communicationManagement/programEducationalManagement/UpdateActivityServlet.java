/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

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

/**
 *
 * @author Antonio Panariello
 */
public class UpdateActivityServlet extends HttpServlet {
    
    public static final int ACT_NAME_MAXLENGTH = 40;
    public static final int ACT_CONTENT_MAXLENGTH = 1500;
    
    private IActivityManager activityManager;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.activityManager = (IActivityManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
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
        try {
            int actId = Integer.parseInt(request.getParameter("hiddenActIdMod"));
            if (actId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: attivit&agrave; errata", "/sectionEdu.jsp");
                return;
            }
            String activityName = request.getParameter("activityNameMod");
            String startDate = request.getParameter("StartDateMod");
            String endDate = request.getParameter("EndDateMod");
            String activityContent = request.getParameter("activityContentMod");
            
            if (activityName.trim().length() > ACT_NAME_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: nome troppo lungo", "/sectionEdu.jsp");
                return;
            }
            
            if (activityContent.trim().length() > ACT_CONTENT_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: contenuto troppo lungo", "/sectionEdu.jsp");
                return;
            }
            
            Activity removeActivity = new Activity();
            removeActivity.setId(actId);
            removeActivity = activityManager.search(removeActivity).get(0);
            int classId = removeActivity.getIdClass();
            activityManager.delete(removeActivity);
            
            Activity act = new Activity();
            act.setName(activityName.trim());
            act.setDescription(activityContent.trim());
            act.setStartDate(CommonMethod.parseGregorianCalendar(startDate));
            act.setEndDate(CommonMethod.parseGregorianCalendar(endDate));
            act.setIdClass(classId);
            
            activityManager.insert(act);
            
            CommonMethod.sendMessageRedirect(request, response, "Modifica attivit&agrave; avvenuta con successo", "/sectionEdu.jsp");
        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: attivit&agrave; errata", "/sectionEdu.jsp");
        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Errore: verifica campi", "/sectionEdu.jsp");
            Logger.getLogger(UpdateActivityServlet.class.getName()).log(Level.SEVERE, "prova", e);
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
