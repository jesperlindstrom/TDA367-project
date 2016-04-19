package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.game.scenes.IEntityHolder;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractModel implements IModel {
    private Point position;
    private IEntityHolder scene;
    private boolean shouldBeRemoved = false;

    protected AbstractModel(Point position) {
        this.position = position;
    }

    protected void setShouldBeRemoved() {
        shouldBeRemoved = true;
    }

    protected IEntityHolder getScene() {
        return scene;
    }

    protected void setPosition(Point position) {
        this.position = new Point(position);
    }

    @Override
    public Point getPosition() {
        return new Point(position);
    }

    @Override
    public void setScene(IEntityHolder scene) {
        this.scene = scene;
    }

    @Override
    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }

    @Override
    public void update(double delta) {
        // Default: do nothing
    }
}
