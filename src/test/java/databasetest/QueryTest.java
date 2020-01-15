package databasetest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.RegisterUser;
import database.TopScores;
import database.UserScore;
import database.VerifyLogin;

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

    @BeforeEach
    void setUp() throws SQLException {
        connection = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
    }

    @Test
    public void verifyLoginTest() throws SQLException {
        VerifyLogin verifyLogin = new VerifyLogin(connection, "test", "pass");
        when(rs.first()).thenReturn(true);
        when(rs.getString(2)).thenReturn(BCrypt.hashpw("pass", BCrypt.gensalt()));
        assertTrue(verifyLogin.execute(connection));

        when(rs.next()).thenReturn(false);
        assertFalse(verifyLogin.execute(connection));
    }

    @Test
    public void addNewUserTest() throws SQLException {
        when(stmt.executeUpdate()).thenReturn(1);
        RegisterUser registerUser = new RegisterUser(connection, "username", "pass", "email");
        assertTrue(registerUser.execute(connection));
        verify(stmt, times(1)).executeUpdate();

        when(stmt.executeUpdate()).thenReturn(0);
        assertFalse(registerUser.execute(connection));
        verify(stmt, times(2)).executeUpdate();
    }

    @Test
    public void getScoresTest() throws SQLException {
        UserScore userScore = new UserScore(connection, "nickname", 100);
        userScore.execute(connection);
        verify(stmt, times(2)).executeUpdate();

        when(rs.next()).thenReturn(false);
        userScore.execute(connection);
        verify(stmt, times(3)).executeUpdate();
    }

    @Test
    public void getTopScores() throws SQLException {
        when(rs.getString(2)).thenReturn("User1");
        when(rs.getInt(1)).thenReturn(100);
        when(rs.next()).thenReturn(true).thenReturn(false);
        TopScores topScores = new TopScores(connection);
        assertFalse(topScores.execute(connection).equals(""));
        verify(stmt, times(1)).executeQuery();
    }

    @Test
    public void getScoresNonExistentUser() throws SQLException {
        when(rs.next()).thenReturn(false);
        UserScore userScore = new UserScore(connection, "nick", 300);
        assertEquals(300, (int) userScore.execute(connection));
    }

    @Test
    public void getScoresSmallerScore() throws SQLException {
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(250);
        UserScore userScore = new UserScore(connection, "nickname", 100);
        assertEquals(250, (int) userScore.execute(connection));
    }

}
