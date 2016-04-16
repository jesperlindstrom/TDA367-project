package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.game.entities.IDataStore;
import se.chalmers.get_rect.utilities.Point;

public class ZombieDataStore implements IDataStore {

    private int x;
    private int y;

    @Override
    public Point getPosition() {
        return new Point(x ,y);
    }
}