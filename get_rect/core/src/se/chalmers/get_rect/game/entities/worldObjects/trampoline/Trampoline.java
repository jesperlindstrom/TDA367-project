package se.chalmers.get_rect.game.entities.worldObjects.trampoline;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public class Trampoline implements IPhysicsModel {
    private static final int WIDTH = 270;
    private static final int HEIGHT = 75;
    private Point position;
    private Point velocity;
    private IRectangleAdapter boundingBox;
    private boolean fly = false;

    public Trampoline(Point position, IRectangleFactoryAdapter rectangleFactory) {
        this.position = position;
        this.velocity = new Point(0, 0);
        boundingBox = rectangleFactory.make(position.add(70, 20), WIDTH, HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (collisionSide.top() && otherObject.getClass().equals(Player.class)) {
            otherObject.setVelocity(otherObject.getVelocity().setY(150));
        }
    }

    @Override
    public void setPosition(Point position) {
        this.position = new Point(position);
        boundingBox.setPosition(position.add(70, 20));
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = new Point(velocity);
    }

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
