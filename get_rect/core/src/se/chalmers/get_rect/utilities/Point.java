package se.chalmers.get_rect.utilities;

/**
 * Created by simsund on 2016-04-06.
 */
public class Point {
    private int xCoodrinate;
    private int yCoordinate;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.xCoodrinate = x;
        this.yCoordinate = y;
    }

    public Point(Point point) {
        this.yCoordinate = point.getyCoordinate();
        this.xCoodrinate = point.getxCoodrinate();
    }

    public int getxCoodrinate() {
        return xCoodrinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Point setxCoodrinate(int xCoodrinate) {
        return new Point(xCoodrinate, this.yCoordinate);
    }

    public Point setyCoordinate(int yCoordinate) {
        return new Point(this.xCoodrinate, yCoordinate);
    }

    public Point addX(int x) {
        return new Point(xCoodrinate + x, yCoordinate);
    }

    public Point addY(int x) {
        return new Point(xCoodrinate + x, yCoordinate);
    }

    public Point add(Point p1, Point p2) {
        return null;
    }

    public Point setPosition(int x, int y) {
        return new Point(x, y);
    }

    public Point setPosition(Point point) {
        return new Point(point);
    }

    @Override
    public Point clone() {
        return new Point(this);
    }
}
