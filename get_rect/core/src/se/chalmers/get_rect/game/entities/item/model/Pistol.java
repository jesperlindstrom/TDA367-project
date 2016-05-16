package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public class Pistol extends AbstractRangedWeapon implements IRanged {

    private final int speed;
    private final int damage;
    private ProjectileFactory projectileFactory;


    public Pistol(IPhysicsModel user, ProjectileFactory projectileFactory, int speed, int damage) {
        super(user);
        this.projectileFactory = projectileFactory;
        this.speed = speed;
        this.damage = damage;
    }
    public Pistol(IPhysicsModel user, ProjectileFactory projectileFactory) {
        this(user, projectileFactory, 200, 10);
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        setAimDirection(aimDirection);
        setUseFrames(5);
        entityHolder.add(projectileFactory.make(getSpawnPoint(), getFireVelocity(speed), damage, getUser()));
    }

    public Point getSpawnPoint() {
        Point offset = getHandPos();

        if (getAimDirection().getX() != 0) {
            offset = offset.addY(20);
        }

        if (getAimDirection().getY() != 0) {
            offset = offset.addX(getAimDirection().getY() < 0 ? 25 : -25 * getFacing());
        }

        return new Point(offset.add(getAimDirection().multiply(50)));
    }

}
