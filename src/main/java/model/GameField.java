package model;

import java.io.*;

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

    public FieldPoint[][] gameField;

    private void createGameField() {
        gameField = new FieldPoint[31][28];
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 28; j++) {
                gameField[i][j] = new FieldPoint(j, i);
            }
        }
    }

    private void createWalls() {
        for (int j = 0; j < 28; j++) {
            gameField[0][j].setIsObstacle();
        }
        for (int j = 0; j < 28; j++) {
            gameField[30][j].setIsObstacle();
        }
        for (int i = 0; i < 31; i++) {
            gameField[i][0].setIsObstacle();
        }
        for (int i = 0; i < 31; i++) {
            gameField[i][27].setIsObstacle();
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
                gameField[i][j].setIsObstacle();
            }
        }
    }

    public void eatFood(int y, int x) {
        gameField[y][x].setIsNone();
    }
}
