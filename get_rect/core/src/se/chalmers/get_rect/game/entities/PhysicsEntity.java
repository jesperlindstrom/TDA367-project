package se.chalmers.get_rect.game.entities;

public class PhysicsEntity extends Entity implements IPhysicsEntity {
    private IPhysicsModel model;

    public PhysicsEntity(IPhysicsModel model, IView view) {
        super(model, view);
        this.model = model;
    }

    @Override
    public IPhysicsModel getModel() {
        return model;
    }
}