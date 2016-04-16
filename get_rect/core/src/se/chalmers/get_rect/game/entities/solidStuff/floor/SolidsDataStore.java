package se.chalmers.get_rect.game.entities.solidStuff.floor;

import se.chalmers.get_rect.game.entities.IDataStore;
import se.chalmers.get_rect.utilities.Point;

/**
 * Created by Simon on 16-04-16.
 */
public class SolidsDataStore implements IDataStore {

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
