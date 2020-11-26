package Ghosts;

import Model.GameField;
import Model.Model;
import Model.FieldPoint;

import java.util.*;

public class Blinky {
    public int currentX = 14;
    public int currentY = 11;
    List<FieldPoint> path = new ArrayList<>();
    GameField field = Model.field;

    public List<FieldPoint> findShortestPath(int pacmanX, int pacmanY) {
        path = new ArrayList<>();
        FieldPoint end = pathCheck(field.gameField[currentY][currentX], pacmanX, pacmanY);
        path = pathFromWeights(end);
        for (int k = 0; k < 31; k++) {
            for (int j = 0; j < 28; j++) {
                field.gameField[k][j].setWeight(Integer.MAX_VALUE);
            }
        }
        return path;
    }

    //волновое задание весов пути до точки
    private FieldPoint pathCheck(FieldPoint start, int pacmanX, int pacmanY) {
        Queue<FieldPoint> fieldsToVisit = new ArrayDeque<>();
        start.setWeight(0);
        FieldPoint currentPosition = start;
        int weight = currentPosition.getWeight();
        while (currentPosition.getX() != pacmanX || currentPosition.getY() != pacmanY) {

            if (!field.gameField[currentPosition.getY() + 1][currentPosition.getX()].isObstacle()
                    && field.gameField[currentPosition.getY() + 1][currentPosition.getX()].getWeight() == Integer.MAX_VALUE) {
                field.gameField[currentPosition.getY() + 1][currentPosition.getX()].setWeight(weight + 1);
                fieldsToVisit.add(field.gameField[currentPosition.getY() + 1][currentPosition.getX()]);
            }
            if (currentPosition.getY() != 0)
                if (!field.gameField[currentPosition.getY() - 1][currentPosition.getX()].isObstacle()
                        && field.gameField[currentPosition.getY() - 1][currentPosition.getX()].getWeight() == Integer.MAX_VALUE) {
                    field.gameField[currentPosition.getY() - 1][currentPosition.getX()].setWeight(weight + 1);
                    fieldsToVisit.add(field.gameField[currentPosition.getY() - 1][currentPosition.getX()]);
                }
            if (!field.gameField[currentPosition.getY()][currentPosition.getX() + 1].isObstacle()
                    && field.gameField[currentPosition.getY()][currentPosition.getX() + 1].getWeight() == Integer.MAX_VALUE) {
                field.gameField[currentPosition.getY()][currentPosition.getX() + 1].setWeight(weight + 1);
                fieldsToVisit.add(field.gameField[currentPosition.getY()][currentPosition.getX() + 1]);
            }
            if (currentPosition.getX() != 0)
                if (!field.gameField[currentPosition.getY()][currentPosition.getX() - 1].isObstacle()
                        && field.gameField[currentPosition.getY()][currentPosition.getX() - 1].getWeight() == Integer.MAX_VALUE) {
                    field.gameField[currentPosition.getY()][currentPosition.getX() - 1].setWeight(weight + 1);
                    fieldsToVisit.add(field.gameField[currentPosition.getY()][currentPosition.getX() - 1]);
                }
            currentPosition = fieldsToVisit.remove();
            weight = currentPosition.getWeight();
        }
        return currentPosition;
    }

    //разворот волнового алгоритма в путь
    private List<FieldPoint> pathFromWeights(FieldPoint end) {
        List<FieldPoint> path = new ArrayList<>();
        FieldPoint currentPosition = end;
        path.add(end);
        int weight;
        while (currentPosition.getWeight() != 0) {
            weight = currentPosition.getWeight();
            if (field.gameField[currentPosition.getY() + 1][currentPosition.getX()].getWeight() < weight) {
                currentPosition = field.gameField[currentPosition.getY() + 1][currentPosition.getX()];
                path.add(currentPosition);
                weight = currentPosition.getWeight();
            }
            if (currentPosition.getY() != 0)
                if (field.gameField[currentPosition.getY() - 1][currentPosition.getX()].getWeight() < weight) {
                    currentPosition = field.gameField[currentPosition.getY() - 1][currentPosition.getX()];
                    path.add(currentPosition);
                    weight = currentPosition.getWeight();
                }
            if (field.gameField[currentPosition.getY()][currentPosition.getX() + 1].getWeight() < weight) {
                currentPosition = field.gameField[currentPosition.getY()][currentPosition.getX() + 1];
                path.add(currentPosition);
                weight = currentPosition.getWeight();
            }
            if (currentPosition.getX() != 0)
                if (field.gameField[currentPosition.getY()][currentPosition.getX() - 1].getWeight() < weight) {
                    currentPosition = field.gameField[currentPosition.getY()][currentPosition.getX() - 1];
                    path.add(currentPosition);
                }
        }
        Collections.reverse(path);
        return path;
    }
}