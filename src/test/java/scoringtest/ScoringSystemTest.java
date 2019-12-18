package scoringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import gamelogic.Puck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scoring.Hud;
import scoring.ScoringSystem;


public class ScoringSystemTest {

    private transient Puck puck;
    private transient Hud mockHud;
    private transient ScoringSystem scoringSystem;

    @BeforeEach
    void setUp() {
        this.puck = new Puck(360, 360, 0, 0, 15, 1);
        this.mockHud = mock(Hud.class);
        this.scoringSystem = new ScoringSystem(puck, mockHud);
    }

    @AfterEach
    void cleanUp() {
        mockHud.dispose();
    }

    @Test
    void constructorTest() {
        assertNotNull(scoringSystem);
    }

    @Test
    void checkIfFinishedGameEnded() {
        when(mockHud.getGameTimer()).thenReturn(0);
        assertTrue(scoringSystem.checkIfGameEnded());
    }

    @Test
    void checkIfGameInProgressEnded() {
        when(mockHud.getGameTimer()).thenReturn(100);
        assertFalse(scoringSystem.checkIfGameEnded());
    }

    @Test
    void checkIfPlayerOneWonTheGameTrue() {
        when(mockHud.getScoreOne()).thenReturn(11);
        assertTrue(scoringSystem.checkScorePlayerOne());
    }

    @Test
    void checkIfPlayerOneWonTheGameFalse() {
        when(mockHud.getScoreOne()).thenReturn(5);
        assertFalse(scoringSystem.checkScorePlayerOne());
    }

    @Test
    void checkIfPlayerTwoWonTheGameTrue() {
        when(mockHud.getScoreTwo()).thenReturn(11);
        assertTrue(scoringSystem.checkScorePlayerTwo());
    }

    @Test
    void checkIfPlayerTwoWonTheGameFalse() {
        when(mockHud.getScoreTwo()).thenReturn(2);
        assertFalse(scoringSystem.checkScorePlayerTwo());
    }

    @Test
    void getTheWinnerOne() {
        when(mockHud.getScoreOne()).thenReturn(10);
        when(mockHud.getScoreTwo()).thenReturn(5);
        assertEquals(1, scoringSystem.getTheWinner());
    }

    @Test
    void getTheWinnerTwo() {
        when(mockHud.getScoreOne()).thenReturn(5);
        when(mockHud.getScoreTwo()).thenReturn(10);
        assertEquals(2, scoringSystem.getTheWinner());
    }

    @Test
    void getTheWinnerTie() {
        when(mockHud.getScoreOne()).thenReturn(5);
        when(mockHud.getScoreTwo()).thenReturn(5);
        assertEquals(0, scoringSystem.getTheWinner());
    }


}
