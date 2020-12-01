package project;

import model.Model;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PacmanTest {
    View a = new View();

    @Test
    public void modelInitializationTest() throws IOException {
        a.createParent();
        View.timeline.stop();
        assertEquals(0, Model.score);
        assertFalse(Model.gameOver);
        assertSame(Model.Direction.NEUTRAL, Model.direction);
        assertEquals(14, Model.blinky.currentX);
        assertEquals(11, Model.blinky.currentY);
        assertEquals(14, Model.pacman.currentX);
        assertEquals(17, Model.pacman.currentY);
    }
}
