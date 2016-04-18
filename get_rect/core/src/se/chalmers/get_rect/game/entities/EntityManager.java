package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.IGameComponent;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements IGameComponent {
    private List<IEntity> list;
    private List<IEntity> addQueue;
    private List<IEntity> removalQueue;
    private boolean inLoop;

    /**
     * Create a new entity manager
     */
    public EntityManager() {
        list = new ArrayList<>();
        addQueue = new ArrayList<>();
        removalQueue = new ArrayList<>();
    }

    /**
     * Add an entity to the list
     * @param entity
     */
    public void add(IEntity entity) {
        if (inLoop) {
            addQueue.add(entity);
        } else {
            list.add(entity);
        }
    }

    /**
     * Update all entities
     */
    @Override
    public void update(double delta) {
        processAdditions();

        inLoop = true;

        for (IEntity entity : list) {
            entity.update();
            checkRemoval(entity);
        }

        inLoop = false;

        processRemovals();
    }

    /**
     * Draw all entities
     * @param graphics
     */
    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : list) {
            entity.draw(graphics);
        }
    }

    private void checkRemoval(IEntity entity) {
        if (entity.getModel().shouldBeRemoved()) {
            removalQueue.add(entity);
        }
    }

    private void processAdditions() {
        if (addQueue.size() > 0) {
            for (IEntity entity : addQueue) {
                list.add(entity);
            }

            addQueue.clear();
        }
    }

    private void processRemovals() {
        for (IEntity entity : removalQueue) {
            list.remove(entity);
        }

        removalQueue.clear();
    }
}