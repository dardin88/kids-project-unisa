/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MealRequestBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author navi
 */
public class GetCanteenClassTableServlet extends HttpServlet {

    // questi attributi dovrebbero essere presenti nella classe it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild
    public static final String FULL_TIME = "full_time";
    public static final String PART_TIME_M = "part_time_mattutina";
    public static final String PART_TIME_P = "part_time_pomeridiana";
    private IAccessFacade accessFacade;
    private ICanteenManager canteenManager;

    public void init(ServletConfig config) {
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
        this.canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MEAL_REQUEST);
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

            List<ClassBean> classList = accessFacade.getClasses();
            ClassBean[] paginateClassSet;


            int linksNumber = classList.size();
            if (linksNumber < amount) {
                amount = linksNumber;
            }
            if (linksNumber != 0) {
                int toShow = linksNumber - start;
                if (toShow > 10) {
                    paginateClassSet = new ClassBean[amount];
                    System.arraycopy(classList.toArray(), start, paginateClassSet, 0, amount);
                } else {
                    paginateClassSet = new ClassBean[toShow];
                    System.arraycopy(classList.toArray(), start, paginateClassSet, 0, toShow);
                }
                for (ClassBean clas : paginateClassSet) {
                    JSONObject jObj = new JSONObject();

                    checkAddToJSON(jObj, "0", clas.getClassName());
                    
                    // mappa che tiene traccia della classe con i suoi bambini, da conservare nella ServletContext per l'uso da parte di GetChildrenTableServlet
                    Map<Integer, List<Integer>> classChildMap = new HashMap<Integer, List<Integer>>();
                    List<Integer> childIds = new ArrayList<Integer>();  // lista degli id dei bambini che necessitano di un pranzo di questa classe

                    List<RegistrationChild> regChildList = clas.getBambini();    // oppure effettuare una ricerca di RegistrationChild con sectionId == clas.getIdClasse();
                    int mealCount = 0;
                    boolean classHasDiffMenu = false;;
                    for (RegistrationChild rc : regChildList) {
                        if (needsLunch(rc)) {
                            mealCount++;
                            childIds.add(rc.getId());
                            if (!classHasDiffMenu && needsDiffMenu(rc)) {
                                classHasDiffMenu = true;
                            }
                        }
                    }

                    checkAddToJSON(jObj, "1", mealCount);
                    checkAddToJSON(jObj, "2", classHasDiffMenu);

                    jObj.put("DT_RowId", "" + clas.getIdClasse());
                    array.put(jObj);
                    
                    // salvo la mappa costruita in questa ServletContext da prelevare nella GetChildrenTableServlet
                    classChildMap.put(clas.getIdClasse(), childIds);
                    getServletContext().setAttribute("classChildMap", classChildMap);
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
            Logger.getLogger(GetCanteenClassTableServlet.class.getName()).log(Level.INFO, "Query result(JSONObject): " + result.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetCanteenClassTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, "");
        }
    }

    private boolean needsLunch(RegistrationChild rc) throws SQLException {
        if (rc.getUserRange().equals(FULL_TIME)) {
            return true;
        }

        MealRequestBean mr = new MealRequestBean();
        mr.setParentId(rc.getParentId());     // in realta' è setParentId, da sistemare in un secondo momento nel manager della mensa
        mr.setDate(new GregorianCalendar());    // setto la data corrente per verificare solo le richieste per il giorno corrente
        List<MealRequestBean> mealReqList = canteenManager.search(mr);

        if (mealReqList.size() > 0) {
            return true;
        }

        return false;
    }

    private boolean needsDiffMenu(RegistrationChild rc) {
        if (rc.getSickness() != null && !rc.getSickness().trim().equals("")) {
            if (rc.getAdditionalNotes() != null && !rc.getAdditionalNotes().trim().equals("")) {
                return true;
            }
        }
        return false;
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
