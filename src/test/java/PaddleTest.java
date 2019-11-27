import GameLogic.Paddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaddleTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;

    private static transient final int deltaTime1 = 5;
    private static transient final int deltaTime2 = 3;

    @BeforeEach
    public void setUpTests() {
        paddle1 = new Paddle(100, 100, 0, 0, 15);
        paddle2 = new Paddle(300, 200, 0, 0, 15);

    }

    @Test
    public void testConstructor() {
        assertNotNull(paddle1);
    }

    @Test
    public void testMove1() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.movePaddle(true, false, true, false, deltaTime1);

        assertEquals(100 + 150, paddle1.x);
        assertEquals(100 + 150, paddle1.y);
    }

    @Test
    public void testMove2() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.movePaddle(false, true, false, true, deltaTime1);

        assertEquals(100 - 150, paddle1.x);
        assertEquals(100 - 150, paddle1.y);
    }

    @Test
    public void testMove3() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.movePaddle(true, true, true, true, deltaTime1);

        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testMove4() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.movePaddle(false, false, false, false, deltaTime1);

        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testBoundaries1() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.movePaddle(false, true, false, true, deltaTime2);

        paddle1.fixPosition();

        assertEquals(paddle1.radius, paddle1.y);
        assertEquals(paddle1.radius, paddle1.x);
    }

    @Test
    public void testBoundaries2() {
        assertEquals(300, paddle2.x);
        assertEquals(200, paddle2.y);

        paddle2.movePaddle(true, false, true, false, deltaTime2);

        paddle2.fixPosition();

        assertEquals(400 - paddle2.radius, paddle2.x);
        assertEquals(300 - paddle2.radius, paddle2.y);
    }

}
