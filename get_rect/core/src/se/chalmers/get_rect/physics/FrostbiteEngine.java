package se.chalmers.get_rect.physics;

import java.util.ArrayList;
import java.util.List;

public class FrostbiteEngine implements IPhysicsEngine {
    private List<ISolidObject> entities;

    public FrostbiteEngine() {
        entities = new ArrayList<>();
    }

    public FrostbiteEngine(List<ISolidObject> entities){
        this.entities = entities;
    }

    public void add(ISolidObject entity) {
        entities.add(entity);
    }

    @Override
    public void addAll(List<ISolidObject> newEntities) {
        entities.addAll(newEntities);
    }

    @Override
    public void update(long delta) {
        for (ISolidObject entity1 : entities) {
            for(ISolidObject entity2 : entities) {
                if (entity1.getBoundingBox().intersects(entity2.getBoundingBox())) {
                    entity1.onCollision(entity2);
                }
            }
        }
    }
}