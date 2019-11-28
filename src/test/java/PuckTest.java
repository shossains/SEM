import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuckTest {
    private transient Puck puck1;
    private transient Puck puck2;
    private transient Puck puck3;

    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 10;

    @BeforeEach
    void setupTestEnvironment() {
        puck1 = new Puck(10, 100, 0, 100, 15);
        puck2 = new Puck(640, 360, 64, 36, 15);
        puck3 = new Puck(640, 360, -64, -36, 15);

    }

    @Test
    public void testConstructor() {
        assertNotNull(puck1);
    }

    @Test
    public void testGetters() {
        assertEquals(0, puck1.getXspeed());
        assertEquals(100, puck1.getYspeed());
    }

    @Test
    public void testMove1() {
        assertEquals(10, puck1.x);
        assertEquals(100, puck1.y);

        puck1.movePuck(deltaTime1);

        assertEquals(10, puck1.x);
        assertEquals(100 + 500, puck1.y);
    }

    @Test
    public void testBoundaries1() {
        puck1.movePuck(deltaTime2);
        puck1.fixPosition();

        assertEquals(720 - puck1.radius, puck1.y);

        puck1.movePuck(deltaTime1);

        assertEquals(720 - puck1.radius - 500, puck1.y);
    }

    @Test
    public void testBoundaries2() {
        puck2.movePuck(deltaTime2);
        puck2.fixPosition();

        assertEquals(705, puck2.y);
        assertEquals(1265, puck2.x);

        puck2.movePuck(deltaTime1);

        assertEquals(1265 - 64 * 5, puck2.x);
        assertEquals(705 - 36 * 5, puck2.y);
    }

    @Test
    public void testBoundaries3() {
        puck3.movePuck(deltaTime2);
        puck3.fixPosition();

        assertEquals(15, puck3.y);
        assertEquals(15, puck3.x);

        puck3.movePuck(deltaTime1);

        assertEquals(15 + 64 * 5, puck3.x);
        assertEquals(15 + 36 * 5, puck3.y);
    }
}
