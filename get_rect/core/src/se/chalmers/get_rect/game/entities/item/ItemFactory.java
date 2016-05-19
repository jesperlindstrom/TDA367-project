package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.libGDX.LibGDXAudioManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.*;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.item.view.*;

public class ItemFactory {
    @Inject private ProjectileFactory projectileFactory;
    @Inject private SwingFactory swingFactory;
    @Inject private IAssetManagerAdapter assetManager;
    @Inject private LibGDXAudioManager audioManager;

    public IWeapon make(String type, IPhysicsModel model, int width, int height, int damage, int frames, int speed, float swingDegrees) {
        switch (type) {
            case "pistol" : return new Pistol(model, projectileFactory, damage, speed);
            case "opsword" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, frames);
            case "opaxe" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, frames, swingDegrees);
            case "lasersword" : return new MeleeWeapon(model, type, swingFactory, width, height, damage, frames);
        }
        throw new EntityNotFoundException("item", type);
    }

    public IWeaponView makeView(IWeapon model) {
        switch (model.getType()) {
            case "pistol" : return new PistolView(model, audioManager);
            case "opsword" : return new OpSwordView(model, audioManager);
            case "opaxe" : return new OpAxeView(model);
            case "lasersword" : return new LaserSwordView(model, audioManager);
        }
        throw new RuntimeException("You done fucked up..  Weapon model didn't have a corresponding view");
    }
}
