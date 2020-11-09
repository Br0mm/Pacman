package Model;

import java.io.*;
import java.util.List;

public class GameField {
    public GameField() {
        createGameField();
        createWalls();
        try {
            createObstacles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum TypeOfPoint {
        NONE,
        COMMON,
        SUPER,
        OBSTACLE
    }

    public TypeOfPoint[][] gameField;

    private void createGameField() {
        gameField = new TypeOfPoint[31][28];
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 28; j++) {
                gameField[i][j] = TypeOfPoint.COMMON;
            }
        }
    }

    private void createWalls() {
        for (int j = 0; j < 28; j++) {
            gameField[0][j] = TypeOfPoint.OBSTACLE;
        }
        for (int j = 0; j < 28; j++) {
            gameField[30][j] = TypeOfPoint.OBSTACLE;
        }
        for (int i = 0; i < 31; i++) {
            gameField[i][0] = TypeOfPoint.OBSTACLE;
        }
        for (int i = 0; i < 31; i++) {
            gameField[i][27] = TypeOfPoint.OBSTACLE;
        }
    }

    private void createObstacles() throws IOException {
        String data;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input/field.txt"))))) {
            data = reader.readLine();
        }
        String[] coordinates = data.split(";");
        for (String coordinate : coordinates) {
            createObstacle(coordinate);
        }
    }

    private void createObstacle(String coordinatesOfObstacle) {
        String[] indexes = coordinatesOfObstacle.split(",");
        for (int i = Integer.parseInt(indexes[0]); i <= Integer.parseInt(indexes[1]); i++) {
            for (int j = Integer.parseInt(indexes[2]); j <= Integer.parseInt(indexes[3]); j++) {
                gameField[i][j] = TypeOfPoint.OBSTACLE;
            }
        }
    }

    public void eatFood(int y, int x) {
        gameField[y][x] = TypeOfPoint.NONE;
    }
}
