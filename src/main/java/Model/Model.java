package Model;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NEUTRAL
    }

    public static int score;
    public static Direction direction = Direction.RIGHT;
    public static Pacman pacman = new Pacman();
    public static GameField field = new GameField();
    public static boolean gameOver;

    public static void classicGame() {
        heroMove();
    }

    public static void heroMove() {
        switch (direction) {
            case RIGHT:
                if (field.gameField[pacman.currentY][pacman.currentX + 1] != GameField.TypeOfPoint.OBSTACLE) {
                    pacman.currentX++;
                }
                break;
            case LEFT:
                if (field.gameField[pacman.currentY][pacman.currentX - 1] != GameField.TypeOfPoint.OBSTACLE) {
                    pacman.currentX--;
                }
                break;
            case UP:
                if (field.gameField[pacman.currentY - 1][pacman.currentX] != GameField.TypeOfPoint.OBSTACLE) {
                    pacman.currentY--;
                }
                break;
            case DOWN:
                if (field.gameField[pacman.currentY + 1][pacman.currentX] != GameField.TypeOfPoint.OBSTACLE) {
                    pacman.currentY++;
                }
                break;
        }
        field.eatFood(pacman.currentY, pacman.currentX);
    }
}
