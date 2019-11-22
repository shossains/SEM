import jdk.swing.interop.SwingInterOpUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloWorld {
    public static void main(String[] args) {
        Student student = new Student("Iron Man", 2345678);

        System.out.println("Hi there, " + student);

        String[] queries = new String[1];
        int id = 0;
        String username = "test";
        String password = "pass";
//        queries[0] = "INSERT INTO users VALUES ('" + id + "', '" + username + "', '" + password + "');";
        queries[0] = "SELECT * FROM users;";
        ResultSet rs = Query.runQueries(queries)[0];
        try {
            while (rs.next()) {
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Query.runQueries(queries);
    }
}
