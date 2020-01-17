package gamelogictest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Screen;
import database.Adapter;
import database.RegisterUser;
import database.VerifyLogin;
import gamelogic.CredentialsChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The purpose of this test class is to be a integration test suite,
 * so not mocking the database. The tests pass as long as there is a
 * connection with the database.
 */
public class CredentialsCheckerTest {

    transient Screen screen;
    transient CredentialsChecker checkerLogin;
    transient CredentialsChecker checkerRegister;

    transient VerifyLogin verifyLogin;
    transient RegisterUser registerUser;

    final transient String pass = "pass";
    final transient String username = "test";
    final transient String email = "test@test.com";
    final transient String response = "empty";

    /**
     * Setup of every component needed for testing.
     * Done before every test.
     */
    @BeforeEach
    public void setUp() {
        screen = mock(Screen.class);
        Adapter adapter = mock(Adapter.class);
        verifyLogin = mock(VerifyLogin.class);
        registerUser = mock(RegisterUser.class);
        checkerLogin = new CredentialsChecker(screen, adapter, verifyLogin);
        checkerRegister = new CredentialsChecker(screen, adapter, registerUser);

    }

    @Test
    public void checkCredentialsLoginEmpty1() {
        assertEquals(checkerLogin.checkLoginCredentials("", pass), response);
    }

    @Test
    public void checkCredentialsLoginEmpty2() {
        assertEquals(checkerLogin.checkLoginCredentials(username, ""), response);
    }

    @Test
    public void checkCredentialsLoginValid() {
        when(verifyLogin.execute(any())).thenReturn(true);
        assertEquals(checkerLogin.checkLoginCredentials(username, pass), "correct");
    }

    @Test
    public void checkCredentialsLoginIncorrect() {
        when(verifyLogin.execute(any())).thenReturn(false);
        assertEquals(checkerLogin.checkLoginCredentials("testtest", "passpass"), "incorrect");
    }

    @Test
    public void checkCredentialsRegistration() {
        assertEquals(checkerRegister.checkRegisterCredentials(username, pass,
                "", pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty() {
        assertEquals(checkerRegister.checkRegisterCredentials("", pass,
                email, pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty2() {
        assertEquals(checkerRegister.checkRegisterCredentials(username, "",
                email, pass), response);
    }

    @Test
    public void checkCredentialsRegistrationEmpty3() {
        assertEquals(checkerRegister.checkRegisterCredentials(username, pass,
                email, ""), response);
    }

    @Test
    public void checkCredentialsRegistrationPasswords() {
        assertEquals(checkerRegister.checkRegisterCredentials(username, pass,
                email, "pass2"), "passwordsNotMatching");
    }

    @Test
    public void checkCredentialsRegistrationIncorect() {
        when(registerUser.execute(any())).thenReturn(false);
        assertEquals(checkerRegister.checkRegisterCredentials(username, pass,
                email, pass), "incorrect");
    }

    @Test
    public void checkCredentialsRegistrationCorrect() {
        double randomNumber = Math.random();
        when(registerUser.execute(any())).thenReturn(true);
        assertEquals(checkerRegister.checkRegisterCredentials(randomNumber + "", pass,
                randomNumber + "", pass), "correct");
    }
}
