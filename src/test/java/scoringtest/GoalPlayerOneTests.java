package scoringtest;

import static org.mockito.Mockito.mock;

import gui.GameScreen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import scoring.BasicScoringSystem;
import scoring.Hud;


public class GoalPlayerOneTests {

    private transient Hud mockHud;
    private transient GameScreen mockGameScreen;
    private transient BasicScoringSystem basicScoringSystem;

    @BeforeEach
    void setUp() {
        this.mockHud = mock(Hud.class);
        this.mockGameScreen = mock(GameScreen.class);
        this.basicScoringSystem = new BasicScoringSystem(mockHud, mockGameScreen);
    }

    @AfterEach
    void cleanUp() {
        mockHud.dispose();
        mockGameScreen.dispose();
    }


}
