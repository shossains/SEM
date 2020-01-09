package databasetest;

import database.Query;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueryTest {
    private transient String jdbcUrl = "jdbc:postgresql://ec2-176-34-183-20.eu-west-1."
            + "compute.amazonaws.com:5432/dains1dt33rtkd";

    private transient String username = "lfxghibojyjdle";
    private transient String password =
            "0dc939d4eb5bd22284f2fe0aed23351b366c098a2bf6cf42f9fc697c0d6ba6d7";

    private transient String userTestName = "nonexistent";
    //    public Adapter ad;

    @BeforeEach
    void intialize() {
    //    Adapter ad = mock(Adapter.class);
    }

    @Test
    public void verifyLoginTest() {
        Query q = new Query();
        assertTrue(q.verifyLogin("test","pass"));
        assertFalse(q.verifyLogin("test","notthecorrectpass"));
        assertFalse(q.verifyLogin("test","wrong"));

        //verify(DriverManager.getConnection(jdbcUrl, username, password));

        //assertThrows(NullPointerException.class, () -> ad.connect());
    }

    @Test
    public void addNewUserTest() {
        Query q = new Query();
        assertFalse(q.verifyLogin(userTestName,"incorrect"));

        q.addNewUser(userTestName, "bla", "empty@delft.nl");
        assertTrue(q.verifyLogin(userTestName,"bla"));

        q.deleteUser(userTestName);
        assertFalse(q.verifyLogin(userTestName,"incorrect"));
        //verify(DriverManager.getConnection(jdbcUrl, username, password));

        //assertThrows(NullPointerException.class, () -> ad.connect());
    }

//    @Test
//    public void getScoresTest() {
//        Query q = new Query();
//        q.addNewUser(userTestName, "bla", "empty@delft.nl");
//        int firstScore = q.getScore(userTestName, 9999);
//        assertEquals(9999, firstScore);
//
//        int secondScore = q.getScore(userTestName, 1);
//        assertEquals(9999, secondScore);
//
//        q.deleteUser(userTestName);
//    }

}
