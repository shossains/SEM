package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("PMD.CloseResource")
public class UserScore implements Query {

    private transient Connection connection;
    private transient String nickname;
    private transient int score;
    private transient PreparedStatements statements;

    /**
     * Constructor for the UserScore class.
     * Its purpose is to add a new user to the database and
     * his/her score or to retrieve the highest score
     * of a given user.
     * @param connection to the database.
     * @param username of the user.
     * @param score of the user.
     */
    public UserScore(Connection connection, String username, int score) {
        this.connection = connection;
        this.nickname = username;
        this.score = score;
        statements = new PreparedStatements(connection);
    }

    @Override
    public Integer execute(Connection connection) {
        try {
            PreparedStatement getScoreUser = statements.getGetScoreUser();
            getScoreUser.setString(1, nickname);
            ResultSet resultSet = getScoreUser.executeQuery();

            if (resultSet.next()) {
                int oldScore = resultSet.getInt(1);
                if (score > oldScore) {
                    PreparedStatement updateScore = statements.getUpdateScore();
                    updateScore.setInt(1, score);
                    updateScore.setString(2, nickname);
                    updateScore.executeUpdate();
                    updateScore.close();
                    resultSet.close();
                    return score;
                } else {
                    return oldScore;
                }
            } else {
                PreparedStatement addScore = statements.getAddScore();
                addScore.setInt(1, score);
                addScore.setString(2, nickname);
                addScore.executeUpdate();
                addScore.close();
                return score;
            }
        } catch (Exception e) {
            return score;
        }
    }
}
