package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatements {

    private transient Connection conn;

    /**
     * Constructor for the Statements class.
     * @param connection from the Adapter class
     */
    public PreparedStatements(Connection connection) {
        this.conn = connection;
    }

    /**
     * Create prepared statements for retrieving
     * a user based on the username.
     * @return new prepared statement
     */
    public PreparedStatement getVerify() {
        try {
            return this.conn.prepareStatement("SELECT username, password"
                    + " FROM users WHERE username = ?");
        } catch (SQLException e) {
            return  null;
        }
    }

    /**
     * Create prepared statements for adding
     * a user based on the username, password and email.
     * @return new prepared statement
     */
    public PreparedStatement getRegister() {
        try {
            return this.conn.prepareStatement("INSERT INTO users(username, "
                    + "password, email) VALUES(? , ?, ?)");
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Create prepared statements for retrieving
     * a user's score based on the username.
     * @return new prepared statement
     */
    public PreparedStatement getGetScoreUser() {
        try {
            return conn.prepareStatement("SELECT highscore FROM scores"
                    + " WHERE nickname = ?");
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Create prepared statements for updated
     * a user based on the username.
     * @return new prepared statement
     */
    public PreparedStatement getUpdateScore() {
        try {
            return conn.prepareStatement("UPDATE scores SET"
                    + " highscore = ? WHERE nickname = ?");
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Create prepared statements for retrieving
     * the top 5 high scores..
     * @return new prepared statement
     */
    public PreparedStatement getTopScore() {
        try {
            return conn.prepareStatement("SELECT *"
                    + " FROM scores ORDER BY highscore DESC LIMIT 5");
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Create prepared statements for inserting
     * a user based on the username.
     * @return new prepared statement
     */
    public PreparedStatement getAddScore() {
        try {
            return conn.prepareStatement("INSERT INTO scores(highscore,"
                    + " nickname) VALUES(?, ?)");
        } catch (SQLException e) {
            return null;
        }
    }
}
