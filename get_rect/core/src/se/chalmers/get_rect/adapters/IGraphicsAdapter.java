package se.chalmers.get_rect.adapters;

public interface IGraphicsAdapter {
    void draw(String img, float x, float y);
    void start();
    void end();
}
