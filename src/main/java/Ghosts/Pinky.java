package Ghosts;

import Model.GameField;
import Model.Model;
import Model.FieldPoint;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Pinky {
    public int currentX = 14;
    public int currentY = 11;
    List<FieldPoint> path = new ArrayList<>();
    GameField field = Model.field;

    public List<FieldPoint> findShortestPath(int pacmanX, int pacmanY) {
        path = new ArrayList<>();
        pathCheck(field.gameField[currentX][currentY], pacmanX, pacmanY);
        //развернуть волновой алгоритм в путь
        for (int k = 0; k < 31; k++) {
            for (int j = 0; j < 28; j++) {
                field.gameField[k][j].setWeight(0);
            }
        }
        return path;
    }

    private FieldPoint pathCheck(FieldPoint start, int pacmanX, int pacmanY) {
        Queue<FieldPoint> fieldsToVisit = new ArrayDeque<>();
        fieldsToVisit.add(start);
        start.setWeight(0);
        int weight;
        FieldPoint currentPosition = start;
        while (currentPosition.getX() != pacmanX && currentPosition.getY() != pacmanY) {
            currentPosition = fieldsToVisit.remove();
            weight = currentPosition.getWeight();
            if (!field.gameField[currentPosition.getY() + 1][currentPosition.getX()].isObstacle()) {
                field.gameField[currentPosition.getY() + 1][currentPosition.getX()].setWeight(weight + 1);
                fieldsToVisit.add(field.gameField[currentPosition.getY() + 1][currentPosition.getX()]);
            }
            if (currentPosition.getY() != 0)
                if (!field.gameField[currentPosition.getY() - 1][currentPosition.getX()].isObstacle()) {
                    field.gameField[currentPosition.getY() - 1][currentPosition.getX()].setWeight(weight + 1);
                    fieldsToVisit.add(field.gameField[currentPosition.getY() - 1][currentPosition.getX()]);
                }
            if (!field.gameField[currentPosition.getY()][currentPosition.getX() + 1].isObstacle()) {
                field.gameField[currentPosition.getY()][currentPosition.getX() + 1].setWeight(weight + 1);
                fieldsToVisit.add(field.gameField[currentPosition.getY()][currentPosition.getX() + 1]);
            }
            if (currentPosition.getX() != 0)
                if (!field.gameField[currentPosition.getY()][currentPosition.getX() - 1].isObstacle()) {
                    field.gameField[currentPosition.getY()][currentPosition.getX() - 1].setWeight(weight + 1);
                    fieldsToVisit.add(field.gameField[currentPosition.getY()][currentPosition.getX() - 1]);
                }
        }
        return currentPosition;
    }
}