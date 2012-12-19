/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.communicationManagement.programEducationalManagement.CommentBean;
import it.unisa.kids.communicationManagement.programEducationalManagement.IActivityManager;
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
 * @author navi
 */
public class InsertCommentServlet extends HttpServlet {

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
        try {
            
            CommentBean insertComment = createInsertComment(request);

            activityManager.insert(insertComment);

        } catch (SQLException e) {
            Logger.getLogger(InsertCommentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            Logger.getLogger(InsertCommentServlet.class.getName()).log(Level.SEVERE, null, e);

        }
    }
    
    private CommentBean createInsertComment(HttpServletRequest request) {
        String commentType = request.getParameter("commentType");
        CommentBean insertComment = new CommentBean();
        
        if (commentType.equals(CommentBean.ANNUAL_COMMENT)) {
            insertComment.setClassId(0);       // setto a 0 per inserire commento annuale
            // altri set...
        } else {
            insertComment.setAnnualId(0);      // setto a 0 per inserire commento per classi
            // altri set...
        }

        return insertComment;
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
