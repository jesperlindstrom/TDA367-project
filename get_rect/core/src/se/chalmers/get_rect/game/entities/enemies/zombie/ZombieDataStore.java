package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.utilities.Point;

public class ZombieDataStore {

    private int x;
    private int y;

    public Point getPosition() {
        return new Point(x, y);
    }
}