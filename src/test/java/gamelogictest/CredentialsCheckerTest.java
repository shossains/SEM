package gamelogictest;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.Screen;
import gamelogic.CredentialsChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CredentialsCheckerTest {

    transient Screen screen;
    transient CredentialsChecker checker;
    final transient String PASS = "pass";
    final transient String USERNAME = "test";
    final transient String EMAIL = "test@test.com";
    final transient String response = "empty";

    @BeforeEach
    public void setUp() {
        screen = Mockito.mock(Screen.class);
        checker = new CredentialsChecker(screen);
    }

    @Test
    public void checkCredentialsLoginEmpty1() {
        assertEquals(checker.checkLoginCredentials("", PASS), response);
    }

    @Test
    public void checkCredentialsLoginEmpty2() {
        assertEquals(checker.checkLoginCredentials(USERNAME, ""), response);
    }

    @Test
    public void checkCredentialsLoginValid() {
        assertEquals(checker.checkLoginCredentials(USERNAME, PASS), "correct");
    }

    @Test
    public void checkCredentialsLoginIncorrect() {
        assertEquals(checker.checkLoginCredentials("testtest", "passpass"), "incorrect");
    }

    @Test
    public void checkCredentialsRegistration() {
        assertEquals(checker.checkRegisterCredentials(USERNAME, PASS,
                "", PASS), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty() {
        assertEquals(checker.checkRegisterCredentials("", PASS,
                EMAIL, PASS), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty2() {
        assertEquals(checker.checkRegisterCredentials(USERNAME, "",
                EMAIL, PASS), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty3() {
        assertEquals(checker.checkRegisterCredentials(USERNAME, PASS,
                EMAIL, ""), response);
    }

    @Test
    public void checkCredentialsRegistrationPasswords() {
        assertEquals(checker.checkRegisterCredentials(USERNAME, PASS,
                EMAIL, "pass2"), "passwordsNotMatching");
    }

    @Test
    public void checkCredentialsRegistrationIncorect() {
        assertEquals(checker.checkRegisterCredentials(USERNAME, PASS,
                EMAIL, PASS), "incorrect");
    }

    @Test
    public void checkCredentialsRegistrationCorrect() {
        double randomNumber = Math.random();
        assertEquals(checker.checkRegisterCredentials(randomNumber + "", PASS,
                randomNumber + "", PASS), "correct");
    }
}
