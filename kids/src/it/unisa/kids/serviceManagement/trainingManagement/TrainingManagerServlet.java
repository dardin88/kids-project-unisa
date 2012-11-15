/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
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
 * @author utente
 */
public class TrainingManagerServlet extends HttpServlet {

    public static final String OPERATION = "operation";
    public static final String INSERTTRAINEE = "INSERT_TRAINEE";
    private ITrainingManager trainingManager;

    public void init(ServletConfig config) {
        RefinedAbstractTrainingManager refinedAbstractTrainingManager = new RefinedAbstractTrainingManager();
        trainingManager = refinedAbstractTrainingManager.getManagerImplementor();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter(OPERATION);
        if (operation.equals(INSERTTRAINEE)) {
            try {
                Trainee trainee = new Trainee();
                trainee.setName(request.getParameter(DBNames.ATT_TRAINEE_NAME));
                trainee.setAddress(request.getParameter(DBNames.ATT_TRAINEE_ADDRESS));
                trainee.setRegister(request.getParameter(DBNames.ATT_TRAINEE_REGISTER));
                trainee.setBirthCity(request.getParameter(DBNames.ATT_TRAINEE_BIRTHCITY));
                trainee.setCap(request.getParameter(DBNames.ATT_TRAINEE_CAP));
                trainee.setCityOfResidence(request.getParameter(DBNames.ATT_TRAINEE_CITYOFRESIDENCE));
                trainee.setEmail(request.getParameter(DBNames.ATT_TRAINEE_EMAIL));
                trainee.setSurname(request.getParameter(DBNames.ATT_TRAINEE_SURNAME));
                trainee.setTelephoneNumber(request.getParameter(DBNames.ATT_TRAINEE_TELEPHONENUMBER));
                trainee.setDelegate(Integer.parseInt(request.getParameter(DBNames.ATT_TRAINEE_DELEGATEACCOUNT)));
                trainee.setBirthDate(parseGregorianCalendar(request.getParameter(DBNames.ATT_TRAINEE_BIRTHDATE)));
                trainingManager.insert(trainee);
            } catch (SQLException ex) {
                Logger.getLogger(TrainingManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }catch(ParseException e){
                Logger.getLogger(TrainingManagerServlet.class.getName()).log(Level.SEVERE,null,e);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private GregorianCalendar parseGregorianCalendar(String pDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = df.parse(pDate);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(parsed);
        return date;
    }
}
