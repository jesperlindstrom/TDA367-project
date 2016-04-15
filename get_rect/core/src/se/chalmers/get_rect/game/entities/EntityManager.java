package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<IEntity> list;

    /**
     * Create a new entity manager
     */
    public EntityManager() {
        list = new ArrayList<>();
    }

    /**
     * Add an entity to the list
     * @param entity
     */
    public void add(IEntity entity) {
        list.add(entity);
    }

    /**
     * Update all entities
     */
    public void update() {
        for (IEntity entity : list) {
            entity.update();
        }
    }

    /**
     * Draw all entities
     * @param graphics
     */
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : list) {
            entity.draw(graphics);
        }
    }
}