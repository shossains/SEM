import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gamelogic.Paddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaddleTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;

    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 3;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(100, 100, 0, 0, 15);
        paddle2 = new Paddle(300, 200, 0, 0, 15);

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

        paddle1.movePaddle(deltaTime1);
        assertEquals(600, paddle1.x);
        assertEquals(600, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds2() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(false, true, false, true);

        paddle1.movePaddle(deltaTime1);
        assertEquals(-400, paddle1.x);
        assertEquals(-400, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds3() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(true, true, true, true);

        paddle1.movePaddle(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testMove4() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(false, false, false, false);

        paddle1.movePaddle(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testBoundaries1() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.setSpeeds(false, true, false, true);

        paddle1.movePaddle(deltaTime2);
        paddle1.fixPosition();

        assertEquals(paddle1.radius, paddle1.y);
        assertEquals(paddle1.radius, paddle1.x);
    }

    @Test
    public void testBoundaries2() {
        assertEquals(300, paddle2.x);
        assertEquals(200, paddle2.y);

        paddle2.setSpeeds(true, false, true, false);

        paddle2.movePaddle(deltaTime2);
        paddle2.fixPosition();

        assertEquals(600, paddle2.x);
        assertEquals(500, paddle2.y);
    }

}
