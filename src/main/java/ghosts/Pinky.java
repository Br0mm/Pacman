package ghosts;

import model.FieldPoint;
import model.GameField;
import model.Model;

public class Pinky {

    public int currentX = 5;
    public int currentY = 5;
    private final GameField field = Model.field;
    private Direction direction = Direction.LEFT;
    private Direction pacmanDirection = Direction.LEFT;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public FieldPoint findNextStep(int pacmanX, int pacmanY) {
        switch (Model.direction) {
            case DOWN:
                pacmanDirection = Direction.DOWN;
                break;
            case LEFT:
                pacmanDirection = Direction.LEFT;
                break;
            case UP:
                pacmanDirection = Direction.UP;
                break;
            case RIGHT:
                pacmanDirection = Direction.RIGHT;
        }
        FieldPoint targetField = targetField(field.gameField[pacmanY][pacmanX]);
        crossroads(field.gameField[currentY][currentX], targetField.getX(), targetField.getY());
        turns(field.gameField[currentY][currentX]);
        return nextStep(field.gameField[currentY][currentX]);
    }

    private void crossroads(FieldPoint start, int targetX, int targetY) {
        double stepUpDistance = Double.MAX_VALUE;
        double stepDownDistance = Double.MAX_VALUE;
        double stepLeftDistance = Double.MAX_VALUE;
        double stepRightDistance = Double.MAX_VALUE;
        if (start.getWays() > 2) {
            if (!field.gameField[start.getY() - 1][start.getX()].isObstacle())
                stepUpDistance = Math.sqrt(((start.getY() - 1) - targetY) * ((start.getY() - 1) - targetY)
                        + (start.getX() - targetX) * (start.getX() - targetX));
            if (!field.gameField[start.getY() + 1][start.getX()].isObstacle())
                stepDownDistance = Math.sqrt(((start.getY() + 1) - targetY) * ((start.getY() + 1) - targetY)
                        + (start.getX() - targetX) * (start.getX() - targetX));
            if (!field.gameField[start.getY()][start.getX() - 1].isObstacle())
                stepLeftDistance = Math.sqrt((start.getY() - targetY) * (start.getY() - targetY)
                        + ((start.getX() - 1) - targetX) * ((start.getX() - 1) - targetX));
            if (!field.gameField[start.getY()][start.getX() + 1].isObstacle())
                stepRightDistance = Math.sqrt((start.getY() - targetY) * (start.getY() - targetY)
                        + ((start.getX() + 1) - targetX) * ((start.getX() + 1) - targetX));
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

    private FieldPoint targetField(FieldPoint start) {
        switch (pacmanDirection) {
            case UP:
                if (start.getY() - 4 < 0) return field.gameField[0][start.getX()];
                else return field.gameField[start.getY() - 4][start.getX()];
            case LEFT:
                if (start.getX() - 4 < 0) return field.gameField[start.getY()][0];
                else return field.gameField[start.getY()][start.getX() - 4];
            case DOWN:
                if (start.getY() + 4 > 30) return field.gameField[30][start.getX()];
                else return field.gameField[start.getY() + 4][start.getX()];
            default:
                if (start.getX() + 4 > 27) return field.gameField[start.getY()][27];
                else return field.gameField[start.getY()][start.getX() + 4];
        }
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
