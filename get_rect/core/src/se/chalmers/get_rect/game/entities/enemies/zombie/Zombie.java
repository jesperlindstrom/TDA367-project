package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.scenes.IEntityHolder;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class Zombie implements IPhysicsModel
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private int speed;
    private IModel player;
    private Point position;
    private Point velocity;
    private IRectangleAdapter boundingBox;

    public Zombie(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this.position = point;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.player = player;
        this.velocity = new Point(0, 0);
        Random rand = new Random();
        speed = rand.nextInt(20) + 5;
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }


    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        // Jump, to simulate a lethal broccoli ninja attack.
        if (otherObject.equals(player) && velocity.getY() == 0) {
            velocity = velocity.setY(50);
        }
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
        // Amazing AI
        int playerX = player.getPosition().getX();
        int zombieX = position.getX();

        if (playerX > zombieX) {
            velocity = velocity.setX(speed);
        } else if (playerX < zombieX) {
            velocity = velocity.setX(-speed);
        }
    }

    @Override
    public void setScene(IEntityHolder scene) {

    }

    public Point getVelocity() {
        return new Point(velocity);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}
