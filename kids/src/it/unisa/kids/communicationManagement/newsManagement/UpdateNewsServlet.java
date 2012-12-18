
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.communicationManagement.newsManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author francesco
 */
public class UpdateNewsServlet extends HttpServlet {

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
            boolean flag = false;
            PrintWriter out = response.getWriter();
            String vecchioAllegato = request.getParameter("oldAllegato");
            String vecchioTitolo = request.getParameter("oldTitolo");
            INewsManager mn = JDBCNewsManager.getInstance();
            News n = new News();
            String newTitle = request.getParameter("artefactTitolo");
            n.setTitle(newTitle);
            n.setDescription(request.getParameter("artefactDescrizione"));
            String allegato = request.getParameter("artefactAllegato");
            String path = (String) getServletContext().getInitParameter("attachedFileFolder");
            File f = new File(path + "/" + vecchioTitolo + vecchioAllegato);
            if (!allegato.equals("")) {
                f.delete();
                n.setAttached(allegato);
                flag = true;
            } else {
                if (!newTitle.equalsIgnoreCase(vecchioTitolo)) {
                    f.renameTo(new File(path + "/" + newTitle +vecchioAllegato));
                }
            }
            int id = Integer.parseInt(request.getParameter("idNews"));
            int scelta = Integer.parseInt(request.getParameter("artefactTipo"));
            switch (scelta) {
                case 1: {
                    n.setType("Evento");
                    break;
                }
                case 2: {
                    n.setType("Notizia");
                    break;
                }
            }
            n.setId(id);
            String data = request.getParameter("artefactData");
            String[] temp = data.split("-");
            GregorianCalendar dataFinale = new GregorianCalendar(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
            n.setDate(dataFinale);
            HttpSession s = request.getSession();
            Account account = (Account) s.getAttribute("user");
            int idDelegato = account.getId();
            n.setDelegate(idDelegato);
            String time = request.getParameter("artefactOra");
            String[] a = time.split(":");
            if (time.length() >= 4) {
                Time t = new Time(Integer.parseInt(a[0]), Integer.parseInt(a[1]), 0);
                n.setTime(t);
            } else {
                Time t = new Time(0, 0, 0);
                n.setTime(t);
            }
            mn.update(n, flag);
        } catch (SQLException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
