package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuckTest {
    private transient Puck puck1;
    private transient Puck puck2;
    private transient Puck puck3;

    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 10;
    private transient float coeffRestitution = 0.85f;

    @BeforeEach
    void setupTestEnvironment() {
        puck1 = new Puck(10, 100, 0, 100, 15,
                10, 1280, 720);
        puck2 = new Puck(640, 360, 64, 36, 15,
                10, 1280, 720);
        puck3 = new Puck(640, 360, -64, -36, 15,
                10, 1280, 720);

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

        puck1.move(deltaTime1);

        assertEquals(10, puck1.x);
        assertEquals(100 + 500, puck1.y);
    }

}
