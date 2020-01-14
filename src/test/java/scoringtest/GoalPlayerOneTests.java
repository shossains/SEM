package scoringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.audio.Sound;
import gamelogic.Puck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scoring.BasicScoringSystem;
import scoring.Hud;

public class GoalPlayerOneTests {

    private transient Puck puck;
    private transient Hud mockHud;
    private transient BasicScoringSystem basicScoringSystem;

    @BeforeEach
    void setUp() {
        this.puck = new Puck(360f, 360f, 0, 0, 15, 1,
                1280, 720, 0.85f, mock(Sound.class));
        this.mockHud = mock(Hud.class);
        this.basicScoringSystem = new BasicScoringSystem(puck, mockHud, mock(Sound.class));
    }

    @AfterEach
    void cleanUp() {
        mockHud.dispose();
    }

    @Test
    void goalPlayerOneTest() {
        puck.setX(1265);
        puck.setY(300);
        assertEquals(1, basicScoringSystem.goal());
    }

    @Test
    void noGoalPlayerOneTest1() {
        puck.setX(1265);
        puck.setY(700);
        assertEquals(0, basicScoringSystem.goal());
    }

    @Test
    void noGoalPlayerOneTest2() {
        puck.setX(700);
        puck.setY(300);
        assertEquals(0, basicScoringSystem.goal());
    }

    @Test
    void noGoalPlayerOneTest3() {
        puck.setX(0);
        puck.setY(0);
        assertEquals(0, basicScoringSystem.goal());
    }

    @Test
    void noGoalPlayerOneTest4() {
        puck.setX(1265);
        puck.setY(0);
        assertEquals(0, basicScoringSystem.goal());
    }
}
