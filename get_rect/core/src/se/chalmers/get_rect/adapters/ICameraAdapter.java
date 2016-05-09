package se.chalmers.get_rect.adapters;


import se.chalmers.get_rect.utilities.Point;

public interface ICameraAdapter {
    Point getRelativePosition(Point point);

    void translate(float x, float y);
    void translate(Point point);
    void update(double delta);
    void draw(IGraphicsAdapter g);
    Point getPosition();
    float getWidth();
    float getHeight();
}
