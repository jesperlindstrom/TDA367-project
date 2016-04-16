package se.chalmers.get_rect.game.entities.worldObjects.floor;

import se.chalmers.get_rect.game.entities.IDataStore;
import se.chalmers.get_rect.utilities.Point;

public class WorldObjectDataStore implements IDataStore {

    private int x;
    private int y;
    private int width;
    private int height;
    private String path;

    @Override
    public Point getPosition() {
        return new Point(x, y);
    }

    public int getWidth() {
        return width;
    }
}
