package gamelogic;

import database.RegisterUser;
import database.VerifyLogin;

import java.sql.Connection;

public class QueryGetter {

    public VerifyLogin getVerifyLogin(Connection conn, String username, String password) {
        return new VerifyLogin(conn, username, password);
    }

    public RegisterUser getRegisterUser(Connection conn, String username, String password, String email) {
        return new RegisterUser(conn, username, password, email);
    }
}
