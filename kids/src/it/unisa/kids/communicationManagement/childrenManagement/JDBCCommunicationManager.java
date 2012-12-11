package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class models the communication manager
 *
 * @author Elena Sollai
 *
 */
public class JDBCCommunicationManager implements ICommunicationManager {

    private static ICommunicationManager manager;

    /**
     * Empty constructor
     */
    private JDBCCommunicationManager() {
    }

    /**
     * this method implements the design pattern "singleton"
     *
     * @return manager
     */
    public static ICommunicationManager getInstance() {
        if (manager == null) {
            return (manager = new JDBCCommunicationManager());
        } else {
            return manager;
        }
    }

    /**
     * this method insert the communication in the database.
     *
     * @param Communication pCommunication
     *
     */
    @Override
    public void insertCommunication(Communication pCommunication) throws SQLException {

        Connection connection = null;
        PreparedStatement stmt = null;
        String query1;
        try {
            String[] temp=pCommunication.getDate().split("-");
            connection = DBConnectionPool.getConnection();
            query1 = "insert into " + DBNames.TABLE_COMMUNICATION + "("
                    + DBNames.ATT_COMMUNICATION_TYPE + ","
                    + DBNames.ATT_COMMUNICATION_IDEDUCATOR + ","
                    + DBNames.ATT_COMMUNICATION_IDCHILD + ","
                    + DBNames.ATT_COMMUNICATION_DESCRIPTION + ","
                    + DBNames.ATT_COMMUNICATION_DATE
                    + ") values (?,?,?,?,?)";
            GregorianCalendar d=new GregorianCalendar(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])-1,Integer.parseInt(temp[2]));
            stmt = connection.prepareStatement(query1);
            stmt.setString(1, pCommunication.getType());
            stmt.setInt(2, pCommunication.getIdEducator());
            stmt.setInt(3, pCommunication.getIdChild());
            stmt.setString(4, pCommunication.getDescription());
            stmt.setDate(5, new Date(d.getTimeInMillis()));
           stmt.executeUpdate();
            connection.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * this method show all the Communication presents in the database
     *
     * @return ArrayListCommunication<Communication> listCommunication
     */
    @Override
    public ArrayList<Communication> showCommunication() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rsCommunication = null;
        String query;
        ArrayList<Communication> listCommunication = new ArrayList<Communication>();
        try {
            connection = DBConnectionPool.getConnection();
            query = "select * from " + DBNames.TABLE_COMMUNICATION;
            stmt = connection.createStatement();
            rsCommunication = stmt.executeQuery(query);
            while (rsCommunication.next()) {
                int id = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_ID);
                String type = rsCommunication.getString(DBNames.ATT_COMMUNICATION_TYPE);
                int idEducator = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDEDUCATOR);
                int idChild = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDCHILD);
                String description = rsCommunication.getString(DBNames.ATT_COMMUNICATION_DESCRIPTION);
                String date = rsCommunication.getDate(DBNames.ATT_COMMUNICATION_DATE).toString();
                //Date date = rsCommunication.getDate(DBNames.ATT_COMMUNICATION_DATE);
                //GregorianCalendar data = new GregorianCalendar();
                //data.setTime(date);
                //data.set(Calendar.MONTH, (data.get(Calendar.MONTH)) + 1);
                String solved = rsCommunication.getString(DBNames.ATT_COMMUNICATION_SOLVED);
                Communication communication = new Communication();
                communication.setId(id);
                communication.setType(type);
                communication.setIdEducator(idEducator);
                communication.setIdChild(idChild);
                communication.setDescription(description);
                communication.setDate(date);
                communication.setSolved(solved);
                listCommunication.add(communication);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
        return listCommunication;
    }

    /**
     * this method solved need communication.
     *
     * @param int id, String solved
     */
    @Override
    public void solvedCommunication(int id, String solved) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String query;
        try {
            connection = DBConnectionPool.getConnection();
            query = "update " + DBNames.TABLE_COMMUNICATION + " set "
                    + DBNames.ATT_COMMUNICATION_SOLVED + "=? where " 
                    + DBNames.ATT_COMMUNICATION_ID + "=" + id;
            stmt = connection.prepareStatement(query);
            stmt.setString(1, solved);
            stmt.executeUpdate();
            connection.commit();
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(connection);
        }

    }

    /**
     * this method researchs communication with keyword in the description of
     * the communication
     *
     * @param String word
     * @return ArrayList<Communication> listSearchCommunication
     */
    @Override
    public ArrayList<Communication> searchCommunication(String word) throws SQLException {
        ArrayList<Communication> listCommunication = new ArrayList<Communication>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rsCommunication = null;
        try {
            connection = DBConnectionPool.getConnection();
            String query;
            query = "select * from " + DBNames.TABLE_COMMUNICATION + " WHERE "
                    + DBNames.ATT_COMMUNICATION_DESCRIPTION + " like '%" + word + "%'"
                    + " or " + DBNames.ATT_COMMUNICATION_DATE + " like '%" + word + "%'"
                    + " or " + DBNames.ATT_COMMUNICATION_TYPE + " like '%" + word + "%'";
            stmt = connection.createStatement();
            rsCommunication = stmt.executeQuery(query);
            while (rsCommunication.next()) {
                int id = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_ID);
                String type = rsCommunication.getString(DBNames.ATT_COMMUNICATION_TYPE);
                int idEducator = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDEDUCATOR);
                int idChild = rsCommunication.getInt(DBNames.ATT_COMMUNICATION_IDCHILD);
                String description = rsCommunication.getString(DBNames.ATT_COMMUNICATION_DESCRIPTION);
                String date = rsCommunication.getDate(DBNames.ATT_COMMUNICATION_DATE).toString();
                //GregorianCalendar data = new GregorianCalendar();
                //data.setTime(date);
                //data.set(Calendar.MONTH, (data.get(Calendar.MONTH)) + 1);
                String solved = rsCommunication.getString(DBNames.ATT_COMMUNICATION_SOLVED);
                Communication communication = new Communication();
                communication.setId(id);
                communication.setType(type);
                communication.setIdEducator(idEducator);
                communication.setIdChild(idChild);
                communication.setDescription(description);
                communication.setDate(date);
                communication.setSolved(solved);
                listCommunication.add(communication);
            }
        } finally {
            stmt.close();
            DBConnectionPool.releaseConnection(connection);
        }
        return listCommunication;
    }

    public int getIdChild(String name, String surname) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rsCommunication = null;
        int id = 0;
        connection = DBConnectionPool.getConnection();
        String query = "select " + DBNames.ATT_REGISTRATIONCHILD_ID
                + " from " + DBNames.TABLE_REGISTRATIONCHILD
                + " where " + DBNames.ATT_REGISTRATIONCHILD_NAME + "='" + name + "' and "
                + DBNames.ATT_REGISTRATIONCHILD_SURNAME + "='" + surname+"'";
        stmt=connection.createStatement();
        rsCommunication = stmt.executeQuery(query);
        while (rsCommunication.next()) {
            id = rsCommunication.getInt(DBNames.ATT_REGISTRATIONCHILD_ID);
        }
        return id;
    }
    
    public String getName(int idChild) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rsCommunication = null;
        String name=null;
        connection = DBConnectionPool.getConnection();
        String query = "select " + DBNames.ATT_REGISTRATIONCHILD_NAME
                + " from " + DBNames.TABLE_REGISTRATIONCHILD
                + " where " + DBNames.ATT_REGISTRATIONCHILD_ID + "='" + idChild +"'";
        stmt=connection.createStatement();
        rsCommunication = stmt.executeQuery(query);
        while (rsCommunication.next()) {
            name = rsCommunication.getString(DBNames.ATT_REGISTRATIONCHILD_NAME);
        }
        return name;
    }
    public String getSurname(int idChild) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rsCommunication = null;
        String surname = null;
        connection = DBConnectionPool.getConnection();
        String query = "select " + DBNames.ATT_REGISTRATIONCHILD_SURNAME
                + " from " + DBNames.TABLE_REGISTRATIONCHILD
                + " where " + DBNames.ATT_REGISTRATIONCHILD_ID + "='" + idChild + "'";
        stmt=connection.createStatement();
        rsCommunication = stmt.executeQuery(query);
        while (rsCommunication.next()) {
            surname = rsCommunication.getString(DBNames.ATT_REGISTRATIONCHILD_SURNAME);
        }
        return surname;
    }
}