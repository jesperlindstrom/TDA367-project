package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public class FireMagic extends AbstractRangedWeapon {

    public FireMagic(IPhysicsModel user, ProjectileFactory projectileFactory, int damage, int speed, int cooldown) {
        super(user, projectileFactory, "firemagic", damage, speed, cooldown);
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (getCooldownFrames() == 0) {
            setCooldownFrames(getCooldown());
            setAimDirection(aimDirection);

            entityHolder.add(getProjectileFactory().make("fireMagic", getSpawnPoint(), getFireVelocity(getSpeed()), getDamage(), getUser()));
        }
    }

    private Point getSpawnPoint() {
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
