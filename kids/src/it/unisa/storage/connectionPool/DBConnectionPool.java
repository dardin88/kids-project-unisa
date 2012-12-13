package it.unisa.storage.connectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class simulates a db connection pool
 *
 * @author Rocco Oliveto
 * @version 1.0
 */
public class DBConnectionPool {

    /*
     * This code prepare the db connection pool. In particular, it creates the
     * free connections queue and defines the db properties.
     */
    static {
        freeDbConnections = new ArrayList<Connection>();
        try {
            DBConnectionPool.loadDbProperties();
            DBConnectionPool.loadDbDriver();
        } catch (ClassNotFoundException e) {
            System.err.println("DB DRIVER NOT FOUND!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("DB CONNECTION POOL ERROR!");
            System.exit(2);
        }
    }
    /**
     * The db properties (driver, url, login, and password)
     */
    private static Properties dbProperties;
    /**
     * The free connection queue
     */
    private static List<Connection> freeDbConnections;

    /**
     * Returns a free db connection accessing to the free db connection queue.
     * If the queue is empty a new db connection will be created.
     *
     * @return A db connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;

        if (!freeDbConnections.isEmpty()) {
            // Extract a connection from the free db connection queue
            connection = (Connection) freeDbConnections.get(0);
            DBConnectionPool.freeDbConnections.remove(0);

            try {
                // If the connection is not valid, a new connection will be
                // analyzed
                if (connection.isClosed()) {
                    connection = DBConnectionPool.getConnection();
                }
            } catch (SQLException e) {
                connection = DBConnectionPool.getConnection();
            }
        } else // The free db connection queue is empty, so a new connection will
        // be created
        {
            connection = DBConnectionPool.createDBConnection();
        }

        return connection;
    }

    /**
     * Releases the connection represented by
     * <code>pReleasedConnection</code> parameter
     *
     * @param pReleasedConnection The db connection to release
     */
    public static synchronized void releaseConnection(
            Connection pReleasedConnection) {
        try {
            // Add the connection to the free db connection queue
            DBConnectionPool.freeDbConnections.add(pReleasedConnection);
            pReleasedConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new db connection
     *
     * @return A db connection
     * @throws SQLException
     */
    private static Connection createDBConnection() throws SQLException {
        Connection newConnection = null;

        // Create a new db connection using the db properties
        newConnection = DriverManager.getConnection(
                DBConnectionPool.dbProperties.getProperty("url"),
                DBConnectionPool.dbProperties.getProperty("username"),
                DBConnectionPool.dbProperties.getProperty("password"));

        newConnection.setAutoCommit(false);

        return newConnection;
    }

    private static void loadDbDriver() throws ClassNotFoundException {
        Class.forName(DBConnectionPool.dbProperties.getProperty("driver"));

    }

    /**
     * Loads the db properties
     *
     * @throws IOException
     */
    private static void loadDbProperties() throws IOException {
//   	InputStream fileProperties = new FileInputStream("database.properties");
//      DBConnectionPool.dbProperties.load(fileProperties);
        DBConnectionPool.dbProperties = new Properties();
        DBConnectionPool.dbProperties.setProperty("driver", "org.gjt.mm.mysql.Driver");
        DBConnectionPool.dbProperties.setProperty("url", "jdbc:mysql://localhost/progetto-kids2");
        DBConnectionPool.dbProperties.setProperty("username", "root");
        DBConnectionPool.dbProperties.setProperty("password", "");
    }
}
