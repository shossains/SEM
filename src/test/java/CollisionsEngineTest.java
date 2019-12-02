import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gamelogic.CollisionsEngine;
import gamelogic.Paddle;
import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lwjgl.Sys;

public class CollisionsEngineTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient Puck puck;
    private transient CollisionsEngine collisionsEngine;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(0, 0, 3, 4, 15, 10);
        paddle2 = new Paddle(4, 3, 0, 0, 15, 10);
        puck = new Puck(10, 100, 0, 100, 15, 10);
        collisionsEngine = new CollisionsEngine(puck, paddle1, paddle2);

    }

    @Test
    public void testAngleBetween() {
        //System.out.println(collisionsEngine.angleBetween(paddle1, paddle2));
        assertEquals(collisionsEngine.angleBetween(paddle1, paddle2), .6435011, 10);
    }

    @Test
    public void testAngleOfDirection() {
        assertEquals(0.927295, collisionsEngine.angleOfMovement(paddle1), 10);
    }

    @Test
    public void testGetIspeed() {
        assertEquals(4.8, collisionsEngine.getispeed(paddle1, .6435011), 10);
    }
}
