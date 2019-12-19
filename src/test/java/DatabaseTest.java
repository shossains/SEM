import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DatabaseTest {

    private transient String jdbcUrl = "jdbc:postgresql://ec2-176-34-183-20.eu-west-1."
            + "compute.amazonaws.com:5432/dains1dt33rtkd";

    private transient String username = "lfxghibojyjdle";
    private transient String password =
            "0dc939d4eb5bd22284f2fe0aed23351b366c098a2bf6cf42f9fc697c0d6ba6d7";

    public Adapter ad;
    @BeforeEach
    void intialize() {
        Adapter ad = mock(Adapter.class);
    }

    @Test
    public void testVerifyLogin() throws SQLException {
        Query q = new Query();
        assertTrue(q.verifyLogin("test","pass"));
        assertFalse(q.verifyLogin("test","notthecorrectpass"));
        assertFalse(q.verifyLogin("nonexistent","bla"));

//        verify(DriverManager.getConnection(jdbcUrl, username, password));

//        assertThrows(NullPointerException.class, () -> ad.connect());
    }

    @Test
    public void testSelect() throws SQLException {
        String[] queries = {"SELECT username"
                + " FROM users WHERE username = 'e';"};
        ResultSet rs = Query.runQueries(queries)[0];
        assertFalse(rs.next());
    }

    @Test
    public void testSelect2() throws SQLException {
        String[] queries = {"SELECT username"
                + " FROM users WHERE username = 'test';"};
        ResultSet rs = Query.runQueries(queries)[0];
        assertTrue(rs.next());
    }

    @Test
    public void testInsert() throws SQLException {
        String[] queries = { "INSERT INTO users (username, password) " +
                "VALUES ('temp', 'temp');"};
        Query.runQueries(queries);

        String[] queries2 = {"SELECT username"
                + " FROM users WHERE username = 'test';"};
        ResultSet rs = Query.runQueries(queries2)[0];
        assertTrue(rs.next());

        String[] queries3 = {"DELETE FROM users " +
                "WHERE username = 'temp';"};
        Query.runQueries(queries3);

        String[] w = {"SELECT username"
                + " FROM users WHERE username = 'test';"};
        ResultSet rs3 = Query.runQueries(w)[0];
        assertTrue(rs3.next());
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
