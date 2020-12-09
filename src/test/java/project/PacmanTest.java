package project;

import model.GameField;
import model.Model;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PacmanTest {
    View a = new View();

    @Test
    public void modelInitializationTest() throws IOException {
        a.createParent();
        View.timeline.stop();
        assertEquals(1, View.timeline.getKeyFrames().size());
        View.timeline.getKeyFrames().remove(0);
        assertEquals(0, Model.score);
        assertFalse(Model.gameOver);
        assertSame(Model.Direction.NEUTRAL, Model.direction);
        assertEquals(14, Model.blinky.currentX);
        assertEquals(11, Model.blinky.currentY);
        assertEquals(14, Model.pacman.currentX);
        assertEquals(17, Model.pacman.currentY);
    }

    @Test
    public void inkyNextStepTest() throws IOException {
        a.createParent();
        View.timeline.stop();
        View.timeline.getKeyFrames().remove(0);
        assertEquals(13, Model.inky.currentX);
        assertEquals(11, Model.inky.currentY);
        assertEquals(14, Model.pacman.currentX);
        assertEquals(17, Model.pacman.currentY);
        assertEquals(Model.field.gameField[11][12], Model.inky.findNextStep(Model.pacman.currentX, Model.pacman.currentY));
        assertEquals(0, Model.counter);
        Model.counter = 20;
        Model.inkyMove();
        assertEquals(12, Model.inky.currentX);
        assertEquals(11, Model.inky.currentY);
        assertEquals(Model.field.gameField[11][11], Model.inky.findNextStep(Model.pacman.currentX, Model.pacman.currentY));
        Model.inkyMove();
        assertEquals(Model.field.gameField[11][10], Model.inky.findNextStep(Model.pacman.currentX, Model.pacman.currentY));
        Model.inkyMove();
        assertEquals(Model.field.gameField[11][9], Model.inky.findNextStep(Model.pacman.currentX, Model.pacman.currentY));
        Model.inkyMove();
        assertEquals(Model.field.gameField[12][9], Model.inky.findNextStep(Model.pacman.currentX, Model.pacman.currentY));
        Model.inkyMove();
        assertEquals(9, Model.inky.currentX);
        assertEquals(12, Model.inky.currentY);
    }
}
