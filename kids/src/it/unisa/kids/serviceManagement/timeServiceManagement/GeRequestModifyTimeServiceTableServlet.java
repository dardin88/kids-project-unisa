/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.timeServiceManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.accessManagement.accountManagement.IAccountManager;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccountFacade;
import it.unisa.kids.common.facade.IAccountFacade;
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
@WebServlet(name = "GetRequestModifyTimeServiceTableServlet", urlPatterns = {"/GetRequestModifyTimeServiceTable"})
public class GeRequestModifyTimeServiceTableServlet extends HttpServlet {

    private ITimeServiceManager timeServiceManager;
    private IAccountFacade accountManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTimeServiceManager = RefinedAbstractManager.getInstance();
        timeServiceManager = (ITimeServiceManager) refinedAbstractTimeServiceManager.getManagerImplementor(DBNames.TABLE_TIMESERVICE);
        accountManager = new AccountFacade();

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
        ModifyTimeServiceRequest[] requestModifiTimeService;
        List<ModifyTimeServiceRequest> listRequestModifyTimeService;
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

            /* if (!request.getParameter("Data").equals("")) {
             String date=request.getParameter("Data");
             String[] dateArray=date.split("-");
             TraineeRequest tr = new TraineeRequest();
             tr.setDate(new GregorianCalendar(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[0])));
             listTraineeRequest = trainingManager.search(tr);
             } else {*/
            ModifyTimeServiceRequest modTimeSerReq = new ModifyTimeServiceRequest();

            HttpSession session = request.getSession();
            if (((Account) session.getAttribute("user")).getAccountType().equals("Responsabile Asilo")) {
                modTimeSerReq.setState("Sottomessa");
            } else {
                modTimeSerReq.setState("Validata");
            }
            listRequestModifyTimeService = timeServiceManager.search(modTimeSerReq);
            //}

            int linksNumber = listRequestModifyTimeService.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    requestModifiTimeService = new ModifyTimeServiceRequest[amount];
                    System.arraycopy(listRequestModifyTimeService.toArray(), start, requestModifiTimeService, 0, amount);
                } else {
                    requestModifiTimeService = new ModifyTimeServiceRequest[toShow];
                    System.arraycopy(listRequestModifyTimeService.toArray(), start, requestModifiTimeService, 0, toShow);
                }
                Account parent = new Account();
                RegistrationChild registrationChild = new RegistrationChild();
                for (ModifyTimeServiceRequest mtsr : requestModifiTimeService) {
                    parent.setId(mtsr.getIdParent());
                    JSONArray ja = new JSONArray();
                    List<Account> list = accountManager.search(parent);
                    ja.put(list.get(0).getNameUser() + " " + list.get(0).getSurnameUser());
                    ja.put(mtsr.getUserRange());
                    ja.put(mtsr.getState());
                    String operazioni = "";
                    if (((Account) session.getAttribute("user")).getAccountType().equals("Responsabile Asilo")) {
                        operazioni = "<input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/lente.gif' onclick='loadInformationRequestModifyTimeServiceAsylumResponsible(\"" + mtsr.getId() + "\")'/>";
                    } else {
                        operazioni = "<input class='tableImage' type='image' style=\"width:20px;height:20px\" src='img/lente.gif' onclick='loadInformationRequestModifyTimeServiceRectorDelegate(\"" + mtsr.getId() + "\")'/>";
                    }

                    ja.put(operazioni);



                    array.put(ja);
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
        } catch (SQLException ex) {
            Logger.getLogger(GetTraineesServletTable.class.getName()).log(Level.SEVERE, null, ex);
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
