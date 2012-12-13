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
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import it.unisa.kids.serviceManagement.paymentManagement.servlet.InsertPaymentServlet;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author navi
 */
public class InsertDailyMenuServlet extends HttpServlet {

    // questi attributi dovrebbero essere presenti nella classe it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild
    public static final String FULL_TIME = "full_time";
    public static final String PART_TIME_M = "part_time_mattutina";
    public static final String PART_TIME_P = "part_time_pomeridiana";
    private static final int MEAL_MAXLENGTH = 200;
    private ICanteenManager canteenManager;
    private IAccessFacade accessFacade;

    public void init(ServletConfig config) {
        this.canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MENU);
        this.accessFacade = new AccessFacade();
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

        try {
            MenuBean menu = new MenuBean();

            menu.setChildInscriptionId(0);
            menu.setType(MenuBean.DAILY_MENU);
            menu.setDate(new GregorianCalendar());  // setto la data corrente

            String first = request.getParameter("firstEditDaily").trim();
            if (first.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setFirst(first);

            String second = request.getParameter("secondEditDaily").trim();
            if (second.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setSecond(second);

            String sideDish = request.getParameter("sideDishEditDaily").trim();
            if (sideDish.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setSideDish(sideDish);

            String fruit = request.getParameter("fruitEditDaily").trim();
            if (fruit.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setFruit(fruit);
            
            MenuBean searchMenu = new MenuBean();
            searchMenu.setChildInscriptionId(0);
            searchMenu.setDate(menu.getDate());
            List<MenuBean> dailyMenuList = canteenManager.search(searchMenu);
            if (dailyMenuList.size() > 0) {
                menu.setId(dailyMenuList.get(0).getId());
                canteenManager.update(menu);
            } else {
                canteenManager.insert(menu);
            }
            
            menu.setId(0);
            // creo l'associazione bambino --- menu giornaliero per tutti i bambini attualmente iscritti
            List<ClassBean> classList = accessFacade.getClasses();
            for (ClassBean clas : classList) {
                List<RegistrationChild> regChildList = clas.getBambini();    // oppure effettuare una ricerca di RegistrationChild con sectionId == clas.getIdClasse();
                for (RegistrationChild rc : regChildList) {
                    if (needsLunch(rc) && !needsDiffMenu(rc)) {
                        menu.setChildInscriptionId(rc.getId());
                        canteenManager.insert(menu);

                        if (!rc.getUserRange().equals(FULL_TIME)) {
                            // setting meal request to fulfilled
                            MealRequestBean mealReqSearch = new MealRequestBean();
                            mealReqSearch.setParentId(rc.getParentId());
                            mealReqSearch.setDate(new GregorianCalendar());

                            // ottengo la lista delle richieste odierne, per sicurezza le aggiorno tutte dato che potrebbe capitare di effettuare piu' di una richiesta odierna
                            List<MealRequestBean> mealReqList = canteenManager.search(mealReqSearch);
                            for (MealRequestBean mr : mealReqList) {
                                mr.setFulfilledUsable(true);
                                mr.setFulfilled(true);
                                canteenManager.update(mr);
                            }
                        }
                    }
                }
            }

            sendMessageRedirect(request, response, "Men&ugrave; giornaliero inserito con successo.");

        } catch (SQLException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            sendMessageRedirect(request, response, "Verfica i campi");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void sendMessageRedirect(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/canteenManagement.jsp").forward(request, response);
    }

    private boolean needsLunch(RegistrationChild rc) throws SQLException {
        if (rc.getUserRange().equals(FULL_TIME)) {
            return true;
        }

        MealRequestBean mr = new MealRequestBean();
        mr.setParentId(rc.getParentId());
        mr.setDate(new GregorianCalendar());    // setto la data corrente per verificare solo le richieste per il giorno corrente
        mr.setFulfilledUsable(true);
        List<MealRequestBean> mealReqList = canteenManager.search(mr);

        if (mealReqList.size() > 0) {
            return true;
        }

        return false;
    }

    private boolean needsDiffMenu(RegistrationChild rc) {
        if (rc.getSickness() != null && !rc.getSickness().trim().equals("")) {
            return true;
        }
        if (rc.getAdditionalNotes() != null && !rc.getAdditionalNotes().trim().equals("")) {
            return true;
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
