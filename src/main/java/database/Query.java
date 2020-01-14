package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("PMD.CloseResource")
public class Query extends Adapter {
    
    /**
     * runQueries identical to the other one, but with authentication.
     * @param username Username given by the client
     * @param password Raw password given by the client
     * @return The result of the queries same as in the other one,
     *      but null if authentication fails.
     */
    public static boolean verifyLogin(String username, String password) {
        Query db = new Query();
        db.connect();
        try {
            PreparedStatement select = conn.prepareStatement("SELECT username, password"
                    + " FROM users WHERE username = ?");
            //replace all non-alphanumeric characters with empty strings
            select.setString(1, username.replaceAll("[^A-Za-z0-9_.?!]", ""));
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString(2))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing authentication SELECT query");
        }
        return false;
    }

    /**
     * Method that adds a new user to the database.
     * @param username of the new user.
     * @param password of the user.
     * @return true if the user was added, false otherwise.
     */
    public static boolean addNewUser(String username, String password, String email) {
        Query db = new Query();
        db.connect();
        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO users(username, "
                    + "password, email) VALUES(? , ?, ?)");
            insert.setString(1, username.replaceAll("[^A-Za-z0-9_.!?]", ""));
            insert.setString(2, BCrypt.hashpw(password.replaceAll("[^A-Za-z0-9_.?!]",
                    ""), BCrypt.gensalt(10)));
            insert.setString(3, email/*email.replaceAll("[^A-Za-z0-9_.?!@]", "")*/);
            int rows = insert.executeUpdate();
            if (rows != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method for updating or retrieving the score given a nickname.
     * @param nickname of the user.
     * @param score new score of the user.
     * @return old score if it is larger, otherwise new score.
     */
    public static int getScore(String nickname, int score) {
        Query db = new Query();
        db.connect();
        try {
            PreparedStatement getScore = conn.prepareStatement("SELECT highscore FROM scores"
                    + " WHERE nickname = ?");
            getScore.setString(1, nickname);
            ResultSet resultSet = getScore.executeQuery();
            if (resultSet.next()) {
                int oldScore = resultSet.getInt(1);
                if (score > oldScore) {
                    PreparedStatement updateScore = conn.prepareStatement("UPDATE scores SET"
                            + " highscore = ? WHERE nickname = ?");
                    updateScore.setInt(1, score);
                    updateScore.setString(2, nickname);
                    updateScore.executeUpdate();
                    return score;
                } else {
                    return oldScore;
                }
            } else {
                PreparedStatement addUser = conn.prepareStatement("INSERT INTO scores(highscore,"
                        + " nickname) VALUES(?, ?)");
                addUser.setInt(1, score);
                addUser.setString(2, nickname);
                addUser.executeUpdate();
                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Score was not found.");
        }
        return score;
    }

    /**
     * Method for returning best scores.
     * @return top 5 best scores from the database.
     */
    public static String getTopScores() {
        Query db = new Query();
        db.connect();
        try {
            PreparedStatement select = conn.prepareStatement("SELECT *"
                    + " FROM scores ORDER BY highscore DESC LIMIT 5");
            //replace all non-alphanumeric characters with empty strings
            //select.setString(1, username.replaceAll("[^A-Za-z0-9_.?!]", ""));
            ResultSet resultSet = select.executeQuery();
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getInt(1) + " " + resultSet.getString(2) + "\n";
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing authentication SELECT query");
        }
        return "";
    }

    /**
     * Helper function to test the database querying.
     * @param username the username that needs to be deleted.
     * @return
     **/
    public static void deleteUser(String username) {
        Query db = new Query();
        db.connect();

        try {
            String delete = "DELETE FROM users WHERE username"
                    + " = '" + username + "';";
            conn.createStatement().addBatch(delete);
            conn.createStatement().executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("User could not be deleted.");
        }
    }
}