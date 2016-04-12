package se.chalmers.get_rect.utilities;


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
        this.yCoordinate = point.getY();
        this.xCoodrinate = point.getX();
    }

    public int getX() {
        return xCoodrinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public Point setX(int xCoodrinate) {
        return new Point(xCoodrinate, this.yCoordinate);
    }

    public Point setY(int yCoordinate) {
        return new Point(this.xCoodrinate, yCoordinate);
    }

    public Point addX(int x) {
        return new Point(xCoodrinate + x, yCoordinate);
    }

    public Point addY(int y) {
        return new Point(xCoodrinate, yCoordinate + y);
    }

    public Point add(Point p2) {
        return new Point(this.getX() + p2.getX(), this.getY() + p2.getY());
    }

    public Point add(int x, int y) {
        return new Point(this.getX() + x, this.getY() + y);
    }

    public Point subtract(Point p2) {
        return new Point(this.getX() - p2.getX(), this.getY() - p2.getY());
    }

    public Point subtract(int x, int y) {
        return new Point(this.getX() - x, this.getY() - y);
    }

    public Point setPosition(int x, int y) {
        return new Point(x, y);
    }

    public Point setPosition(Point point) {
        return new Point(point);
    }

    public int deltaX(Point point){
        return new Point(this.subtract(point)).getX();
    }

    public int deltaY(Point point){
        return new Point(this.subtract(point)).getY();
    }

    public Point inverse(){
        return new Point(-this.getX(),-this.getY());
    }

    public String toString(){
        return ("X = " + getX() + " Y = " + getY());
    }

    public Point vector(Point p) {
        return p.subtract(this);
    }

    public Point multiply(double factor){
        return new Point((int)(xCoodrinate * factor),(int)(yCoordinate * factor));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Point p2 = (Point)obj;
            if (this.getX() == p2.getX() && this.getY() == p2.getY()) {
                return true;
            }
        }
        return false;
    }
}
