package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class Adapter {

    /* 02 Variables ---------------------------------------- */
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
}