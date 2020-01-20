package gamelogictest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import gamelogic.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuckTest {
    private transient Puck puck1;
    private transient Puck puck2;
    private transient Puck puck3;

    private transient int deltaTime1 = 5;
    private transient int deltaTime2 = 10;
    private transient float coeffRestitution = 0.85f;

    @BeforeEach
    void setupTestEnvironment() {
        puck1 = new Puck.PuckBuilder()
                .atXCoordinate(10f)
                .atYCoordinate(100f)
                .withSpeedX(0f)
                .withSpeedY(100f)
                .withRadius(15f)
                .withMass(10)
                .onWidth(1280)
                .onHeight(720)
                .build();

        puck2 = new Puck.PuckBuilder()
                .atXCoordinate(640f)
                .atYCoordinate(360f)
                .withSpeedX(64f)
                .withSpeedY(36f)
                .withRadius(15f)
                .withMass(10)
                .onWidth(1280)
                .onHeight(720)
                .build();

        puck3 = new Puck.PuckBuilder()
                .atXCoordinate(640f)
                .atYCoordinate(360f)
                .withSpeedX(-64f)
                .withSpeedY(-36f)
                .withRadius(15f)
                .withMass(10)
                .onWidth(1280)
                .onHeight(720)
                .build();

    }

    @Test
    public void testConstructor() {
        assertNotNull(puck1);
    }

    @Test
    public void testGetters() {
        assertEquals(0, puck1.getXspeed());
        assertEquals(100, puck1.getYspeed());
    }

    @Test
    public void testMove1() {
        assertEquals(10, puck1.x);
        assertEquals(100, puck1.y);

        puck1.move(deltaTime1);

        assertEquals(10, puck1.x);
        assertEquals(100 + 500, puck1.y);
    }

}
