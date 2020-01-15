package scoringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import gamelogic.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoring.BasicScoringSystem;
import scoring.Board;
import scoring.Goal;


public class BoardTest {

    transient Board board;
    transient Goal goalOne;
    transient Goal goalTwo;

    @BeforeEach
    void setUp() {
        goalOne = new Goal(240, 480, 15, mock(BasicScoringSystem.class));
        goalTwo = new Goal(240, 480, 1265, mock(BasicScoringSystem.class));
        board = new Board(0, 0, 1280, 720, goalOne, goalTwo);
    }

    @Test
    void constructorTest() {
        Board toCompare = new Board(10f, 20f, 500f, 100f, goalOne, goalTwo);
        assertEquals(new Board(10f, 20f, 500f, 100f, goalOne, goalTwo), toCompare);
        assertEquals(new Board(10f, 20f, 500f, 100f, goalOne, goalTwo).x, toCompare.x);
        assertEquals(new Board(10f, 20f, 500f, 100f, goalOne, goalTwo).y, toCompare.y);
        assertEquals(new Board(10f, 20f, 500f, 100f, goalOne, goalTwo).width, toCompare.width);
        assertEquals(new Board(10f, 20f, 500f, 100f, goalOne, goalTwo).height, toCompare.height);
    }

    @Test
    void getGoalOneTest() {
        assertNotNull(goalOne);
        assertEquals(goalOne, board.getGoalOne());
    }

    @Test
    void getGoalTwoTest() {
        assertNotNull(goalTwo);
        assertEquals(goalTwo, board.getGoalTwo());
    }

    @Test
    void entityTypeTest() {
        assertEquals(EntityType.BOARD, board.getEntityType());
    }
}
