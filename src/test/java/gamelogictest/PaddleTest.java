package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gamelogic.Paddle;
import gamelogic.PlayerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaddleTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient Paddle paddle3;
    private transient Paddle paddle4;

    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 3;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(100, 100, 0, 0, 15, 10, PlayerType.PLAYER1);
        paddle2 = new Paddle(300, 200, 0, 0, 15, 10, PlayerType.PLAYER2);

        paddle3 = new Paddle(1000f, 360f, 0f, 0f, 40f, 10, PlayerType.PLAYER1);
        paddle4 = new Paddle(360, 360f, 0f, 0f, 40f, 10, PlayerType.PLAYER2);
    }

    @Test
    public void testConstructor() {
        assertNotNull(paddle1);
    }

    @Test
    public void testMoveAndSetSpeeds1() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(true, false, true, false);

        paddle1.move(deltaTime1);
        assertEquals(600, paddle1.x);
        assertEquals(600, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds2() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(false, true, false, true);

        paddle1.move(deltaTime1);
        assertEquals(-400, paddle1.x);
        assertEquals(-400, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds3() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(true, true, true, true);

        paddle1.move(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testMove4() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(false, false, false, false);

        paddle1.move(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testBoundaries1() {
        assertEquals(1000, paddle3.x);
        assertEquals(360, paddle3.y);

        paddle3.setSpeeds(false, true, false, true);

        paddle3.move(deltaTime1);
        //500, -140
        paddle3.fixPosition();

        assertEquals(paddle3.radius, paddle3.y);
        assertEquals(paddle3.radius + 640, paddle3.x);
    }

    @Test
    public void testBoundaries2() {
        assertEquals(1000, paddle3.x);
        assertEquals(360, paddle3.y);

        paddle3.setSpeeds(true, false, true, false);

        paddle3.move(deltaTime1);

        assertEquals(860, paddle3.y);
        //1500, 860
        paddle3.fixPosition();

        assertEquals(720 - paddle3.radius, paddle3.y);
        assertEquals(1280 - paddle3.radius, paddle3.x);
    }

    @Test
    public void testBoundaries3() {
        assertEquals(360, paddle4.x);
        assertEquals(360, paddle4.y);

        paddle4.setSpeeds(true, false, true, false);

        paddle4.move(deltaTime1);
        paddle4.fixPosition();

        assertEquals(640 - paddle4.radius, paddle4.x);
        assertEquals(720 - paddle4.radius, paddle4.y);
    }

    @Test
    public void testBoundaries4() {
        assertEquals(360, paddle4.x);
        assertEquals(360, paddle4.y);

        paddle4.setSpeeds(false, true, false, true);

        paddle4.move(deltaTime1);
        paddle4.fixPosition();

        assertEquals(paddle4.radius, paddle4.x);
        assertEquals(paddle4.radius, paddle4.y);
    }

}
