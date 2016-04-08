package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.utilities.Point;

public interface IModel {
    void setX(int x);
    void setY(int y);
    void setPosition(int x, int y);
    void setPosition(Point point);
    Point getPosition();
}
