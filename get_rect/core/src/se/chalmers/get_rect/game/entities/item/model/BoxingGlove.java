package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class BoxingGlove extends MeleeWeapon {
    private final int reach;
    private final int damage;
    private final int cooldown;

    public BoxingGlove(IPhysicsModel user, String type, SwingFactory swingFactory, int reach, int damage, int cooldown, float swingDegrees, int startTilt, boolean solid) {
        super(user, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, solid);
        this.reach = reach;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        System.out.println(getUsable());
        if (getUsable()) {
            setUsable(false);
            setCooldownFrames(cooldown);
            entityHolder.add(getSwingFactory().makeUppercut(damage, reach, cooldown, getUser(), this));
        }
    }
}
