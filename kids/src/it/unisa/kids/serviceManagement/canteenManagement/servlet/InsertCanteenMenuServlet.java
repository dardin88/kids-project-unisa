/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.canteenManagement.servlet;

import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.serviceManagement.canteenManagement.ICanteenManager;
import it.unisa.kids.serviceManagement.canteenManagement.MenuBean;
import it.unisa.kids.serviceManagement.paymentManagement.servlet.InsertPaymentServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class InsertCanteenMenuServlet extends HttpServlet {

    private static final int MEAL_MAXLENGTH = 200;
    private ICanteenManager canteenManager;

    public void init(ServletConfig config) {
        this.canteenManager = (ICanteenManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MENU);
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
                sendMessageRedirect(request, response, "Errore: bambino selezionato non corretto - " + childId);
                return;
            }
            menu.setChildInscriptionId(childId);

            menu.setType(MenuBean.DIFF_MENU);
            menu.setDate(new GregorianCalendar());  // setto la data corrente

            String first = request.getParameter("firstDiff").trim();
            if (first.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setFirst(first);

            String second = request.getParameter("secondDiff").trim();
            if (second.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setSecond(second);

            String sideDish = request.getParameter("sideDishDiff").trim();
            if (sideDish.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setSideDish(sideDish);

            String fruit = request.getParameter("fruitDiff").trim();
            if (fruit.length() > MEAL_MAXLENGTH) {
                sendMessageRedirect(request, response, "Errore: campo menu troppo lungo.");
                return;
            }
            menu.setFruit(fruit);

            canteenManager.insert(menu);

            sendMessageRedirect(request, response, "Men&ugrave; differenziato inserito con successo.");

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
