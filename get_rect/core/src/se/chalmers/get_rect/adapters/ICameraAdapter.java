package se.chalmers.get_rect.adapters;


public interface ICameraAdapter {

    void translate(float x, float y);
    void update();
    void draw(IGraphicsAdapter g);
}
