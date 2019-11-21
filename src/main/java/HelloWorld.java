public class HelloWorld {
    public static void main(String[] args) {
        Student student = new Student("Iron Man", 2345678);

        System.out.println("Hi there, " + student);

        String[] queries = new String[1];
        int id = 0;
        String username = "test";
        String password = "pass";
        queries[0] = "INSERT INTO points VALUES ('" + id + "', '" + username + "', '" + password + "'+";
        Query.runQueries(queries);
    }
}
