package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.io.DataStore;

public class ZombieDataStore implements DataStore<ZombieController> {

    @Override
    public ZombieController extract() {
        return null;
    }

    @Override
    public void compress(ZombieController entity) {

    }
}