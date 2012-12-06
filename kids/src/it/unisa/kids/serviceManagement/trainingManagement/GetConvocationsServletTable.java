/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
 * @author utente
 */
public class GetConvocationsServletTable extends HttpServlet {
private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractTrainingManager = RefinedAbstractManager.getInstance();
        trainingManager = (ITrainingManager) refinedAbstractTrainingManager.getManagerImplementor(DBNames.TABLE_TRAINEE);
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
        TraineeConvocation[] paginateTraineeConvocationSet;
        List<TraineeConvocation> listTraineeConvocation;
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
           /*if (!request.getParameter("Data").equals("") ) {
                TraineeConvocation t = new TraineeConvocation();
                t.setNameU(request.getParameter("Nome"));
                listTraineeConvocation = trainingManager.search(t);
            } else if (request.getParameter("Nome").equals("") && !request.getParameter("Cognome").equals("")) {
                Account t = new Account();
                t.setSurnameUser(request.getParameter("Cognome"));
                listTraineeConvocation = trainingManager.search(t);
            } else if (!request.getParameter("Nome").equals("") && !request.getParameter("Cognome").equals("")) {
                Account t = new Account();
                t.setSurnameUser(request.getParameter("Cognome"));
                t.setNameUser(request.getParameter("Nome"));

                listTraineeConvocation = trainingManager.search(t);
            } else {*/
                listTraineeConvocation = trainingManager.getTraineeConvocationList();

            

            int linksNumber = listTraineeConvocation.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateTraineeConvocationSet = new TraineeConvocation[amount];
                    System.arraycopy(listTraineeConvocation.toArray(), start, paginateTraineeConvocationSet, 0, amount);
                } else {
                    paginateTraineeConvocationSet = new TraineeConvocation[toShow];
                    System.arraycopy(listTraineeConvocation.toArray(), start, paginateTraineeConvocationSet, 0, toShow);
                }
                for (TraineeConvocation traineeConvocation : paginateTraineeConvocationSet) {
                    Account account=new Account();
                    account.setId(traineeConvocation.getTraineeId());
                    List<Account> list=trainingManager.search(account);
                    
                    JSONArray ja = new JSONArray();
                    ja.put(list.get(0).getRegister());
                    ja.put(traineeConvocation.getActivityName());
                    String date=traineeConvocation.getDate().get(Calendar.YEAR)+"/"+(traineeConvocation.getDate().get(Calendar.MONTH)+1)+"/"+traineeConvocation.getDate().get(Calendar.DAY_OF_MONTH);
                    ja.put(date);
                    ja.put(traineeConvocation.getStartTime().getHours()+":"+traineeConvocation.getStartTime().getMinutes());
                    ja.put(traineeConvocation.getEndTime().getHours()+":"+traineeConvocation.getEndTime().getMinutes());
                    String confirmed="";
                    if(traineeConvocation.getConfirmed()==0)
                        confirmed="Non confermato";
                    else
                        confirmed="Confermato";
                    ja.put(confirmed);
                   String operazioni = "<input class='tableImage' type='image' src='img/trash.png' onclick='removeTraineeConvocation(\"" + traineeConvocation.getId() + "\")'/>";
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
