package se.chalmers.get_rect.game.entities.item.projectile;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

import java.util.Random;

public class Projectile extends AbstractPhysicsModel {
    private boolean shouldFireCluster = false;
    private int width = 10;
    private int height = 10;
    private int dmg;
    private IModel owner;
    private ProjectileFactory factory;
    private String clusterProjectile;

    public Projectile(Point position, Point velocity, int damage, IRectangleFactoryAdapter rectangleFactory, IModel owner, boolean affectedByGravity, ProjectileFactory factory, String clusterProjectile) {
        super(position, velocity, false, affectedByGravity, rectangleFactory);
        setBoundingBox(width, height);
        this.owner = owner;
        this.dmg = damage;
        this.shouldFireCluster = factory != null;
        this.factory = factory;
        this.clusterProjectile = clusterProjectile;
    }

    public Projectile(Point position, Point velocity, int damage, IRectangleFactoryAdapter rectangleFactory, IModel owner, boolean affectedByGravity) {
        this(position, velocity, damage, rectangleFactory, owner, affectedByGravity, null, null);
        this.shouldFireCluster = false;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (isSolid && collisionSide.bottom()) {
            int friction = getVelocity().getX() > 0 ? -5 : 5;
            setVelocity(getVelocity().addX(friction).setY(0));
            System.out.println(getVelocity().addX(friction).setY(0));
        }

        if (otherObject instanceof ICombatModel && !otherObject.equals(owner)) {
            setShouldBeRemoved();
            otherObject.setPosition(otherObject.getPosition().addY(30));
            otherObject.setVelocity(getVelocity().multiply(0.1));
            ((ICombatModel) otherObject).takeDamage(dmg);

            if (shouldFireCluster) {
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

            IEntity projectile = factory.make(clusterProjectile, getPosition().addY(100), vel, dmg, owner);
            getScene().add(projectile);
        }
    }

    @Override
    public void update(double delta) {
        if (Math.abs(getVelocity().getX()) < 5 && Math.abs(getVelocity().getY()) < 5) {
            setShouldBeRemoved();

            if (shouldFireCluster) {
                launchCluster();
            }
        }
    }
}