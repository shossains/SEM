package gamelogictest;

import com.badlogic.gdx.Screen;
import gamelogic.CredentialsChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CredentialsCheckerTest {

    Screen screen;
    CredentialsChecker checker;

    @BeforeEach
    public void setUp() {
        screen = Mockito.mock(Screen.class);
        checker = new CredentialsChecker(screen);
    }

    @Test
    public void checkCredentialsLoginEmpty1() {
        assertEquals(checker.checkLoginCredentials("", "pass"), "empty");
    }
    @Test
    public void checkCredentialsLoginEmpty2() {
        assertEquals(checker.checkLoginCredentials("test", ""), "empty");
    }

    @Test
    public void checkCredentialsLoginValid() {
        assertEquals(checker.checkLoginCredentials("test", "pass"), "correct");
    }

    @Test
    public void checkCredentialsLoginIncorrect() {
        assertEquals(checker.checkLoginCredentials("testtest", "passpass"), "incorrect");
    }

    @Test
    public void checkCredentialsRegistration() {
        assertEquals(checker.checkRegisterCredentials("test", "pass",
                "", "pass"), "empty");
    }

    @Test
    public void checkCredentialsRegistrationEmpty() {
        assertEquals(checker.checkRegisterCredentials("", "pass",
                "test@test.com", "pass"), "empty");
    }

    @Test
    public void checkCredentialsRegistrationEmpty2() {
        assertEquals(checker.checkRegisterCredentials("test", "",
                "test@test.com", "pass"), "empty");
    }

    @Test
    public void checkCredentialsRegistrationEmpty3() {
        assertEquals(checker.checkRegisterCredentials("test", "pass",
                "test@test.com", ""), "empty");
    }

    @Test
    public void checkCredentialsRegistrationPasswords() {
        assertEquals(checker.checkRegisterCredentials("test", "pass",
                "test@test.com", "pass2"), "passwordsNotMatching");
    }

    @Test
    public void checkCredentialsRegistrationIncorect() {
        assertEquals(checker.checkRegisterCredentials("test", "pass",
                "test@test.com", "pass"), "incorrect");
    }

    @Test
    public void checkCredentialsRegistrationCorrect() {
        double randomNumber = Math.random();
        assertEquals(checker.checkRegisterCredentials(randomNumber + "", "pass",
                randomNumber + "", "pass"), "correct");
    }
}
