package Model;

public class FieldPoint {
    public enum PointType {
        NONE,
        COMMON,
        SUPER,
        OBSTACLE
    }

    int weight = 0;
    PointType type = PointType.COMMON;

    public void setIsObstacle() {
        type = PointType.OBSTACLE;
    }

    public void setIsNone() {
        type = PointType.NONE;
    }

    public PointType getType() {
        return type;
    }
}
