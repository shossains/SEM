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

import scoring.BasicScoringSystem;
import scoring.Hud;



public class BasicScoringSystemTest {

    private transient Puck puck;
    private transient Hud mockHud;
    private transient BasicScoringSystem basicScoringSystem;

    @BeforeEach
    void setUp() {
        this.puck = new Puck(360, 360, 0, 0, 15, 1, 1280, 720, 0.85f);
        this.mockHud = mock(Hud.class);
        this.basicScoringSystem = new BasicScoringSystem(puck, mockHud);
    }

    @AfterEach
    void cleanUp() {
        mockHud.dispose();
    }

    @Test
    void constructorTest() {
        assertNotNull(basicScoringSystem);
    }

    @Test
    void checkIfFinishedGameEnded() {
        when(mockHud.getGameTimer()).thenReturn(0);
        assertTrue(basicScoringSystem.checkIfGameEnded());
    }

    @Test
    void checkIfGameInProgressEnded() {
        when(mockHud.getGameTimer()).thenReturn(100);
        assertFalse(basicScoringSystem.checkIfGameEnded());
    }

    @Test
    void checkIfPlayerOneWonTheGameTrue() {
        when(mockHud.getScoreOne()).thenReturn(11);
        assertTrue(basicScoringSystem.checkScorePlayerOne());
    }

    @Test
    void checkIfPlayerOneWonTheGameFalse() {
        when(mockHud.getScoreOne()).thenReturn(5);
        assertFalse(basicScoringSystem.checkScorePlayerOne());
    }

    @Test
    void checkIfPlayerTwoWonTheGameTrue() {
        when(mockHud.getScoreTwo()).thenReturn(11);
        assertTrue(basicScoringSystem.checkScorePlayerTwo());
    }

    @Test
    void checkIfPlayerTwoWonTheGameFalse() {
        when(mockHud.getScoreTwo()).thenReturn(2);
        assertFalse(basicScoringSystem.checkScorePlayerTwo());
    }

    @Test
    void getTheWinnerOne() {
        when(mockHud.getScoreOne()).thenReturn(10);
        when(mockHud.getScoreTwo()).thenReturn(5);
        assertEquals(1, basicScoringSystem.getTheWinner());
    }

    @Test
    void getTheWinnerTwo() {
        when(mockHud.getScoreOne()).thenReturn(5);
        when(mockHud.getScoreTwo()).thenReturn(10);
        assertEquals(2, basicScoringSystem.getTheWinner());
    }

    @Test
    void getTheWinnerTie() {
        when(mockHud.getScoreOne()).thenReturn(5);
        when(mockHud.getScoreTwo()).thenReturn(5);
        assertEquals(0, basicScoringSystem.getTheWinner());
    }


}
