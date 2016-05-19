package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.*;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.item.view.*;

public class ItemFactory {
    @Inject private ProjectileFactory projectileFactory;
    @Inject private SwingFactory swingFactory;
    @Inject private IAssetManagerAdapter assetManager;

    public IWeapon make(String type, IPhysicsModel model, int width, int height, int damage, int cooldown, int speed, float swingDegrees) {
        switch (type) {
            case "pistol" : return new Pistol(model, projectileFactory, damage, speed, cooldown);
            case "opsword" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, cooldown);
            case "opaxe" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, cooldown, swingDegrees);
            case "lasersword" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, cooldown, swingDegrees);
        }
        throw new EntityNotFoundException("item", type);
    }

    public IWeaponView makeView(IWeapon model) {
        switch (model.getType()) {
            case "pistol" : return new PistolView(model, assetManager);
            case "opsword" : return new OpSwordView(model, assetManager);
            case "opaxe" : return new OpAxeView(model);
            case "lasersword" : return new LaserSwordView(model);
        }
        throw new RuntimeException("You done fucked up..  Weapon model didn't have a corresponding view");
    }
}
