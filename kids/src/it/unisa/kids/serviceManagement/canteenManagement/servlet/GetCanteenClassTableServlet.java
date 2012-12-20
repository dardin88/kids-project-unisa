/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.classManagement.ClassBean;
import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    private IAccessFacade accessFacade;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accessFacade = new AccessFacade();     // si dovrebbe implementare il singleton anche qui?
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
                Map<Integer, List<Integer>> classChildMap = null;
                for (ClassBean clas : paginateClassSet) {
                    JSONObject jObj = new JSONObject();

                    // mappa che tiene traccia della classe con i suoi bambini, da conservare nella ServletContext per l'uso da parte di GetChildrenTableServlet
                    classChildMap = new HashMap<Integer, List<Integer>>();
                    List<Integer> childIds = new ArrayList<Integer>();  // lista degli id dei bambini che necessitano di un pranzo di questa classe

                    List<RegistrationChild> regChildList = clas.getBambini();    // oppure effettuare una ricerca di RegistrationChild con sectionId == clas.getIdClasse();
                    int mealCount = 0;
                    boolean classHasDiffMenu = false;
                    for (RegistrationChild rc : regChildList) {
                        if (CanteenUtilities.needsLunch(rc)) {
                            mealCount++;
                            if (CanteenUtilities.needsDiffMenu(rc)) {
                                classHasDiffMenu = true;
                                childIds.add(rc.getId());
                            }
                        }
                    }

                    CommonMethod.checkAddToJSON(jObj, "0", clas.getClassName());
                    CommonMethod.checkAddToJSON(jObj, "1", mealCount);

                    String acceptImage = "<img class=\"tableImage\" style=\"width:20px;height:20px\" title=\"Si\" alt=\"Si\" src=\"img/accept.png\" />";
                    String negateImage = "<img class=\"tableImage\" style=\"width:20px; height:20px;\" title=\"No\" alt=\"No\" src=\"img/negate.png\" />";
                    jObj.put("2", classHasDiffMenu ? acceptImage : negateImage);

                    jObj.put("DT_RowId", "" + clas.getIdClasse());
                    array.put(jObj);

                    // salvo la mappa costruita in questa ServletContext da prelevare nella GetChildrenTableServlet
                    classChildMap.put(clas.getIdClasse(), childIds);
                }
                getServletContext().setAttribute("classChildMap", classChildMap);
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
