package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.audio.Sound;
import gamelogic.CollisionsEngine;
import gamelogic.Paddle;
import gamelogic.PlayerType;
import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollisionsEngineTest {

    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient Puck puck;
    private transient CollisionsEngine collisionsEngine;

    //this is the degree of tolerance (for checking angles between calculator and methods)
    private final transient double delta = 0.000001;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(0, 0, 3, 4, 25, 2, 1280, 720,
                PlayerType.PLAYER1, 200, 6, 50);
        paddle2 = new Paddle(4, 3, 0, 0, 25, 2, 1280, 720,
                PlayerType.PLAYER2, 200, 6, 50);
        puck = new Puck(30, 0, 0, 0, 15, 1, 1280, 720, 0.85f, mock(Sound.class));
        collisionsEngine = new CollisionsEngine(puck, paddle1, paddle2, 0.8f, mock(Sound.class));

    }

    @Test
    public void testDistanceAndIntersection() {
        paddle2.setX(40);
        paddle2.setY(30);

        double distance = collisionsEngine.distance(paddle1, paddle2);
        assertEquals(50, distance);

        assertTrue(collisionsEngine.isIntersecting(paddle1, paddle2));
    }

    @Test
    public void testDistanceAndIntersectionFalse() {
        paddle2.setX(80);
        paddle2.setY(60);

        double distance = collisionsEngine.distance(paddle1, paddle2);
        assertEquals(100, distance);

        assertFalse(collisionsEngine.isIntersecting(paddle1, paddle2));
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
        float ecurr = 5f / 7f;
        System.out.println(ecurr);
        float[] res = collisionsEngine.solveSimultaneous(2, 1, 3, -4, ecurr);
        assertArrayEquals(ans, res);
    }

    @Test
    public void testCollideAndNewSpeeds() {
        paddle1.setX(0);
        paddle1.setY(0);

        puck.setX(40);
        puck.setY(0);

        paddle2.setY(600);
        paddle2.setX(600);

        assertTrue(collisionsEngine.isIntersecting(paddle1, puck));

        paddle1.setXspeed(0);
        paddle1.setYspeed(0);
        paddle1.setSpeeds(true, false, false, false);
        puck.setXspeed(-30);
        puck.setYspeed(50);

        collisionsEngine.collide();

        assertEquals(2, paddle1.getXspeed(), delta);
        assertEquals(0, paddle1.getYspeed(), delta);
        assertEquals(66, puck.getXspeed(), delta);
        assertEquals(50, puck.getYspeed(), delta);

        assertFalse(collisionsEngine.isIntersecting(paddle1, puck));
    }

    @Test
    public void testNotColliding() {
        paddle1.setX(0);
        paddle1.setY(0);

        puck.setX(100);
        puck.setY(0);

        paddle2.setY(600);
        paddle2.setX(600);

        assertFalse(collisionsEngine.isIntersecting(paddle1, puck));

        paddle1.setXspeed(0);
        paddle1.setYspeed(0);
        paddle1.setSpeeds(true, false, false, false);
        puck.setXspeed(-30);
        puck.setYspeed(50);

        collisionsEngine.collide();

        assertEquals(50, paddle1.getXspeed(), delta);
        assertEquals(0, paddle1.getYspeed(), delta);
        assertEquals(-30, puck.getXspeed(), delta);
        assertEquals(50, puck.getYspeed(), delta);
    }

    @Test
    public void testCollide2() {

        paddle2.setX(0);
        paddle2.setY(0);

        puck.setX(0);
        puck.setY(0);

        paddle1.setY(600);
        paddle1.setX(600);

        assertTrue(collisionsEngine.isIntersecting(paddle2, puck));

        paddle2.setXspeed(160);
        paddle2.setYspeed(120);
        puck.setXspeed(-40);
        puck.setYspeed(-30);

        collisionsEngine.collide();

        assertEquals(40, paddle2.getXspeed(), delta);
        assertEquals(120, paddle2.getYspeed(), delta);
        assertEquals(200, puck.getXspeed(), delta);
        assertEquals(-30, puck.getYspeed(), delta);

        assertFalse(collisionsEngine.isIntersecting(paddle2, puck));
    }

}
