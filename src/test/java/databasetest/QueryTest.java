package databasetest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

@RunWith(MockitoJUnitRunner.class)
public class QueryTest {

    @Mock
    private transient Connection connection;
    @Mock
    private transient PreparedStatement stmt;
    @Mock
    private transient ResultSet rs;

    public transient Query query;

    @BeforeEach
    void setUp() throws SQLException {
        connection = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        query = new Query(connection);
    }

    @Test
    public void verifyLoginTest() throws SQLException {
        when(rs.first()).thenReturn(true);
        when(rs.getString(2)).thenReturn(BCrypt.hashpw("pass", BCrypt.gensalt()));
        assertTrue(query.verifyLogin("test","pass"));
        verify(stmt, times(1)).executeQuery();
        verify(connection, times(1)).prepareStatement(any(String.class));
        assertFalse(query.verifyLogin("test","notthecorrectpass"));
    }

    @Test
    public void addNewUserTest() throws SQLException {
        when(stmt.executeUpdate()).thenReturn(1);
        assertTrue(query.addNewUser("username", "pass", "email"));
        verify(stmt, times(1)).executeUpdate();

        when(stmt.executeUpdate()).thenReturn(0);
        assertFalse(query.addNewUser("test", "passfalse", "false"));
        verify(stmt, times(2)).executeUpdate();
    }

    @Test
    public void getScoresTest() throws SQLException {
        query.getScore("nickname", 100);
        verify(stmt, times(1)).executeUpdate();

        when(rs.next()).thenReturn(false);
        query.getScore("nickname2", 200);
        verify(stmt, times(2)).executeUpdate();
    }

    @Test
    public void getTopScores() throws SQLException {
        when(rs.getString(2)).thenReturn("User1");
        when(rs.getInt(1)).thenReturn(100);
        when(rs.next()).thenReturn(true).thenReturn(false);
        assertFalse(query.getTopScores().equals(""));
        verify(stmt, times(1)).executeQuery();
    }

}
