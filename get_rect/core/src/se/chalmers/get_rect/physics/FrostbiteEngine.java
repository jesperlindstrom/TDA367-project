package se.chalmers.get_rect.physics;

import java.util.List;

public class FrostbiteEngine implements IPhysicsEngine {
    private List<ISolidObject> entities;

    public FrostbiteEngine(List<ISolidObject> entities){
        this.entities = entities;
    }
}