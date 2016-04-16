package se.chalmers.get_rect.game.entities.enemies;

import se.chalmers.get_rect.game.entities.IDataStore;
import se.chalmers.get_rect.utilities.Point;

public class EnemyDataStore implements IDataStore {
    private int x;
    private int y;
    private String type;

    @Override
    public Point getPosition() {
        return new Point(x ,y);
    }

    public String getType() {
        return type;
    }
}