package database;

import java.sql.Connection;
import java.sql.ResultSet;

@SuppressWarnings("PMD.CloseResource")
public class TopScores implements Query {

    private transient Connection connection;
    private transient PreparedStatements statements;

    /**
     * Constructor for the TopScores class.
     * Its purpose is to interact with the database using
     * prepared statements and retrieve the top 5 high scores.
     * @param connection to the database.
     */
    public TopScores(Connection connection) {
        this.connection = connection;
        statements = new PreparedStatements(connection);
    }

    @Override
    public String execute(Connection connection) {
        try {
            ResultSet resultSet = statements.getTopScore().executeQuery();
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getInt(1) + " " + resultSet.getString(2) + "\n";
            }
            resultSet.close();
            return result;
        } catch (Exception e) {
            return "";
        }
    }
}
