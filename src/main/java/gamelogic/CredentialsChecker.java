package gamelogic;

import com.badlogic.gdx.Screen;
import database.Adapter;
import database.RegisterUser;
import database.VerifyLogin;

public class CredentialsChecker {

    transient Screen screen;
    private transient Adapter adapter;

    /**
     * Constructor for the credentials checker class.
     * @param screen the screen that is currently on.
     */
    public CredentialsChecker(Screen screen, Adapter adapter) {
        this.screen = screen;
        this.adapter = adapter;
    }

    /**
     * Method that interacts with the database
     * and checks if the entered credentials
     * are correct.
     * @param username of the user
     * @param password of the user
     * @return messages for each possible case.
     */
    public String checkLoginCredentials(String username, String password) {
        if (username.equals("") || password.equals("")) {
            return "empty";
        }
        VerifyLogin verifyLogin = new VerifyLogin(adapter.conn, username, password);
        if (verifyLogin.execute(adapter.conn)) {
            return "correct";
        } else {
            return "incorrect";
        }
    }

    /**
     * Method used for checking the correctness of
     * the registration of a new user.
     * If the supplied credentials are alright, the user
     * is added to the database.
     * @param username entered by the user
     * @param password entered by the user
     * @param email entered by the user
     * @param passwordAgain entered by the user
     * @return messages for each possible case
     */
    public String checkRegisterCredentials(String username, String password,
                                           String email, String passwordAgain) {
        if (username.equals("") || password.equals("")
                || email.equals("") || passwordAgain.equals("")) {
            return "empty";
        }
        if (!password.equals(passwordAgain)) {
            return "passwordsNotMatching";
        }
        RegisterUser registerUser = new RegisterUser(adapter.conn, username, password, email);
        if (registerUser.execute(adapter.conn)) {
            return "correct";
        } else {
            return "incorrect";
        }
    }
}
