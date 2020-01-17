package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gamelogic.Direction;
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
    private transient int deltaTime2 = 10;

    @BeforeEach
    void setupTestEnvironment() {
        paddle1 = new Paddle(100, 100, 0, 0, 15, 10, 1280, 720,
                PlayerType.PLAYER1, 200, 6, 50);
        paddle2 = new Paddle(300, 200, 0, 0, 15, 10, 1280, 720,
                PlayerType.PLAYER2, 200, 6, 50);

        paddle3 = new Paddle(1000f, 360f, 0f, 0f, 40f, 10, 1280, 720,
                PlayerType.PLAYER1, 200, 6, 50);
        paddle4 = new Paddle(360, 360f, 0f, 0f, 40f, 10, 1280, 720,
                PlayerType.PLAYER2, 200, 6, 50);
    }

    @Test
    public void testConstructor() {
        assertNotNull(paddle1);
    }

    @Test
    public void testMoveAndSetSpeeds1() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.direction = new Direction(true, false, true, false);

        paddle1.setSpeeds();

        paddle1.move(deltaTime2);
        assertEquals(600, paddle1.x);
        assertEquals(600, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds2() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.direction = new Direction(false, true, false, true);

        paddle1.setSpeeds();

        paddle1.move(deltaTime1);
        assertEquals(-150, paddle1.x);
        assertEquals(-150, paddle1.y);
    }

    @Test
    public void testMoveAndSetSpeeds3() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.direction = new Direction(true, true, true, true);

        paddle1.setSpeeds();

        paddle1.move(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testMove4() {
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);

        paddle1.direction = new Direction(false, false, false, false);

        paddle1.setSpeeds();

        paddle1.move(deltaTime1);
        assertEquals(100, paddle1.x);
        assertEquals(100, paddle1.y);
    }

    @Test
    public void testSetRightSpeed() {
        paddle1.setXspeed(100);

        paddle1.setRightSpeed(true, false);
        assertEquals(106, paddle1.getXspeed());
    }

    @Test
    public void testSetLeftSpeed() {
        paddle1.setXspeed(-100);

        paddle1.setLeftSpeed(false, true);
        assertEquals(-106, paddle1.getXspeed());
    }

    @Test
    public void testSetUpwardsSpeed() {
        paddle1.setYspeed(100);

        paddle1.setUpwardsSpeed(true, false);
        assertEquals(106, paddle1.getYspeed());
    }

    @Test
    public void testSetDownwardsSpeed() {
        paddle1.setYspeed(-100);

        paddle1.setDownwardsSpeed(false, true);
        assertEquals(-106, paddle1.getYspeed());
    }

    @Test
    public void testSpeedsMaxPositive() {
        paddle1.setXspeed(200);
        paddle1.setYspeed(200);

        paddle1.setLateralSpeeds(true, false);
        paddle1.setVerticalSpeeds(true, false);

        assertEquals(200, paddle1.getXspeed());
        assertEquals(200, paddle1.getYspeed());
    }

    @Test
    public void testSpeedsMaxNegative() {
        paddle1.setXspeed(-200);
        paddle1.setYspeed(-200);

        paddle1.setLateralSpeeds(false, true);
        paddle1.setVerticalSpeeds(false, true);

        assertEquals(-200, paddle1.getXspeed());
        assertEquals(-200, paddle1.getYspeed());
    }


}
