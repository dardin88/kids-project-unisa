package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class JDBCTrainingManager implements ITrainingManager {

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
    public void insert(Trainee pTrainee) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query1;

        String query2;
        try {
            con = DBConnectionPool.getConnection();
            query1 = "INSERT INTO " + DBNames.TABLE_TRAINEE + "("
                    + DBNames.ATT_TRAINEE_REGISTER + ","
                    + DBNames.ATT_TRAINEE_NAME + ","
                    + DBNames.ATT_TRAINEE_SURNAME + ","
                    + DBNames.ATT_TRAINEE_EMAIL + ","
                    + DBNames.ATT_TRAINEE_BIRTHDATE + ","
                    + DBNames.ATT_TRAINEE_BIRTHCITY + ","
                    + DBNames.ATT_TRAINEE_CITYOFRESIDENCE + ","
                    + DBNames.ATT_TRAINEE_ADDRESS + ","
                    + DBNames.ATT_TRAINEE_CAP + ","
                    + DBNames.ATT_TRAINEE_DELEGATEACCOUNT;
            query2 = "VALUES (?,?,?,?,?,?,?,?,?,?";

            if (pTrainee.getTelephoneNumber() != null) {
                query1 += ", " + DBNames.ATT_TRAINEE_TELEPHONENUMBER;
                query2 += ",?";
            }
            query1 += ")";
            query2 += ")";
            pStmt = con.prepareStatement(query1 + query2);
            pStmt.setString(1, pTrainee.getRegister());
            pStmt.setString(2, pTrainee.getName());
            pStmt.setString(3, pTrainee.getSurname());
            pStmt.setString(4, pTrainee.getEmail());
            pStmt.setDate(5, new Date(pTrainee.getBirthDate().getTimeInMillis()));
            pStmt.setString(6, pTrainee.getBirthCity());
            pStmt.setString(7, pTrainee.getCityOfResidence());
            pStmt.setString(8, pTrainee.getAddress());
            pStmt.setString(9, pTrainee.getCap());
            pStmt.setInt(10, pTrainee.getDelegate());
            if (pTrainee.getTelephoneNumber() != null) {
                pStmt.setString(11, pTrainee.getTelephoneNumber());
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
    public void update(Trainee pTrainee) throws SQLException {
        Connection con = null;
        PreparedStatement pStmt = null;
        String query1;

        String query2;
        try {
            con = DBConnectionPool.getConnection();
            query1 = "UPDATE " + DBNames.TABLE_TRAINEE + " SET "
                    + DBNames.ATT_TRAINEE_NAME + "=? ,"
                    + DBNames.ATT_TRAINEE_SURNAME + "=? ,"
                    + DBNames.ATT_TRAINEE_EMAIL + "=? ,"
                    + DBNames.ATT_TRAINEE_BIRTHDATE + "=? ,"
                    + DBNames.ATT_TRAINEE_BIRTHCITY + "=? ,"
                    + DBNames.ATT_TRAINEE_CITYOFRESIDENCE + "=? ,"
                    + DBNames.ATT_TRAINEE_ADDRESS + "=? ,"
                    + DBNames.ATT_TRAINEE_CAP + "=? ,"
                    + DBNames.ATT_TRAINEE_DELEGATEACCOUNT + "=?";

            if (pTrainee.getTelephoneNumber() != null) {
                query1 += ", " + DBNames.ATT_TRAINEE_TELEPHONENUMBER + "=?";

            }
            query1 += "WHERE " + DBNames.ATT_TRAINEE_REGISTER + "=?";

            pStmt = con.prepareStatement(query1);
            pStmt.setString(1, pTrainee.getName());
            pStmt.setString(2, pTrainee.getSurname());
            pStmt.setString(3, pTrainee.getEmail());
            pStmt.setDate(4, new Date(pTrainee.getBirthDate().getTimeInMillis()));
            pStmt.setString(5, pTrainee.getBirthCity());
            pStmt.setString(6, pTrainee.getCityOfResidence());
            pStmt.setString(7, pTrainee.getAddress());
            pStmt.setString(8, pTrainee.getCap());
            pStmt.setInt(9, pTrainee.getDelegate());
            if (pTrainee.getTelephoneNumber() != null) {
                pStmt.setString(10, pTrainee.getTelephoneNumber());
                pStmt.setString(11, pTrainee.getRegister());

            } else {
                pStmt.setString(10, pTrainee.getRegister());

            }
            pStmt.executeUpdate();
            con.commit();


        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#deleteTrainee(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public void delete(Trainee pTrainee) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "DELETE FROM " + DBNames.TABLE_TRAINEE + " WHERE " + DBNames.ATT_TRAINEE_REGISTER + "='" + pTrainee.getRegister() + "'";
            System.out.println(query);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);
        }

    }
    /* (non-Javadoc)
     * @see it.unisa.kids.serviceManagement.trainingManagement.ITrainingManager#getTrainees(it.unisa.kids.serviceManagement.trainingManagement.Trainee)
     */

    @Override
    public ArrayList<Trainee> search(Trainee pTrainee) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rsTrainee = null;
        ArrayList<Trainee> traineeList = new ArrayList<Trainee>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEE + " WHERE ";
            if (pTrainee.getName() != null && pTrainee.getSurname() != null) {
                query += DBNames.ATT_TRAINEE_NAME + " LIKE '" + pTrainee.getName() + "%' AND "
                        + DBNames.ATT_TRAINEE_SURNAME + " LIKE '" + pTrainee.getSurname() + "%'";
            } else if (pTrainee.getName() != null && pTrainee.getSurname() == null) {
                query += DBNames.ATT_TRAINEE_NAME + " LIKE '" + pTrainee.getName() + "%' ";
            } else if (pTrainee.getName() == null && pTrainee.getSurname() == null && pTrainee.getRegister() != null) {
                query += DBNames.ATT_TRAINEE_REGISTER + "='" + pTrainee.getRegister() + "' ";
            } else {
                query += DBNames.ATT_TRAINEE_SURNAME + " LIKE '" + pTrainee.getSurname() + "%'";
            }
            stmt = con.createStatement();
            rsTrainee = stmt.executeQuery(query);
            con.commit();
            while (rsTrainee.next()) {
                String name = rsTrainee.getString(DBNames.ATT_TRAINEE_NAME);
                String surname = rsTrainee.getString(DBNames.ATT_TRAINEE_SURNAME);
                String register = rsTrainee.getString(DBNames.ATT_TRAINEE_REGISTER);
                String email = rsTrainee.getString(DBNames.ATT_TRAINEE_EMAIL);
                String address = rsTrainee.getString(DBNames.ATT_TRAINEE_ADDRESS);
                String birthCity = rsTrainee.getString(DBNames.ATT_TRAINEE_BIRTHCITY);
                String cityOfResidence = rsTrainee.getString(DBNames.ATT_TRAINEE_CITYOFRESIDENCE);
                String cap = rsTrainee.getString(DBNames.ATT_TRAINEE_CAP);
                Date birthDateSQL = rsTrainee.getDate(DBNames.ATT_TRAINEE_BIRTHDATE);
                GregorianCalendar birthDate = new GregorianCalendar();
                birthDate.setTime(birthDateSQL);
                int delegate = rsTrainee.getInt(DBNames.ATT_TRAINEE_DELEGATEACCOUNT);
                String telephoneNumber = rsTrainee.getString(DBNames.ATT_TRAINEE_TELEPHONENUMBER);
                Trainee trainee = new Trainee();
                trainee.setName(name);
                trainee.setSurname(surname);
                trainee.setRegister(register);
                trainee.setEmail(email);
                trainee.setAddress(address);
                trainee.setBirthCity(birthCity);
                trainee.setCityOfResidence(cityOfResidence);
                trainee.setCap(cap);
                trainee.setBirthDate(birthDate);
                trainee.setDelegate(delegate);
                trainee.setTelephoneNumber(telephoneNumber);
                traineeList.add(trainee);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return traineeList;
    }

    public ArrayList<Trainee> search() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rsTrainee = null;
        ArrayList<Trainee> traineeList = new ArrayList<Trainee>();
        String query;
        try {
            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_TRAINEE;
            stmt = con.createStatement();
            rsTrainee = stmt.executeQuery(query);
            con.commit();
            while (rsTrainee.next()) {
                String name = rsTrainee.getString(DBNames.ATT_TRAINEE_NAME);
                String surname = rsTrainee.getString(DBNames.ATT_TRAINEE_SURNAME);
                String register = rsTrainee.getString(DBNames.ATT_TRAINEE_REGISTER);
                String email = rsTrainee.getString(DBNames.ATT_TRAINEE_EMAIL);
                String address = rsTrainee.getString(DBNames.ATT_TRAINEE_ADDRESS);
                String birthCity = rsTrainee.getString(DBNames.ATT_TRAINEE_BIRTHCITY);
                String cityOfResidence = rsTrainee.getString(DBNames.ATT_TRAINEE_CITYOFRESIDENCE);
                String cap = rsTrainee.getString(DBNames.ATT_TRAINEE_CAP);
                Date birthDateSQL = rsTrainee.getDate(DBNames.ATT_TRAINEE_BIRTHDATE);
                GregorianCalendar birthDate = new GregorianCalendar();
                birthDate.setTime(birthDateSQL);
                int delegate = rsTrainee.getInt(DBNames.ATT_TRAINEE_DELEGATEACCOUNT);
                String telephoneNumber = rsTrainee.getString(DBNames.ATT_TRAINEE_TELEPHONENUMBER);
                Trainee trainee = new Trainee();
                trainee.setName(name);
                trainee.setSurname(surname);
                trainee.setRegister(register);
                trainee.setEmail(email);
                trainee.setAddress(address);
                trainee.setBirthCity(birthCity);
                trainee.setCityOfResidence(cityOfResidence);
                trainee.setCap(cap);
                trainee.setBirthDate(birthDate);
                trainee.setDelegate(delegate);
                trainee.setTelephoneNumber(telephoneNumber);
                traineeList.add(trainee);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return traineeList;
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
            query1 = "INSERT INTO " + DBNames.TABLE_TRAINEE_ACT + "("
                    + DBNames.ATT_TRAINEEACTIVITY_DATE + ","
                    + DBNames.ATT_TRAINEEACTIVITY_NAME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_STARTTIME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_ENDTIME + ","
                    + DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT + ","
                    + DBNames.ATT_TRAINEEACTIVITY_TRAINEE;


            query2 = "VALUES (?,?,?,?,?,?";
            if (pTraineeActivity.getDescription() != null) {
                query1 += "," + DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION;
                query2 += ",?";
            }
            query1 += ")";
            query2 += ")";
            pStmt = con.prepareStatement(query1 + query2);
            pStmt.setDate(1, new Date(pTraineeActivity.getDate().getTimeInMillis()));
            pStmt.setString(2, pTraineeActivity.getName());
            pStmt.setTime(3, pTraineeActivity.getStart());
            pStmt.setTime(4, pTraineeActivity.getEnd());
            pStmt.setInt(5, pTraineeActivity.getDelegate());
            pStmt.setString(6, pTraineeActivity.getTrainee());
            if (pTraineeActivity.getDescription() != null) {
                pStmt.setString(7, pTraineeActivity.getDescription());
            }
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
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
            query = "DELETE FROM " + DBNames.TABLE_TRAINEE_ACT + " WHERE " + DBNames.ATT_TRAINEEACTIVITY_DATE + "=? AND " + DBNames.ATT_TRAINEE_NAME + "=?";

            pStmt = con.prepareStatement(query);
            pStmt.setDate(1, new Date(pTraineeActivity.getDate().getTimeInMillis()));
            pStmt.setString(2, pTraineeActivity.getName());
            pStmt.executeUpdate();
            con.commit();
        } finally {
            pStmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    public ArrayList<TraineeActivity> getActivitiesByTrainee(Trainee pTrainee) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        String query;
        ArrayList<TraineeActivity> listActivity = new ArrayList<TraineeActivity>();
        try {

            con = DBConnectionPool.getConnection();
            query = "SELECT * FROM " + DBNames.TABLE_ACT + " WHERE " + DBNames.ATT_TRAINEEACTIVITY_TRAINEE + "='" + pTrainee.getRegister() + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            con.commit();
            while (rs.next()) {
                TraineeActivity activity = new TraineeActivity();
                String name = rs.getString(DBNames.ATT_TRAINEEACTIVITY_NAME);
                String description = rs.getString(DBNames.ATT_TRAINEEACTIVITY_DESCRIPTION);
                String trainee = rs.getString(DBNames.ATT_TRAINEEACTIVITY_TRAINEE);
                Date dateSQL = rs.getDate(DBNames.ATT_TRAINEEACTIVITY_DATE);
                GregorianCalendar date = new GregorianCalendar();
                date.setTime(dateSQL);
                int delegate = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_DELEGATEACCOUNT);
                Time startTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_STARTTIME);
                Time endTime = rs.getTime(DBNames.ATT_TRAINEEACTIVITY_ENDTIME);
                activity.setName(name);
                activity.setDescription(description);
                activity.setTrainee(trainee);
                activity.setDate(date);
                activity.setDelegate(delegate);
                activity.setStart(startTime);
                activity.setEnd(endTime);
                listActivity.add(activity);
            }
            return listActivity;

        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }


    }

    @Override
    public void update(TraineeActivity pTrainee) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
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
            logger.info(query);

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

    public ArrayList<TraineeRequest> getRequestsList() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TraineeRequest> requestList = new ArrayList<TraineeRequest>();
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

    public ArrayList<TraineeActivity> getTraineeActivityList() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TraineeActivity> activityList = new ArrayList<TraineeActivity>();
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
                String trainee = rs.getString(DBNames.ATT_TRAINEEACTIVITY_TRAINEE);
                int id = rs.getInt(DBNames.ATT_TRAINEEACTIVITY_ID);

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
                activityList.add(traineeActivity);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(con);

        }
        return activityList;
    }
}
