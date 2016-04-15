package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public class Projectile implements IPhysicsModel {
    private Point position;
    private int damage;
    private int speed;
    private IRectangleAdapter boundingBox;
    private static final int WIDTH = 50;//Ändra width och height beroende på projektil?
    private static final int HEIGHT = 50;

    public Projectile(Point position, IRectangleFactoryAdapter rectangleFactory){
        this.position = position;
        //this.damage = damage;
        //this.speed = speed;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);

    }
    public IRectangleAdapter getBoundingBox() {return boundingBox;}

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {

    }


    @Override
    public void setPosition(Point point) {
        position = new Point(point);
        boundingBox.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {

    }

    @Override
    public Point getVelocity() {
        return null;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void update() {

    }
}
