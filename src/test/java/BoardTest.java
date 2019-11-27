import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void constructorTest() {
        Board toCompare = new Board(10f, 20f, 500f, 100f);
        assertEquals(new Board(10f, 20f, 500f, 100f), toCompare);
        assertEquals(new Board(10f, 20f, 500f, 100f).x, toCompare.x);
        assertEquals(new Board(10f, 20f, 500f, 100f).y, toCompare.y);
        assertEquals(new Board(10f, 20f, 500f, 100f).width, toCompare.width);
        assertEquals(new Board(10f, 20f, 500f, 100f).height, toCompare.height);
    }
}