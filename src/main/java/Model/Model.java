package Model;

import Ghosts.Pinky;

import java.util.List;

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
    public static Pinky pinky = new Pinky();
    public static boolean gameOver;

    public static void classicGame() {
        heroMove();
    }

    public static void heroMove() {
        switch (direction) {
            case RIGHT:
                if (field.gameField[pacman.currentY][pacman.currentX + 1].getType() != FieldPoint.PointType.OBSTACLE) {
                    pacman.currentX++;
                }
                break;
            case LEFT:
                if (field.gameField[pacman.currentY][pacman.currentX - 1].getType() != FieldPoint.PointType.OBSTACLE) {
                    pacman.currentX--;
                }
                break;
            case UP:
                if (field.gameField[pacman.currentY - 1][pacman.currentX].getType() != FieldPoint.PointType.OBSTACLE) {
                    pacman.currentY--;
                }
                break;
            case DOWN:
                if (field.gameField[pacman.currentY + 1][pacman.currentX].getType() != FieldPoint.PointType.OBSTACLE) {
                    pacman.currentY++;
                }
                break;
        }
        field.eatFood(pacman.currentY, pacman.currentX);
    }

    public static void pinkyMove() {
        List<FieldPoint> pinkyPath = pinky.findShortestPath(pacman.currentX, pacman.currentY);
        if (pinkyPath.size() > 1) {
            FieldPoint nextStep = pinkyPath.get(1);
            pinky.currentX = nextStep.getX();
            pinky.currentY = nextStep.getY();
        }
    }
}
