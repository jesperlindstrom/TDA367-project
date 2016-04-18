package se.chalmers.get_rect.game.entities.worldObjects;

import se.chalmers.get_rect.utilities.Point;

public class WorldObjectDataStore {
    private int x;
    private int y;
    private int width;
    private int height;
    private String type;

    public Point getPosition() {
        return new Point(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }
}
