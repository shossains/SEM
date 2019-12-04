import gamelogic.CollisionsEngine;
import gamelogic.Paddle;
import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lwjgl.Sys;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionsEngineTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient Puck puck;
    private transient CollisionsEngine collisionsEngine;

    //this is the degree of tolerance (for checking angles between calculator and methods)
    private transient final double delta = 0.000001;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(0, 0, 3, 4, 15, 2);
        paddle2 = new Paddle(4, 3, 0, 0, 15, 2);
        puck = new Puck(30, 0, 0, 0, 15, 1);
        collisionsEngine = new CollisionsEngine(puck, paddle1, paddle2, 0.8f);

    }

    @Test
    public void testAngleBetween() {
        //System.out.println(collisionsEngine.angleBetween(paddle1, paddle2));
        assertEquals(collisionsEngine.angleBetween(paddle1, paddle2), .6435011, delta);

        System.out.println(Math.cos(1.6));
        System.out.println(Math.cos(3.2));
    }

    @Test
    public void testAnglebetween2() {
        paddle2.setY(0);
        assertEquals(0, collisionsEngine.angleBetween(paddle1, paddle2));
    }

    @Test
    public void testAnglebetween3() {
        paddle2.setX(-3);
        paddle2.setY(4);

        assertEquals(2.214297438, collisionsEngine.angleBetween(paddle1, paddle2), delta);
    }

    @Test
    public void testAnglebetween4() {
        paddle2.setX(-3);
        paddle2.setY(-4);

        assertEquals(-2.2142974355, collisionsEngine.angleBetween(paddle1, paddle2), delta);

    }

    @Test
    public void testAnglebetween5() {

        paddle2.setX(0);
        paddle2.setY(1);

        assertEquals(1.57079632679, collisionsEngine.angleBetween(paddle1, paddle2), delta);

        assertEquals(-1.57079632679, collisionsEngine.angleBetween(paddle2, paddle1), delta);

    }

    @Test
    public void obliqueCollide() {
        puck.setXspeed(-4);
        puck.setYspeed(3);

        double theta = collisionsEngine.angleBetween(paddle1, puck);
        assertEquals(0, theta);

        //double moveAnglePaddle = collisionsEngine.angleOfMovement(paddle1);
        //assertEquals(.927295218, moveAnglePaddle, delta);

        assertEquals(3, collisionsEngine.getispeed(paddle1, theta));

        //double moveAnglePuck = collisionsEngine.angleOfMovement(puck);
        //assertEquals(-.6435011088, moveAnglePuck, delta);

        assertEquals(-4, collisionsEngine.getispeed(puck, theta));

    }

    @Test
    public void testispeed() {
        paddle2.setYspeed(0);
        paddle2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddle2, 0), delta);
    }

    @Test
    public void testispeed2() {
        paddle2.setYspeed(4);
        paddle2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddle2, 0), delta);
    }

    @Test
    public void testispeed3() {
        paddle2.setYspeed(0);
        paddle2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddle2, 0), delta);
        assertEquals(0, collisionsEngine.getjspeed(paddle2, 0), delta);
    }

    @Test
    public void testispeed4() {
        paddle2.setYspeed(4);
        paddle2.setXspeed(-4);

        assertEquals(0, collisionsEngine.getispeed(paddle2, Math.PI/4), delta);
    }

    @Test
    public void testSpeeds() {
        paddle2.setYspeed(4);
        paddle2.setXspeed(-4);

        paddle2.setX(4);
        paddle2.setY(4);

        double theta = collisionsEngine.angleBetween(paddle1, paddle2);

        System.out.println(theta);

        float jspeed = collisionsEngine.getjspeed(paddle2, theta);
        float ispeed = collisionsEngine.getispeed(paddle2, theta);

        assertEquals(5.656854249, jspeed, delta);
        assertEquals(0, ispeed, delta);

        float xspeedagain = collisionsEngine.rotateToX(ispeed, jspeed, theta);
        float yspeedagain = collisionsEngine.rotateToY(ispeed, jspeed, theta);

        assertEquals(-4, xspeedagain);
        assertEquals(4, yspeedagain);

    }

    @Test
    public void testSimultaneous() {
        float[] ans = {-1, 4};
        float ecurr = 5f/7f;
        System.out.println(ecurr);
        float[] res = collisionsEngine.solveSimultaneous(2, 1, 3, -4, ecurr);
        assertArrayEquals(ans, res);
    }

    /*
    @Test
    public void testSimultaneous2() {
        //collision in the same axis
        float[] ans = {4, 3};
        float[] res = collisionsEngine.solveSimultaneous(2, 1, 4, 3, .99f);
        assertArrayEquals(ans, res);
    }

     */
}
