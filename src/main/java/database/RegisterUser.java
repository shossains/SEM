package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("PMD.CloseResource")
public class RegisterUser implements Query {

    private transient Connection connection;
    private transient String username;
    private transient String email;
    private transient String password;
    private transient PreparedStatements statements;

    /**
     * Constructor for the RegisterUser class.
     * Its purpose is to add a new user in the database
     * if the user does not exists there.
     * @param connection to the database, established in Adapter class.
     * @param username of the user.
     * @param password of the user.
     * @param email of the user.
     */
    public RegisterUser(Connection connection, String username, String password, String email) {
        this.connection = connection;
        this.username = username;
        this.password = password;
        this.email = email;
        statements = new PreparedStatements(connection);
    }

    @Override
    public Boolean execute(Connection connection) {
        try {
            PreparedStatement register = statements.getRegister();
            register.setString(1, username);
            register.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            register.setString(3, email);

            int rows = register.executeUpdate();
            if (rows != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
