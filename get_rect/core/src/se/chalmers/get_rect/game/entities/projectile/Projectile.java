package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.scenes.IEntityHolder;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

import java.util.Random;

public class Projectile implements IPhysicsModel {
    private ProjectileFactory projectileFactory;
    private Point position;
    private Point velocity;
    private IRectangleAdapter boundingBox;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private boolean shouldBeRemoved = false;
    private boolean cluster = false;
    private IEntityHolder scene;

    public Projectile(Point position, Point velocity, IRectangleFactoryAdapter rectangleFactory, ProjectileFactory projectileFactory){
        this(position, velocity, rectangleFactory);
        this.projectileFactory = projectileFactory;
        cluster = true;
    }

    public Projectile(Point position, Point velocity, IRectangleFactoryAdapter rectangleFactory){
        this.position = position;
        this.velocity = velocity;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

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
        this.velocity = velocity;
    }

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }

    @Override
    public void update() {
        if (velocity.getX() == 0 && velocity.getY() == 0) {
            shouldBeRemoved = true;

            if (cluster) {
                launchCluster();
            }
        }
    }

    private void launchCluster() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int velX = rand.nextInt(100) - 50;
            int velY = rand.nextInt(300) - 20;
            Point vel = new Point(velX, velY);

            if (Math.abs(velX) < 50) {
                velX *= 10;
            }

            if (Math.abs(velY) < 50) {
                velY *= 10;
            }

            IPhysicsEntity projectile = projectileFactory.make("normal", position.addY(100), vel);
            scene.addPhysicsEntity(IScene.layer.FOREGROUND_EFFECTS, projectile);
        }
    }

    @Override
    public void setScene(IEntityHolder scene) {
        this.scene = scene;
    }
}
