package se.chalmers.get_rect.adapters;


import se.chalmers.get_rect.utilities.Point;

public interface IGraphicsAdapter {
    void draw(String img, float x, float y);
    void draw(String img, float x, float y, float width, float height);
    void draw(String img, float x, float y, float width, float height, float offsetX, float offsetY);

    void draw(String img, Point point);
    void draw(String img, Point point, float width, float height);
    void draw(String img, Point point, float width, float height, Point offsetPoint);
    void draw(String img, Point position, Point origin, float scaleX, float scaleY, float rotation);
    void drawText(String text, Point point, Colors color);
    void drawText(String text, Point point);
    void start();
    void end();
    void clear();

    enum Colors{
        RED, WHITE, BLACK
    }

}
