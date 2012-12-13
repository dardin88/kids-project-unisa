/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.accessManagement.registrationChildManagement.IRegistrationChildManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.trainingManagement.GetTraineesServletTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author marco
 */
@WebServlet(name = "GetRequestModifyTimeServiceServlet", urlPatterns = {"/GetRequestModifyTimeService"})

public class GetRequestModifyTimeServiceServlet extends HttpServlet {

    private ITimeServiceManager timeServiceManager;
    private IRegistrationChildManager registrationChildManager;
    private IAccountManager accountManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        timeServiceManager = (ITimeServiceManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_TIMESERVICE);
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
        accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);

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
            PrintWriter out=response.getWriter();
            int id = Integer.parseInt(request.getParameter("id"));
            ModifyTimeServiceRequest modifyTimeServiceRequest = new ModifyTimeServiceRequest();
            modifyTimeServiceRequest.setId(id);
            List<ModifyTimeServiceRequest> listModifyTimeServiceRequest = timeServiceManager.search(modifyTimeServiceRequest);
            RegistrationChild registrationChild = new RegistrationChild();
            registrationChild.setId(listModifyTimeServiceRequest.get(0).getIdChild());
            List<RegistrationChild> listChild = registrationChildManager.search(registrationChild);
            Account parent = new Account();
            parent.setId(listModifyTimeServiceRequest.get(0).getIdParent());

            List<Account> listAccount =accountManager.search(parent);
            String toJsp="";
            if(listModifyTimeServiceRequest.get(0).getMotivation()!=null){
                toJsp+=listChild.get(0).getName()+","+listChild.get(0).getSurname()+","+listAccount.get(0).getNameUser()+","+listAccount.get(0).getSurnameUser()+","+listModifyTimeServiceRequest.get(0).getUserRange()+","+listModifyTimeServiceRequest.get(0).getMotivation()+","+listModifyTimeServiceRequest.get(0).getState()+","+listModifyTimeServiceRequest.get(0).getId()+","+listModifyTimeServiceRequest.get(0).getOpinion();

            }
            else{
                toJsp+=listChild.get(0).getName()+","+listChild.get(0).getSurname()+","+listAccount.get(0).getNameUser()+","+listAccount.get(0).getSurnameUser()+","+listModifyTimeServiceRequest.get(0).getUserRange()+", ,"+listModifyTimeServiceRequest.get(0).getState()+","+listModifyTimeServiceRequest.get(0).getId()+","+listModifyTimeServiceRequest.get(0).getOpinion();
            }
            out.print(toJsp);
            
        } catch (SQLException ex) {
            Logger.getLogger(GetRequestModifyTimeServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
