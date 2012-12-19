/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccountFacade;
import it.unisa.kids.common.facade.IAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
public class GetDailyActivityServlet extends HttpServlet {

    private IActivityManager manager;
    private IAccountFacade accountFacade;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        manager = (IActivityManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_ACTIVITYSECTIONDAILY);
        accountFacade = new AccountFacade();

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
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("activityId"));
            System.out.println("Attivita"+id);
            DailyActivitySection dailyActivitySection = new DailyActivitySection();
            dailyActivitySection.setId(id);
            List<DailyActivitySection> listDailyActivity = manager.search(dailyActivitySection);

            Account account = new Account();
            Activity activity=new Activity();
            activity.setId(listDailyActivity.get(0).getIdActivity());
            account.setId(listDailyActivity.get(0).getIdEducator());
            List<Account> listAccount=accountFacade.search(account);
            List<Activity> listActivity=manager.search(activity);
            GregorianCalendar data = listDailyActivity.get(0).getData();
            if(listDailyActivity.get(0).getNotes()!=null)
                out.println(listActivity.get(0).getName()+","+data.get(Calendar.YEAR) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.DAY_OF_MONTH)+","+listAccount.get(0).getNameUser()+" "+listAccount.get(0).getSurnameUser()+","+listDailyActivity.get(0).getNotes());
            else
                out.println(listActivity.get(0).getName()+","+data.get(Calendar.YEAR) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.DAY_OF_MONTH)+","+listAccount.get(0).getNameUser()+" "+listAccount.get(0).getSurnameUser()+", ");
        } catch (SQLException ex) {
            Logger.getLogger(GetDailyActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
