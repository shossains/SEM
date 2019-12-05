import database.Adapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DatabaseTest {

    private transient String jdbcUrl = "jdbc:postgresql://ec2-176-34-183-20.eu-west-1."
            + "compute.amazonaws.com:5432/dains1dt33rtkd";

    private transient String username = "lfxghibojyjdle";
    private transient String password =
            "0dc939d4eb5bd22284f2fe0aed23351b366c098a2bf6cf42f9fc697c0d6ba6d7";

    public Adapter ad;
    @BeforeEach
    void intialize() {
//        Adapter ad = mock(Adapter.class);
    }

    @Test
    public void test2 () throws SQLException {
//        verify(DriverManager.getConnection(jdbcUrl, username, password));
//
//        assertThrows(NullPointerException.class, () -> ad.connect());
    }

//    @Test
//    public void test1() {
//        this.ad.connect();
//
//        try {
//            verify(DriverManager.getConnection(jdbcUrl, username, password));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test2() {
//        String[] que = {"SELECT * FROM users"};
//        ResultSet[] resultSets = Query.runQueries(que);
//    }
}
