/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Elena
 */
public class AddCommunicationServlet extends HttpServlet {
    Connection connection = null;
    Statement stmt = null;
    ResultSet rsCommunication = null;

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
            ICommunicationManager am = JDBCCommunicationManager.getInstance();
            Communication a = new Communication();  
            int aId = Integer.parseInt(request.getParameter("Id"));
            int aType = Integer.parseInt(request.getParameter("Type"));
            //int aIdEducator = Integer.parseInt(request.getParameter("IdEducator"));
           
            HttpSession session = request.getSession();
            Account user = (Account) session.getAttribute("user");
            int aIdEducator = user.getId();
            
            String childName = request.getParameter("childName");
            String childSurname = request.getParameter("childSurname");
            
            connection = DBConnectionPool.getConnection();
            String query = "select "+DBNames.ATT_REGISTRATIONCHILD_ID+" from " + DBNames.TABLE_REGISTRATIONCHILD
                    + " where "+DBNames.ATT_REGISTRATIONCHILD_NAME+"="+childName+"&&"
                    +DBNames.ATT_REGISTRATIONCHILD_SURNAME+"="+childSurname;
            rsCommunication = stmt.executeQuery(query);
            int IdC = 0;
            while (rsCommunication.next()) {
                IdC = rsCommunication.getInt(DBNames.ATT_REGISTRATIONCHILD_ID);
            }
            int aIdChild=IdC;            
            
            String aDescription = request.getParameter("Description");
            String aDate = request.getParameter("Date");
            //GregorianCalendar aDate= parseGregorianCalendar(aDat);
            boolean aSolved = Boolean.getBoolean(request.getParameter("Solved"));
            
            a.setId(aId);
            a.setType(aType);
            a.setIdEducator(aIdEducator);
            a.setIdChild(aIdChild);
            a.setDescription(aDescription);
            a.setDate(aDate);
            a.setSolved(aSolved);
            am.insertCommunication(a);   
        } catch (Exception ex) {
            Logger.getLogger(GetCommunicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }*/

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
