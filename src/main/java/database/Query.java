package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("PMD.CloseResource")
public class Query {

    private transient Connection conn;
    private transient PreparedStatements statements;


    public Query(Connection connection) {
        this.conn = connection;
        statements = new PreparedStatements(connection);
    }

    /**
     * runQueries identical to the other one, but with authentication.
     * @param username Username given by the client
     * @param password Raw password given by the client
     * @return The result of the queries same as in the other one,
     *      but null if authentication fails.
     */
    public boolean verifyLogin(String username, String password) {
        try {
            PreparedStatement statement = statements.getVerify();
            statement.setString(1, username);
            ResultSet resultSet = this.execute(statement);
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString(2))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }


    }

    public ResultSet execute(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    /**
     * Method that adds a new user to the database.
     * @param username of the new user.
     * @param password of the user.
     * @return true if the user was added, false otherwise.
     */
    public boolean addNewUser(String username, String password, String email) {
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

    /**
     * Method for updating or retrieving the score given a nickname.
     * @param nickname of the user.
     * @param score new score of the user.
     * @return old score if it is larger, otherwise new score.
     */
    public int getScore(String nickname, int score) {
        try {
            if (getScoreExistingUser(nickname, score) == -1) {
                PreparedStatement addScore = statements.getAddScore();
                addScore.setInt(1, score);
                addScore.setString(2, nickname);
                addScore.executeUpdate();
                return score;
            } else {
                return getScoreExistingUser(nickname, score);
            }
        } catch (Exception e) {
            return score;
        }
    }

    /**
     * Method for returning best scores.
     * @return top 5 best scores from the database.
     */
    public String getTopScores() {
        try {
            ResultSet resultSet = this.execute(statements.getTopScore());
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getInt(1) + " " + resultSet.getString(2) + "\n";
            }
            return result;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Method that retrieves the best score
     * of a user that exists in the database.
     * @param nickname of the user.
     * @param score won by the user.
     * @return the best score of this user, -1 if this user
     * is not in the database.
     */
    public int getScoreExistingUser(String nickname, int score) {
        try {
            PreparedStatement getScoreUser = statements.getGetScoreUser();
            getScoreUser.setString(1, nickname);
            ResultSet resultSet = this.execute(getScoreUser);
            if (resultSet.next()) {
                int oldScore = resultSet.getInt(1);
                if (score > oldScore) {
                    PreparedStatement updateScore = statements.getUpdateScore();
                    updateScore.setInt(1, score);
                    updateScore.setString(2, nickname);
                    updateScore.executeUpdate();
                    return score;
                } else {
                    return oldScore;
                }
            } else {
                return -1;
            }
        } catch (Exception e) {
            return score;
        }
    }

}