package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.*;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;

public class ItemFactory {
    @Inject private ProjectileFactory projectileFactory;
    @Inject private SwingFactory swingFactory;

    public IWeapon make(String type, IPhysicsModel model, int width, int height, int damage, int frames, int speed) {
        switch (type) {
            case "pistol" : return new Pistol(model, projectileFactory, damage, speed);
            case "opsword" : return new MeleeWeapon(model, "opsword", swingFactory, width, height, damage, frames);
            case "opaxe" : return new MeleeWeapon(model, "opaxe", swingFactory, width, height, damage, frames);
        }
        throw new EntityNotFoundException("item", type);
    }
}
