package gamelogic;

import com.badlogic.gdx.Screen;
import database.Adapter;

public class CredentialsChecker {

    transient Screen screen;
    private transient Adapter adapter;
    private transient QueryGetter queryGetter;

    /**
     * Constructor for the credentials checker class.
     * @param screen the screen that is currently on.
     */
    public CredentialsChecker(Screen screen, Adapter adapter, QueryGetter queryGetter) {
        this.screen = screen;
        this.adapter = adapter;
        this.queryGetter = queryGetter;
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
        if (queryGetter.getVerifyLogin(this.adapter.conn, username, password)
                .execute(adapter.conn)) {
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
        if (queryGetter.getRegisterUser(this.adapter.conn, username, password, email)
                .execute(adapter.conn)) {
            return "correct";
        } else {
            return "incorrect";
        }
    }
}
