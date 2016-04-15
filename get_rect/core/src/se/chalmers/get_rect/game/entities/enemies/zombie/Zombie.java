package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public class Zombie implements IPhysicsModel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private IModel player;
    private Point position;
    private Point velocity;
    private IRectangleAdapter boundingBox;

    public Zombie(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this.position = point;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.player = player;
        this.velocity = new Point(0, 0);
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, Side side, boolean isSolid) {

    }

    @Override
    public void setPosition(Point point) {
        position = new Point(point);
        boundingBox.setPosition(position);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = new Point(velocity);
    }

    @Override
    public void update() {
        velocity.add(player.getPosition().subtract(position));
    }

    public Point getVelocity() {
        return new Point(velocity);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
