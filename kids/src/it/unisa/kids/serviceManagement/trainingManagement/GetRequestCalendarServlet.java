/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import com.google.gson.Gson;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class GetRequestCalendarServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger("global");
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

        try {
            PrintWriter out = response.getWriter();
            List<TraineeRequest> listTraineeRequest;
            List<Map<String, Object>> events = new ArrayList<Map<String, Object>>();
            listTraineeRequest = trainingManager.getRequestsList();
            for (TraineeRequest traineeRequest : listTraineeRequest) {
                Map<String, Object> map = new HashMap<String, Object>();
               //String date = traineeRequest.getDate().get(Calendar.YEAR) + "-" + (traineeRequest.getDate().get(Calendar.MONTH) + 1) + "-" + traineeRequest.getDate().get(Calendar.DAY_OF_MONTH);
                int year=traineeRequest.getDate().get(Calendar.YEAR);
                int month=traineeRequest.getDate().get(Calendar.MONTH);
                int day=traineeRequest.getDate().get(Calendar.DAY_OF_MONTH);
                int hoursStart=traineeRequest.getStartTime().getHours();
                int minutesStart=traineeRequest.getStartTime().getMinutes();
                int hoursEnd=traineeRequest.getEndTime().getHours();
                int minutesEnd=traineeRequest.getEndTime().getMinutes();
                String date=year+"-"+(month+1)+"-"+day;
               // GregorianCalendar date1=new GregorianCalendar();
            //    date1.set(2012,11,21);
              //  date1.setTime(traineeRequest.getStartTime());
               
               Date start=new Date(year-1900,month,day,hoursStart,minutesStart);
               Date end=new Date(year-1900,month,day,hoursEnd,minutesEnd);
               // Calendar date=new GregorianCalendar(year,month,day);
               // date = new Date(traineeRequest.getDate().get(Calendar.YEAR),(traineeRequest.getDate().get(Calendar.MONTH) + 1), traineeRequest.getDate().get(Calendar.DAY_OF_MONTH));
               //logger.info(""+traineeRequest.getStartTime().getMinutes());
                // date.setMinutes(traineeRequest.getStartTime().getMinutes());
                //date.setHours(traineeRequest.getStartTime().getHours());
                map.put("id", traineeRequest.getId());
                map.put("title", "richiesta di "+traineeRequest.getTraineeNumber()+" tirocinanti");
                map.put("traineeNumber", traineeRequest.getTraineeNumber());
                map.put("dateRequest",date);
                map.put("startTime", hoursStart+":"+minutesStart);
                map.put("endTime",hoursEnd+":"+minutesEnd);
                map.put("acitivity",traineeRequest.getActivity());
                map.put("start", start);
                map.put("end", end);
                map.put("allDay", false);
                map.put("activity",traineeRequest.getActivity());
                // map.put("")

                events.add(map);

            }
            String json = new Gson().toJson(events);
            System.out.println(json);
            out.write(json);


        } catch (SQLException ex) {
            Logger.getLogger(GetRequestCalendarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
