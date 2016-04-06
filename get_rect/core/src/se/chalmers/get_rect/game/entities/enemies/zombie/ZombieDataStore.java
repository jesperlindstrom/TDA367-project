package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.io.DataStore;

public class ZombieDataStore implements DataStore<ZombieController> {
    private static ZombieFactory factory = new ZombieFactory();

    private int x;
    private int y;

    @Override
    public ZombieController extract() {
        return factory.make(x, y);
    }

    @Override
    public void compress(ZombieController entity) {

    }
}