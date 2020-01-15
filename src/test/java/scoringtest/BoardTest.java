package scoringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoring.Board;
import scoring.Goal;


public class BoardTest {

    transient Goal goalOne;
    transient Goal goalTwo;

    @BeforeEach
    void setUp() {
        goalOne = mock(Goal.class);
        goalTwo = mock(Goal.class);
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
}
