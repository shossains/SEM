package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.AssignmentToNonFinalStatic"})
public class Adapter {
    public static Connection conn = null;

    transient Properties prop = readPropertiesFile("database.properties");

    private transient String jdbcUrl = prop.getProperty("jdbcUrl");
    private transient String username = prop.getProperty("username");
    private transient String password = prop.getProperty("password");

    public Adapter() {
        conn = this.connect();
    }

    /**
     * Method that creates a connection to the database.
     * @return a new connection if possible, null otherwise.
     */
    private Connection connect() {
        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("DbAdapter: Connection to database established");
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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