package ghosts;

import model.FieldPoint;
import model.GameField;
import model.Model;

public class Inky {

    public int currentX = 13;
    public int currentY = 11;
    private final GameField field = Model.field;
    private Direction direction = Direction.LEFT;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public FieldPoint findNextStep(int pacmanX, int pacmanY) {
        crossroads(field.gameField[currentY][currentX], pacmanX, pacmanY);
        turns(field.gameField[currentY][currentX]);
        FieldPoint nextStep = nextStep(field.gameField[currentY][currentX]);
        for (int k = 0; k < 31; k++) {
            for (int j = 0; j < 28; j++) {
                field.gameField[k][j].setWeight(Integer.MAX_VALUE);
            }
        }
        return nextStep;
    }

    private void crossroads(FieldPoint start, int pacmanX, int pacmanY) {
        double stepUpDistance = Double.MAX_VALUE;
        double stepDownDistance = Double.MAX_VALUE;
        double stepLeftDistance = Double.MAX_VALUE;
        double stepRightDistance = Double.MAX_VALUE;
        if (start.getWays() > 2) {
            if (!field.gameField[start.getY() - 1][start.getX()].isObstacle())
                stepUpDistance = Math.sqrt(((start.getY() - 1) - pacmanY) * ((start.getY() - 1) - pacmanY)
                        + (start.getX() - pacmanX) * (start.getX() - pacmanX));
            if (!field.gameField[start.getY() + 1][start.getX()].isObstacle())
                stepDownDistance = Math.sqrt(((start.getY() + 1) - pacmanY) * ((start.getY() + 1) - pacmanY)
                        + (start.getX() - pacmanX) * (start.getX() - pacmanX));
            if (!field.gameField[start.getY()][start.getX() - 1].isObstacle())
                stepLeftDistance = Math.sqrt((start.getY() - pacmanY) * (start.getY() - pacmanY)
                        + ((start.getX() - 1) - pacmanX) * ((start.getX() - 1) - pacmanX));
            if (!field.gameField[start.getY()][start.getX() + 1].isObstacle())
                stepRightDistance = Math.sqrt((start.getY() - pacmanY) * (start.getY() - pacmanY)
                        + ((start.getX() + 1) - pacmanX) * ((start.getX() + 1) - pacmanX));
        }
        if (stepUpDistance <= stepDownDistance && stepUpDistance <= stepLeftDistance &&
                stepUpDistance <= stepRightDistance && stepUpDistance != Double.MAX_VALUE && direction != Direction.DOWN) {
            direction = Direction.UP;
        }
        if (stepLeftDistance <= stepDownDistance && stepLeftDistance <= stepUpDistance &&
                stepLeftDistance <= stepRightDistance && stepLeftDistance != Double.MAX_VALUE && direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        }
        if (stepDownDistance <= stepUpDistance && stepDownDistance <= stepLeftDistance &&
                stepDownDistance <= stepRightDistance && stepDownDistance != Double.MAX_VALUE && direction != Direction.UP) {
            direction = Direction.DOWN;
        }
        if (stepRightDistance <= stepDownDistance && stepRightDistance <= stepLeftDistance &&
                stepRightDistance <= stepUpDistance && stepRightDistance != Double.MAX_VALUE && direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        }
    }

    private void turns(FieldPoint start) {
        if (direction == Direction.DOWN && field.gameField[start.getY() + 1][start.getX()].isObstacle())
            if (!field.gameField[start.getY()][start.getX() - 1].isObstacle()) direction = Direction.LEFT;
            else  direction = Direction.RIGHT;

        if (direction == Direction.LEFT && field.gameField[start.getY()][start.getX() - 1].isObstacle())
            if (!field.gameField[start.getY() - 1][start.getX()].isObstacle()) direction = Direction.UP;
            else direction = Direction.DOWN;

        if (direction == Direction.UP && field.gameField[start.getY() - 1][start.getX()].isObstacle())
            if (!field.gameField[start.getY()][start.getX() - 1].isObstacle()) direction = Direction.LEFT;
            else direction = Direction.RIGHT;

        if (direction == Direction.RIGHT && field.gameField[start.getY()][start.getX() + 1].isObstacle())
            if (!field.gameField[start.getY() - 1][start.getX()].isObstacle()) direction = Direction.UP;
            else direction = Direction.DOWN;
    }

    private FieldPoint nextStep(FieldPoint start) {
        switch (direction) {
            case UP:
                return field.gameField[start.getY() - 1][start.getX()];
            case LEFT:
                return field.gameField[start.getY()][start.getX() - 1];
            case DOWN:
                return field.gameField[start.getY() + 1][start.getX()];
            default:
                return field.gameField[start.getY()][start.getX() + 1];
        }
    }
}
