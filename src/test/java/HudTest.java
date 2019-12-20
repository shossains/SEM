/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class HudTest {

    private transient MyGdxGame game;
    private transient GUI.Hud hud;
    private transient GUI.Hud hud1;
    private transient SpriteBatch mockedSpriteBatch;


    private transient int deltaShort = 1;
    private transient int deltaLong = 5;

    @BeforeEach
    void setUp() {
        mockedSpriteBatch = mock(SpriteBatch.class);
        hud = new GUI.Hud(mockedSpriteBatch);
        hud1 = Mockito.spy(hud);
    }

    @Test
    void constructorTest() {
        GUI.Hud testHud = new GUI.Hud(new SpriteBatch());
        assertNotNull(hud);
        assertEquals(hud, testHud);
        assertEquals(240, hud.getGameTimer());
        assertEquals(0, hud.getScoreOne());
        assertEquals(0, hud.getScoreTwo());
    }

    @Test
    void updateTimeShortTest() {
        assertEquals(240, hud.getGameTimer());
        hud.updateTime(deltaShort);
        assertEquals(239, hud.getGameTimer());
    }

    @Test
    void updateTimeLongTest() {
        assertEquals(240, hud.getGameTimer());
        hud.updateTime(deltaLong);
        assertEquals(235, hud.getGameTimer());
    }
}
*/
