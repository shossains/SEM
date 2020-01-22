package scoringtest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.audio.Sound;
import gui.GameScreen;
import gui.Hud;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoring.BasicScoringSystem;

public class BasicScoringSystemTest {

    private transient Hud mockHud;
    private transient GameScreen gameScreenMock;
    private transient BasicScoringSystem basicScoringSystem;

    @BeforeEach
    void setUp() {
        this.mockHud = mock(Hud.class);
        this.gameScreenMock = mock(GameScreen.class);
        this.basicScoringSystem = new BasicScoringSystem(mockHud,
                gameScreenMock, mock(Sound.class));
    }

    @AfterEach
    void cleanUp() {
        mockHud.dispose();
        gameScreenMock.dispose();
    }

    @Test
    void constructorTest() {
        assertNotNull(basicScoringSystem);
    }

    @Test
    void checkIfFinishedGameEnded() {
        when(mockHud.getGameTimer()).thenReturn(0);
    }


}
