package it.unisa.kids.accessManagement.registrationChildManagement;

import it.unisa.kids.common.CommonMethod;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.RefinedAbstractManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet used to create a new draft of registrationchild
 * 
 * @author Lauri Giuseppe Giovanni
 */
public class ServletEditRegistrationChild extends HttpServlet {
    private IRegistrationChildManager registrationChildManager;

    public void init(ServletConfig config) {
        RefinedAbstractManager refinedAbstractRegistrationChildManager = RefinedAbstractManager.getInstance();
        registrationChildManager = (IRegistrationChildManager) refinedAbstractRegistrationChildManager.getManagerImplementor(DBNames.TABLE_REGISTRATIONCHILD);
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
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            // Prelevo i dati necessari
            String surname = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_SURNAME);
            String name = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_NAME);

            String birthDate = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_BIRTHDATE);
            GregorianCalendar birth = CommonMethod.parseGregorianCalendar(birthDate);

            String birthPlace = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_BIRTHPLACE);
            String fiscalCode = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_FISCALCODE);
            String citizenship = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_CITIZENSHIP);
            String userRange = request.getParameter(DBNames.ATT_REGISTRATIONCHILD_USERRANGE);
            
            // Prelevo dalla Session la domanda di iscrizione bambino da aggiornare
            // E' stata inserita nella session perchè è stato necessario prelevarla dal db
            // per formare la pagina jsp
            HttpSession session = request.getSession();
            RegistrationChild registrationChild = (RegistrationChild) session.getAttribute("registrationchildtoedit");
            
            // Ricerca: non ci possono essere più domande di iscrizione con lo stesso ID
            // Se si preleva dalla session non è necessario effettuare un ulteriore ricerca nel db
            // registrationChild = registrationChildManager.search(registrationChild).get(0);
            
            // Aggiorno i campi
            registrationChild.setSurname(surname);
            registrationChild.setName(name);
            registrationChild.setBirthDate(birth);
            registrationChild.setBirthPlace(birthPlace);
            registrationChild.setFiscalCode(fiscalCode);
            registrationChild.setCitizenship(citizenship);
            registrationChild.setUserRange(userRange);
            
            // Aggiorno il db
            registrationChildManager.update(registrationChild);
        } catch (SQLException ex) {
            request.setAttribute("message", "Verfica i campi");
            request.getServletContext().getRequestDispatcher("/RegistrationChildViewAll.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletEditRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletEditRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletEditRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletEditRegistrationChild.class.getName()).log(Level.SEVERE, null, ex);
        }
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
