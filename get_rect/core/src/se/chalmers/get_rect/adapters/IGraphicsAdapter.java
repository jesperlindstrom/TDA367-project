package se.chalmers.get_rect.adapters;


import se.chalmers.get_rect.utilities.Point;

public interface IGraphicsAdapter {
    void draw(String img, float x, float y);
    void draw(String img, float x, float y, float width, float height);
    void draw(String img, float x, float y, float width, float height, float offsetX, float offsetY);
    void draw(String img, Point point);
    void draw(String img, Point point, float width, float height);
    void drawText(String text, Point point);
    void drawText(String text, int x, int y);
    void start();
    void end();
    void clear();
}
