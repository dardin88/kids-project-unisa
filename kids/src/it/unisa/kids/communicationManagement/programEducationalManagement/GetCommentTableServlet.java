/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
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
 * @author Antonio Panariello
 */
public class GetCommentTableServlet extends HttpServlet {

    private IActivityManager activityManager;
    private IAccessFacade accessFacade;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        activityManager = (IActivityManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
        accessFacade = new AccessFacade();
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
            out = response.getWriter();
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

            List<CommentBean> commentList;
            CommentBean searchComment = createSearchComment(request);
            commentList = activityManager.search(searchComment);
            CommentBean[] paginateCommentSet;
            int linksNumber = commentList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateCommentSet = new CommentBean[amount];
                    System.arraycopy(commentList.toArray(), start, paginateCommentSet, 0, amount);
                } else {
                    paginateCommentSet = new CommentBean[toShow];
                    System.arraycopy(commentList.toArray(), start, paginateCommentSet, 0, toShow);
                }

                for (CommentBean comm : paginateCommentSet) {
                    JSONObject jObj = new JSONObject();

                    Account searchAccount = new Account();
                    searchAccount.setId(comm.getAuthorId());
                    Account authorAccount = accessFacade.search(searchAccount).get(0);
                    String author = authorAccount.getNameUser() + " " + authorAccount.getSurnameUser();
                    String operazioni = "<input class='tableImage' type='image' src='img/trash.png' onclick=\"removeComment(" + comm.getId() + ");\" />";

                    CommonMethod.checkAddToJSON(jObj, "0", CommonMethod.parseString(comm.getDate()));
                    CommonMethod.checkAddToJSON(jObj, "1", comm.getTime().toString());
                    CommonMethod.checkAddToJSON(jObj, "2", comm.getContent());
                    CommonMethod.checkAddToJSON(jObj, "3", author);
                    CommonMethod.checkAddToJSON(jObj, "4", operazioni);

                    jObj.put("DT_RowId", "" + comm.getId());        // setto l'id del commento nella rowId della datatable per comodita'
                    array.put(jObj);
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
            Logger.getLogger(GetSectionActivitiesTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (SQLException e) {
            Logger.getLogger(GetSectionActivitiesTableServlet.class.getName()).log(Level.INFO, null, e);
        } finally {
            out.close();
        }
    }

    private CommentBean createSearchComment(HttpServletRequest request) {
        String commentType = request.getParameter("commentType");
        CommentBean searchComment = new CommentBean();
        if (commentType.equals(CommentBean.ANNUAL_COMMENT)) {
            searchComment.setClassId(0);       // setto a 0 per cercare solo i commenti annuali
            searchComment.setAnnualId(-1);
            searchComment.setContent(request.getParameter("sSearch"));
        } else {
            searchComment.setAnnualId(0);      // setto a 0 per cercare solo i commenti per classi
            searchComment.setClassId(Integer.parseInt(request.getParameter("classId")));
        }

        return searchComment;
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
