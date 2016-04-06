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
}