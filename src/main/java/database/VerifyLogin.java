package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("PMD.CloseResource")
public class VerifyLogin implements Query {

    private transient Connection connection;
    private transient String username;
    private transient String password;
    private transient PreparedStatements statements;

    /**
     * Constructor for the VerifyLogin class.
     * Its purpose is to verify that a user exists
     * in the database.
     * @param connection to the database.
     * @param username of the user.
     * @param password of the user.
     */
    public VerifyLogin(Connection connection, String username, String password) {
        this.connection = connection;
        this.username = username;
        this.password = password;
        statements = new PreparedStatements(connection);
    }

    @Override
    public Boolean execute(Connection connection) {
        try {
            PreparedStatement statement = statements.getVerify();
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString(2))) {
                    resultSet.close();
                    statement.close();
                    return true;
                }
            }
            resultSet.close();
            statement.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
