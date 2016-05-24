package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.projectile.Projectile;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractRangedWeapon extends AbstractWeapon implements IRanged {

    private Point aimDirection;
    private final int damage;
    private final int speed;
    private final int cooldown;
    private final ProjectileFactory projectileFactory;

    protected AbstractRangedWeapon(IPhysicsModel user, ProjectileFactory projectileFactory, String type, int damage, int speed, int cooldown) {
        super(user, type);
        this.damage = damage;
        this.speed = speed;
        this.cooldown = cooldown;
        this.projectileFactory = projectileFactory;
        setAimDirection(user.getVelocity().normalize());
    }

    protected void setAimDirection(Point direction) {
        this.aimDirection = direction;
    }

    @Override
    public Point getAimDirection() {
        return aimDirection;
    }


    protected Point getFireVelocity(int speed) {
        return aimDirection.multiply(speed).add(getUser().getVelocity().multiply(0.5));
    }

    protected int getSpeed() {
        return speed;
    }

    protected int getDamage() {
        return damage;
    }

    protected ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    public int getCooldown() { return cooldown; }
}
