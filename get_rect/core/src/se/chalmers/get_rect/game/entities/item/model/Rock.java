package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public class Rock extends AbstractRangedWeapon {

    public Rock(IPhysicsModel user, ProjectileFactory projectileFactory, int damage, int speed, int cooldown) {
        super(user, projectileFactory, "rock", damage, speed, cooldown);
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (getCooldownFrames() == 0) {
            setCooldownFrames(getCooldown());
            setAimDirection(aimDirection);

            entityHolder.add(getProjectileFactory().make("rock", getHandPos(), getFireVelocity(getSpeed()), getDamage(), getUser()));
        }
    }
}
