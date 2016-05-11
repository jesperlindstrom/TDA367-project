package se.chalmers.get_rect.utilities;


public class Point {
    private int xCoordinate;
    private int yCoordinate;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Point(Point point) {
        this.yCoordinate = point.getY();
        this.xCoordinate = point.getX();
    }


    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public Point setX(int xCoordinate) {

        return new Point(xCoordinate, this.yCoordinate);
    }

    public Point setY(int yCoordinate) {
        return new Point(this.xCoordinate, yCoordinate);
    }

    public Point addX(int x) {
        return new Point(xCoordinate + x, yCoordinate);
    }

    public Point addY(int y) {
        return new Point(xCoordinate, yCoordinate + y);
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

    public int distanceTo(Point p) {
        int xSide = Math.abs(this.getX() - p.getX());
        int ySide = Math.abs(this.getY() - p.getY());

        // Pythagoras, c = sqrt(a^2+b^2)
        return (int)Math.sqrt((xSide * xSide) + (ySide * ySide));
    }

    public Point multiply(double factor){
        return new Point((int)(xCoordinate * factor),(int)(yCoordinate * factor));

    }

    public Point normalize() {
        int x = xCoordinate < 0 ? -1 : xCoordinate == 0 ? 0 : 1;
        int y = yCoordinate < 0 ? -1 : yCoordinate == 0 ? 0 : 1;

        return new Point(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!obj.getClass().equals(getClass()))
            return false;

        Point p2 = (Point)obj;

        return getX() == p2.getX() && getY() == p2.getY();
    }

    @Override
    public int hashCode() {
        return xCoordinate*35729 + yCoordinate*36571;
    } // If we get a background that is larger than 35729 we need to change these values
}
