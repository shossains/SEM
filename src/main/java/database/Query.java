package database;

import java.sql.Connection;

public interface Query<T> {

    T execute(Connection connection);

}
