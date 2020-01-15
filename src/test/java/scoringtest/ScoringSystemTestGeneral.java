package scoringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.audio.Sound;
import gui.GameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoring.BasicScoringSystem;
import scoring.Hud;

public class ScoringSystemTestGeneral {

    private transient Hud mockHud;
    private transient GameScreen mockGameScreen;
    private transient BasicScoringSystem basicScoringSystem;

    @BeforeEach
    void setUp() {
        this.mockGameScreen = mock(GameScreen.class);
        this.mockHud = mock(Hud.class);
        this.basicScoringSystem = new BasicScoringSystem(mockHud,
                mockGameScreen, mock(Sound.class));
    }

    @Test
    void playerOneWinsTest() {
        basicScoringSystem.scorePlayerOne = 10;
        basicScoringSystem.scorePlayerTwo = 5;

        assertEquals(1, basicScoringSystem.getTheWinner());
    }

    @Test
    void playerTwoWinsTest() {
        basicScoringSystem.scorePlayerOne = 5;
        basicScoringSystem.scorePlayerTwo = 10;

        assertEquals(2, basicScoringSystem.getTheWinner());
    }

    @Test
    void noWinnerTest() {
        basicScoringSystem.scorePlayerOne = 5;
        basicScoringSystem.scorePlayerTwo = 5;

        assertEquals(0, basicScoringSystem.getTheWinner());
    }

}
