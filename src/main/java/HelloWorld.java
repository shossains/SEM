import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The main class which will start up the game.
 */
@SuppressWarnings("PMD.CloseResource")
public class HelloWorld {

    public static void main(String[] args) {
        Student student = new Student("Iron Man", 2345678);

        System.out.println("Hi there, " + student);

        String[] queries = {"SELECT * FROM users;"};
        //int id = 0;
        //String username = "test";
        //String password = "pass";
        //queries[0] = "INSERT INTO users VALUES ('" + id + "', '"
        //+ username + "', '" + password + "');";
        ResultSet rs = Query.runQueries(queries)[0];
        try {
            while (rs.next()) {
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Query.runQueries(queries);
    }
}
