package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.accessManagement.accountManagement.Account;
import it.unisa.kids.common.DBNames;
import it.unisa.kids.common.Mail;
import it.unisa.kids.common.RefinedAbstractManager;
import it.unisa.kids.common.facade.AccessFacade;
import it.unisa.kids.common.facade.IAccessFacade;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.util.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCTrainingManager extends Observable implements ITrainingManager {
    
    private static ITrainingManager manager;
    private static Logger logger = Logger.getLogger("global");

    private JDBCTrainingManager() {
    }

    public static ITrainingManager getInstance() {
        if (manager == null) {
            manager = new JDBCTrainingManager();
        }
        return manager;
    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#insertTrainee(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public void insert(Account pTrainee) throws SQLException {
        IAccessFacade accessFacade = new AccessFacade();
        if(accessFacade.insert(pTrainee)==null)
            throw new SQLException();



    }
    
    @Override
    public void insert(TraineeRequest pTraineeRequest) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "INSERT INTO " + DBNames.TABLE_TRAINEEREQUEST + "("
                    + DBNames.ATT_TRAINEEREQUEST_DATE + ","
                    + DBNames.ATT_TRAINEEREQUEST_TRAINEENUMBER + ","
                    + DBNames.ATT_TRAINEEREQUEST_STARTTIME + ","
                    + DBNames.ATT_TRAINEEREQUEST_ENDTIME + ","
                    + DBNames.ATT_TRAINEEREQUEST_DELEGATE + ","
                    + DBNames.ATT_TRAINEEREQUEST_ACTIVITY + ") VALUES(?,?,?,?,?,?)";
            pStmt = con.prepareStatement(query);
            pStmt.setDate(1, new Date(pTraineeRequest.getDate().getTimeInMillis()));
            pStmt.setInt(2, pTraineeRequest.getTraineeNumber());
            pStmt.setTime(3, pTraineeRequest.getStartTime());
            pStmt.setTime(4, pTraineeRequest.getEndTime());
            pStmt.setInt(5, pTraineeRequest.getDelegate());
            pStmt.setString(6, pTraineeRequest.getActivity());
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    @Override
    public void insert(TraineeConvocation pTraineeConvocation) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "INSERT INTO " + DBNames.TABLE_TRAINEECONVOCATION + "("
                    + DBNames.ATT_TRAINEECONVOCATION_DATE + ","
                    + DBNames.ATT_TRAINEECONVOCATION_ACTIVITYNAME + ","
                    + DBNames.ATT_TRAINEECONVOCATION_STARTTIME + ","
                    + DBNames.ATT_TRAINEECONVOCATION_ENDTIME + ","
                    + DBNames.ATT_TRAINEECONVOCATION_DELEGATE + ","
                    + DBNames.ATT_TRAINEECONVOCATION_TRAINEE+") VALUES (?,?,?,?,?,?)" ;

            pStmt = con.prepareStatement(query);
            pStmt.setDate(1, new Date(pTraineeConvocation.getDate().getTimeInMillis()));
            pStmt.setString(2, pTraineeConvocation.getActivityName());
            pStmt.setTime(3, pTraineeConvocation.getStartTime());
            pStmt.setTime(4, pTraineeConvocation.getEndTime());
            pStmt.setInt(5, pTraineeConvocation.getDelegateId());
            pStmt.setInt(6, pTraineeConvocation.getTraineeId());
            
            pStmt.executeUpdate();
            con.commit();
            super.setChanged();
            Mail mail=new Mail();
            String body = "Giorno :" + pTraineeConvocation.getDate().get(Calendar.YEAR) + "/" + pTraineeConvocation.getDate().get(Calendar.MONTH) + "/" + pTraineeConvocation.getDate().get(Calendar.DAY_OF_MONTH)
                    + "<br>Ora di inzio:" + pTraineeConvocation.getStartTime().getHours() + ":" + pTraineeConvocation.getStartTime().getMinutes()
                    + "<br>Ora di fine:" + pTraineeConvocation.getEndTime().getHours() + ":" + pTraineeConvocation.getEndTime().getMinutes()
                    + "<br>Attvità:" + pTraineeConvocation.getActivityName()
                    + "<br>Si prega di confermare la propria presenza sul sito";
            mail.setBody(body);
            mail.setSubject("Convocazione asilo");
            Account account=new Account ();
            account.setId(pTraineeConvocation.getTraineeId());
            ArrayList<String> listTo=new ArrayList<String>();
            listTo.add(search(account).get(0).getEmail());
            mail.setTo(listTo);
            
            super.notifyObservers(mail);
            
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
    
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#insertActivity(it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity)
     */
    @Override
    public void insert(TraineeActivity pTraineeActivity) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query1;
        String query2;
        try {
            con = DBConnectionPool.getConnection();
            query1 = "INSERT INTO " + DBNames.TABLE_TRAINEEACTIVITY + "("
                    + DBNames.ATT_TRAINEEACTIVITY_DATE + ","
                    + DBNames.ATT_TRAINEEACTIVITY_NAME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_STARTTIME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_ENDTIME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT + ","
                    + DBNames.ATT_TRAINEEACTIVITY_TRAINEE+","
                    +DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION;
                    


            query2 = "VALUES (?,?,?,?,?,?,?";
           /* if (pTraineeActivity.getDescription() != null) {
                query1 += "," + DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION;
                query2 += ",?";
            }*/
            if(pTraineeActivity.getOpinion()!=null){
                query1+=","+DBNames.ATT_TRAINEEACTIVITY_OPINION;
                query2+=",?";
            }
            query1 += ")";
            query2 += ")";
            pStmt = con.prepareStatement(query1 + query2);
            pStmt.setDate(1, new Date(pTraineeActivity.getDate().getTimeInMillis()));
            pStmt.setString(2, pTraineeActivity.getName());
            pStmt.setTime(3, pTraineeActivity.getStart());
            pStmt.setTime(4, pTraineeActivity.getEnd());
            pStmt.setInt(5, pTraineeActivity.getDelegate());
            pStmt.setInt(6, pTraineeActivity.getTrainee());
            
          // if (pTraineeActivity.getDescription() != null) {
                pStmt.setString(7, pTraineeActivity.getDescription());
          //  }
            if(pTraineeActivity.getOpinion()!=null){
                pStmt.setString(8, pTraineeActivity.getOpinion());
            }
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#update(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public void update(Account pTrainee) throws SQLException {
        pTrainee.setAccountType("Tirocinante");
        IAccessFacade accessFacade = new AccessFacade();
        accessFacade.update(pTrainee);
    }
    
    @Override
    public void update(TraineeActivity pTrainee) throws SQLException {
        
    }
    
     @Override
    public void update(TraineeRequest pTraineeRequest) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TraineeConvocation pTraineeConvocation) throws SQLException {
    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#deleteTrainee(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public void delete(Account pTrainee) throws SQLException {
        IAccessFacade accessFacade = new AccessFacade();
        accessFacade.delete(pTrainee);

    }
    
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#deleteActivity(it.unisa.kids.serviceManagement.trainingManagement.TraineeActivity)
     */

    @Override
    public void delete(TraineeActivity pTraineeActivity) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "DELETE FROM " + DBNames.TABLE_TRAINEEACTIVITY + " WHERE " + DBNames.ATT_TRAINEEACTIVITY_ID+"=?";
            pStmt = con.prepareStatement(query);
            pStmt.setInt(1, pTraineeActivity.getId());
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
    
    @Override
    public void delete(TraineeRequest pTraineeRequest) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(TraineeConvocation pTraineeConvocation) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "DELETE FROM " + DBNames.TABLE_TRAINEECONVOCATION + " WHERE " + DBNames.ATT_TRAINEECONVOCATION_ID + "=? ";
            pStmt = con.prepareStatement(query);
            pStmt.setInt(1, pTraineeConvocation.getId());
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#getTrainees(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public List<Account> search(Account pTrainee) throws SQLException {
        IAccessFacade accessFacade = new AccessFacade();
        List<Account> list = null;
        pTrainee.setAccountType("Tirocinante");
        list = accessFacade.search(pTrainee);
        return list;
    }

    
    

    @Override
    public List<TraineeActivity> search(TraineeActivity pTraineeActivity) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query;
        List<TraineeActivity> listActivity = new ArrayList<TraineeActivity>();
        try {

            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEEACTIVITY + " WHERE " + DBNames.ATT_TRAINEEACTIVITY_TRAINEE + "='" + pTraineeActivity.getTrainee() + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                TraineeActivity activity = new TraineeActivity();
                String name = rs.getString(DBNames.ATT_TRAINEEACTIVITY_NAME);
                String description = rs.getString(DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION);
                int trainee = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_TRAINEE);
                Date dateSQL = rs.getDate(DBNames.ATT_TRAINEEACTIVITY_DATE);
                GregorianCalendar date = new GregorianCalendar();
                date.setTime(dateSQL);
                int delegate = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_ENDTIME);
                String opinion=rs.getString(DBNames.ATT_TRAINEEACTIVITY_OPINION);
                activity.setName(name);
                activity.setDescription(description);
                activity.setTrainee(trainee);
                activity.setDate(date);
                activity.setDelegate(delegate);
                activity.setStart(startTime);
                activity.setEnd(endTime);
                activity.setOpinion(opinion);
                listActivity.add(activity);
            }
            return listActivity;

        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }


    }

    

    public ArrayList<TraineeRequest> search(TraineeRequest pTraineeRequest) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TraineeRequest> requestList = new ArrayList<TraineeRequest>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            if (pTraineeRequest.getDate() != null) {
                query = "SELECT * FROM " + DBNames.TABLE_TRAINEEREQUEST + " WHERE " + DBNames.ATT_TRAINEEREQUEST_DATE + "=?";
            } else if (pTraineeRequest.getId() != 0) {
                query = "SELECT * FROM " + DBNames.TABLE_TRAINEEREQUEST + " WHERE " + DBNames.ATT_TRAINEEREQUEST_ID + "=?";
            } else {
                query = "SELECT * FROM " + DBNames.TABLE_TRAINEEREQUEST;
            }
            stmt = con.prepareStatement(query);

            if (pTraineeRequest.getDate() != null) {

                stmt.setDate(1, new Date(pTraineeRequest.getDate().getTimeInMillis()));
            } else if (pTraineeRequest.getId() != 0) {
                stmt.setInt(1, pTraineeRequest.getId());

            }

            rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                int id = rs.getInt(DBNames.ATT_TRAINEEREQUEST_ID);
                int traineeNumber = rs.getInt(DBNames.ATT_TRAINEEREQUEST_TRAINEENUMBER);
                int delegate = rs.getInt(DBNames.ATT_TRAINEEREQUEST_DELEGATE);
                Date date = rs.getDate(DBNames.ATT_TRAINEEREQUEST_DATE);
                String activity = rs.getString(DBNames.ATT_TRAINEEREQUEST_ACTIVITY);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEEREQUEST_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEEREQUEST_ENDTIME);
                GregorianCalendar dateCalendar = new GregorianCalendar();
                dateCalendar.setTime(date);
                TraineeRequest traineeRequest = new TraineeRequest();
                traineeRequest.setId(id);
                traineeRequest.setTraineeNumber(traineeNumber);
                traineeRequest.setDelegate(delegate);
                traineeRequest.setDate(dateCalendar);
                traineeRequest.setActivity(activity);
                traineeRequest.setStartTime(startTime);
                traineeRequest.setEndTime(endTime);
                requestList.add(traineeRequest);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return requestList;
    }
    
    @Override
    public List<TraineeConvocation> search(TraineeConvocation pTraineeConvocation) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TraineeConvocation> convocationList = new ArrayList<TraineeConvocation>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query="SELECT * FROM "+DBNames.TABLE_TRAINEECONVOCATION+" WHERE "+DBNames.ATT_TRAINEECONVOCATION_TRAINEE+"='"+pTraineeConvocation.getTraineeId()+"'";
            stmt=con.createStatement();

            rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                int id = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_ID);
                int trainee = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_TRAINEE);
                int delegate = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_DELEGATE);
                Date date = rs.getDate(DBNames.ATT_TRAINEECONVOCATION_DATE);
                String activity = rs.getString(DBNames.ATT_TRAINEECONVOCATION_ACTIVITYNAME);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEECONVOCATION_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEECONVOCATION_ENDTIME);
                int confirmed=rs.getInt(DBNames.ATT_TRAINEECONVOCATION_CONFIRMED);
                GregorianCalendar dateCalendar = new GregorianCalendar();
                dateCalendar.setTime(date);
                TraineeConvocation traineeConvocation = new TraineeConvocation();
                traineeConvocation.setId(id);
                traineeConvocation.setTraineeId(trainee);
                traineeConvocation.setDelegateId(delegate);
                traineeConvocation.setDate(dateCalendar);
                traineeConvocation.setActivityName(activity);
                traineeConvocation.setStartTime(startTime);
                traineeConvocation.setEndTime(endTime);
                traineeConvocation.setConfirmed(confirmed);
                convocationList.add(traineeConvocation);
                
            }
            return convocationList;
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
    }

    public List<TraineeRequest> getRequestsList() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TraineeRequest> requestList = new ArrayList<TraineeRequest>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEEREQUEST;
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                int id = rs.getInt(DBNames.ATT_TRAINEEREQUEST_ID);
                int traineeNumber = rs.getInt(DBNames.ATT_TRAINEEREQUEST_TRAINEENUMBER);
                int delegate = rs.getInt(DBNames.ATT_TRAINEEREQUEST_DELEGATE);
                Date date = rs.getDate(DBNames.ATT_TRAINEEREQUEST_DATE);
                String activity = rs.getString(DBNames.ATT_TRAINEEREQUEST_ACTIVITY);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEEREQUEST_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEEREQUEST_ENDTIME);
                GregorianCalendar dateCalendar = new GregorianCalendar();
                dateCalendar.setTime(date);
                TraineeRequest traineeRequest = new TraineeRequest();
                traineeRequest.setId(id);
                traineeRequest.setTraineeNumber(traineeNumber);
                traineeRequest.setDelegate(delegate);
                traineeRequest.setDate(dateCalendar);
                traineeRequest.setActivity(activity);
                traineeRequest.setStartTime(startTime);
                traineeRequest.setEndTime(endTime);
                requestList.add(traineeRequest);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return requestList;
    }

    public List<TraineeActivity> getTraineeActivityList() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TraineeActivity> activityList = new ArrayList<TraineeActivity>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEEACTIVITY;
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                String name = rs.getString(DBNames.ATT_TRAINEEACTIVITY_NAME);
                Date date = rs.getDate(DBNames.ATT_TRAINEEACTIVITY_DATE);
                String description = rs.getString(DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_ENDTIME);
                int delegate = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT);
                int trainee = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_TRAINEE);
                int id = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_ID);
                String opinion=rs.getString(DBNames.ATT_TRAINEEACTIVITY_OPINION);

                GregorianCalendar dateCalendar = new GregorianCalendar();
                dateCalendar.setTime(date);
                TraineeActivity traineeActivity = new TraineeActivity();
                traineeActivity.setDate(dateCalendar);
                traineeActivity.setDelegate(delegate);
                traineeActivity.setDescription(description);
                traineeActivity.setEnd(endTime);
                traineeActivity.setName(name);
                traineeActivity.setStart(startTime);
                traineeActivity.setTrainee(trainee);
                traineeActivity.setId(id);
                traineeActivity.setOpinion(opinion);
                activityList.add(traineeActivity);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return activityList;
    }

    

   

    

    

    @Override
    public List<Account> getTraineeList() throws SQLException {
        Account account = new Account();
        account.setAccountType("Tirocinante");
        IAccessFacade accessFacade = new AccessFacade();
        List<Account> list = accessFacade.search(account);
        return list;

    }

    @Override
    public List<TraineeConvocation> getTraineeConvocationList() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TraineeConvocation> convocationList = new ArrayList<TraineeConvocation>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEECONVOCATION;
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                int id = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_ID);
                int traineeNumber = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_TRAINEE);
                int delegate = rs.getInt(DBNames.ATT_TRAINEECONVOCATION_DELEGATE);
                Date date = rs.getDate(DBNames.ATT_TRAINEECONVOCATION_DATE);
                String activity = rs.getString(DBNames.ATT_TRAINEECONVOCATION_ACTIVITYNAME);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEECONVOCATION_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEECONVOCATION_ENDTIME);
                int confirmed=rs.getInt(DBNames.ATT_TRAINEECONVOCATION_CONFIRMED);
                GregorianCalendar dateCalendar = new GregorianCalendar();
                dateCalendar.setTime(date);
                TraineeConvocation traineeConvocation = new TraineeConvocation();
                traineeConvocation.setId(id);
                traineeConvocation.setTraineeId(traineeNumber);
                traineeConvocation.setDelegateId(delegate);
                traineeConvocation.setDate(dateCalendar);
                traineeConvocation.setActivityName(activity);
                traineeConvocation.setStartTime(startTime);
                traineeConvocation.setEndTime(endTime);
                traineeConvocation.setConfirmed(confirmed);
                convocationList.add(traineeConvocation);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return convocationList;
    }
    
    
    
    
}
