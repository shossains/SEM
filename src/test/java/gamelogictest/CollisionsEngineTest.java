package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.audio.Sound;
import gamelogic.CollisionsEngine;
import gamelogic.Direction;
import gamelogic.Paddle;
import gamelogic.PlayerType;
import gamelogic.Puck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoring.BasicScoringSystem;
import scoring.Board;
import scoring.Goal;

public class CollisionsEngineTest {

    private transient Paddle paddlec1;
    private transient Paddle paddlec2;
    private transient Puck puck;

    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient Paddle paddle3;
    private transient Paddle paddle4;

    private transient Board board1;
    private transient BasicScoringSystem basicScoringSystem;
    private transient Goal goalOne;
    private transient Goal goalTwo;
    private transient float coeffRestitution = 0.85f;

    private transient Puck puck1, puck2, puck3;
    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 10;

    private transient CollisionsEngine collisionsEngine;

    //this is the degree of tolerance (for checking angles between calculator and methods)
    private final transient double delta = 0.000001;

    @BeforeEach
    void setupTestEnvironment() {
        paddlec1 = new Paddle(0, 0, 3, 4, 25, 2, 1280, 720,
                PlayerType.PLAYER1, 200, 6, 50);
        paddlec2 = new Paddle(4, 3, 0, 0, 25, 2, 1280, 720,
                PlayerType.PLAYER2, 200, 6, 50);

        puck = new Puck(30, 0, 0, 0, 15, 1, 1280, 720);

        puck1 = new Puck(10, 100, 0, 100, 15,
                10, 1280, 720);
        puck2 = new Puck(640, 360, 64, 36, 15,
                10, 1280, 720);
        puck3 = new Puck(640, 360, -64, -36, 15,
                10, 1280, 720);
        collisionsEngine = new CollisionsEngine(0.8f, 0.85f, mock(Sound.class));

        basicScoringSystem = mock(BasicScoringSystem.class);

        goalOne = new Goal((720 / 3), 2 * (720 / 3),
                15, basicScoringSystem, PlayerType.PLAYER1);
        goalTwo = new Goal((720 / 3), 2 * (720 / 3),
                (1280 - 15), basicScoringSystem, PlayerType.PLAYER2);

        board1 = new Board(0, 0, 1280, 720, goalOne, goalTwo);

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
    public void testDistanceAndIntersection() {
        paddlec2.setX(40);
        paddlec2.setY(30);

        double distance = collisionsEngine.distance(paddlec1, paddlec2);
        assertEquals(50, distance);

        assertTrue(collisionsEngine.isIntersecting(paddlec1, paddlec2));
    }

    @Test
    public void testDistanceAndIntersectionFalse() {
        paddlec2.setX(80);
        paddlec2.setY(60);

        double distance = collisionsEngine.distance(paddlec1, paddlec2);
        assertEquals(100, distance);

        assertFalse(collisionsEngine.isIntersecting(paddlec1, paddlec2));
    }

    @Test
    public void testAngleBetween() {
        //System.out.println(collisionsEngine.angleBetween(paddle1, paddle2));
        assertEquals(collisionsEngine.angleBetween(paddlec1, paddlec2), .6435011, delta);

        System.out.println(Math.cos(1.6));
        System.out.println(Math.cos(3.2));
    }

    @Test
    public void testAnglebetween2() {
        paddlec2.setY(0);
        assertEquals(0, collisionsEngine.angleBetween(paddlec1, paddlec2));
    }

    @Test
    public void testAnglebetween3() {
        paddlec2.setX(-3);
        paddlec2.setY(4);

        assertEquals(2.214297438, collisionsEngine.angleBetween(paddlec1, paddlec2), delta);
    }

    @Test
    public void testAnglebetween4() {
        paddlec2.setX(-3);
        paddlec2.setY(-4);

        assertEquals(-2.2142974355, collisionsEngine.angleBetween(paddlec1, paddlec2), delta);

    }

    @Test
    public void testAnglebetween5() {

        paddlec2.setX(0);
        paddlec2.setY(1);

        assertEquals(1.57079632679, collisionsEngine.angleBetween(paddlec1, paddlec2), delta);

        assertEquals(-1.57079632679, collisionsEngine.angleBetween(paddlec2, paddlec1), delta);

    }

    @Test
    public void obliqueCollide() {
        puck.setXspeed(-4);
        puck.setYspeed(3);

        double theta = collisionsEngine.angleBetween(paddlec1, puck);
        assertEquals(0, theta);

        //double moveAnglePaddle = collisionsEngine.angleOfMovement(paddle1);
        //assertEquals(.927295218, moveAnglePaddle, delta);

        assertEquals(3, collisionsEngine.getispeed(paddlec1, theta));

        //double moveAnglePuck = collisionsEngine.angleOfMovement(puck);
        //assertEquals(-.6435011088, moveAnglePuck, delta);

        assertEquals(-4, collisionsEngine.getispeed(puck, theta));

    }

    @Test
    public void testispeed() {
        paddlec2.setYspeed(0);
        paddlec2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddlec2, 0), delta);
    }

    @Test
    public void testispeed2() {
        paddlec2.setYspeed(4);
        paddlec2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddlec2, 0), delta);
    }

    @Test
    public void testispeed3() {
        paddlec2.setYspeed(0);
        paddlec2.setXspeed(4);

        assertEquals(4, collisionsEngine.getispeed(paddlec2, 0), delta);
        assertEquals(0, collisionsEngine.getjspeed(paddlec2, 0), delta);
    }

    @Test
    public void testSpeeds() {
        paddlec2.setYspeed(4);
        paddlec2.setXspeed(-4);

        paddlec2.setX(4);
        paddlec2.setY(4);

        double theta = collisionsEngine.angleBetween(paddlec1, paddlec2);

        System.out.println(theta);

        float jspeed = collisionsEngine.getjspeed(paddlec2, theta);
        float ispeed = collisionsEngine.getispeed(paddlec2, theta);

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
        paddlec1.setX(0);
        paddlec1.setY(0);

        puck.setX(40);
        puck.setY(0);

        paddlec2.setY(600);
        paddlec2.setX(600);

        assertTrue(collisionsEngine.isIntersecting(paddlec1, puck));

        paddlec1.setXspeed(0);
        paddlec1.setYspeed(0);

        paddlec1.direction = new Direction(false, false, true, false);
        paddlec1.setSpeeds();
        puck.setXspeed(-30);
        puck.setYspeed(50);

        collisionsEngine.collide(paddlec1, puck);

        assertEquals(2, paddlec1.getXspeed(), delta);
        assertEquals(0, paddlec1.getYspeed(), delta);
        assertEquals(66, puck.getXspeed(), delta);
        assertEquals(50, puck.getYspeed(), delta);

        assertFalse(collisionsEngine.isIntersecting(paddlec1, puck));
    }

    @Test
    public void testNotColliding() {
        paddlec1.setX(0);
        paddlec1.setY(0);

        puck.setX(100);
        puck.setY(0);

        paddlec2.setY(600);
        paddlec2.setX(600);

        assertFalse(collisionsEngine.isIntersecting(paddlec1, puck));

        paddlec1.setXspeed(0);
        paddlec1.setYspeed(0);

        paddlec1.direction = new Direction(false, false, true, false);
        paddlec1.setSpeeds();
        puck.setXspeed(-30);
        puck.setYspeed(50);

        collisionsEngine.collide(paddlec1, puck);

        assertEquals(50, paddlec1.getXspeed(), delta);
        assertEquals(0, paddlec1.getYspeed(), delta);
        assertEquals(-30, puck.getXspeed(), delta);
        assertEquals(50, puck.getYspeed(), delta);
    }

    @Test
    public void testCollide2() {

        paddlec2.setX(0);
        paddlec2.setY(0);

        puck.setX(0);
        puck.setY(0);

        paddlec1.setY(600);
        paddlec1.setX(600);

        assertTrue(collisionsEngine.isIntersecting(paddlec2, puck));

        paddlec2.setXspeed(160);
        paddlec2.setYspeed(120);
        puck.setXspeed(-40);
        puck.setYspeed(-30);

        collisionsEngine.collide(paddlec2, puck);

        assertEquals(40, paddlec2.getXspeed(), delta);
        assertEquals(120, paddlec2.getYspeed(), delta);
        assertEquals(200, puck.getXspeed(), delta);
        assertEquals(-30, puck.getYspeed(), delta);

        assertFalse(collisionsEngine.isIntersecting(paddlec2, puck));
    }

    @Test
    public void collideEntities1() {
        paddlec2.setX(0);
        paddlec2.setY(0);
        puck.setX(0);
        puck.setY(0);

        paddlec2.setXspeed(160);
        paddlec2.setYspeed(120);
        puck.setXspeed(-40);
        puck.setYspeed(-30);

        collisionsEngine.collideEntities(puck, paddlec2);

        assertEquals(40, paddlec2.getXspeed(), delta);
        assertEquals(120, paddlec2.getYspeed(), delta);
        assertEquals(200, puck.getXspeed(), delta);
        assertEquals(-30, puck.getYspeed(), delta);
    }

    @Test
    public void collideEntities2() {
        paddlec2.setX(0);
        paddlec2.setY(0);
        puck.setX(0);
        puck.setY(0);

        paddlec2.setXspeed(160);
        paddlec2.setYspeed(120);
        puck.setXspeed(-40);
        puck.setYspeed(-30);

        collisionsEngine.collideEntities(puck, paddlec2);

        assertEquals(40, paddlec2.getXspeed(), delta);
        assertEquals(120, paddlec2.getYspeed(), delta);
        assertEquals(200, puck.getXspeed(), delta);
        assertEquals(-30, puck.getYspeed(), delta);
    }

    @Test
    public void collideEntities3() {
        paddlec2.setX(0);
        paddlec2.setY(0);

        paddlec2.setXspeed(160);
        paddlec2.setYspeed(120);
        paddlec1.setY(600);
        paddlec1.setX(600);

        collisionsEngine.collideEntities(paddlec1, paddlec2);
        assertEquals(600, paddlec1.x);
    }

    @Test
    public void collideEntities4() {
        puck.setX(0);
        puck.setY(0);
        puck.setXspeed(-40);
        puck.setYspeed(-30);

        collisionsEngine.collideEntities(puck, puck);
        assertEquals(0.0, puck.x);
    }

    @Test
    public void collideEntities5() {
        puck.setX(0);
        puck.setY(0);
        puck.setXspeed(-40);
        puck.setYspeed(-30);
        Board board = new Board(0, 0, 1250, 600, mock(Goal.class), mock(Goal.class));
        collisionsEngine.collideEntities(board, puck);
        assertEquals(15, puck.x);
    }

    @Test
    public void collideEntities6() {
        Goal goal = mock(Goal.class);
        puck.setX(100);
        puck.setY(100);
        Board board1 = new Board(0, 0, 1250, 600, goal, goal);
        Board board2 = new Board(0, 0, 1250, 600, goal, goal);
        collisionsEngine.collideEntities(board1, board2);
        assertEquals(100.0, puck.x);
        assertEquals(100.0, puck.y);
    }


    @Test
    public void testPuckBoundaries1() {
        puck1.move(deltaTime2);

        collisionsEngine.collide(puck1, board1);

        assertEquals(720 - puck1.radius, puck1.y);

        puck1.move(deltaTime1);

        assertEquals(720 - puck1.radius - 500 * coeffRestitution, puck1.y);
    }

    @Test
    public void testPuckBoundaries2() {
        puck2.move(deltaTime2);
        collisionsEngine.collide(puck2, board1);

        assertEquals(705, puck2.y);
        assertEquals(1265, puck2.x);

        puck2.move(deltaTime1);

        assertEquals(1265 - 64 * 5 * coeffRestitution, puck2.x);
        assertEquals(705 - 36 * 5 * coeffRestitution, puck2.y);
    }

    @Test
    public void testPuckBoundaries3() {
        puck3.move(deltaTime2);
        collisionsEngine.collide(puck3, board1);

        assertEquals(15, puck3.y);
        assertEquals(15, puck3.x);

        puck3.move(deltaTime1);

        assertEquals(15 + 64 * 5 * coeffRestitution, puck3.x);
        assertEquals(15 + 36 * 5 * coeffRestitution, puck3.y);
    }

    @Test
    public void testBoundaries1() {
        assertEquals(1000, paddle3.x);
        assertEquals(360, paddle3.y);

        paddle3.direction = new Direction(false, true, false, true);

        paddle3.setSpeeds();

        paddle3.move(deltaTime2);
        //500, -140
        collisionsEngine.collideEntities(board1, paddle3);
        //paddle3.fixPosition();

        assertEquals(paddle3.radius, paddle3.y);
        assertEquals(paddle3.radius + 640, paddle3.x);
    }

    @Test
    public void testBoundaries2() {
        assertEquals(1000, paddle3.x);
        assertEquals(360, paddle3.y);

        paddle3.direction = new Direction(true, false, true, false);

        paddle3.setSpeeds();

        paddle3.move(deltaTime2);

        assertEquals(860, paddle3.y);
        //1500, 860
        collisionsEngine.collideEntities(board1, paddle3);
        //paddle3.fixPosition();

        assertEquals(720 - paddle3.radius, paddle3.y);
        assertEquals(1280 - paddle3.radius, paddle3.x);
    }

    @Test
    public void testBoundaries3() {
        assertEquals(360, paddle4.x);
        assertEquals(360, paddle4.y);

        paddle4.direction = new Direction(true, false, true, false);

        paddle4.setSpeeds();

        paddle4.move(deltaTime2);
        collisionsEngine.collideEntities(board1, paddle4);
        //paddle4.fixPosition();

        assertEquals(640 - paddle4.radius, paddle4.x);
        assertEquals(720 - paddle4.radius, paddle4.y);
    }

    @Test
    public void testBoundaries4() {
        assertEquals(360, paddle4.x);
        assertEquals(360, paddle4.y);

        paddle4.direction = new Direction(false, true, false, true);

        paddle4.setSpeeds();

        paddle4.move(deltaTime2);
        collisionsEngine.collideEntities(board1, paddle4);
        //paddle4.fixPosition();

        assertEquals(paddle4.radius, paddle4.x);
        assertEquals(paddle4.radius, paddle4.y);
    }

    @Test
    public void testGoalPlayer1() {
        puck.setX(0);
        puck.setY(360);

        collisionsEngine.collideEntities(goalOne, puck);
        verify(basicScoringSystem, times(1)).checkScorePlayerTwo();
    }

    @Test
    public void testGoalPlayer2() {
        puck.setX(400);
        puck.setY(360);

        collisionsEngine.collideEntities(goalOne, puck);

        assertEquals(400, puck.x);
        assertEquals(360, puck.y);

    }

    @Test
    public void testGoalPlayer3() {
        puck.setX(1270);
        puck.setY(360);

        collisionsEngine.collideEntities(goalTwo, puck);
        verify(basicScoringSystem, times(1)).checkScorePlayerOne();

    }

    @Test
    public void testGoalPlayer4() {
        puck.setX(0);
        puck.setY(360);

        collisionsEngine.collideEntities(goalTwo, puck);
        verify(basicScoringSystem, times(0)).checkScorePlayerOne();
    }

    @Test
    public void testGoalPlayer5() {
        puck.setX(1270);
        puck.setY(0);

        collisionsEngine.collideEntities(goalTwo, puck);
        verify(basicScoringSystem, times(0)).checkScorePlayerOne();
    }

    @Test
    public void testGoalPlayer6() {
        puck.setX(15);
        puck.setY(0);

        collisionsEngine.collideEntities(goalOne, puck);
        verify(basicScoringSystem, times(0)).checkScorePlayerTwo();
    }

    @Test
    public void testGoalPlayer7() {
        puck.setX(700);
        puck.setY(700);

        collisionsEngine.collideEntities(goalTwo, puck);
        verify(basicScoringSystem, times(0)).checkScorePlayerOne();
    }

}
