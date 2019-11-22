
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adapter {
    /* 01 Database variables ------------------------------- */
    static Connection conn = null;
    private transient Statement stmt = null;
    private transient ResultSet rs = null;

    /* 02 Variables ---------------------------------------- */
    private transient String jdbcUrl = "jdbc:postgresql://ec2-176-34-183-20.eu-west-1."
            + "compute.amazonaws.com:5432/dains1dt33rtkd";

    private transient String username = "lfxghibojyjdle";
    private transient String password = "0dc939d4eb5bd22284f2fe0aed23351b366c098a2bf6cf42f9fc697c0d6ba6d7";

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
}