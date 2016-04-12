package se.chalmers.get_rect.adapters;


import se.chalmers.get_rect.utilities.Point;

public interface ICameraAdapter {
    void translate(float x, float y);
    void translate(Point point);
    void update(double delta);
    void draw(IGraphicsAdapter g);
    Point getPosition();
}
