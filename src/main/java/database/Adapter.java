package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
public class Adapter {
    /* 01 Database variables ------------------------------- */
    public transient Connection conn;
    private transient Statement stmt;
    private transient ResultSet rs;

    /* 02 Variables ---------------------------------------- */
    private transient String jdbcUrl = "jdbc:postgresql://ec2-176-34-183-20.eu-west-1."
            + "compute.amazonaws.com:5432/dains1dt33rtkd";

    private transient String username = "lfxghibojyjdle";
    private transient String password =
            "0dc939d4eb5bd22284f2fe0aed23351b366c098a2bf6cf42f9fc697c0d6ba6d7";

    /* 03 Constructor for DbAdapter ------------------------ */
    public Adapter() {
        conn = this.connect();
    }

    /**
     *  Connect to the database.
     */
    public Connection connect() {
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