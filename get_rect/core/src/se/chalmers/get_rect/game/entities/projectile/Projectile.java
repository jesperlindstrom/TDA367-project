package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.zombie.Zombie;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

import java.util.Random;

public class Projectile extends AbstractPhysicsModel {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private ProjectileFactory projectileFactory;
    private boolean cluster = false;
    private int dmg = 10;

    public Projectile(Point position, Point velocity, IRectangleFactoryAdapter rectangleFactory, ProjectileFactory projectileFactory){
        this(position, velocity, rectangleFactory);
        this.projectileFactory = projectileFactory;
        cluster = true;
    }

    public Projectile(Point position, Point velocity, IRectangleFactoryAdapter rectangleFactory){
        super(position, velocity, false, rectangleFactory);
        setBoundingBox(getPosition(), WIDTH, HEIGHT);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (isSolid && collisionSide.bottom()) {
            int friction = getVelocity().getX() > 0 ? -1 : 1;
            setVelocity(getVelocity().addX(friction));
        }

        if (otherObject.getClass().equals(Zombie.class) && cluster) {
            setShouldBeRemoved();
            launchCluster();
            otherObject.setPosition(otherObject.getPosition().addY(10));
            otherObject.setVelocity(getVelocity().setY(10));
            otherObject.takeDamage(dmg);
        }
    }

    @Override
    public void update() {
        if (getVelocity().getX() == 0 && getVelocity().getY() == 0) {
            setShouldBeRemoved();

            if (cluster) {
                launchCluster();
            }
        }
    }

    private void launchCluster() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int velX = rand.nextInt(100) - 50;
            int velY = rand.nextInt(300) - 20;
            Point vel = new Point(velX, velY);

            IPhysicsEntity projectile = projectileFactory.make("normal", getPosition().addY(100), vel);
            getScene().addPhysicsEntity(IScene.layer.FOREGROUND_EFFECTS, projectile);
        }
    }
}
