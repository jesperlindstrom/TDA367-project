package se.chalmers.get_rect.adapters;

public interface IGraphicsAdapter {
    void draw(String img, float x, float y);
    void draw(String img, float x, float y, float width, float height);
    void start();
    void end();
    void clear();
}
