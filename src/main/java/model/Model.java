package model;

import ghosts.Blinky;
import ghosts.Inky;
import project.Controller;

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
    public static Direction direction = Direction.NEUTRAL;
    public static Pacman pacman = new Pacman();
    public static GameField field = new GameField();
    public static Blinky blinky = new Blinky();
    public static Inky inky = new Inky();
    private static int counter = 0;
    public static boolean gameOver = false;

    public static void classicGame() {
        heroMove();
    }

    public static void heroMove() {
        //счётчик, чтобы пакмен двигался каждые 0,15 секунды
        counter++;
        if (counter % 15 == 0) {
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
    }

    public static void blinkyMove() {
        //счётчик, чтобы блинки двигался каждые 0,2 секунды
        if (counter % 20 == 0) {
            List<FieldPoint> pinkyPath = blinky.findShortestPath(pacman.currentX, pacman.currentY);
            if (pinkyPath.size() > 1) {
                FieldPoint nextStep = pinkyPath.get(1);
                blinky.currentX = nextStep.getX();
                blinky.currentY = nextStep.getY();
            }
        }
    }

    public static void InkyMove() {
        //счётчик, чтобы пинки двигался каждые 0,2 секунды
        if (counter % 20 == 0) {
            FieldPoint nextStep = inky.findNextStep(pacman.currentX, pacman.currentY);
            inky.currentX = nextStep.getX();
            inky.currentY = nextStep.getY();
        }
    }

    public static void gameOverCheck() {
        if (blinky.currentX == pacman.currentX && blinky.currentY == pacman.currentY) {
            Controller.gameOver();
            return;
        }
        if (inky.currentX == pacman.currentX && inky.currentY == pacman.currentY) {
            Controller.gameOver();
            return;
        }
        int commonPoints = 0;
        for (FieldPoint[] points : field.gameField)
            for (FieldPoint point : points) {
                if (point.getType() == FieldPoint.PointType.COMMON || point.getType() == FieldPoint.PointType.SUPER)
                    commonPoints++;
            }
        if (commonPoints == 0) Controller.gameOver();
    }
}
