package gamelogictest;

import com.badlogic.gdx.Screen;
import gamelogic.CredentialsChecker;

/**
 * The purpose of this test class is to be a integration test suite,
 * so not mocking the database. The tests pass as long as there is a
 * connection with the database.
 */
public class CredentialsCheckerTest {

    transient Screen screen;
    transient CredentialsChecker checker;

    final transient String pass = "pass";
    final transient String username = "test";
    final transient String email = "test@test.com";
    final transient String response = "empty";

    //TODO Make these test pass without a internet
    /*@BeforeEach
    public void setUp() {
        screen = Mockito.mock(Screen.class);
        checker = new CredentialsChecker(screen, new Adapter());
    }

    @Test
    public void checkCredentialsLoginEmpty1() {
        assertEquals(checker.checkLoginCredentials("", pass), response);
    }

    @Test
    public void checkCredentialsLoginEmpty2() {
        assertEquals(checker.checkLoginCredentials(username, ""), response);
    }

    @Test
    public void checkCredentialsLoginValid() {
        assertEquals(checker.checkLoginCredentials(username, pass), "correct");
    }

    @Test
    public void checkCredentialsLoginIncorrect() {
        assertEquals(checker.checkLoginCredentials("testtest", "passpass"), "incorrect");
    }

    @Test
    public void checkCredentialsRegistration() {
        assertEquals(checker.checkRegisterCredentials(username, pass,
                "", pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty() {
        assertEquals(checker.checkRegisterCredentials("", pass,
                email, pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty2() {
        assertEquals(checker.checkRegisterCredentials(username, "",
                email, pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty3() {
        assertEquals(checker.checkRegisterCredentials(username, pass,
                email, ""), response);
    }

    @Test
    public void checkCredentialsRegistrationPasswords() {
        assertEquals(checker.checkRegisterCredentials(username, pass,
                email, "pass2"), "passwordsNotMatching");
    }

    @Test
    public void checkCredentialsRegistrationIncorect() {
        assertEquals(checker.checkRegisterCredentials(username, pass,
                email, pass), "incorrect");
    }

    @Test
    public void checkCredentialsRegistrationCorrect() {
        double randomNumber = Math.random();
        assertEquals(checker.checkRegisterCredentials(randomNumber + "", pass,
                randomNumber + "", pass), "correct");
    }*/
}
