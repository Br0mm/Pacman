package model;

public class FieldPoint {
    private final int x;
    private final int y;
    int weight = Integer.MAX_VALUE;
    PointType type = PointType.COMMON;
    private boolean isObstacle = false;

    FieldPoint (int x, int y) {
        this.x = x;
        this.y = y;
    }


    public enum PointType {
        NONE,
        COMMON,
        SUPER,
        OBSTACLE
    }

    public void setIsObstacle() {
        type = PointType.OBSTACLE;
        isObstacle = true;
    }

    public void setIsNone() {
        type = PointType.NONE;
    }

    public PointType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
