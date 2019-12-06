package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("PMD.CloseResource")
public class Query extends Adapter {

    /**
     * Execute any given query, SELECT or otherwise. Returns a ResultSet array for results
     * from any SELECT queries that were passed.
     *
     * @param query The queries given as string to be executed
     * @param db    A query object if you want to continue an existing connection
     * @return a ResultSet array of SELECT query results, in order corresponding to that
     *      of the query array order.
     *      I.E.: the first select query appears in the return array first, the second second.
     *      Returns an empty array if no SELECT queries were specified.
     */
    public static ResultSet[] runQueries(String[] query, Query db) {
        if (db == null) {
            db = new Query();
            db.connect();
        }

        try {
            conn.setAutoCommit(false);
            ArrayList<ResultSet> resultSets = new ArrayList<>();

            for (int i = 0; i < query.length; i++) {
                if (query[i].contains("SELECT")) {
                    //SELECT query, add resultSet to array
                    try {
                        PreparedStatement select = conn.prepareStatement(query[i]);
                        ResultSet resultSet = select.executeQuery();
                        resultSets.add(resultSet);
                        //select.close();
                        //resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Error executing SELECT query");
                    }

                } else {
                    //Not a SELECT query, so it can be safely added to the batch.
                    try {
                        conn.createStatement().addBatch(query[i]);
                        conn.createStatement().executeUpdate(query[i]);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conn.commit();
            db.disconnect();
            return resultSets.toArray(new ResultSet[resultSets.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<ResultSet> nulll = new ArrayList<>();
        return nulll.toArray(new ResultSet[nulll.size()]);
    }

    /**
     * Basic version of runQueries, starts and ends a new database connection.
     * Executes any query, SELECT or otherwise.
     *
     * @param query Queries to be executed as a string
     * @return Array of result sets for the select queries.
     */
    public static ResultSet[] runQueries(String[] query) {
        return runQueries(query, null);
    }

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
            insert.setString(3, email.replaceAll("[^A-Za-z0-9_.?!@]", ""));
            int rows = insert.executeUpdate();
            if (rows != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("User could not be added.");
        }
        return false;
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
}