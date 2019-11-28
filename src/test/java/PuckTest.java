import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import GameLogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuckTest {
    private transient Puck puck1;
    private transient Puck puck2;
    private transient Puck puck3;

    private static transient final int deltaTime1 = 5;
    private static transient final int deltaTime2 = 10;

    @BeforeEach
    public void setUpTests() {
        puck1 = new Puck(10, 100, 0, 30, 15);
        puck2 = new Puck(200, 150, 40, 30, 15);
        puck3 = new Puck(200, 150, -40, -30, 15);

    }

    @Test
    public void testConstructor() {
        assertNotNull(puck1);
    }

    @Test
    public void testGetters() {
        assertEquals(0, puck1.getxSpeed());
        assertEquals(30, puck1.getySpeed());
    }

    @Test
    public void testMove1() {
        assertEquals(10, puck1.x);
        assertEquals(100, puck1.y);

        puck1.movePuck(deltaTime1);

        assertEquals(10, puck1.x);
        assertEquals(100+150, puck1.y);
    }

    @Test
    public void testBoundaries1() {
        puck1.movePuck(deltaTime2);
        puck1.fixPosition();

        assertEquals(400, puck1.y);
    }

    @Test
    public void testBoundaries2() {
        puck2.movePuck(deltaTime1);
        puck2.fixPosition();

        assertEquals(300, puck2.y);
        assertEquals(400, puck2.x);
    }

    @Test
    public void testBoundaries3() {
        puck3.movePuck(deltaTime2);
        puck3.fixPosition();

        assertEquals(puck3.radius, puck3.y);
        assertEquals(puck3.radius, puck3.x);
    }
}
