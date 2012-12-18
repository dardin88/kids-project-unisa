/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.accessManagement.registrationChildManagement.RegistrationChild;
import it.unisa.kids.common.CommonMethod;
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
public class InsertDiffMenuServlet extends HttpServlet {

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

            int childId = Integer.parseInt(request.getParameter("hiddenChildIdInsDiff"));
            if (childId <= 0) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: bambino selezionato non corretto - " + childId, "/canteenManagement.jsp");
                return;
            }
            menu.setChildInscriptionId(childId);

            menu.setType(MenuBean.DIFF_MENU);
            menu.setDate(new GregorianCalendar());  // setto la data corrente

            String first = request.getParameter("firstDiff").trim();
            if (first.length() > MEAL_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.", "/canteenManagement.jsp");
                return;
            }
            menu.setFirst(first);

            String second = request.getParameter("secondDiff").trim();
            if (second.length() > MEAL_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.", "/canteenManagement.jsp");
                return;
            }
            menu.setSecond(second);

            String sideDish = request.getParameter("sideDishDiff").trim();
            if (sideDish.length() > MEAL_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.", "/canteenManagement.jsp");
                return;
            }
            menu.setSideDish(sideDish);

            String fruit = request.getParameter("fruitDiff").trim();
            if (fruit.length() > MEAL_MAXLENGTH) {
                CommonMethod.sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.", "/canteenManagement.jsp");
                return;
            }
            menu.setFruit(fruit);

            // controllo se l'associazione bambino --- menu esiste già per il giorno attuale e se esiste effettuo update sulla voce già inserita
            MenuBean searchMenu = new MenuBean();
            searchMenu.setDate(menu.getDate());
            searchMenu.setChildInscriptionId(childId);
            searchMenu.setType(MenuBean.DIFF_MENU);
            List<MenuBean> childDiffMenuList = canteenManager.search(searchMenu);
            if (!childDiffMenuList.isEmpty()) {
                menu.setId(childDiffMenuList.get(0).getId());
                canteenManager.update(menu);
            } else {
                canteenManager.insert(menu);
            }

            // setting meal request to fulfilled
            RegistrationChild regChildSearch = new RegistrationChild();
            regChildSearch.setId(childId);
            RegistrationChild regChild = accessFacade.search(regChildSearch).get(0);

            MealRequestBean mealReqSearch = new MealRequestBean();
            mealReqSearch.setParentId(regChild.getParentId());
            mealReqSearch.setDate(menu.getDate());

            // ottengo la lista delle richieste odierne, per sicurezza le aggiorno tutte dato che potrebbe capitare di effettuare piu' di una richiesta odierna
            List<MealRequestBean> mealReqList = canteenManager.search(mealReqSearch);
            for (MealRequestBean mr : mealReqList) {
                mr.setFulfilledUsable(true);
                mr.setFulfilled(true);
                canteenManager.update(mr);
            }

            CommonMethod.sendMessageRedirect(request, response, "Men&ugrave; differenziato inserito con successo.", "/canteenManagement.jsp");

        } catch (SQLException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/canteenManagement.jsp");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

        } catch (NumberFormatException e) {
            CommonMethod.sendMessageRedirect(request, response, "Verfica i campi", "/canteenManagement.jsp");
            Logger.getLogger(InsertPaymentServlet.class.getName()).log(Level.SEVERE, null, e);

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
