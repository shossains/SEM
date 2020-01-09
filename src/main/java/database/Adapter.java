package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Adapter {
    /* 01 Database variables ------------------------------- */
    public static Connection conn = null;
    private transient Statement stmt = null;
    private transient ResultSet rs = null;

    /* 02 Variables ---------------------------------------- */
    Properties prop = readPropertiesFile("database.properties");

    private transient String jdbcUrl = prop.getProperty("jdbcUrl");
    private transient String username = prop.getProperty("username");
    private transient String password = prop.getProperty("password");

    /* 03 Constructor for DbAdapter ------------------------ */
    public Adapter() {
    }

    /**
     *  Connect to the database.
     */
    public void connect() {
        try {
            // Step 2 - Open connection
            conn = DriverManager.getConnection(jdbcUrl, username, password);

            // Print connected
            System.out.println("DbAdapter: Connection to database established");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // connect

    /**
     * Disconnect from database.
     */
    public void disconnect() {
        try {

            // Step 5 Close connection
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            // Print connected
            System.out.println("DbAdapter: Connection to database closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // disconnect

    /**
     * This method retrieves data from a properties file.
     * This is neccesary since the db credentials are in a seperate file
     * @param fileName name of the properties file.
     * @return the property of the attribute.
     * @throws IOException Throw if something goes wrong.
     */
    public static Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}